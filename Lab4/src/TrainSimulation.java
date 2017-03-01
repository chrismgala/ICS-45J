// TrainSimulation.java
// 

// Chris Gala 64338761
// Wai Phyo 60902242

import java.util.*;

public class TrainSimulation
{
	private int totalSimulationTime;
	private int simulatedSecondRate;
	
	private TrainSystemManager tsm;
	
	ArrayList< ArrayList<PassengerArrival> > moveQueue;
	
	public TrainSimulation()
	{
		this.tsm = new TrainSystemManager();
	}
	
	public void start()
	{
		
	}
	
	public void printTrainState()
	{
		
	}
	
	public void parseTrainConfig()
	{
		
	}

	public int getTotalSimulationTime()
	{
		return totalSimulationTime;
	}

	public void setTotalSimulationTime(int totalSimulationTime)
	{
		this.totalSimulationTime = totalSimulationTime;
	}

	public int getSimulatedSecondRate()
	{
		return simulatedSecondRate;
	}

	public void setSimulatedSecondRate(int simulatedSecondRate)
	{
		this.simulatedSecondRate = simulatedSecondRate;
	}
}
