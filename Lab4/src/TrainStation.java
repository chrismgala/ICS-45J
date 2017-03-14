// TrainStation.java
// The TrainStation class. There will be 5 instances of this class and their states will be managed
// using these class variables and methods while the simulation runs.

// Chris Gala 64338761
// Wai Phyo 60902242

public class TrainStation
{
	public int[] totalDestinationRequests;
	public int[] arrivedPassengers;
	public int[] passengerRequests;
	public int approachingTrain;
	
	public TrainStation()
	{
		this.totalDestinationRequests = new int[5];
		this.arrivedPassengers = new int[5];
		this.passengerRequests = new int[5];
		this.approachingTrain = -1;
	}
	
	public int getTotalPassengers(){
		int total = 0;
		for (int i = 0; i < 5; i++)
		{
			total += this.totalDestinationRequests[i];
		}
		return total;
	}
	
	public int[] getTotalDestinationRequests()
	{
		return totalDestinationRequests;
	}
	
	public void setTotalDestinationRequests(int[] totalDestinationRequests)
	{
		this.totalDestinationRequests = totalDestinationRequests;
	}
	
	public int getArrivedPassengers()
	{
		int total = 0;
		for (int i = 0; i < 5; i++)
		{
			total += this.arrivedPassengers[i];
		}
		return total;	
	}
	
	public void setArrivedPassengers(int[] arrivedPassengers)
	{
		this.arrivedPassengers = arrivedPassengers;
	}
	
	public int getPassengerRequests()
	{
		int total = 0;
		for (int i = 0; i < 5; i++)
		{
			total += this.passengerRequests[i];
		}
		return total;		
	}
	
	public void setPassengerRequests(int[] passengerRequests)
	{
		this.passengerRequests = passengerRequests;
	}
	
	public int getApproachingTrain()
	{
		return approachingTrain;
	}
	
	public void setApproachingTrain(int approachingTrain)
	{
		this.approachingTrain = approachingTrain;
	}
}
