import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class TrainScheduler {
	private Map trainMap;
	
	public ArrayList<Train> schedule;//O(1) access
	
	private HashMap<Integer, Dijkstra> paths;
	private HashMap<Track, Integer> tracks;
	private HashMap<Integer, Track> trackId;
	
	

	// 0 -> base, 1 -> optimized
	private int mode;

	public TrainScheduler (int numberOfStations, File inputSchedule, int mode) throws FileNotFoundException {
		this.trainMap = new Map(numberOfStations);
		this.tracks = new HashMap<Track, Integer>();
		this.trackId = new HashMap<Integer, Track>();
		this.paths = new HashMap<Integer, Dijkstra>();
		this.mode = mode;
		
		this.schedule = makeSchedule(inputSchedule);
		
		this.calculateLocks();
	}

	private ArrayList<Train> makeSchedule(File file) throws FileNotFoundException {
		
		ArrayList<Train> schedule = new ArrayList<Train>();
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextInt()) {
			int startStation = sc.nextInt();
			int endStation = sc.nextInt();
			int expectedStartTime = sc.nextInt();
			int trainType = sc.nextInt();

			if (!paths.containsKey(startStation)) {
				paths.put(startStation, new Dijkstra(this.trainMap, startStation));
			}
			
			Departure leave = new Departure(paths.get(startStation).pathTo(endStation, tracks, trackId), expectedStartTime);
			
			schedule.add(new Train(trainType, leave));
			
			Collections.sort(schedule);
		}
		
		sc.close();

		return schedule;
	}

	private void calculateLocks() {
		if (mode == 0) { //base case
			for (Train train : schedule) {
				LinkedList<Pair<Track, Lock>> path = new LinkedList<Pair<Track, Lock>>();
				
				int start = train.expectedDeparture;
				int end = start;
				
				for (Track track : train.route) {
					end += (int) Math.ceil(track.weight / train.speed);
				}
				
				for (Track track : train.route) {
					Lock grantedTime= track.addLock(start, end);
					
					path.add(new Pair<Track, Lock>(track, grantedTime));
				}
				
				train.setGrantedPath(path);
			}
		} else if (mode == 1) { //optimized
			for (Train train : schedule) {
				LinkedList<Pair<Track, Lock>> path = new LinkedList<Pair<Track, Lock>>();
				int start = train.expectedDeparture;
				
				for (Track track : train.route) {
					int lockStart = start;
					int lockEnd = lockStart + (int) Math.ceil(track.weight / train.speed);
					
					Lock grantedTime = track.addLock(lockStart, lockEnd);
					
					path.add(new Pair<Track, Lock>(track, grantedTime));
				}
				
				train.setGrantedPath(path);
			}
		}
	}
	public String toCSV() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Train #,Start,End,Train Speed,Expected Departure,Expected Arrival,Actual Arrival,Delay\n");
		for (int i = 0; i < schedule.size(); i++) {
			Train train = schedule.get(i);
			sb.append(i + "," + train.start() + "," + train.end()+","+ train.speed +"," + train.expectedDeparture + "," + train.expectedTime()+","+ train.getArrivalTime() + "," + train.getDelay());
			sb.append('\n');
		}
		
		return sb.toString();
	}
	
	public String toTXT() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Train #, Start, End, Route, Expected Departure, Expected Arrival, Delay\n");
		for (int i = 0; i < schedule.size(); i++) {
			Train train = schedule.get(i);
			sb.append(i + "," + train.start() + "," + train.end()+"," + train.route()  +"," + train.expectedDeparture + "," + train.expectedTime() + "," + train.getDelay());
			sb.append('\n');
		}
		
		return sb.toString();
	}
}
