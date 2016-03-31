import java.util.ArrayList;
import java.util.Scanner;

public class Map extends Graph{

	ArrayList<Edge> lockedTracks = new ArrayList<Edge>();
	
	//constructor for number of vertices
	public Map(int V) {
		super(V);
		// TODO Auto-generated constructor stub
	}
	
	public Map(String map) {
		Scanner sc = new Scanner("mapFile.txt");
		
		int numStations = sc.nextInt();
		
		while (sc.hasNextLine()) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();
			
			this.addEdge(start, end, weight);
		}
	}
	
	void addEdge (int startStation, int endStation, int weight) {
		
	}
	
	void lock(Edge edge) {
		lockedTracks.add(edge);
	}
	
	void unlock(Edge edge) {
		lockedTracks.remove(edge);
	}
 }
