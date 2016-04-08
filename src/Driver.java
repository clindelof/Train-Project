import java.io.FileNotFoundException;

public class Driver {
	
	public static final int NUMBEROFSTATIONS = 13;
	public static String inputSchedule;

	public static void main(String[] args) {
		
		inputSchedule = args[0];
		TrainScheduler schedule;
		try {
			schedule = new TrainScheduler(NUMBEROFSTATIONS, inputSchedule);
			
			System.out.println(schedule.schedule);
		} catch (FileNotFoundException e) {
			System.out.println("File error");
			e.printStackTrace();
		}
	}
}
