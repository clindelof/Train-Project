import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class TrainScheduler {
	public Map trainMap;
	public int numberOfStations;
	
	public LinkedList<Train> schedule;
	
	private HashMap<Integer, Dijkstra> paths;
	
	//0 = base case, 1 = optimized
	private final int mode = 0;
	
	private ArrayList<Edge>[] lockedTimes;
	
	@SuppressWarnings("unchecked")
	public TrainScheduler (int numberOfStations, File inputSchedule) throws FileNotFoundException {
		this.numberOfStations = numberOfStations;
		this.trainMap = new Map(this.numberOfStations);
		
		this.lockedTimes = (ArrayList<Edge>[]) Array.newInstance(ArrayList.class, numberOfStations);
		
		this.paths = new HashMap<Integer, Dijkstra>();
		
		this.schedule = makeSchedule(inputSchedule);
	}
	
	private LinkedList<Train> makeSchedule(File file) throws FileNotFoundException {
		
		LinkedList<Train> schedule = new LinkedList<Train>();
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextInt()) {
			int startStation = sc.nextInt();
			int endStation = sc.nextInt();
			int expectedStartTime = sc.nextInt();
			int trainType = sc.nextInt();

			if (!paths.containsKey(startStation)) {
				paths.put(startStation, new Dijkstra(this.trainMap, startStation));
			}
			
			Departure leave = new Departure(paths.get(startStation).pathTo(endStation), expectedStartTime);
			
			schedule.add(new Train(trainType, leave));
			
			Collections.sort(schedule);
		}
		
		sc.close();
		
		return schedule;
	}
	
	public void calculateLocks(boolean mode) {
		if (!mode) {
			
		} else {
			
		}
	}
}
