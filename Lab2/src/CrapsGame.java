// CrapsGame.java
// Chris Gala 64338761
// Wai Phyo

import java.util.*;

public class CrapsGame
{
	private static Map<Integer, Integer> num_of_rolls;
	private static CrapsMetricsMonitor cmm;
	
	// Constructor
	public CrapsGame(CrapsMetricsMonitor monitor) 
	{
		num_of_rolls = new HashMap<Integer, Integer>();
		
		for (int i = 2; i <= 12; i++) 
		{
			num_of_rolls.put(i, 0);
		}
		
		cmm = monitor;
	}
	
	// Play a single game
	public boolean playGame() {
		boolean first_roll = true;
		int roll = 0;
		int point = 0;
		
		// Statistics to keep track of
		int rolls = 0;
		
		Random rand = new Random();
		
		while (true) 
		{
			roll = rand.nextInt(11) + 2;
			System.out.println("Rolled a " + Integer.toString(roll));
			rolls++;
			
			cmm.updateMaxRolls(rolls);
			num_of_rolls.put(roll, num_of_rolls.get(roll) + 1);
			
			if (first_roll == true)
			{
				if (roll == 7 || roll == 11) 
				{
					cmm.updateNaturalCount();
					System.out.println("*****Natural! You win!*****");
					return true; // break out of loop 
				}
				
				else if (roll == 2 || roll == 3 || roll == 12) 
				{
					cmm.updateCrapsCount();
					System.out.println("*****Craps! You loose.*****");
					return false; 
				}
				
				else 
				{
					first_roll = false;
					point = roll;
				}
				
			} 
			
			else 
			{
				if (roll == 7) 
				{
					System.out.println("*****Crap out! You loose.*****");
					return false;
				}
				else if (roll == point) 
				{
					System.out.println("*****Rolled the point! You win!*****");
					return true;
				}
			}
		}
	}
}