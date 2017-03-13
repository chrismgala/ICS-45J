// TrainSimulation.java
// 

// Chris Gala 64338761
// Wai Phyo 60902242

// Train system components such as the train threads and the TrainSystemManager.

// Each train will run in its own thread.

// All trains start on the 0th train station.

// A train will only head to a train station to load passengers if it currently
// does not have any passengers and is not moving towards a train station.

// By default, a train will load all passengers requesting to go up first.
// 		o If a train reaches a train station to load passengers and there are no passengers that want to go up, 
// 	then the train will load all passengers that want to go down.

//	â€� The train must drop off current passengers to the train station that it is
//	closest to.
//		o For example, if the train at train station 2 has two passengers requesting to go to train station 3 and train station 4, then it will stop at train station 3 first and then train station 4 (not 4 and then 3).

//	â€� If a train has no passengers to drop off, then it will continuously check all train stations to see if any passengers are waiting for a train and no other train is approaching that train station for passenger pickup.
//		o No two trains will approach a train station for passenger pickup at the same time.

// â€� If a train detects that there are passengers waiting on a train station, and no other train is currently approaching a train station for passenger pickup, then it will go towards that train station to load passengers.
// â€� A train takes 10 simulated seconds to load / unload passengers.
// â€� A train takes 5 simulated seconds to pass through one train station.
// â€� A train does not have a max capacity of passengers.

import java.util.*;
import java.io.*;

public class TrainSimulation
{
	private int totalSimulationTime;
	private int simulatedSecondRate;
	
	TrainSystemManager tsm;
	
	ArrayList< ArrayList<PassengerArrival> > passengerArrivals = new ArrayList< ArrayList<PassengerArrival> >();
	ArrayList<Train> trains = new ArrayList<Train>();
	TreeMap<Integer,PassengerArrival> all_arrival_times = new TreeMap<Integer,PassengerArrival>();
	
	public TrainSimulation()
	{
		this.tsm = new TrainSystemManager();
		parseTrainConfig();
		
	}
	
	public void start()
	{
		// Runs the main simulation loop including incrementing the 
		// simulated time and managing passenger arrival behavior. 
		// The simulation ends when the current simulation time is greater 
		// than the total simulation time defined in TrainConfig.txt
		
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
			// DEBUGGING PURPOSES
			
			
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
	}
	
	public void printTrainState()
	{
		
	}
	
	public void parseTrainConfig()
	{
		BufferedReader br = null;
        try 
        {
            br = new BufferedReader(new FileReader("TrainConfig.txt"));
            String line = br.readLine();
            this.totalSimulationTime = Integer.parseInt(line);
            line = br.readLine();
            this.simulatedSecondRate = Integer.parseInt(line);
            
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

	public int getTotalSimulationTime()
	{
		return totalSimulationTime;
	}

	public void setTotalSimulationTime(int totalSimulationTime)
	{
		this.totalSimulationTime = totalSimulationTime;
	}

	public int getSimulatedSecondRate()
	{
		return simulatedSecondRate;
	}

	public void setSimulatedSecondRate(int simulatedSecondRate)
	{
		this.simulatedSecondRate = simulatedSecondRate;
	}
}
