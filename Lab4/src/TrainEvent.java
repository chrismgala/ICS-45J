// TrainEvent.java
// 

// Chris Gala 64338761
// Wai Phyo 60902242

public class TrainEvent
{
	private int origination;
	private int destination;
	private int expectedArrival;
	private int n_pass;
	
	public TrainEvent()
	{
		this.setOrigination(0);
		this.destination = 0;
		this.expectedArrival = 0;
		this.n_pass = 0;
	}
	
	public TrainEvent(int origination, int destination, int expectedArrival, int n_pass)
	{
		this.origination = origination;
		this.destination = destination;
		this.expectedArrival = expectedArrival;
		this.n_pass = n_pass;
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

	public int getn_pass() {
		return n_pass;
	}

	public void setn_pass(int n_pass) {
		this.n_pass = n_pass;
	}

	public int getOrigination() {
		return origination;
	}

	public void setOrigination(int origination) {
		this.origination = origination;
	}
}
