// TrainEvent.java
// A class representing an event in which a Train needs to go from 'origination' TrainStation to 'destination'
// TrainStation. An calculated time of arrival is stored as well so that the corresponding Train thread can be
// blocked off for the time being.

// Chris Gala 64338761
// Wai Phyo 60902242

public class TrainEvent
{
	private int origination;
	private int destination;
	private int expectedArrival;
	
	public TrainEvent()
	{
		this.setOrigination(0);
		this.destination = 0;
		this.expectedArrival = 0;
	}
	
	public TrainEvent(int origination, int destination, int expectedArrival)
	{
		this.origination = origination;
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

	public int getOrigination() {
		return origination;
	}

	public void setOrigination(int origination) {
		this.origination = origination;
	}
}
