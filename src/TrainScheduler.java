import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TrainScheduler {
	public Map trainMap;
	public int numberOfStations;
	public ArrayList<Train> trains;
	
	public LinkedList<Departure> schedule;
	
	public TrainScheduler (int numberOfStations, String filePath) {
		this.numberOfStations = numberOfStations;
		trainMap = new Map(this.numberOfStations);
		
		schedule = makeSchedule(filePath);
	}
	
	private LinkedList<Departure> makeSchedule(String filePath) {
		
		LinkedList<Departure> schedule = new LinkedList<Departure>();
		Scanner sc = new Scanner(filePath);
		
		while (sc.hasNextLine()) {
			int startStation = sc.nextInt();
			int endStation = sc.nextInt();
			int expectedStartTime = sc.nextInt();
			
			Departure leave = new Departure(trainMap.shortestPath(startStation, endStation), expectedStartTime);
			
			this.insertRoute(schedule, leave);
		}
		
		sc.close();
		return schedule;
	}
	
	private void insertRoute (LinkedList<Departure> schedule, Departure toAdd) {
		if (schedule.size() == 0) {
			schedule.add(toAdd);
		} else {
			int i = 0;
			boolean added = false;
			while (i <= schedule.size() && !added) {
				try {
					if (schedule.get(i).compareTo(toAdd) == -1 && schedule.get(i + 1).compareTo(toAdd) == 1) {
						schedule.add(i, toAdd);
						added = true;
					}
				} catch (NoSuchElementException e) {
					schedule.addLast(toAdd);
				}
			}
		}
	}
}
