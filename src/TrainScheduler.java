import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class TrainScheduler {
	public Map trainMap;
	public int numberOfStations;
	
	public LinkedList<Train> schedule;
	
	private HashMap<Integer, Dijkstra> paths;

	// 0 -> base, 1 -> optimized
	private int mode;

	public TrainScheduler (int numberOfStations, File inputSchedule, int mode) throws FileNotFoundException {
		this.numberOfStations = numberOfStations;
		this.trainMap = new Map(this.numberOfStations);
		this.mode = mode;
		
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
	
	public void calculateLocks() {
		
		if (this.mode == 1) { //if running for optimized
			// for each train in the schedule
			for (Train train : schedule) {
				// start time is initially the expected departure time for the first track
				int start = train.route.expectedDeparture;
				for (Edge track : train.route.route) {
					track.startLock = start; 
					// endLock is the time that it will take the train to get to the end of that track
					track.endLock = track.startLock + (int)Math.ceil((train.speed * track.weight));
					
					start = track.endLock;
				}
			}
		} else if (this.mode == 0) { //if running base case
			// for each train in the schedule
			for (Train train : schedule) {
				int start = train.route.expectedDeparture;
				int end = start;
				
				// sum the times for each track
				for (Edge track : train.route.route) {
					end = end + (int) Math.ceil(train.speed * track.weight);
				}
				
				// assign the start and end time for all tracks in the route
				for (Edge track: train.route.route) {
					track.startLock = start;
					track.endLock = end;
				}
			}
		} else {
			throw new RuntimeException("Invalid test case type");
		}
		
		this.calculateDelays();
	}
	
	public void calculateDelays() {
		
		//start with second train, because first train will not experience delays
		for (int i = 0; i < schedule.size(); i++) {
			//compare only to those trains which are schedule to be on tracks before
			for (int j = 0; j < i; j++) {
				
			}
			
		}
	}
	
	public String toCSV() {
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}
	
	public String toTXT() {
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}
}
