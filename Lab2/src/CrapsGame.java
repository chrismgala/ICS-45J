import java.util.*;

public class CrapsGame
{
	private static Map<Integer, Integer> num_of_rolls;
	private static CrapsMetricsMonitor cm;
	
	// constructor
	public CrapsGame(CrapsMetricsMonitor monitor) {
		num_of_rolls = new HashMap<Integer, Integer>();
		for (int i = 2; i <= 12; i++) {
			num_of_rolls.put(i, 0);
		}
		cm = monitor;
	}
	
	// play the actual game
	public boolean playGame() {
		boolean first_roll = true;
		int roll = 0;
		int point = 0;
		Random rand = new Random();
		while(true) {
			roll = rand.nextInt(11) + 2;
			num_of_rolls.put(roll, num_of_rolls.get(roll) + 1);
			if (first_roll == true){
				if (roll == 7 || roll == 11) {
					return true; // break out of loop 
				} 
				else if (roll == 2 || roll == 3 || roll == 12) {
					return false; 
				}
				else {
					first_roll = false;
					point = roll;
				}
			} else {
				if (roll == 7) {
					return false;
				}
				else if (roll == point) {
					return true;
				}
			}
		}
	}
}
