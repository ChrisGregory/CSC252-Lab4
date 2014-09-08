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
		if(x >= 0 && y >= 0 && x < width() && y < height()){
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
			
			if(x == 0){
				left = pic.get(width()-1, y);
				right = pic.get(x+1, y);
			}
			if(x == width()-1){
				left = pic.get(x-1, y);
				right = pic.get(0, y);
			} else {
				left = pic.get(x-1, y);
				right = pic.get(x+1, y);
			}
			if(y == 0){
				top = pic.get(x, height()-1);
				bottom = pic.get(x, y+1);
			}
			if(y == height()-1){
				top = pic.get(x, y-1);
				bottom = pic.get(x, 0);
			} else {
				top = pic.get(x, y-1);
				bottom = pic.get(x, y+1);
			}
			horizontalRedShift = left.getRed() - right.getRed();
			horizontalGreenShift = left.getGreen() - right.getGreen();
			horizontalBlueShift = left.getBlue() - right.getBlue();
			verticalRedShift = top.getRed() - bottom.getRed();
			verticalGreenShift = top.getGreen() - bottom.getGreen();
			verticalBlueShift = top.getBlue() - bottom.getBlue();
			
			double horizontalEnergy = Math.pow(horizontalRedShift, 2) + Math.pow(horizontalGreenShift, 2) + Math.pow(horizontalBlueShift, 2);
			double verticalEnergy = Math.pow(verticalRedShift,2) + Math.pow(verticalGreenShift, 2) + Math.pow(verticalBlueShift,2);
			
			return horizontalEnergy + verticalEnergy;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	public int[] findHorizontalSeam() {
		int[] result = new int[pic.width()];
		
		return result;
	}
	public int[] horizontalSeamHelper(int x, int y, int[] current){
		int[] result = current;
		return result;
	}

	public int[] findVerticalSeam() {
		int[] result = new int[pic.height()];
		
		return result;
	}
	public int[] verticalSeamHelper(int x, int y, int[] current){
		int[] result = current;
		return result;
	}

	public void removeHorizontalSeam() {
		removeHorizontalSeam(findHorizontalSeam());
	}

	public void removeVerticalSeam() {
		removeVerticalSeam(findVerticalSeam());
	}

	public void removeHorizontalSeam(int[] indicies) {
		if(indicies.length == width()){
			for(int i = 0; i < indicies.length; i++){
				
			}
		}else{
			throw new IllegalArgumentException();
		}
	}

	public void removeVerticalSeam(int[] indicies) {
		if(indicies.length == height()){
			for(int i = 0; i < indicies.length; i++){
				
			}
		}else{
			throw new IllegalArgumentException();
		}
	}

}
