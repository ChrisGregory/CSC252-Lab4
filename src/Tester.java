
public class Tester {

	public static void main(String[] args) {
		SeamCarver carver = new SeamCarver(new Picture("overlayimagewithhiddenmessage.png"));
		System.out.println("Width: " + carver.width());
		System.out.println("Height: " + carver.height());	
		System.out.println(carver.findHorizontalSeam());
		System.out.println(carver.findVerticalSeam());
		
	}

}
