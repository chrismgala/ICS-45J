// TrainSystemManager.java
// 

// Chris Gala 64338761
// Wai Phyo 60902242

public class TrainSystemManager
{
	private TrainStation[] trainStations;
	
	public TrainSystemManager()
	{
		this.setTrainStations(new TrainStation[5]);
	}

	public TrainStation[] getTrainStations()
	{
		return trainStations;
	}

	public void setTrainStations(TrainStation[] trainStations)
	{
		this.trainStations = trainStations;
	}
}
