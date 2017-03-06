// TrainEvent.java
// 

// Chris Gala 64338761
// Wai Phyo 60902242

public class TrainEvent
{
	private int destination;
	private int expectedArrival;
	
	public TrainEvent()
	{
		this.destination = 0;
		this.expectedArrival = 0;
	}
	
	public TrainEvent(int destination, int expectedArrival)
	{
		this.destination = destination;
		this.expectedArrival = expectedArrival;
	}

	public int getDestination()
	{
		return destination;
	}

	public void setDestination(int destination)
	{
		this.destination = destination;
	}

	public int getExpectedArrival()
	{
		return expectedArrival;
	}

	public void setExpectedArrival(int expectedArrival)
	{
		this.expectedArrival = expectedArrival;
	}
}
