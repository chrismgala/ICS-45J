// SimClock.java
// 

// Chris Gala 64338761
// Wai Phyo 60902242

public class SimClock
{
	private static int simulatedTime = 0;
	
	public SimClock()
	{
		SimClock.simulatedTime = 0;
	}
	
	public static void tick()
	{
		SimClock.simulatedTime++;
	}
	
	public static int getTime()
	{
		return SimClock.simulatedTime;
	}
}
