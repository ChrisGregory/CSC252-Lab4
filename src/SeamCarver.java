import java.awt.Color;

public class SeamCarver {

	private Picture pic;

	public SeamCarver(Picture pic) {
		this.pic = pic;
	}

	public Picture getPicture() {
		return pic;
	}

	public int width() {
		return pic.width();
	}

	public int height() {
		return pic.height();
	}

	public double energy(int x, int y) {
		if (x >= 0 && y >= 0 && x < width() && y < height()) {
			double horizontalRedShift;
			double horizontalGreenShift;
			double horizontalBlueShift;
			double verticalRedShift;
			double verticalGreenShift;
			double verticalBlueShift;
			Color top;
			Color bottom;
			Color left;
			Color right;

			if (x == 0) {
				left = pic.get(width() - 1, y);
				right = pic.get(x + 1, y);
			} else if (x == width() - 1) {
				left = pic.get(x - 1, y);
				right = pic.get(0, y);
			} else {
				left = pic.get(x - 1, y);
				right = pic.get(x + 1, y);
			}
			if (y == 0) {
				top = pic.get(x, height() - 1);
				bottom = pic.get(x, y + 1);
			} else if (y == height() - 1) {
				top = pic.get(x, y - 1);
				bottom = pic.get(x, 0);
			} else {
				top = pic.get(x, y - 1);
				bottom = pic.get(x, y + 1);
			}
			horizontalRedShift = left.getRed() - right.getRed();
			horizontalGreenShift = left.getGreen() - right.getGreen();
			horizontalBlueShift = left.getBlue() - right.getBlue();
			verticalRedShift = top.getRed() - bottom.getRed();
			verticalGreenShift = top.getGreen() - bottom.getGreen();
			verticalBlueShift = top.getBlue() - bottom.getBlue();

			double horizontalEnergy = Math.pow(horizontalRedShift, 2)
					+ Math.pow(horizontalGreenShift, 2)
					+ Math.pow(horizontalBlueShift, 2);
			double verticalEnergy = Math.pow(verticalRedShift, 2)
					+ Math.pow(verticalGreenShift, 2)
					+ Math.pow(verticalBlueShift, 2);

			return horizontalEnergy + verticalEnergy;
		} else {
			System.out.println("Index Out Of Bounds: (" + x + ", " + y + ")");
			throw new IndexOutOfBoundsException();
		}
	}

	public int[] findHorizontalSeam() {
		int leastEnergyIndex = 0;
		double leastEnergy = Double.MAX_VALUE;
		for (int i = 0; i < width(); i++) {
			if (energy(i, 0) < leastEnergy) {
				leastEnergy = energy(i, 0);
				leastEnergyIndex = i;
			}
		}
		System.out.println("Least Horizontal Energy: " + leastEnergyIndex + " - " + leastEnergy);
		int[] result = new int[width()];
		result[0] = leastEnergyIndex;
		System.out.println("Finding best seam... Please Wait...");
		result = horizontalSeamHelper(0, leastEnergyIndex, result).getIndicies();
		System.out.println("Seam found.");
		return result;
	}

	public Seam horizontalSeamHelper(int x, int y, int[] current) {
		if (x < width() - 1) {
			int[] result = current;
			result[x] = y;
			Seam top = new Seam(Double.MAX_VALUE);
			Seam bot = new Seam(Double.MAX_VALUE);
			if (y > 0) {
				top = horizontalSeamHelper(x + 1, y - 1, current);
				top.addWeight(energy(x, y));
			}

			Seam mid = horizontalSeamHelper(x + 1, y, current);
			mid.addWeight(energy(x, y));
			
			if (y < height() - 1) {
				bot = horizontalSeamHelper(x + 1, y + 1, current);
				bot.addWeight(energy(x, y));
			}

			Seam cheapest;
			if (top.getWeight() < bot.getWeight()) {
				if (top.getWeight() < mid.getWeight()) {
					cheapest = top;
				} else {
					cheapest = mid;
				}
			} else {
				if (bot.getWeight() < mid.getWeight()) {
					cheapest = bot;
				} else {
					cheapest = mid;
				}
			}
			return cheapest;
		} else {
			return new Seam(current, energy(x, y));
		}
	}

	public int[] findVerticalSeam() {
		int leastEnergyIndex = 0;
		double leastEnergy = Double.MAX_VALUE;
		for (int i = 0; i < height(); i++) {
			if (energy(0, i) < leastEnergy) {
				leastEnergy = energy(0, i);
				leastEnergyIndex = i;
			}
		}
		System.out.println("Least Vertical Energy: " + leastEnergyIndex + " - " + leastEnergy);
		
		int[] result = new int[height()];
		result[0] = leastEnergyIndex;
		System.out.println("Finding best seam... Please Wait...");
		result = horizontalSeamHelper(leastEnergyIndex, 0, result).getIndicies();
		System.out.println("Seam found.");
		return result;
	}

	public Seam verticalSeamHelper(int x, int y, int[] current) {
		if (y < height() - 1) {
			int[] result = current;
			result[y] = x;
			Seam left = new Seam(Double.MAX_VALUE);
			Seam right = new Seam(Double.MAX_VALUE);
			if(x > 0){
			left = horizontalSeamHelper(x - 1, y + 1, current);
			left.addWeight(energy(x, y));}
			Seam mid = horizontalSeamHelper(x, y + 1, current);
			mid.addWeight(energy(x, y));
			if(x < width() - 1){
			right = horizontalSeamHelper(x + 1, y + 1, current);
			right.addWeight(energy(x, y));}
			Seam cheapest;
			if (left.getWeight() < right.getWeight()) {
				if (left.getWeight() < mid.getWeight()) {
					cheapest = left;
				} else {
					cheapest = mid;
				}
			} else {
				if (right.getWeight() < mid.getWeight()) {
					cheapest = right;
				} else {
					cheapest = mid;
				}
			}
			return cheapest;
		} else {
			return new Seam(current, energy(x, y));
		}
	}

	public void removeHorizontalSeam(int[] indicies) {
		if (indicies.length == width()) {
			for (int i = 0; i < indicies.length; i++) {

			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void removeVerticalSeam(int[] indicies) {
		if (indicies.length == height()) {
			for (int i = 0; i < indicies.length; i++) {

			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void removeHorizontalSeam() {
		removeHorizontalSeam(findHorizontalSeam());
	}

	public void removeVerticalSeam() {
		removeVerticalSeam(findVerticalSeam());
	}
}
