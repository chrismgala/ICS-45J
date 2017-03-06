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

//	‐ The train must drop off current passengers to the train station that it is
//	closest to.
//		o For example, if the train at train station 2 has two passengers requesting to go to train station 3 and train station 4, then it will stop at train station 3 first and then train station 4 (not 4 and then 3).

//	‐ If a train has no passengers to drop off, then it will continuously check all train stations to see if any passengers are waiting for a train and no other train is approaching that train station for passenger pickup.
//		o No two trains will approach a train station for passenger pickup at the same time.

// ‐ If a train detects that there are passengers waiting on a train station, and no other train is currently approaching a train station for passenger pickup, then it will go towards that train station to load passengers.
// ‐ A train takes 10 simulated seconds to load / unload passengers.
// ‐ A train takes 5 simulated seconds to pass through one train station.
// ‐ A train does not have a max capacity of passengers.

import java.util.*;

public class TrainSimulation
{
	private int totalSimulationTime;
	private int simulatedSecondRate;
	
	private TrainSystemManager tsm;
	
	ArrayList< ArrayList<PassengerArrival> > passengerArrivals = new ArrayList< ArrayList<PassengerArrival> >();
	
	public TrainSimulation()
	{
		this.tsm = new TrainSystemManager();
	}
	
	public void start()
	{
		// Runs the main simulation loop including incrementing the 
		// simulated time and managing passenger arrival behavior. 
		// The simulation ends when the current simulation time is greater 
		// than the total simulation time defined in trainConfig.txt
		
		// Example values from instructions
		totalSimulationTime = 1000;
		simulatedSecondRate = 100;
		long currentSystemTime = System.currentTimeMillis();
		
		// Train station 0 => 2 4 100;5 2 300
		ArrayList<PassengerArrival> ts0 = new ArrayList<PassengerArrival>();
		ts0.add(new PassengerArrival(2, 0, 4, 100));
		ts0.add(new PassengerArrival(5, 0, 2, 300));
		
		// Train station 1 => 3 0 500;1 4 200
		ArrayList<PassengerArrival> ts1 = new ArrayList<PassengerArrival>();
		ts1.add(new PassengerArrival(3, 1, 0, 500));
		ts1.add(new PassengerArrival(1, 1, 4, 200));
		
		// Train station 2 => 5 0 200;2 1 500;3 3 600
		ArrayList<PassengerArrival> ts2 = new ArrayList<PassengerArrival>();
		ts2.add(new PassengerArrival(5, 2, 0, 200));
		ts2.add(new PassengerArrival(2, 2, 1, 500));
		ts2.add(new PassengerArrival(3, 2, 3, 600));
		
		// Train station 3 => 4 0 200
		ArrayList<PassengerArrival> ts3 = new ArrayList<PassengerArrival>();
		ts3.add(new PassengerArrival(4, 3, 0, 200));

		// Train station 4 => 2 3 300;6 2 100;4 0 40
		ArrayList<PassengerArrival> ts4 = new ArrayList<PassengerArrival>();
		ts4.add(new PassengerArrival(2, 4, 3, 300));
		ts4.add(new PassengerArrival(6, 4, 2, 100));
		ts4.add(new PassengerArrival(4, 4, 0, 40));
		
		passengerArrivals.add(ts0);
		passengerArrivals.add(ts1);
		passengerArrivals.add(ts2);
		passengerArrivals.add(ts3);
		passengerArrivals.add(ts4);
		
		Train t0 = new Train(0, tsm);
		Train t1 = new Train(1, tsm);
		Train t2 = new Train(2, tsm);
		Train t3 = new Train(3, tsm);
		Train t4 = new Train(4, tsm);
		
		t0.start();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		
//		while (SimClock.getTime() <= totalSimulationTime)
//		{		
//			if (System.currentTimeMillis() - currentSystemTime >= simulatedSecondRate) 
//			{
//				SimClock.tick();
//				currentSystemTime = System.currentTimeMillis();	
//
//				// DEBUGGING PURPOSES
//				System.out.println(Integer.toString(SimClock.getTime()));
//				
//				// NOW MANAGE PASSENGER BEHAVIOR
//				// Iterate through passengerArrivals
//				for (ArrayList<PassengerArrival> al : passengerArrivals)
//				{
//					for (PassengerArrival pa : al)
//					{
//						if (SimClock.getTime() > 0 && SimClock.getTime() % pa.getTimePeriod() == 0)
//						{
//							TrainEvent te = t1.createTrainEvent(pa.getDestinationTrainStation(), pa.getExpectedTimeOfArrival());
//							this.tsm.trainStations[pa.getDestinationTrainStation()].setApproachingTrain(t1.getTrainID());
//							
//							
//						}
//					}
//				}
//			}
//		}
	}
	
	public void printTrainState()
	{
		
	}
	
	public void parseTrainConfig()
	{
		
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
