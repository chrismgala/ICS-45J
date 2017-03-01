// MovieManager.java
// The class representing the highest layer of the Movie Manager program.
// This class contains the run method, which runs in a loop until the user quits.
// Other methods included help add / remove Movies from the inventory, and add / remove Renters for each Movie.

// Chris Gala 64338761
// Wai Phyo 60902242

public class MovieManager
{
	private Movie[] movies;
	private int totalMovies;
	
	// Constructs a Movie Manager object, with an inventory of fixed size 20 and total movie count set to 0.
	public MovieManager() 
	{
		movies = new Movie[20];
		totalMovies = 0;
	}
	
	// The run method prints the menu, gets a command from the user and executes the
	// proper corresponding function. If the user enters an invalid command, the menu is reprinted.
	public void run() 
	{
		while (true)
		{
			MovieManagerUI.printMenu();
			String curr_command = MovieManagerUI.getCommand();
			
			if (curr_command.equals("am"))
			{
				String movieCode = MovieManagerUI.getMovieCode();
				String movieName = MovieManagerUI.getMovieName();
				Movie newMovie = new Movie(movieCode, movieName);
				
				addMovie(newMovie);
			}
			
			else if (curr_command.equals("dm"))
			{
				String movieCode = MovieManagerUI.getMovieCode();
				discontinueMovie(movieCode);
			}
			
			else if (curr_command.equals("rm"))
			{
				String movieCode = MovieManagerUI.getMovieCode();
				
				int renterId = MovieManagerUI.getRenterId();
				String firstName = MovieManagerUI.getRenterFirstName();
				String lastName = MovieManagerUI.getRenterLastName();
				
				Renter newRenter = new Renter(renterId, firstName, lastName);
				
				rentMovie(movieCode, newRenter);
			}
			
			else if (curr_command.equals("rr"))
			{
				int renterId = MovieManagerUI.getRenterId();
				String movieCode = MovieManagerUI.getMovieCode();
				
				returnRental(renterId, movieCode);
			}
			
			else if (curr_command.equals("p"))
			{
				printInventory();
			}
			
			else if (curr_command.equals("q")){
				break;
			}
		}
	}

	// This method takes a new Movie m and adds it to the inventory. If the code or name is empty,
	// the inventory is already full, or the movie already exists, then an exception is thrown and handled
	// in the catch block of the try / catch statement.
	public void addMovie(Movie m)
	{
		try 
		{
			if (m.getMovieCode().equals("") || m.getMovieName().equals(""))
			{
				throw new myexceptions.EmptyMovieInfoException();
			}
			
			if (totalMovies == 20)
			{
				throw new myexceptions.MovieLimitException();
			}
			
			if (totalMovies > 0)
			{
				String movieCode = m.getMovieCode();
				
				for (int i=0; i < this.totalMovies; i++)
				{
					if (movies[i].getMovieCode().equals(movieCode))
					{
						throw new myexceptions.DuplicateMovieException();
					}
				}
			}
			
			movies[totalMovies] = m;
			this.totalMovies++;
		}
		
		catch (myexceptions.EmptyMovieInfoException e)
		{
			System.out.println(e + ": Could not add movie. Movie info was blank.\n");
		}
		
		catch (myexceptions.MovieLimitException e)
		{
			System.out.println(e + ": Could not add movie. Maximum number of movies in inventory reached.\n");
		}
		
		catch (myexceptions.DuplicateMovieException e)
		{
			System.out.println(e + ": Could not add movie. Movie is already in inventory.\n");
		}
		
	}
	
	// This method removes a Movie with code movieCode from the inventory. If there are no movies at all,
	// a movie isn't in the inventory, or a movie is currently being rented, an exception is thrown and handled
	// in the catch block of the try / catch statement.
	public void discontinueMovie(String movieCode)
	{
		try 
		{
			if (totalMovies == 0)
			{
				throw new myexceptions.EmptyMovieListException();
			}
			
			
			if (!movieExists(movieCode))
			{
				throw new myexceptions.MovieNotFoundException(); 
			}
			
			Movie[] tempMovies = new Movie[20];
			int i = 0;
			
			for (int j = 0; j < this.totalMovies; j++)
			{
				Movie m = movies[j];
				if (!m.getMovieCode().equals(movieCode))
				{
					tempMovies[i] = m;
					i++;
				}
				
				else
				{
					if (m.getRentedMovieCopies() != 0)
					{
						throw new myexceptions.RentedMovieException();
					}
				}
			}
			
			movies = tempMovies;
			this.totalMovies--;
		}
		
		catch (myexceptions.EmptyMovieListException e)
		{
			System.out.println(e + ": Could not discontinue movie. Movie inventory is empty.\n");
		}
		
		catch (myexceptions.MovieNotFoundException e)
		{
			System.out.println(e + ": Could not discontinue movie. That movie does not exist.\n");
		}
		
		catch (myexceptions.RentedMovieException e)
		{
			System.out.println(e + ": Could not discontinue movie. This movie is already being rented out.\n");
		}
	}
	
	// This method rents out a Movie with code movieCode for Renter r. If the renter has a blank first or last name,
	// there are no movies in the inventory, or the movie does not exist, then an exception is thrown and handled
	// in the catch block of the try / catch statement.
	public void rentMovie(String movieCode, Renter r)
	{
		try 
		{
			if (r.getFirstName().equals("") || r.getLastName().equals(""))
			{
				throw new myexceptions.EmptyRenterNameException();
			}
			
			if (totalMovies == 0)
			{
				throw new myexceptions.EmptyMovieListException();
			}
			
			
			if (!movieExists(movieCode))
			{
				throw new myexceptions.MovieNotFoundException(); 
			}
			
			
			int mi = getMovieIndex(movieCode);
			movies[mi].rentMovie(r);
		}
		
		catch (myexceptions.EmptyRenterNameException e)
		{
			System.out.println(e + ": Could not rent movie. Renter name (first/last) is empty.\n");
		}
		
		catch (myexceptions.EmptyMovieListException e)
		{
			System.out.println(e + ": Could not rent movie. Movie inventory is empty.\n");
		}
		
		catch (myexceptions.MovieNotFoundException e)
		{
			System.out.println(e + ": Could not rent movie. That movie does not exist.\n");
		}
	}
	
	// This method returns a Movie with code movieCode from a Renter with id renterId. If there are no movies
	// in the inventory, the movie does not exist, the renter does not exist, or there are no renters at all
	// then an exception is thrown and handled in the catch block of the try / catch statement.
	public void returnRental(int renterId, String movieCode)
	{
		try 
		{
			if (totalMovies == 0)
			{
				throw new myexceptions.EmptyMovieListException();
			}
			
			
			if (!movieExists(movieCode))
			{
				throw new myexceptions.MovieNotFoundException(); 
			}
			
			int mi = getMovieIndex(movieCode);
			
			if (movies[mi].getRentedMovieCopies() == 0)
			{
				throw new myexceptions.EmptyRenterListException();
			}
			
			if (!renterExists(movies[mi], renterId))
			{
				throw new myexceptions.RenterNotFoundException();
			}
			
			movies[mi].returnRental(renterId);
		}
		catch (myexceptions.EmptyMovieListException e)
		{
			System.out.println(e + ": Could not return rental. Movie inventory is empty.\n");
		}
		
		catch (myexceptions.MovieNotFoundException e)
		{
			System.out.println(e + ": Could not return rental. That movie does not exist.\n");
		}
		
		catch (myexceptions.RenterNotFoundException e)
		{
			System.out.println(e + ": Could not return rental. Renter does not exist.\n");
		}
		
		catch (myexceptions.EmptyRenterListException e)
		{
			System.out.println(e + ": Could not return rental. No are being rented.\n");
		}
	}
	
	// This method prints out the inventory of movies and all the renters for each movie.
	// If there are no movies, it throws an exception. If there are no renters for a movie, it says so as well.
	public void printInventory()
	{
		try 
		{
			if (totalMovies == 0)
			{
				throw new myexceptions.EmptyMovieListException();
			}
			
			for (int i = 0; i < this.totalMovies; i++)
			{
				Movie m = movies[i];
				System.out.println("=================");
				System.out.println("Code: " + m.getMovieCode());
				System.out.println("Name: " + m.getMovieName());
				System.out.println("Copies rented: " + Integer.toString(m.getRentedMovieCopies()));
				System.out.println("Renters:");
				System.out.println("-----------------");
				if (m.getRentedMovieCopies() > 0) 
				{
					Renter[] renters = m.getRenters();
					
					for (int j = 0; j < m.getRentedMovieCopies(); j++)
					{
						Renter r = renters[j];
						System.out.println("ID: " + Integer.toString(r.getRenterId()));
						System.out.println("First Name: " + r.getFirstName());
						System.out.println("Last Name: " + r.getLastName());
					}
				}
				
				else 
				{
					System.out.println("No renters for this movie!");
				}
				
				System.out.println("-----------------");
				System.out.println("=================");
			}
		}
		
		catch (myexceptions.EmptyMovieListException e)
		{
			System.out.println("Movie inventory is empty.\n");
		}
	}
	
	// Gets the array of Movies.
	public Movie[] getMovies()
	{
		return this.movies;
	}

	// Sets the array of Movies to 'movies'.
	public void setMovies(Movie[] movies)
	{
		this.movies = movies;
	}
	
	// Gets the number of movies in the inventory.
	public int getTotalMovies()
	{
		return this.totalMovies;
	}

	// Increments the number of movies in the inventory by 1.
	public void updateTotalMovies()
	{
		this.totalMovies++;
	}
	
	// Given a code, returns if a Movie with that code exists in the inventory.
	public Boolean movieExists(String movie_code)
	{
		for (int i=0; i < this.totalMovies; i++)
		{
			if (movies[i].getMovieCode().equals(movie_code))
			{
				return true;
			}
		}
		return false;
	}
	
	// Given a code, finds the index of the Movie with that code.
	// If it does not exist, then it returns -1.
	public int getMovieIndex(String movie_code)
	{
		for (int i = 0; i < this.totalMovies; i++)
		{
			if (movies[i].getMovieCode().equals(movie_code))
			{
				return i;
			}
		}
		return -1;
	}
	
	// Given a Movie m, and a renter id, returns if a Renter with that renter id exists for Movie m.
	public Boolean renterExists(Movie m, int renterId)
	{
		Renter[] renters = m.getRenters();
		for (int i = 0; i < m.getRentedMovieCopies(); i++)
		{
			if (renters[i].getRenterId() == renterId)
			{
				return true;
			}
		}
		
		return false;
	}
}
