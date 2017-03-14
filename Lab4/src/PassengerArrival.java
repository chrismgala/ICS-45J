// PassengerArrival.java
// This class is used to represent each of the lines (2 - 7) of the TrainConfig that is parsed.
// We can generate the arrival times based on those lines in the TrainConfig ahead of time. This is randomized
// each time because in a "real" simulation, the PassengerArrivals would not be constant and the same each time.

// Chris Gala 64338761
// Wai Phyo 60902242

import java.util.*;

public class PassengerArrival
{
	private int numPassengers;
	private int originTrainStation;
	private int destinationTrainStation;
	private int timePeriod;
	private int travelTime;
	private ArrayList<Integer> arrivalTimes = new ArrayList<Integer>();
	public PassengerArrival()
	{
		this.numPassengers = 0;
		this.destinationTrainStation = 0;
		this.timePeriod = 0;
		this.travelTime = 0;
	}
	
	public PassengerArrival(int numPassengers, int originTrainStation, int destinationTrainStation, int timePeriod)
	{
		this.numPassengers = numPassengers;
		this.originTrainStation = originTrainStation;
		this.destinationTrainStation = destinationTrainStation;
		this.timePeriod = timePeriod;
		this.travelTime = 10 + (5 * (Math.abs(originTrainStation - destinationTrainStation) - 1)) + 10;
		generateArrivalTimes();
	}
	
	// use poison point process to generate all passenger arrival times at a station
	public void generateArrivalTimes()
	{
		int start = 0;
		double avg_occ = this.numPassengers/(double)timePeriod;
		while (true){
			double u = Math.random();
			double t_arr = -Math.log(u)/avg_occ;
			int rt = (int) Math.round(t_arr);
			start += rt;
			if (start > TrainSimulation.totalSimulationTime){
				break;
			}
			arrivalTimes.add(start);
		}
	}
	
	public ArrayList<Integer> getArrivalTimes()
	{
		return this.arrivalTimes;
	}
	
	public int getNumPassengers()
	{
		return numPassengers;
	}

	public void setNumPassengers(int numPassengers)
	{
		this.numPassengers = numPassengers;
	}

	public int getDestinationTrainStation()
	{
		return destinationTrainStation;
	}

	public void setDestinationTrainStation(int destinationTrainStation)
	{
		this.destinationTrainStation = destinationTrainStation;
	}

	public int getTimePeriod()
	{
		return timePeriod;
	}

	public void setTimePeriod(int timePeriod)
	{
		this.timePeriod = timePeriod;
	}

	public int getTravelTime()
	{
		return travelTime;
	}

	public void setTravelTime(int travelTime)
	{
		this.travelTime = travelTime;
	}

	public int getOriginTrainStation()
	{
		return originTrainStation;
	}

	public void setOriginTrainStation(int originTrainStation)
	{
		this.originTrainStation = originTrainStation;
	}

	
}
