
public class Driver {
	
	public static final int NUMBEROFSTATIONS = 13;
	public static String inputSchedule;

	public static void main(String[] args) {
		
		inputSchedule = args[0];
		TrainScheduler schedule = new TrainScheduler(NUMBEROFSTATIONS, inputSchedule);
		
		//System.out.println(schedule.trainMap.toString());
	}
}
