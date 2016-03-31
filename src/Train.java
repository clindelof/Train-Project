import java.util.Comparator;

public class Train implements Comparator<Train>{
	
	final double[] speeds = {1.0, 0.5, 0.25};
	
	int type; //what kind freight, passenger...
	double speed; //speed of train
	
	public Train () {
		this.type = 0; 
		this.speed = speeds[this.type];
	}
	
	public Train (int trainType) {
		this.type = trainType;
		this.speed = speeds[trainType];
	}
	
	@Override
	public int compare(Train o1, Train o2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean equals () {
		
	}
}
