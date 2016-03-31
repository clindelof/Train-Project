import java.util.Comparator;

public class Edge implements Comparator<Edge>{

	int point1;
	int point2;
	int weight;
	
	void Pair(int p1, int p2, int weight) {
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
	
	boolean equals(Edge second) {
		return ((this.point1 == second.point1 && this.point2 == second.point2) || (this.point2 == second.point1 && this.point1 == second.point2)) && this.weight == second.weight;
	}
}
