import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopologicalSorter {
	/**
	 * Create a class called TopologicalSort that takes a Graph structure and
	 * returns the list of vertices in topological order:
	 * 
	 * List<Integer> sort(Graph g);
	 * 
	 * You may use either traversal approach, though you must explain which you
	 * used, why, and what the other approach is.
	 */

	Queue<Integer> queue = new PriorityQueue<Integer>();

	List<Integer> sort(Graph graph) {
		Graph g = new Graph(graph);
		queue = new PriorityQueue<Integer>();
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < g.vCount(); i++) {
			if (g.degree(i) == 0) {
				queue.add(i);
				result.add(i);
			}
		}
		while (!queue.isEmpty()) {
			int currentNode = queue.poll();
			int previousNode = g.first(currentNode);
			while(previousNode != g.vCount()) {
				int nextNode = g.next(currentNode, previousNode);
				g.removeEdge(currentNode, previousNode);
				if (g.degree(previousNode) == 0) {
					queue.add(previousNode);
					result.add(previousNode);
				}
				previousNode = nextNode;
			}
		}
		return result;
	}
}
