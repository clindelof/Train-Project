import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Map extends Graph{

	private final File mapFile = new File("mapFile.txt");
	private ArrayList<Edge> lockedTracks = new ArrayList<Edge>();

	//constructor for number of vertices
	public Map(int V) {
		super(V); //calls Graph.java constructor

		this.build();
	}

	private void build() {

		Scanner sc;
		try {
			sc = new Scanner(this.mapFile);

			while(sc.hasNextInt()) {
				int start = sc.nextInt();
				int end = sc.nextInt();
				int weight = sc.nextInt();

				this.addEdge(start, end, weight);
			}

			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Add the connection between two stations. Since tracks are able to be traversed in both directions,
	 * this connection must be added from start -> end and end -> start.
	 * 
	 * @param startStation - vertex to start from
	 * @param endStation - vertex to end at
	 * @param weight - weight of the connection between start and end.
	 */
	public void addEdge (int startStation, int endStation, int weight) {
		this.addWeightedEdge(startStation, endStation, weight);
		this.addWeightedEdge(endStation, startStation, weight);
	}

	/*
	 * Return if a track is locked
	 */
	public boolean isLocked(Edge edge) {
		return lockedTracks.contains(edge);
	}

	public void lock(Edge edge) {
		lockedTracks.add(edge);
	}

	public void unlock(Edge edge) {
		lockedTracks.remove(edge);
	}
}

