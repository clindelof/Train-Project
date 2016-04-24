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
		
		this.calculateLocks();
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

					end += (int) Math.ceil(train.speed * track.weight);
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
		for (int i = 1; i < schedule.size(); i++) {
			Train toBeScheduled = schedule.get(i);
			//compare only to those trains which are schedule to be on tracks before
			for (int j = 0; j < i; j++) {
				Train causingDelay = schedule.get(j);
				
				// check any track is in a previous trains route
				for (Edge track : toBeScheduled.route.route) {
					//if it does, check the lock time
					if (causingDelay.route.route.contains(track)) {
						Edge delayTrack = causingDelay.route.route.get(causingDelay.route.route.indexOf(track));
						
						if (delayTrack.startLock >= track.endLock) { //no problem exists, lock is prior to next trains usage
							
						} else if (delayTrack.endLock <= track.startLock) { // no problem exists, lock is after second train needs track
							
						} else { //if not before or after, they intersect.
							int delayTime = delayTrack.endLock - track.startLock;
							
							this.delayTrain(toBeScheduled, delayTime);
						}
					}
				}
			}
			
		}
	}
	
	private void delayTrain(Train toBeScheduled, int delayTime) {
		toBeScheduled.timeDelayed += delayTime;
		
		for (Edge track: toBeScheduled.route.route) {
			track.startLock += delayTime;
			track.endLock += delayTime;
		}
	}

	public String toCSV() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Train #,Start,End,Expected Departure,Actual Departure,Delay\n");
		for (int i = 0; i < schedule.size(); i++) {
			Train train = schedule.get(i);
			sb.append(i + "," + train.start() + "," + train.end()+ "," + train.route.expectedDeparture + "," + (train.route.expectedDeparture + train.timeDelayed) + "," + train.timeDelayed);
			sb.append('\n');
		}
		
		return sb.toString();
	}
	
	public String toTXT() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Train #, Start, End, Route, Expected Departure, Actual Departure, Delay\n");
		for (int i = 0; i < schedule.size(); i++) {
			Train train = schedule.get(i);
			sb.append(i + "," + train.start() + "," + train.end()+"," + train.route()  +"," + train.route.expectedDeparture + "," + (train.route.expectedDeparture + train.timeDelayed) + "," + train.timeDelayed);
			sb.append('\n');
		}
		
		return sb.toString();
	}
}
