import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Driver {

	public static final int NUMBEROFSTATIONS = 13;
	public static int NUMBEROFTRAINS;
	public static String inputSchedule;
	public static int mode;
	public static boolean testing; 
	
	public static final File dir = new File("./results/");

	public static void main(String[] args) {

		if (args.length >= 2) {
			NUMBEROFTRAINS = Integer.parseInt(args[0]);
			mode = Integer.parseInt(args[1]);
			
			try {
				testing = Boolean.getBoolean(args[2]);
			} catch (IndexOutOfBoundsException e) {
				testing = false;
			}
		} else {
			throw new RuntimeException("Invalid arguments provided");
		}

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
		
			long start = System.currentTimeMillis();
			TrainScheduler schedule = new TrainScheduler(NUMBEROFSTATIONS, inputSchedule, mode);
			
			long end = System.currentTimeMillis();
			
			long elapsed = end - start;
			
			inputSchedule.delete();
			
			dir.mkdir();
			new File("./results/csv").mkdir();
			new File("./results/txt/").mkdir();
			
			String csvFilename = "./results/csv/TrainData-"+NUMBEROFTRAINS+"-"+mode+".csv";
			String txtFilename = "./results/txt/Train-"+NUMBEROFTRAINS+"-"+mode+".txt";
			File csvFile = new File(csvFilename);
			File txtFile = new File(txtFilename);
			
			FileWriter csv = new FileWriter(csvFile);
			FileWriter txt = new FileWriter(txtFile);
			
			csv.write(schedule.toCSV());
			csv.write("Elapsed time for run: " + elapsed);
			txt.write(schedule.toTXT());
			txt.write("Elapsed time for run: " + elapsed);
			
			csv.close();
			txt.close();
			
			if (!testing) {
				//call gui for results
			}
		} catch (IOException e) {
			System.out.println("File error");
			e.printStackTrace();
		}
	}
}
