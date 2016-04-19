
public class Train implements Comparable<Train>{
	
	final double baseSpeed = 50;
	final double[] speeds = {1.0, 0.5, 0.25};
	
	int type; //what kind passenger, freight, dangerous cargo...
	double speed; //speed of train
	Departure route;
	
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Train type: " + this.type +'\n');
		sb.append("Train speed: " + this.speed + '\n');
		sb.append(route.toString() + '\n');
		
		return sb.toString();
	}
}
