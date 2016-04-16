import java.util.ArrayList;
import java.util.LinkedList;

public class Dijkstra {

	private double[] distTo;
	private int[] pathTo;
	private Map map;
	
	public Dijkstra(Map map, int source) {
		distTo = new double[map.V()];
		pathTo = new int[map.V()];
		this.map = map;
		
		ArrayList<Integer> unvisited = new ArrayList<Integer>();
		
		//init all dist to infinity and previous to -1 (force exception when traversing backwards)
		for (int i = 0; i < distTo.length; i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
			pathTo[i] = -1; //all stations are number 1 - n
			unvisited.add(i);
		}
		distTo[source] = 0.0;
		
		while(!unvisited.isEmpty()) {
			int evalStation = getShortestDist(unvisited, distTo);
			
			unvisited.remove(new Integer(evalStation));
			
			this.relax(evalStation, unvisited);
		}
		
		
	}
	
	private int getShortestDist(ArrayList<Integer> unvisited, double[] dist) {
		int shortest = 0;
		
		for (int i = 0; i < dist.length; i++) {
			if (dist[shortest] > dist[i] && unvisited.contains(i)) {
				shortest = i;
			}
		}
		
		return shortest;
	}
	
	private void relax(int eval, ArrayList<Integer> unsettled) {
		for (Integer destination: map.adj(eval)) {
			if (unsettled.contains(destination)) {
				double dist = map.getWeight(eval, destination);
				double newDist = this.distTo[eval] + dist;
				
				if (distTo[destination] > newDist) {
					distTo[destination] = newDist;
					unsettled.add(destination);
					this.pathTo[destination] = eval; 
				}
			}
		}
	}

	public LinkedList<Edge> pathTo(int endStation) {
		LinkedList<Edge> path = new LinkedList<Edge>();
		int current = endStation;
		
		while (this.pathTo[current] != -1) {
			path.addFirst(new Edge(this.pathTo[current], current, map.getWeight(this.pathTo[current], current)));
			
			current = this.pathTo[current];
		}
		
		return path;
	}
}
