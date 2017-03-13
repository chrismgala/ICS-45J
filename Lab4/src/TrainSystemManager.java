// TrainSystemManager.java
// 

// Chris Gala 64338761
// Wai Phyo 60902242

public class TrainSystemManager
{
	public TrainStation[] trainStations;
	
	public TrainSystemManager()
	{
		this.setTrainStations(new TrainStation[5]);
		
		for (int i = 0; i < 5; i++)
		{
			this.trainStations[i] = new TrainStation();
		}
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
