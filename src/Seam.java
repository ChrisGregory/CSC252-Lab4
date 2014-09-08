
public class Seam {
	private int[] indicies;
	private int index = 0;
	private double totalWeight = 0;
	
	public Seam(int size){
		indicies = new int[size];
	}
	public Seam(int[] array, double weight){
		indicies = array;
		totalWeight = weight;
	}
	public Seam(double weight){
		indicies = new int[0];
		totalWeight = weight;
	}
	
	public void insert(int value){
		indicies[index] = value;
		index++;
	}
	
	public void insert(int value, int position){
		if(position < indicies.length && position >= 0){
			indicies[position] = value;
		}
	}
	
	public int[] getIndicies(){
		return indicies;
	}
	
	public double getWeight(){
		return totalWeight;
	}
	
	public void addWeight(double weight){
		totalWeight += weight;
	}
	
	
	
}
