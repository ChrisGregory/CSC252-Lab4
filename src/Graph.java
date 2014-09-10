public class Graph {
	private int vertexCount = 0;
	private int edgeCount = 0;

	public int[][] adjacencyMatrix;
	public int[] marks;

	public Graph(int vertexCount) {
		this.vertexCount = vertexCount;
		adjacencyMatrix = new int[vertexCount][vertexCount];
		marks = new int[vertexCount];
	}

	public Graph(Graph graph) {
		this.vertexCount = graph.vertexCount;
		this.edgeCount = graph.edgeCount;
		this.adjacencyMatrix = graph.adjacencyMatrix;
		this.marks = graph.marks;
	}

	public int vCount() {
		return vertexCount;
	}

	public int eCount() {
		return edgeCount;
	}

	public void printAdjacencyMatrix() {
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix[i].length; j++) {
				System.out.print(adjacencyMatrix[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	public void printAdjacencyList() {
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			System.out.print(i +": ");
			for (int j = 0; j < adjacencyMatrix[i].length; j++) {
				if(adjacencyMatrix[i][j] != 0){
					System.out.print(j+", ");
				}
			}
			System.out.print("\n");
		}
		System.out.println();
	}

	public int first(int vertex) {
		// returns the first vertex (in natural order) connected to vertex v. If
		// there are none, then vcount() is returned.
		int index = -1;
		boolean searching = true;
		for (int i = 0; i < vertexCount && searching; i++) {
			if (adjacencyMatrix[vertex][i] != 0) {
				index = i;
				searching = false;
			}
		}
		return index != -1 ? index : vCount();
	}

	public int next(int vertexV, int vertexW) {
		// rreturns the vertex (in natural order) connected to vertex v after
		// vertex w. If there are no more edges after w, vcount() is returned
		int index = -1;
		boolean searching = true;
		for (int i = vertexW + 1; i < vertexCount && searching; i++) {
			if (adjacencyMatrix[vertexV][i] != 0) {
				index = i;
				searching = false;
			}
		}
		return index != -1 ? index : vCount();
	}

	public void addEdge(int vertex1, int vertex2, int weight) {
		if (adjacencyMatrix[vertex1][vertex2] == 0) {
			edgeCount++;
		}
		adjacencyMatrix[vertex1][vertex2] = weight;
		adjacencyMatrix[vertex2][vertex1] = weight;
	}

	public void addOneWayEdge(int vertex1, int vertex2, int weight) {
		if (adjacencyMatrix[vertex1][vertex2] == 0) {
			edgeCount++;
		}
		adjacencyMatrix[vertex1][vertex2] = weight;
	}

	public void removeEdge(int vertex1, int vertex2) {
		boolean removing = false;
		if (adjacencyMatrix[vertex1][vertex2] != 0) {
			adjacencyMatrix[vertex1][vertex2] = 0;
			removing = true;
		}
		if (adjacencyMatrix[vertex2][vertex1] != 0) {
			adjacencyMatrix[vertex2][vertex1] = 0;
			removing = true;
		}
		if(removing){
			edgeCount--;
		}
	}

	public boolean isEdge(int vertex1, int vertex2) {
		boolean result = false;
		if (vertex1 < vCount() && vertex2 < vCount()) {
			result = adjacencyMatrix[vertex1][vertex2] != 0;
		}
		return result;
	}

	// Returns how many edges connect to this vertex.
	// NOT how many edges this vertex connects to.
	public int degree(int vertex) {
		int degree = 0;
		for (int i = 0; i < vertexCount; i++) {
			if (adjacencyMatrix[i][vertex] != 0) {
				degree++;
			}
		}
		return degree;
	}

	public int getMark(int vertex) {
		return marks[vertex];
	}

	public void setMark(int vertex, int mark) {
		marks[vertex] = mark;
	}
}