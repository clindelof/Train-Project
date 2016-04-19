import java.util.Comparator;

public class Edge implements Comparator<Edge>{

	int point1;
	int point2;
	double weight;
	int startLock;
	int endLock;
	
	public Edge(int p1, int p2, double weight) {
		this.point1 = p1;
		this.point2 = p2;
		this.weight = weight;
	}
	
	@Override
	public int compare (Edge first, Edge second) {
		if (((first.point1 == second.point1 && first.point2 == second.point2) || (first.point2 == second.point1 && first.point1 == second.point2)) && first.weight == second.weight) {
			return 0;
		} else if (((first.point1 == second.point1 && first.point2 == second.point2) || (first.point2 == second.point1 && first.point1 == second.point2)) && first.weight > second.weight) {
			return 1;
		} else if (((first.point1 == second.point1 && first.point2 == second.point2) || (first.point2 == second.point1 && first.point1 == second.point2)) && first.weight < second.weight) {
			return -1;
		}
		
		throw new RuntimeException("Uncomparable Edges");
	}
	
	public boolean equals(Edge second) {
		return ((this.point1 == second.point1 && this.point2 == second.point2) || (this.point2 == second.point1 && this.point1 == second.point2)) && this.weight == second.weight;
	}
	
	public boolean equals(int vertex) {
		return (this.point1 == vertex) || (this.point2 == vertex);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append('{');
		sb.append('[');
		sb.append(this.point1 +", " +this.point2);
		sb.append(']');
		sb.append(", " + this.weight);
		sb.append('}');
		
		return sb.toString();
	}
	
	public void lock (int start, int end) {
		this.startLock = start;
		this.endLock = end;
	}
	
	public void unlock() {
		this.startLock = -1;
		this.endLock = -1;
		
	}
}
