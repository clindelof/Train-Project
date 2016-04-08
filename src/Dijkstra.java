import java.util.ArrayList;

public class Dijkstra {
	int[] dist;
	Edge[] pred;
	
	public Dijkstra(Map map, int source, int target) {
		dist = new int[map.V()];
		pred = new Edge[map.V()];
		
		ArrayList<Edge> visited = new ArrayList<Edge>();
		
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
			pred[i] = null;
		}
		
		dist[source] = 0;
		
		while (visited.size() != map.V()) {
			int v = this.smallest(dist);
			
			if (visited.contains(v)) continue;
			
			for (Integer u : map.adj(v)) {
				if (dist[u] > dist[v] + this.weight(map, v, u)) {
					dist[u] = dist[v] + this.weight(map, v, u);
					visited.add(new Edge(v, u, this.weight(map, v, u)));
				}
			}
		}
	}
	
	/**
	 * finds the index of the smallest integer in the array pasted
	 * @param array - array to search
	 * @return return the index in the array of the smallest number
	 */
	private int smallest(int[] array) {
		int smallest = 0;
		
		for (int num = 1; num <array.length; num++) {
			if (array[num] < array[smallest]) {
				smallest = num;
			}
		}
		
		return smallest;
	}
	private int weight (Map map, int start, int end) {
		return (int) map.getWeight(start, end); //all distances are integers so cast is safe
	}
}
