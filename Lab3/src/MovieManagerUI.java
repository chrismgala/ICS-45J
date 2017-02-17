// MovieManagerUI.java


// Chris Gala 64338761
// Wai Phyo 60902242

import java.util.Scanner;

public class MovieManagerUI
{
	// custom exceptions
	
	public static void printMenu()
	{
		System.out.println("Welcome to Movie Manager!");
		System.out.println("Select an action based on the following menu:");
		System.out.println("----------");
		System.out.println("am: Add Movie");
		System.out.println("dm: Discontinue Movie");
		System.out.println("rm: Rent Movie");
		System.out.println("rr: Return Rental");
		System.out.println("p: Print Movie Inventory");
		System.out.println("q: Quit Program");
		System.out.println("----------");
		System.out.println("Enter Command:");
	}
	
	public static String getCommand(){
		Scanner reader = new Scanner(System.in);
		while (true) {
			String command = reader.nextLine().toLowerCase();
			if (command.equals("am") || command.equals("dm") || command.equals("rm") || command.equals("rr") || command.equals("p") || command.equals("q")) {
				//reader.close();
				return command;
			}
			else {
				System.out.println("Invalid command. Please try again.");
			}
		}
	}
	
	public static String getMovieInfo() {
		Scanner reader = new Scanner(System.in);
		System.out.println("Please provide movie info (code/name): ");
		while (true){
			try{
				String movie_info = reader.nextLine();
				if (movie_info.equals("")){
					throw new myexceptions.EmptyMovieInfoException();
				}
				else {
					//reader.close();
					return movie_info;
				}
			} catch (myexceptions.EmptyMovieInfoException e){
				System.out.println("Empty movie info... please try again.");
			}
		}		
	}
	
	public static String getRenterInfo() {
		Scanner reader = new Scanner(System.in);
		System.out.println("Please provide renter info (first and last): ");
		while (true){
			try{
				String renter_info = reader.nextLine();
				if (renter_info.equals("")){
					throw new myexceptions.EmptyRenterNameException();
				}
				else {
					//reader.close();
					return renter_info;
				}
			} catch (myexceptions.EmptyRenterNameException e){
				System.out.println("Empty renter info... please try again.");
			}
		}
	}
	
	public static int getRenterId() {
		Scanner reader = new Scanner(System.in);
		System.out.println("Please provide renter ID: ");
		while (true){
			try{
				int renter_id = reader.nextInt();
				if (renter_id <= 0){
					throw new myexceptions.InvalidRenterIDException();
				}
				else {
					//reader.close();
					return renter_id;
				}
			} catch (myexceptions.InvalidRenterIDException e){
				System.out.println("Invalid renter ID... please try again.");
			}
		}
	}
}
