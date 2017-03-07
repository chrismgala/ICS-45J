// PassengerArrival.java
// 

// Chris Gala 64338761
// Wai Phyo 60902242

public class PassengerArrival
{
	private int numPassengers;
	private int destinationTrainStation;
	private int timePeriod;
	private int travelTime;

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
		this.destinationTrainStation = destinationTrainStation;
		this.timePeriod = timePeriod;
		this.travelTime = this.timePeriod + 10 + (5 * (Math.abs(originTrainStation - destinationTrainStation) - 1)) + 10;
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

	
}
