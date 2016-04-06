import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import javax.xml.soap.Node;

public class Map extends Graph{
	private static final int INFINITY = 0;
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
	
	public int[] shortestPath(int start, int finish) {
		
		LinkedList<Edge> path = new LinkedList<Edge>();
		
	int cost;
		// get the set of vertices
	
	    int n = path.length;

	    // dist[i] is the distance from start to finish
	    //YAYYYYYY FOR SEARCHING> i"M SO HAPPY RIGHT NOW
	    
	    int[] dist = new int[n];

	    // s[i] is true if there is a path from start to finish
	    
	    boolean[] s = new boolean[n];

	    // lets find that shortest distance 
	    
	    for(int i = 0; i < n; i++)
	    	
	        dist[i] = cost[start].get(i).getCost();

	    s[start] = true;

	    // determine n-1 paths from int start
	    
	    for ( int j = 2 ; j < n  ; j++ )
	    {
	    	
	        int u = -1;

	        for (int k = 0; k < n; k++)
	        	
	            if ( !s[k] && dist[k] < INFINITY)
	            	
	                // check if shit needs fixing
	            	
	                if ( u < 0 || dist[k] < dist[u])
	                    u = k;
	        if (u < 0)
	            break; 

	        // set s[u] to true and update the distances
	        s[u]=true;

	        for (int k = 0; k < n; k++)
	        	
	            if ( !s[k] && cost[u].get(k).getCost() < INFINITY )
	            	
	                if( dist[k] > dist[u] + cost[u].get(k).getCost())
	                	
	                    dist[k] = dist[u] + cost[u].get(k).getCost();

	        // at this point dist[k] is the smallest cost path from
	        // v to k of length j.
	    }       
	    return dist;
}
	}
 
