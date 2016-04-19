import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Driver {

	public static final int NUMBEROFSTATIONS = 13;
	public static int NUMBEROFTRAINS;
	public static String inputSchedule;

	public static void main(String[] args) {

		inputSchedule = args[0];
		TrainScheduler schedule;

		System.out.println("How many trains to be scheduled? ");

		Scanner sc = new Scanner(System.in);
		NUMBEROFTRAINS = sc.nextInt();
		sc.close();

		File inputSchedule = new File("./inputSchedule.txt");
		Random rnd = new Random();
		
		try {
			FileWriter fw = new FileWriter(inputSchedule);

			for (int i = 0; i < NUMBEROFTRAINS; i++) {
				int start = rnd.nextInt(NUMBEROFSTATIONS);
				int end;

				do {
					end = rnd.nextInt(NUMBEROFSTATIONS);
				} while (end == start);

				Time time = new Time(rnd.nextInt(120));

				int type = rnd.nextInt(3);

				fw.write(start + " " + end +" " + time + " " + type + '\n');
			}

			fw.close();
		} catch (IOException e) {
			System.out.println("ERROR with creating file");
		}

		try {
			schedule = new TrainScheduler(NUMBEROFSTATIONS, inputSchedule);

			inputSchedule.delete();
			
			System.out.println(schedule.schedule);
		} catch (FileNotFoundException e) {
			System.out.println("File error");
			e.printStackTrace();
		}
	}
}
