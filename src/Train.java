import java.util.Comparator;

public class Train implements Comparator<Train>{
	
	final double[] speeds = {1.0, 0.5, 0.25};
	
	int type; //what kind freight, passenger, dangerous cargo...
	double speed; //speed of train
	
	public Train () {
		this.type = 0; 
		this.speed = speeds[this.type];
	}
	
	public Train (int trainType) {
		this.type = trainType;
		this.speed = speeds[trainType];
	}
	
	/*
	 * Assumes no train will have the same speed and be different type
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Train o1, Train o2) {
		// TODO Auto-generated method stub
		if (speeds[o1.type]>speeds[o2.type]) {
			return 1;
		}
		else if (speeds[o1.type]<speeds[o2.type]) {
			return -1;
		}
		else {
		    return 0;
		}
	}

	public boolean equals (Train t1) {
		if (this == t1) {
			return true;
		}
		else return false;
	}
}
