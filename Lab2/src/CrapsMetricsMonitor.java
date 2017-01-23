
public class CrapsMetricsMonitor
{
	private static int games_played;
	private static int games_won;
	private static int games_lost;
	private static int max_rolls;
	private static int natural_count;
	private static int craps_count;
	private static int max_win_streak;
	private static int max_lose_streak;
	private static int max_balance;
	private static int game_num_of_max_balance;
	
	public CrapsMetricsMonitor() {
		games_played = 0;
		games_won = 0;
		games_lost = 0;
		max_rolls = 0;
		natural_count = 0;
		craps_count = 0;
		max_win_streak = 0;
		max_lose_streak = 0;
		max_balance = 0;
		game_num_of_max_balance = 0;
	}
	
	public void updateGamesPlayed() {
		games_played++;
	}
	
	public int getGamesPlayed() {
		return games_played;
	}
	
	public void updateGamesWon() {
		games_won++;
	}
	
	public int getGamesWon() {
		return games_won;
	}
	
	public void updateGamesLost() {
		games_lost++;
	}
	
	public int getGamesLost() {
		return games_lost;
	}
	
	public void updateMaxRolls(int rolls) {
		if (rolls > max_rolls) {
			max_rolls = rolls;
		}
	}
	
	public int getMaxRolls() {
		return max_rolls;
	}
	
	public void updateNaturalCount() {
		natural_count++;
	}
	
	public int getNaturalCount() {
		return natural_count;
	}
	
	public void updateCrapsCount() {
		craps_count++;
	}
	
	public int getCrapsCount() {
		return craps_count;
	}
	
	public void updateMaxWinStreak(int win_streak) {
		if (win_streak > max_win_streak) {
			max_win_streak = win_streak;
		}
	}
	
	public int getMaxWinStreak() {
		return max_win_streak;
	}
	
	public void updateMaxLoseStreak(int lose_streak) {
		if (lose_streak > max_lose_streak) {
			max_lose_streak = lose_streak;
		}
	}
	
	public int getMaxLoseStreak() {
		return max_lose_streak;
	}
	
	public void updateMaxBalanceAndGame(int balance, int game_num) {
		if (balance > max_balance) {
			max_balance = balance;
			game_num_of_max_balance = game_num;
		}
	}
	
	public int getMaxBalance() {
		return max_balance;
	}
	
	public int getMaxBalanceGameNum() {
		return game_num_of_max_balance;
	}
}
