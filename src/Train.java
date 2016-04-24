import java.util.ArrayList;
import java.util.LinkedList;

public class Train implements Comparable<Train>{
	
	final double baseSpeed = 50;
	final double[] speeds = {1.0, 0.5, 0.25};
	
	double speed; //speed of train
	LinkedList<Track> route;
	LinkedList<Pair<Track, Lock>> grantedPath;
	int expectedDeparture;
	
	public Train (int trainType, Departure route) {
		this.speed = baseSpeed * speeds[trainType];
		this.route = route.route;
		this.expectedDeparture = route.expectedDeparture;
	}

	@Override
	public int compareTo(Train o) {
		if (this.expectedDeparture < o.expectedDeparture) {
			return -1;
		} else if (this.expectedDeparture > o.expectedDeparture) {
			return 1;
		}
		return 0;
	}
	
	public boolean equals(Train o) {
		return (this.speed == o.speed) && (this.route.equals(o.route));
	}

	public int getDelay() {
		return grantedPath.getLast().lock.end - this.expectedTime();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Train speed: " + this.speed + '\n');
		sb.append(route.toString() + '\n');
		
		return sb.toString();
	}
	
	public int start() {
		return this.route.getFirst().point1;
	}
	
	public int end() {
		return this.route.getLast().point2;
	}

	public ArrayList<Integer> route() {
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		if (this.route.size() == 1) {
			path.add(this.route.getFirst().point1);
			path.add(this.route.getFirst().point2);
			
			return path;
		}
		for (Track track : this.route) {
			path.add(track.point1);
		}
		
		return path;
	}
	
	public int expectedTime() {
		double expectedArrival = this.expectedDeparture;
		
		for (Track track : this.route) {
			expectedArrival += (int) Math.ceil(track.weight / this.speed);
		}
		
		return (int) Math.ceil(expectedArrival);
		
	}
	
	public void setGrantedPath (LinkedList<Pair<Track, Lock>> path) {
		this.grantedPath = path;
	}
	
	public int getArrivalTime() {
		return this.grantedPath.getLast().lock.end;
	}
}
