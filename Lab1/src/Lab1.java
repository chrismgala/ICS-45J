// Lab1.java (Main)

public class Lab1
{
	public static void main(String[] args) 
	{
		// Create a new movie with default values for all attributes 
		Movie movie = new Movie();
		
		// Set the appropriate fields for the book’s objects 
		movie.setTitle("Harry Potter and the Goblet of Fire"); 
		movie.setPriceUSD(19.99); 
		movie.setReleaseYear(2000); 
		movie.setDurationMin(734); 
		movie.setDirector("J.K.", "Rowling", 1965, 7);
		
		// Prints out the state of the movie. 
		System.out.println(movie.toString());
	}
}
