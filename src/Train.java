import java.util.ArrayList;

public class Train implements Comparable<Train>{
	
	final double baseSpeed = 50;
	final double[] speeds = {1.0, 0.5, 0.25};
	
	int type; //what kind passenger, freight, dangerous cargo...
	double speed; //speed of train
	Departure route;
	int timeDelayed;
	
	public Train (int trainType, Departure route) {
		this.type = trainType;
		this.speed = baseSpeed * speeds[trainType];
		this.route = route;
	}

	@Override
	public int compareTo(Train o) {
		if (this.route.expectedDeparture < o.route.expectedDeparture) {
			return -1;
		} else if (this.route.expectedDeparture > o.route.expectedDeparture) {
			return 1;
		}
		return 0;
	}
	
	public boolean equals(Train o) {
		return (this.type == o.type) && (this.route.equals(o.route));
	}
	public void setDelay(int delay) {
		this.timeDelayed = delay;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Train type: " + this.type +'\n');
		sb.append("Train speed: " + this.speed + '\n');
		sb.append(route.toString() + '\n');
		
		return sb.toString();
	}
	
	public int start() {
		return this.route.route.getFirst().point1;
	}
	
	public int end() {
		return this.route.route.getLast().point2;
	}

	public ArrayList<Integer> route() {
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		if (this.route.route.size() == 1) {
			path.add(this.route.route.getFirst().point1);
			path.add(this.route.route.getFirst().point2);
			
			return path;
		}
		for (Edge track : this.route.route) {
			path.add(track.point1);
		}
		
		return path;
	}
}
