// Train.java
// 

// Chris Gala 64338761
// Wai Phyo 60902242

import java.util.*;

// Each train will check the state of the train system and passengers without sleeping.

public class Train implements Runnable
{
	private Thread t;
	
	private int trainID;
	private int currentTrainStation;
	private int numPassengers;
	private int totalLoadedPassengers;
	private int totalUnloadedPassengers;
	
	ArrayList<TrainEvent> moveQueue = new ArrayList<TrainEvent>();
	int[] passengerDestinations;
	
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
	      try {
	         for(int i = 4; i > 0; i--) {
	            System.out.println("Thread: " + Integer.toString(this.trainID) + ", " + i);
	            Thread.sleep(0);
	         }
	      }catch (InterruptedException e) {
	         System.out.println("Thread " +  Integer.toString(this.trainID) + " interrupted.");
	      }
	      System.out.println("Thread " +  Integer.toString(this.trainID) + " exiting.");
	}
	
	public void start()
	{
		System.out.println("Starting Train " +  Integer.toString(this.trainID) );
		if (t == null) 
		{
			t = new Thread (this, Integer.toString(this.trainID));
			t.setPriority(Thread.MAX_PRIORITY);
			t.start();
		}
	}
	
	public TrainEvent createTrainEvent(int destination, int expectedArrival)
	{
		TrainEvent te = new TrainEvent(destination, expectedArrival);
		moveQueue.add(te);
		return new TrainEvent(destination, expectedArrival);
	}
	
	public void updateMoveQueue()
	{
		
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

	public void setTotalLoadedPassengers(int totalLoadedPassengers)
	{
		this.totalLoadedPassengers = totalLoadedPassengers;
	}

	public int getTotalUnloadedPassengers()
	{
		return totalUnloadedPassengers;
	}

	public void setTotalUnloadedPassengers(int totalUnloadedPassengers)
	{
		this.totalUnloadedPassengers = totalUnloadedPassengers;
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
