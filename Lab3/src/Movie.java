// Movie.java
// This class represents the Movie entity. The constructor makes a new renter using the movieCode, and movieName.
// It also includes all the necessary Getters / Setters.

// Chris Gala 64338761
// Wai Phyo 60902242

public class Movie
{
	private String movieCode;
	private String movieName;
	private int rentedMovieCopies;
	private Renter[] renters;
	
	// Constructs a new Movie given a code and name. Rented movie copies is set to 0 and the array of
	// Renters is set to a fixed size of 10.
	public Movie(String code, String name) 
	{
		setMovieCode(code);
		setMovieName(name);
		setRentedMovieCopies(0);
		setRenters(new Renter[10]);
	}
	
	// This method rents a copy of 'this' movie for Renter newRenter. If all copies are already rented or 
	// the renter is already renting the movie, then an exception is thrown and it is handled in the catch
	// block of the try / catch statement. Renters are also ordered lexicographically (if there is a tie,
	// order by the size of the id).
	public void rentMovie(Renter newRenter)
	{
		try 
		{
			// Check if all copies are already rented
			if (this.rentedMovieCopies == 10)
			{
				throw new myexceptions.RenterLimitException();
			}

			// Make sure renter is not already renting this movie
			if (this.rentedMovieCopies > 0)
			{
				for (int i = 0; i < rentedMovieCopies; i++)
				{
					if (renters[i].getRenterId() == newRenter.getRenterId())
					{
						throw new myexceptions.DuplicateRenterException();
					}
				}
			}
			// If everything checks out, add the renter to the list and increment the copies rented
			int index = -1;
			for (int i = 0; i < rentedMovieCopies; i++)
			{
				int compare = renters[i].getLastName().compareTo(newRenter.getLastName());
				if (compare > 0)
				{
					index = i;
				}
				
				else if (compare == 0)
				{
					if (newRenter.getRenterId() < renters[i].getRenterId())
					{
						index = i;
					}
					else
					{
						index = i + 1;
					}
				}
			}
			
			
			if (index == -1) 
			{
				renters[rentedMovieCopies] = newRenter;
			} 
			
			else 
			{
				// shift all renters
				for (int i = index; i < this.rentedMovieCopies; i++)
				{
					renters[i+1] = renters[i];
				}
				
				renters[index] = newRenter;
			}
			this.rentedMovieCopies++;
		}
		
		catch (myexceptions.DuplicateRenterException e)
		{
			System.out.println(e + ": Could not rent movie. Renter already exists.\n");
		}
		
		catch (myexceptions.RenterLimitException e)
		{
			System.out.println(e + ": Could not rent movie. Renter limit reached.\n");
		}
	}
	
	// This method returns a copy of 'this' movie for Renter with the given renter id. If no copies are rented
	// in the first place or the renter does not already exist in the array of Renters, then an exception is thrown
	// and handled in the catch block of the try / catch statement.
	public void returnRental(int renterId)
	{
		try
		{
			if (this.rentedMovieCopies == 0)
			{
				throw new myexceptions.EmptyRenterListException();
			}
			
			Renter[] temp_renters = new Renter[10];
			int i = 0;
			
			for (int j = 0; j < this.getRentedMovieCopies(); j++)
			{
				Renter r = renters[j];
				if (r.getRenterId() != (renterId))
				{
					temp_renters[i] = r;
					i++;
				}
			}
			
			if (i == this.rentedMovieCopies)
			{
				throw new myexceptions.RenterNotFoundException();
			}
			
			renters = temp_renters;
			this.rentedMovieCopies--;
		}
		
		catch (myexceptions.EmptyRenterListException e)
		{
			System.out.println("Could not return rental. All copies available.");
		}
		
		catch (myexceptions.RenterNotFoundException e)
		{
			System.out.println("Could not return rental. Renter does not exist.");
		}
		
	}

	// Gets the Movie's name.
	public String getMovieName()
	{
		return movieName;
	}

	// Sets the Movie's name to movieName.
	public void setMovieName(String movieName)
	{
		this.movieName = movieName;
	}

	// Gets the Movie's code.
	public String getMovieCode()
	{
		return movieCode;
	}

	// Sets the Movie's code to movieCode.
	public void setMovieCode(String movieCode)
	{
		this.movieCode = movieCode;
	}

	// Gets the array of 'this' Movie's Renters.
	public Renter[] getRenters()
	{
		return this.renters;
	}

	// Set the array of 'this' Movie's Renters to renters.
	public void setRenters(Renter[] renters)
	{
		this.renters = renters;
	}

	// Get the number of copies being rented currently.
	public int getRentedMovieCopies()
	{
		return rentedMovieCopies;
	}

	// Set the number of copies being rented currently to rentedMovieCopies.
	public void setRentedMovieCopies(int rentedMovieCopies)
	{
		this.rentedMovieCopies = rentedMovieCopies;
	}
	
}
