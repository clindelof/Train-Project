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

	@Override
	public String toString() {


		StringBuilder sb = new StringBuilder();	

		sb.append("Expected Departure: " + this.expectedDeparture + "/n");

		sb.append('[');

		for (Edge vertix: route) {
			sb.append(vertix + ",");
		}

		sb.deleteCharAt(sb.length() - 1);

		sb.append(']');

		return sb.toString();
	}

}
