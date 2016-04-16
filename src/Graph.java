import java.util.ArrayList;
import java.util.Collections;

public class Graph {
    private int V;
    private int E;
    private ArrayList<Integer>[] adj;
    private double[][] w;

    @SuppressWarnings("unchecked")
    public Graph(int V){
        this.V=V;
        this.E=0;
        this.w = new double[V][V];
        for (int i = 0; i < w.length; i++) {
        	for (int j = 0; j < w[i].length; j++) {
        		this.w[i][j] = Double.POSITIVE_INFINITY;
        	}
        }
        
        adj=new ArrayList[V];
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
    public double getWeight(int from, int to){
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
        this.w[from][to] = price;
    }

    /**
     * Add a weighted edge to the graph
     * @param v1 - starting vertex
     * @param v2 - ending vertex
     * @param weight - weight of the connection
     */
    public void addWeightedEdge(int v1, int v2, double weight){
        adj[v1].add(v2);
        w[v1][v2]= weight;
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
                if (this.w[v][w]!=0) {
                    s.append(w+"["+this.w[v][w]+"] ");
                }else{
                    s.append(w+" ");
                }
                
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public void printOut(){
        System.out.println(this.toString());
    }



    /**Override equals methods for graphs: compare number of edges, vertices and the
     * adjacency lists correspondence. Nothing to implement here ( will be used for grading)
     * 
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Graph)) {
            return false;
        }

        Graph that = (Graph) other;

        boolean isAdjSame=true;

        // iterate over adjacency list to check if they are the same
        try{
            for (int i = 0; i < Math.max(this.adj.length, that.adj.length); i++) {
                // sort so that order doesn't matter
                Collections.sort(this.adj[i]);
                Collections.sort(that.adj[i]);
                for (int j = 0; j < Math.max(this.adj[i].size(), that.adj[i].size()); j++) {
                    if (this.adj[i].get(j)!=that.adj[i].get(j)){
                        isAdjSame=false;
                        // once at least one is found there is no need to continue
                        break;
                    }
                }
                if (!isAdjSame) break;
            }

        }catch(ArrayIndexOutOfBoundsException e){
            isAdjSame=false;
        }

        // if graphs are the same all should match
        return this.V==(that.V)&& this.E==(that.E)&&isAdjSame;
    }
}