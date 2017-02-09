// CrapsGame.java
// This is where a game is played. Only one stat is kept track of from game to game, which is the number of rolls.
// The logic for a passline bet version of Craps is here. The main method (playGame()) returns whether or not the player
// won the game. It is continuously called as long as the user wants to keep playing.

// Chris Gala 64338761
// Wai Phyo 60902242

import java.util.*;

public class CrapsGame
{
	private static int num_of_rolls;
	private static CrapsMetricsMonitor cmm;
	
	// Constructor
	public CrapsGame(CrapsMetricsMonitor monitor) 
	{
		cmm = monitor;
	}
	
	// Play a single game
	public boolean playGame() {
		boolean first_roll = true;
		int roll = 0;
		int point = 0;
		num_of_rolls = 0;
		// Statistics to keep track of
		
		Random rand = new Random();
		
		while (true) 
		{
			roll = rand.nextInt(11) + 2;
			System.out.println("Rolled a " + Integer.toString(roll));
			num_of_rolls++;
			cmm.updateMaxRolls(num_of_rolls);
			
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