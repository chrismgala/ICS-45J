// Train.java
// The class representing each of the 5 instances of Train threads. This class extends properties of a
// Thread since there are 5 that will be moving and accessing shared data simultaneously.

// Chris Gala 64338761
// Wai Phyo 60902242

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Train extends Thread
{
	private Thread t;
	
	private int trainID;
	private int currentTrainStation;
	private int numPassengers;
	private int totalLoadedPassengers;
	private int totalUnloadedPassengers;
	private Lock lock = new ReentrantLock();
	
	private ArrayList<TrainEvent> moveQueue = new ArrayList<TrainEvent>();
	private int[] passengerDestinations;
	
	TrainSystemManager manager;
	
	public Train(int trainID, TrainSystemManager manager)
	{
		this.trainID = trainID;
		this.currentTrainStation = 0;
		this.numPassengers = 0;
		this.manager = manager;
		this.passengerDestinations = new int[5];
	}
	
	public void run()
	{
		// Should continuously loop and process trainEvents in the moveQueue.
		System.out.println("Running " +  Integer.toString(this.trainID) );

		while(SimClock.getTime() <= TrainSimulation.totalSimulationTime) 
	    {
			if (this.moveQueue.isEmpty())
			{
				synchronized (this.manager)
				{
					this.check_requests();
				}
				
				// there is a request to pick up passengers
				if (!this.moveQueue.isEmpty())
				{
					this.lock.lock();

					TrainEvent toRun = this.moveQueue.get(0);
					
					// lock the train.. move to ... 
					// another train station to pick up passengers
					int curr_sim_time = SimClock.getTime();
					int expected_travel_time = curr_sim_time + this.calculateTravelTime(toRun.getOrigination());
					
					// end thread if train arrives later then the totalSimulationTime
					if (expected_travel_time >= TrainSimulation.totalSimulationTime)
					{
						break;
					}
					
					System.out.println("Train " + Integer.toString(this.trainID) + " is moving to " + Integer.toString(toRun.getOrigination()) + 
							" from " + Integer.toString(this.currentTrainStation) + " at expected Arrival Time: " + Integer.toString(expected_travel_time));
					
					// do nothing while the train is driving to the dst
					try {
						Thread.sleep(Math.round(expected_travel_time*TrainSimulation.simulatedSecondRate) - curr_sim_time*TrainSimulation.simulatedSecondRate);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// alert that train has arrived
					System.out.println("Train " + Integer.toString(this.trainID) + " has arrived at Station #" + toRun.getOrigination());
					
					// train has arrived... update current train station
					this.currentTrainStation = toRun.getOrigination();
					
					// work is done... unlock
					this.lock.unlock();
				}
			}
			
			if (!this.moveQueue.isEmpty())
			{
				this.lock.lock();
				
				// get first event to process
				TrainEvent toRun = this.moveQueue.get(0);
					
				// does anyone want to get on the train?
				int num_to_load = this.num_passengers_to_load();
				if (num_to_load > 0)
				{
					this.totalLoadedPassengers += num_to_load;
					this.setNumPassengers(this.getNumPassengers() + num_to_load);
					this.load_passengers();
					System.out.println("LOADED: Train " + Integer.toString(this.trainID) + " has now a total of " + Integer.toString(this.getNumPassengers()) + " passengers");
					this.manager.trainStations[this.currentTrainStation].setApproachingTrain(-1);
					// set other approaching train?
				}
				
				// does anyone want to get off?
				int num_to_unload = this.passengerDestinations[this.currentTrainStation];
				if (num_to_unload > 0)
				{
					this.totalUnloadedPassengers += num_to_unload;
					this.setNumPassengers(this.getNumPassengers() - num_to_unload);
					this.passengerDestinations[this.currentTrainStation] -= num_to_unload;
					this.manager.trainStations[this.currentTrainStation].arrivedPassengers[toRun.getOrigination()] += num_to_unload;
					System.out.println("UNLOADED: Train " + Integer.toString(this.trainID) + " has now a total of " + Integer.toString(this.getNumPassengers()) + " passengers");
				}
				this.moveQueue.remove(0);
				
				// get next travel event if there are any passengers left
				if (this.isBusy())
				{
					toRun = this.moveQueue.get(0);
					
					// we need to go to another train station to pick up passengers
					int curr_sim_time = SimClock.getTime();
					int expected_travel_time = curr_sim_time + this.calculateTravelTime(toRun.getDestination());
					
					// end thread if train arrives later then the totalSimulationTime
					if (expected_travel_time >= TrainSimulation.totalSimulationTime)
					{
						break;
					}
					
					System.out.println("Train " + Integer.toString(this.trainID) + " is moving to " + Integer.toString(toRun.getDestination()) + 
							" from " + Integer.toString(this.currentTrainStation) + " at expected Arrival Time: " + Integer.toString(expected_travel_time));
					
					// do nothing while the train is driving to the dst
					try {
						Thread.sleep(Math.round(expected_travel_time*TrainSimulation.simulatedSecondRate) - curr_sim_time*TrainSimulation.simulatedSecondRate);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// alert that train has arrived
					System.out.println("Train " + Integer.toString(this.trainID) + " has arrived at Station #" + toRun.getDestination());
					
					// train has arrived... update current train station
					this.currentTrainStation = toRun.getDestination();
					this.lock.unlock();
				}
				else
				{
					System.out.println("Train " + Integer.toString(this.trainID) + " is now idle");
				}
			}
	    }
	}
	
	public void start()
	{
		if (t == null) 
		{
			t = new Thread (this, Integer.toString(this.trainID));
			t.setPriority(Thread.MAX_PRIORITY);
			t.start();
		}
	}
	
	// check all train stations for passengers and queue it into MoveQueue if there is a pending request
	public void check_requests()
	{
		Boolean assigned = false;
		for (int i = 0; i < 5; i++){
			for (int j = 0; j < 5; j++)
			{
				if (i != j)
				{
					int curr_pass_requests = this.manager.trainStations[i].passengerRequests[j];
					int heading_train = this.manager.trainStations[i].getApproachingTrain();
					// if there are passengers at the station -- create a train event to go to that station
					if (curr_pass_requests > 0 && heading_train == -1)
					{
						this.manager.trainStations[i].setApproachingTrain(this.trainID);
						int expected_arrival_time = SimClock.getTime() + this.calculateTravelTime(i);
						this.createTrainEvent(i, j, expected_arrival_time);
						assigned = true;
						break;
					}
				}
			}
			if (assigned)
			{
				break;
			}
		}
	}
	
	// calculate how many passengers need to be loaded
	public int num_passengers_to_load()
	{
		int total = 0;
		for (int i = 0; i < 5; i++)
		{
			total += this.manager.trainStations[this.currentTrainStation].passengerRequests[i];
		}
		return total;
	}
	
	// only loads when the train has currently arrived at the station
	public void load_passengers()
	{
		for (int i = 0; i < 5; i++)
		{
			int pr = this.manager.trainStations[this.currentTrainStation].passengerRequests[i];
			if (pr > 0)
			{
				this.manager.trainStations[i].setApproachingTrain(this.trainID);
				int expected_arrival_time = SimClock.getTime() + this.calculateTravelTime(i);
				this.createTrainEvent(this.currentTrainStation, i, expected_arrival_time);
				this.passengerDestinations[i] += pr;
				this.manager.trainStations[this.currentTrainStation].passengerRequests[i] = 0;
			}
		}
	}
	
	
	public void filter_moveQueue()
	{
		ArrayList<Integer> to_rem = new ArrayList<Integer>();
		for (int i = 0; i < this.moveQueue.size(); i++)
		{
			int req_dst_ts = this.moveQueue.get(i).getDestination();
			if (req_dst_ts == this.currentTrainStation)
			{
				to_rem.add(i);
			}
		}
		for (int i = 0; i < to_rem.size(); i++)
		{
			this.moveQueue.remove(to_rem.get(i));
		}
	}
	
	public int get_closest_dst_trainstation()
	{
		int te_index = 0;
		int closest_ts_diff = Integer.MAX_VALUE;
		for (int i = 0; i < this.moveQueue.size(); i++){
			int req_dst_ts = this.moveQueue.get(i).getDestination();
			if (Math.abs(req_dst_ts - this.currentTrainStation) < closest_ts_diff)
			{
				closest_ts_diff = Math.abs(req_dst_ts - this.currentTrainStation);
				te_index = i;
			}
		}
		return te_index;
	}
	
	// Create a TrainEvent and move it into the moveQueue
	public void createTrainEvent(int origination, int destination, int expectedArrival)
	{
		TrainEvent te = new TrainEvent(origination, destination, expectedArrival);
		this.moveQueue.add(te);
	}
	
	// Calculate the travel time for a particular event
	public int calculateTravelTime(int passengerTrainStation)
	{
		return (5 * (Math.abs(passengerTrainStation - this.currentTrainStation)));
	}
	
	// Check whether a Train is busy or not
	public boolean isBusy()
	{
		return this.numPassengers != 0;
	}
	
	public int getTrainID()
	{
		return trainID;
	}

	public void setTrainID(int trainID)
	{
		this.trainID = trainID;
	}

	public int getCurrentTrainStation()
	{
		return currentTrainStation;
	}

	public void setCurrentTrainStation(int currentTrainStation)
	{
		this.currentTrainStation = currentTrainStation;
	}

	public int getNumPassengers()
	{
		return numPassengers;
	}

	public void setNumPassengers(int numPassengers)
	{
		this.numPassengers = numPassengers;
	}

	public int getTotalLoadedPassengers()
	{
		return totalLoadedPassengers;
	}

	public void addTotalLoadedPassengers(int passengers)
	{
		this.totalLoadedPassengers += passengers;
	}

	public int getTotalUnloadedPassengers()
	{
		return totalUnloadedPassengers;
	}

	public void addTotalUnloadedPassengers(int passengers)
	{
		this.totalUnloadedPassengers += passengers;
	}

	public ArrayList<TrainEvent> getMoveQueue()
	{
		return moveQueue;
	}

	public void setMoveQueue(ArrayList<TrainEvent> moveQueue)
	{
		this.moveQueue = moveQueue;
	}

	public int[] getPassengerDestinations()
	{
		return passengerDestinations;
	}

	public void setPassengerDestinations(int[] passengerDestinations)
	{
		this.passengerDestinations = passengerDestinations;
	}

	public TrainSystemManager getManager()
	{
		return manager;
	}

	public void setManager(TrainSystemManager manager)
	{
		this.manager = manager;
	}
}
