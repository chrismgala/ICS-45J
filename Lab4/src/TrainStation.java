// TrainStation.java
// 

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
	
	public int[] getTotalDestinationRequests()
	{
		return totalDestinationRequests;
	}
	
	public void setTotalDestinationRequests(int[] totalDestinationRequests)
	{
		this.totalDestinationRequests = totalDestinationRequests;
	}
	
	public int[] getArrivedPassengers()
	{
		return arrivedPassengers;
	}
	
	public void setArrivedPassengers(int[] arrivedPassengers)
	{
		this.arrivedPassengers = arrivedPassengers;
	}
	
	public int[] getPassengerRequests()
	{
		return passengerRequests;
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
