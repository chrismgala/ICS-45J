// CrapsSimulation.java
// Contains all the variables and methods for a new simulation.
// Keeps track of simulation stats like win & lose streaks, balances, and the ongoing bet.
// Each time a new CrapsSimulation is started (through start()), user input is collected.
// A new CrapsMetricsMonitor is also created to maintain and update stats throughout each simulation.

// Chris Gala 64338761
// Wai Phyo 60902242

import java.util.Scanner;

public class CrapsSimulation
{
	// Private variables
	private static CrapsGame cg;
	private static CrapsMetricsMonitor cmm;
	private static String user_name;
	private static int balance;
	private static int bet;
	private static int win_streak;
	private static int lose_streak;
	
	
	// Constructor
	public CrapsSimulation() 
	{
		cmm = new CrapsMetricsMonitor();
		cg = new CrapsGame(cmm);
		user_name = "";
		balance = 0;
		bet = 0;
		win_streak = 0;
		lose_streak = 0;
	}
	
	// Getters / Setters
	public void set_CrapsGame() 
	{
		
	}
	
	public CrapsGame get_CrapsGame() 
	{
		return cg;
	}
	
	public void set_CrapsMetricsMonitor() 
	{
		
	}
	
	public CrapsMetricsMonitor get_CrapsMetricsMonitor() 
	{
		return cmm;
	}
	
	public void set_UserName(String new_name) 
	{
		user_name = new_name;
	}
	
	
	public String get_UserName() 
	{
		return user_name;
	}
	
	public void set_balance(int new_balance) 
	{
		balance = new_balance;
	}
	
	public int get_balance() 
	{
		return balance;
	}
	
	public void set_bet(int new_bet) 
	{
		bet = new_bet;
	}
	
	public int get_bet() 
	{
		return bet;
	}
	
	public void set_winstreak(int new_ws) 
	{
		win_streak = new_ws;
	}
	
	public int get_winstreak() 
	{
		return win_streak;
	}
	
	public void set_losestreak(int new_ls) 
	{
		lose_streak = new_ls;
	}
	
	public int get_losestreak() 
	{
		return lose_streak;
	}
	
	// Start method
	public void start() 
	{
		// Get user input
		Scanner reader = new Scanner(System.in);
		System.out.println("Welcome to SimCraps! Enter your user name: ");
		set_UserName(reader.nextLine());
		System.out.println("Hello " + get_UserName() + "!");
		System.out.println("Enter the amount of money you will bring to the table: ");
		set_balance(reader.nextInt());
		
		win_streak = 0;
		lose_streak = 0;
		int game_num = 1;
		
		// Start a simulation
		while(get_balance() != 0) 
		{
			if (get_bet() == 0) 
			{
				System.out.println("Enter the bet amount between $1 and $" + Integer.toString(get_balance()) + ": ");
				set_bet(reader.nextInt());
			}
			
			int b = get_bet();
			
			
			int current_balance = get_balance();
			
			if (current_balance < b && game_num > 1)
			{
				b = current_balance;
			}
		
			
			if (b >= 1 && b <= current_balance) 
			{
				System.out.println(get_UserName() + " bets $" + b);
				boolean result = cg.playGame();
				cmm.updateGamesPlayed();
				
				
				if (result == true) 
				{
					set_balance(current_balance + b);
					cmm.updateGamesWon();
					
					cmm.updateMaxLoseStreak(lose_streak);
					lose_streak = 0;
					win_streak++;
					cmm.updateMaxWinStreak(win_streak);
				}
				else 
				{
					set_balance(current_balance - b);
					cmm.updateGamesLost();
					
					cmm.updateMaxWinStreak(win_streak);
					win_streak = 0;
					lose_streak++;
					cmm.updateMaxLoseStreak(lose_streak);
				}
				
				
				if (get_balance() != 0)
				{
					System.out.println(get_UserName() + "'s balance: $" + get_balance() + ".Playing a new game...");
				}
				
				else
				{
					System.out.println(get_UserName() + "'s balance: $" + get_balance());
					
					cmm.printStatistics();
					
					Scanner replayReader = new Scanner(System.in);
					System.out.println("Replay? Enter 'y' or 'n': ");
					String answer = replayReader.nextLine();
					if (answer.equals("y") || answer.equals("Y"))
					{
						set_bet(0);
						cmm.reset();
						start();
					}
					
					replayReader.close();
				}
				
				game_num = game_num + 1;
				cmm.updateMaxBalanceAndGame(get_balance(), game_num);
			}
			
			else 
			{
				System.out.println("Invalid bet! Please enter a bet between $1 and $" + Integer.toString(get_balance()) + ": ");
				set_bet(reader.nextInt());
			}
		}
		
		reader.close();
		
	}
}
