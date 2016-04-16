import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class TrainScheduler {
	public Map trainMap;
	public int numberOfStations;
	public ArrayList<Train> trains;
	
	public LinkedList<Departure> schedule;
	
	private HashMap<Integer, Dijkstra> paths;
	
	public TrainScheduler (int numberOfStations, String filePath) throws FileNotFoundException {
		this.numberOfStations = numberOfStations;
		trainMap = new Map(this.numberOfStations);
		
		paths = new HashMap<Integer, Dijkstra>();
		
		schedule = makeSchedule(filePath);
	}
	
	private LinkedList<Departure> makeSchedule(String filePath) throws FileNotFoundException {
		
		LinkedList<Departure> schedule = new LinkedList<Departure>();
		File file = new File(filePath);
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextInt()) {
			int startStation = sc.nextInt();
			int endStation = sc.nextInt();
			int expectedStartTime = sc.nextInt();

			if (!paths.containsKey(startStation)) {
				paths.put(startStation, new Dijkstra(this.trainMap, startStation));
			}
			
			Departure leave = new Departure(paths.get(startStation).pathTo(endStation), expectedStartTime);
			
			schedule.add(leave);
			
			Collections.sort(schedule);
		}
		
		sc.close();
		
		return schedule;
	}
}
