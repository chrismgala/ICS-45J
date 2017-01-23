import java.util.Scanner;

// Shooter.java
// Chris Gala 64338761
// Wai Phyo

public class CrapsSimulation
{
	// private variables
	private static CrapsGame c_game;
	private static CrapsMetricsMonitor c_monitor;
	private static String user_name;
	private static int balance;
	private static int bet;
	private static int win_streak;
	private static int lose_streak;
	
	
	// constructors
	public CrapsSimulation() {
		c_monitor = new CrapsMetricsMonitor();
		c_game = new CrapsGame(c_monitor);
		user_name = "";
		balance = 0;
		bet = 0;
		win_streak = 0;
		lose_streak = 0;
	}
	
	// setters and getters
	public void set_CrapsGame() {
		
	}
	
	public CrapsGame get_CrapsGame() {
		return c_game;
	}
	
	public void set_CrapsMetricsMonitor() {
		
	}
	
	public CrapsMetricsMonitor get_CrapsMetricsMonitor() {
		return c_monitor;
	}
	
	public void set_UserName(String new_name) {
		user_name = new_name;
	}
	
	
	public String get_UserName() {
		return user_name;
	}
	
	public void set_balance(int new_balance) {
		balance = new_balance;
	}
	
	public int get_balance() {
		return balance;
	}
	
	public void set_bet(int new_bet) {
		bet = new_bet;
	}
	
	public int get_bet() {
		return bet;
	}
	
	public void set_winstreak(int new_ws) {
		win_streak = new_ws;
	}
	
	public int get_winstreak() {
		return win_streak;
	}
	
	public void set_losestreak(int new_ls) {
		lose_streak = new_ls;
	}
	
	public int get_losestreak() {
		return lose_streak;
	}
	
	// simulation start method
	public void start() {
		// get user input
		Scanner reader = new Scanner(System.in);
		System.out.println("Welcome to SimCraps! Enter your user name: ");
		set_UserName(reader.nextLine());
		System.out.println("Hello " + get_UserName() + "!");
		System.out.println("Enter the amount of money you will bring to the table: ");
		set_balance(reader.nextInt());
		System.out.println("Enter the bet amount between $1 and $" + Integer.toString(get_balance()) + ": ");
		
		// start simulation
		while(get_balance() != 0) {
			set_bet(reader.nextInt());
			int b = get_bet();
			int current_balance = get_balance();
			if (b >= 1 && b <= current_balance) {
				System.out.println(get_UserName() + " bets $" + b);
				boolean result = c_game.playGame();
				if (result == true) {
					set_balance(current_balance + b);
				}
				else {
					set_balance(current_balance - b);
				}
				
				System.out.println(get_UserName() + "'s balance: " + get_balance());
			}
			else {
				System.out.println("Invalid bet! Please enter a bet between $1 and $" + Integer.toString(get_balance()) + ": ");
			}
			
			if (get_balance() != 0) {
				System.out.println("Playing a new game...");
			}
		}
		
		reader.close();
	}
}
