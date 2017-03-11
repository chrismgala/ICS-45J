// Train.java
// 

// Chris Gala 64338761
// Wai Phyo 60902242

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Each train will check the state of the train system and passengers without sleeping.

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
	}
	
	public void run()
	{
		// Should continuously loop and process trainEvents in the moveQueue.
		System.out.println("Running " +  Integer.toString(this.trainID) );

		while(true) 
	    {
//				System.out.println("Train " + Integer.toString(this.trainID) + " is now at " + Integer.toString(this.currentTrainStation));
			if (!this.isBusy())
			{
				synchronized (this.manager)
				{
					this.check_requests();
				}
				// check all train stations for passengers and queue it into MoveQueue if there is a pending request
//					System.out.println("Train " + Integer.toString(this.trainID) + " is now at " + Integer.toString(this.currentTrainStation));
			}
			
			if (!this.moveQueue.isEmpty())
			{
//				// process all events in the move queue and find which dst is the closest to current destination
//				int te_index = this.get_closest_dst_trainstation();
//				TrainEvent toRun = this.moveQueue.get(te_index);
//				System.out.println("Train " + Integer.toString(this.trainID) + " is moving to " + toRun.getDestination() + " at expected Arrival Time: " + Integer.toString(toRun.getExpectedArrival()));
				TrainEvent toRun = this.moveQueue.get(0);
				System.out.println("Train " + Integer.toString(this.trainID) + " is moving to " + toRun.getDestination() + " at expected Arrival Time: " + Integer.toString(toRun.getExpectedArrival()));
				
				// do nothing while the train is driving to the dst
//				this.lock.lock();
//				while (SimClock.getTime() != toRun.getExpectedArrival())
//				{
//					this.sleep(millis);
//				}
//				this.lock.unlock();
				int curr_sim_time = SimClock.getTime();
				try {
					Thread.sleep(Math.round(toRun.getExpectedArrival()*100) - curr_sim_time*100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Train " + Integer.toString(this.trainID) + " has arrived at " + toRun.getDestination());
				
				// train has arrived... update current train station & check number of passengers to unload & filter the moveQueue
				this.currentTrainStation = toRun.getDestination();
				this.moveQueue.remove(0);
				
				// first unload passengers that wants to get off at current station
				int num_to_unload = this.num_passengers_to_unload();
				if (num_to_unload > 0)
				{
					this.setNumPassengers(this.getNumPassengers() - num_to_unload);
//					this.filter_moveQueue();
					System.out.println("UNLOADED: Train " + Integer.toString(this.trainID) + " has now a total of " + Integer.toString(this.getNumPassengers()) + " passengers");
				}
				
				int num_to_load = this.num_passengers_to_load();
				if (num_to_load > 0)
				{
					this.setNumPassengers(this.getNumPassengers() + num_to_load);
//					this.filter_moveQueue();
					System.out.println("LOADED: Train " + Integer.toString(this.trainID) + " has now a total of " + Integer.toString(this.getNumPassengers()) + " passengers");
				}
				this.manager.trainStations[this.currentTrainStation].setApproachingTrain(-1);
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
	
	public void check_requests()
	{
		// check all train stations for passengers and queue it into MoveQueue if there is a pending request
		Boolean assigned = false;
		for (int i = 0; i < 5; i++){
			for (int j = 0; j < 5; j++)
			{
				if (i != j)
				{
					int curr_pass_requests = this.manager.trainStations[i].passengerRequests[j];
					int heading_train = this.manager.trainStations[i].getApproachingTrain();
					
					// if there are passengers at the station and there aren't any trains heading to said train station
					if (curr_pass_requests > 0 && heading_train == -1)
					{
						this.manager.trainStations[i].setApproachingTrain(this.trainID);
						int expected_arrival_time = SimClock.getTime() + this.calculateTravelTime(i);
						this.createTrainEvent(i, expected_arrival_time, curr_pass_requests);
						assigned = true;
						this.manager.trainStations[i].passengerRequests[j] = 0;
//						System.out.println("Assigned!");
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
	
	public int num_passengers_to_load()
	{
		int total = 0;
		for (int i = 0; i < 5; i++)
		{
			total += this.manager.trainStations[this.currentTrainStation].passengerRequests[i];
		}
		return total;
	}
	public int num_passengers_to_unload()
	{
		int total = 0;
		for (int i = 0; i < this.moveQueue.size(); i++)
		{
			int req_dst_ts = this.moveQueue.get(i).getDestination();
			if (req_dst_ts == this.currentTrainStation)
			{
				total += this.moveQueue.get(i).getn_pass();
			}
		}
		return total;
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
			this.moveQueue.remove(i);
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
	
	public int get_latest_arrival_time()
	{
		TrainEvent last = this.moveQueue.get(0);
		return last.getExpectedArrival();
	}
	
	public int get_Destination_tid()
	{
		TrainEvent last = this.moveQueue.get(0);
		return last.getDestination();
	}
	
	public void createTrainEvent(int destination, int expectedArrival, int n_pass)
	{
		TrainEvent te = new TrainEvent(destination, expectedArrival, n_pass);
		this.moveQueue.add(te);
	}
	
	public int calculateTravelTime(int passengerTrainStation)
	{
		return (5 * (Math.abs(passengerTrainStation - this.currentTrainStation)));
	}
	
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
