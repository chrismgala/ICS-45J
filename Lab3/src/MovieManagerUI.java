// MovieManagerUI.java
// This class represents the UI / command-line interface for the program.
// For example, the menu is printed through printMenu and commands are received through getCommand.
// Some custom methods are written for getting the Movie Code, Movie Name, Renter ID, Renter First Name, and Renter Last Name.

// Chris Gala 64338761
// Wai Phyo 60902242

import java.util.Scanner;

public class MovieManagerUI
{
	
	// This method prints out the Movie Manager menu and all of the available commands for the user.
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
	
	// This method gets input from the user as to which command they want to execute. If the user inputs
	// an invalid command, the menu is reprinted.
	public static String getCommand()
	{
		Scanner reader = new Scanner(System.in);
		while (true) 
		{
			String command = reader.nextLine().toLowerCase();
			if (command.equals("am") || command.equals("dm") || command.equals("rm") || command.equals("rr") || command.equals("p") || command.equals("q")) 
			{
				//reader.close();
				return command;
			}
			
			else 
			{
				System.out.println("Invalid command. Please try again.");
			}
		}
	}
	
	// This method prompts the user for and returns the Movie code.
	// If the user enters a blank String, throw an exception and handle it in the catch block of the
	// try / catch statement.
	public static String getMovieCode() 
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("Please provide movie code: ");
		while (true)
		{
			try
			{
				String movieCode = reader.nextLine();
				if (movieCode.equals(""))
				{
					throw new myexceptions.EmptyMovieInfoException();
				}
				
				else 
				{
					//reader.close();
					return movieCode;
				}
			} 
			
			catch (myexceptions.EmptyMovieInfoException e)
			{
				System.out.println(e + ": Empty movie code... please try again.");
			}
		}		
	}
	
	// This method prompts the user for and returns the Movie name.
	// If the user enters a blank String, throw an exception and handle it in the catch block of the
	// try / catch statement.
	public static String getMovieName() 
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("Please provide movie name: ");
		while (true)
		{
			try
			{
				String movieName = reader.nextLine();
				if (movieName.equals(""))
				{
					throw new myexceptions.EmptyMovieInfoException();
				}
				
				else 
				{
					//reader.close();
					return movieName;
				}
			} 
			
			catch (myexceptions.EmptyMovieInfoException e)
			{
				System.out.println(e + ": Empty movie name... please try again.");
			}
		}		
	}
	
	// This method prompts the user for and returns the Renter's first name.
	// If the user enters a blank String, throw an exception and handle it in the catch block of the
	// try / catch statement.
	public static String getRenterFirstName() 
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("Please provide renter first name: ");
		while (true)
		{
			try
			{
				String renterFirstName = reader.nextLine();
				if (renterFirstName.equals(""))
				{
					throw new myexceptions.EmptyRenterNameException();
				}
				
				else 
				{
					//reader.close();
					return renterFirstName;
				}
			} 
			
			catch (myexceptions.EmptyRenterNameException e)
			{
				System.out.println(e + ": Empty renter first name... please try again.");
			}
		}
	}
	
	// This method prompts the user for and returns the Renter's last name.
	// If the user enters a blank String, throw an exception and handle it in the catch block of the
	// try / catch statement.
	public static String getRenterLastName() 
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("Please provide renter last name: ");
		while (true)
		{
			try
			{
				String renterLastName = reader.nextLine();
				if (renterLastName.equals(""))
				{
					throw new myexceptions.EmptyRenterNameException();
				}
				
				else 
				{
					//reader.close();
					return renterLastName;
				}
			} 
			
			catch (myexceptions.EmptyRenterNameException e)
			{
				System.out.println(e + ": Empty renter first name... please try again.");
			}
		}
	}
	
	// This method prompts the user for and returns the Renter's id.
	// If the user enters a blank String, throw an exception and handle it in the catch block of the
	// try / catch statement.
	public static int getRenterId() 
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("Please provide renter id: ");
		while (true)
		{
			try
			{
				int renterId = reader.nextInt();
				if (renterId <= 0)
				{
					throw new myexceptions.InvalidRenterIDException();
				}
				
				else 
				{
					//reader.close();
					return renterId;
				}
			} 
			
			catch (myexceptions.InvalidRenterIDException e)
			{
				System.out.println(e + ": Invalid renter ID... please try again.");
			}
		}
	}
}
