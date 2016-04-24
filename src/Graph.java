import java.lang.reflect.Array;
import java.util.ArrayList;

public class Graph {
	private int V;
	private int E;
	private ArrayList<Integer>[] adj;
	private ArrayList<Double>[][] w;

	@SuppressWarnings("unchecked")
	public Graph(int V){
		this.V=V;
		this.E=0;
		this.w = (ArrayList<Double>[][]) Array.newInstance(ArrayList.class, V, V);
		for (int i = 0; i < w.length; i++) {
			for (int j = 0; j < w[i].length; j++) {
				this.w[i][j] = new ArrayList<Double>();
			}
		}

		adj = (ArrayList<Integer>[]) Array.newInstance(ArrayList.class, V);
		for (int v=0;v<V;v++){
			adj[v]=new ArrayList<Integer>();
		}
	}

	/**
	 * Get the number of vertices in the graph
	 * @return this.V - the number of vertices
	 */
	public int V(){
		return V;
	}

	/**
	 * Get the number of edges in the graph.
	 * @return this.E - this number of edges
	 */
	public int E(){
		return E;
	}

	/**
	 * Weights are stored in a 2D array. The weight of the connection between points 1 and 2
	 * is found by going to array[1][2];
	 * 
	 * @return - the double representation of the weight of the connection from 1 to 2.
	 */
	public ArrayList<Double> getWeight(int from, int to){
		return this.w[from][to];
	}

	/**
	 * Sets the weight of the connection from point 1 to 2.
	 * 
	 * @param from - starting point
	 * @param to - ending point
	 * @param price - the weight of the connection
	 */
	public void setWeight(int from, int to, double price){
		this.w[from][to].add(price);
	}

	/**
	 * Add a weighted edge to the graph
	 * @param v1 - starting vertex
	 * @param v2 - ending vertex
	 * @param weight - weight of the connection
	 */
	public void addWeightedEdge(int v1, int v2, double weight){
		adj[v1].add(v2);
		w[v1][v2].add(weight);
		E++;
	}

	/**
	 * Get the points which are directly connected to the given point v
	 * @param v - vertex to get connections from
	 * @return - the ArrayList which represents the direct connections from the vertex v
	 */
	public ArrayList<Integer> adj(int v){
		return adj[v];
	}



	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		String NEWLINE = System.getProperty("line.separator");
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj[v]) {
				for (double weight: this.w[v][w]) {
					if (weight != 0.0) {
						s.append(w+"["+weight+"] ");
					} else {
						s.append(w+" ");
					}
				}
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

	public void printOut(){
		System.out.println(this.toString());
	}
}