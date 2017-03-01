// Train.java
// 

// Chris Gala 64338761
// Wai Phyo 60902242

import java.util.*;

public class Train
{
	private int trainID;
	private int currentTrainStation;
	private int numPassengers;
	private int totalLoadedPassengers;
	private int totalUnloadedPassengers;
	
	ArrayList<TrainEvent> moveQueue;
	int[] passengerDestinations;
	
	TrainSystemManager manager;
	
	public Train(int trainID, TrainSystemManager manager)
	{
		this.trainID = trainID;
		this.manager = manager;
	}
	
	public void run()
	{
		
	}
	
//	public TrainEvent createTrainEvent()
//	{
//		return TrainEvent;
//	}
	
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
