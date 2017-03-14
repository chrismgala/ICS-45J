// TrainSimulation.java
// Contains all logic for the simulation. This includes running the simulation loop and keeping track of
// the SimClock time and incrementing it accordingly. After the simulation ends, TrainSimulation prints out the
// appropriate statistics.

// Chris Gala 64338761
// Wai Phyo 60902242

import java.util.*;
import java.io.*;

public class TrainSimulation
{
	public static int totalSimulationTime;
	public static int simulatedSecondRate;
	
	TrainSystemManager tsm;
	
	ArrayList< ArrayList<PassengerArrival> > passengerArrivals = new ArrayList< ArrayList<PassengerArrival> >();
	ArrayList<Train> trains = new ArrayList<Train>();
	TreeMap<Integer,PassengerArrival> all_arrival_times = new TreeMap<Integer,PassengerArrival>();
	
	public TrainSimulation()
	{
		this.tsm = new TrainSystemManager();
		parseTrainConfig();
		
	}
	
	// Runs the main simulation loop including incrementing the 
	// simulated time and managing passenger arrival behavior. 
	// The simulation ends when the current simulation time is greater 
	// than the total simulation time defined in TrainConfig.txt
	public void start()
	{	
		Train t0 = new Train(0, tsm);
		Train t1 = new Train(1, tsm);
		Train t2 = new Train(2, tsm);
		Train t3 = new Train(3, tsm);
		Train t4 = new Train(4, tsm);
		
		trains.add(t0);
		trains.add(t1);
		trains.add(t2);
		trains.add(t3);
		trains.add(t4);
		
		for (Train t : trains)
		{
			t.start();
		}
		
		for (int i=0; i < passengerArrivals.size(); i++){
			for (int j=0; j < passengerArrivals.get(i).size(); j++){
				PassengerArrival pa = passengerArrivals.get(i).get(j);
				ArrayList<Integer> t = pa.getArrivalTimes();
				for (int k = 0; k < t.size(); k++){
					all_arrival_times.put(t.get(k), pa);
				}
			}
		}
		
		long past_time = System.currentTimeMillis();
		
		while (SimClock.getTime() <= totalSimulationTime)
		{	
		
			long curr_time = System.currentTimeMillis();
			if (Math.round(curr_time - past_time) == 100)
			{
				SimClock.tick();
				System.out.println(Integer.toString(SimClock.getTime()));
				past_time = curr_time;
			}
			
			
			// NOW MANAGE PASSENGER BEHAVIOR
			// get next event from any station
			if (!all_arrival_times.isEmpty())
			{
				Map.Entry<Integer, PassengerArrival> next_event = all_arrival_times.firstEntry();
				
				// check if current time has reached the next upcoming event if so... then assign event to train system manager
				if (SimClock.getTime() == next_event.getKey())
				{
					PassengerArrival pa = next_event.getValue();
					int dst = pa.getDestinationTrainStation();
					int ori = pa.getOriginTrainStation();
					
					
					// update total number of passengers who have requested to go to dst train station
					this.tsm.trainStations[ori].totalDestinationRequests[dst] += 1;
					// update current number of passengers who have requested to go to dst train station
					this.tsm.trainStations[ori].passengerRequests[dst] += 1;
					System.out.println("Passenger arrived at Station #" + Integer.toString(ori) + " wanting to go to Station #" + Integer.toString(dst));

					
					// remove event
					all_arrival_times.remove(next_event.getKey());
				}
			}
		}
		
		this.printTrainState();
	}
	
	// Print all statistics for each Train and each TrainStation
	public void printTrainState()
	{
		for (int i = 0; i < 5; i++)
		{
			TrainStation ts = this.tsm.trainStations[i];
			int totalNumPassengers = ts.getTotalPassengers();
			int arrivedNumPassengers = ts.getArrivedPassengers();
			int currentNumPassengers = ts.getPassengerRequests();
			// Print out state for each train station
			System.out.println("STATE REPORT for Train Station " + Integer.toString(i));
			System.out.println("Total number of passengers requested: " + Integer.toString(totalNumPassengers));
			System.out.println("Total number of passengers arrived: " + Integer.toString(arrivedNumPassengers));
			System.out.println("Current number of passengers waiting: " + Integer.toString(currentNumPassengers));
			System.out.println("Current train heading to station: " + Integer.toString(ts.getApproachingTrain()));
		}
		
		// Print out state of each train
		for (int i = 0; i < 5; i++)
		{
			Train t = this.trains.get(i);
			System.out.println("STATE REPORT for Train " + Integer.toString(i));
			System.out.println("Total number of loaded passengers: " + Integer.toString(t.getTotalLoadedPassengers()));
			System.out.println("Total number of unloaded passengers: " + Integer.toString(t.getTotalUnloadedPassengers()));
			System.out.println("Current number of passengers: " + Integer.toString(t.getNumPassengers()));
		}
	}
	
	// Read the TrainConfig.txt file and load the data into the class variables
	public void parseTrainConfig()
	{
		BufferedReader br = null;
        try 
        {
            br = new BufferedReader(new FileReader("TrainConfig.txt"));
            String line = br.readLine();
            TrainSimulation.totalSimulationTime = Integer.parseInt(line);
            line = br.readLine();
            TrainSimulation.simulatedSecondRate = Integer.parseInt(line);
            
            for (int i = 0; i < 5; i++)
            {
            	String[] lines = br.readLine().split(";");
            	ArrayList<PassengerArrival> ts = new ArrayList<PassengerArrival>();
            	
            	for (int j = 0; j < lines.length; j++)
            	{
            		String[] arrivalRate = lines[j].split(" ");

            		ts.add(new PassengerArrival(Integer.parseInt(arrivalRate[0]), i, Integer.parseInt(arrivalRate[1]), Integer.parseInt(arrivalRate[2])));
            	}
            	
            	passengerArrivals.add(ts);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        finally 
        {
            try 
            {
                if (br != null) 
                {
                    br.close();
                }
            } 
            catch (IOException ex) 
            {
                ex.printStackTrace();
            }
        }
	}
}
