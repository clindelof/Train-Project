import java.util.ArrayList;
import java.util.Scanner;

public class Map extends Graph{

	ArrayList<Edge> lockedTracks = new ArrayList<Edge>();
	
	//constructor for number of vertices
	public Map(int V) {
		super(V); //calls Graph.java constructor
	}
	
	
	/**
	 * Add the connection between two stations. Since tracks are able to be traversed in both directions,
	 * this connection must be added from start -> end and end -> start.
	 * 
	 * @param startStation - vertex to start from
	 * @param endStation - vertex to end at
	 * @param weight - weight of the connection between start and end.
	 */
	void addEdge (int startStation, int endStation, int weight) {
		this.addWeightedEdge(startStation, endStation, weight);
		this.addWeightedEdge(endStation, startStation, weight);
	}
	
	void lock(Edge edge) {
		lockedTracks.add(edge);
	}
	
	void unlock(Edge edge) {
		lockedTracks.remove(edge);
	}
 }
