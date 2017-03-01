// TrainStation.java
// 

// Chris Gala 64338761
// Wai Phyo 60902242

public class TrainStation
{
	private int[] totalDestinationRequests;
	private int[] arrivedPassengers;
	private int[] passengerRequests;
	private int approachingTrain;
	
	public TrainStation()
	{
		this.totalDestinationRequests = new int[10];
		this.arrivedPassengers = new int[10];
		this.passengerRequests = new int[10];
		this.approachingTrain = 0;
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
