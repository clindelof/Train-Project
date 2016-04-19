
public class Time {
	int minutes;
	public Time (int rep) {
		this.minutes = 12 * rep;
	}
	
	@Override
	public String toString() {
		return Integer.toString(minutes);
		
	}
}
