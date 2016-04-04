
public class Driver {
	
	public static final int NUMBEROFTRAINS = 13;
	public static String inputSchedule;

	public static void main(String[] args) {
		
		inputSchedule = args[0];
		TrainScheduler schedule = new TrainScheduler(NUMBEROFTRAINS, inputSchedule);
		
		//System.out.println(schedule.trainMap.toString());
	}
}
