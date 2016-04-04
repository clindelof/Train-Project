import java.util.LinkedList;

public class Departure implements Comparable<Departure>{
	LinkedList<Edge> route;
	int expectedDeparture;
	
	public Departure (LinkedList<Edge> route_, int expectDeparture_) {
		this.route = route_;
		this.expectedDeparture = expectDeparture_;
	}

	@Override
	public int compareTo(Departure o) {
		if (this.expectedDeparture > o.expectedDeparture) {
			return 1;
		} else if (this.expectedDeparture < o.expectedDeparture) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
