import java.util.List;
import java.util.Random;


public class Tester {

	public static Random rand = new Random();
	
	public static void main(String[] args) {
		
		
		SeamCarver carver = new SeamCarver(new Picture("overlayimagewithhiddenmessage.png"));
		System.out.println("Width: " + carver.width());
		System.out.println("Height: " + carver.height());
		
		System.out.println();
		for(int i = 0; i < 100; i++){
			carver.removeHorizontalSeam();
			//carver.removeVerticalSeam();
		}
//		
//		int[] horizontalSeam = carver.findHorizontalSeam();
//		if(horizontalSeam.length == carver.width()){
//			System.out.println("Correct Length.");
//		}
//		printSeam(horizontalSeam);
//		
//		System.out.println();
//		
//		int[] verticalSeam = carver.findVerticalSeam();
//		if(verticalSeam.length == carver.height()){
//			System.out.println("Correct Length.");
//		}
//		printSeam(verticalSeam);
//		
//		System.out.println();
//		
//		System.out.println("Trimming");
//		
		
		System.out.println("Topo Sort Test");
		Graph g = new Graph(10);
		System.out.println("Generating a random " + g.vCount() + " node graph...");
		
		for(int i = 0; i < g.vCount(); i++)
		{
			int connection1 = rand.nextInt(g.vCount());			
			if(connection1 != i)
				g.addOneWayEdge(i, connection1, 1);
		}
		
		System.out.println("Graph Built.\n");
		
		//System.out.println("Adjacency Matrix:");
		//g.printAdjacencyMatrix();
		System.out.println();
		System.out.println("Adjacency List:");
		g.printAdjacencyList();
		System.out.println("Dependency List");
		g.printDependencyList();
		
		TopologicalSorter topo = new TopologicalSorter();
		List<Integer> list = topo.sort(g);
		System.out.println("\nTOPO:");
		for (int i : list) {
			System.out.print(i + ", ");
		}
		
		
		
	}
	public static void printSeam(int[] seam){
		int lastNumber = -1;
		for(int i = 0; i < seam.length; i++){
			int currentNumber = seam[i];
			System.out.print(lastNumber != -1 ? lastNumber == currentNumber ? "" : currentNumber > lastNumber ? "^":"v": "");
			System.out.print(currentNumber+", ");
			lastNumber = currentNumber;
		}
		System.out.print("\n");
	}

}
