// Movie.java


// Chris Gala 64338761
// Wai Phyo 60902242

public class Movie
{
	private String movieCode;
	private String movieName;
	private int rentedMovieCopies;
	private Renter[] renters;
	
	public Movie(String code, String name) 
	{
		setMovieCode(code);
		setMovieName(name);
		setRentedMovieCopies(0);
		setRenters(new Renter[10]);
	}
	
	public void rentMovie(Renter new_renter)
	{
		try 
		{
			// Check if all copies are already rented
			if (this.rentedMovieCopies == 10)
			{
				throw new myexceptions.RenterLimitException();
			}

			// Make sure renter is not already renting this movie
			if (this.rentedMovieCopies > 0){
				for (Renter r : renters){
					if (r.getRenterId() == new_renter.getRenterId())
					{
						throw new myexceptions.DuplicateRenterException();
					}
				}
			}
			// If everything checks out, add the renter to the list and increment the copies rented
			int index = -1;
			for (int i = 0; i < rentedMovieCopies; i++){
				int compare = renters[i].getLastName().compareTo(new_renter.getLastName());
				if (compare < 0){
					index = i;
				}
				else if (compare == 0){
					if (new_renter.getRenterId() < renters[i].getRenterId())
					{
						index = i;
					}
					else
					{
						index = i + 1;
					}
				}
			}
			if (index == -1) {
				renters[rentedMovieCopies] = new_renter;
			} else {
				// shift all renters
				for (int i = index; index < rentedMovieCopies; i++){
					renters[i+1] = renters[i];
				}
				renters[index] = new_renter;
			}
			rentedMovieCopies++;
		}
		
		catch (myexceptions.DuplicateRenterException e)
		{
			System.out.println("Could not rent movie. Renter already exists.");
		}
		
		catch (myexceptions.RenterLimitException e)
		{
			System.out.println("Could not rent movie. Renter limit reached.");
		}
	}
	
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
				if (r.getRenterId() != (renterId)){
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

	public String getMovieName()
	{
		return movieName;
	}

	public void setMovieName(String movieName)
	{
		this.movieName = movieName;
	}

	public String getMovieCode()
	{
		return movieCode;
	}

	public void setMovieCode(String movieCode)
	{
		this.movieCode = movieCode;
	}

	public Renter[] getRenters()
	{
		return this.renters;
	}

	public void setRenters(Renter[] renters)
	{
		this.renters = renters;
	}

	public int getRentedMovieCopies()
	{
		return rentedMovieCopies;
	}

	public void setRentedMovieCopies(int rentedMovieCopies)
	{
		this.rentedMovieCopies = rentedMovieCopies;
	}
	
}
