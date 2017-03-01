// MovieManagerTest.java
// This is the JUnit test suite for all necessary tests.
// It tests all four main methods in MovieManager including addMovie, discontinueMovie, rentMovie, and returnRental.

// Chris Gala 64338761
// Wai Phyo 60902242

import static org.junit.Assert.*;
import org.junit.Test;

public class MovieManagerTest
{
	// Make a new MovieManager
	MovieManager mm = new MovieManager();
	
	// We were not able to get exceptions to be handled within each test,
	// BUT the exceptions still get output to the console and we make sure
	// that ALL exceptions for each method are thrown in their respective circumstances.
	
	// Test the addMovie method
	// Test normal, error and boundary cases
	@Test
	public void testAddMovie()
	{
		
		String movieCode = "m1";
		String movieName = "Movie";
		Movie m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		Movie[] movies = mm.getMovies();
		
		assertEquals(movies[0].getMovieCode(), "m1");
		assertEquals(movies[0].getMovieName(), "Movie");
		
		movieCode = "m2";
		movieName = "Movie2";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[1].getMovieCode(), "m2");
		assertEquals(movies[1].getMovieName(), "Movie2");
		
		movieCode = "m3";
		movieName = "Movie3";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[2].getMovieCode(), "m3");
		assertEquals(movies[2].getMovieName(), "Movie3");
		
		movieCode = "m4";
		movieName = "Movie4";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[3].getMovieCode(), "m4");
		assertEquals(movies[3].getMovieName(), "Movie4");
		
		movieCode = "m5";
		movieName = "Movie5";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[4].getMovieCode(), "m5");
		assertEquals(movies[4].getMovieName(), "Movie5");
		
		movieCode = "m6";
		movieName = "Movie6";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[5].getMovieCode(), "m6");
		assertEquals(movies[5].getMovieName(), "Movie6");
		
		movieCode = "m7";
		movieName = "Movie7";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[6].getMovieCode(), "m7");
		assertEquals(movies[6].getMovieName(), "Movie7");
		
		movieCode = "m8";
		movieName = "Movie8";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[7].getMovieCode(), "m8");
		assertEquals(movies[7].getMovieName(), "Movie8");
		
		movieCode = "m9";
		movieName = "Movie9";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[8].getMovieCode(), "m9");
		assertEquals(movies[8].getMovieName(), "Movie9");
		
		movieCode = "m10";
		movieName = "Movie10";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[9].getMovieCode(), "m10");
		assertEquals(movies[9].getMovieName(), "Movie10");
		
		movieCode = "m11";
		movieName = "Movie11";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[10].getMovieCode(), "m11");
		assertEquals(movies[10].getMovieName(), "Movie11");
		
		movieCode = "m12";
		movieName = "Movie12";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[11].getMovieCode(), "m12");
		assertEquals(movies[11].getMovieName(), "Movie12");
		
		movieCode = "m13";
		movieName = "Movie13";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[12].getMovieCode(), "m13");
		assertEquals(movies[12].getMovieName(), "Movie13");
		
		movieCode = "m14";
		movieName = "Movie14";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[13].getMovieCode(), "m14");
		assertEquals(movies[13].getMovieName(), "Movie14");
		
		movieCode = "m15";
		movieName = "Movie15";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[14].getMovieCode(), "m15");
		assertEquals(movies[14].getMovieName(), "Movie15");
		
		movieCode = "m16";
		movieName = "Movie16";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[15].getMovieCode(), "m16");
		assertEquals(movies[15].getMovieName(), "Movie16");
		
		movieCode = "m17";
		movieName = "Movie17";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[16].getMovieCode(), "m17");
		assertEquals(movies[16].getMovieName(), "Movie17");
		
		movieCode = "m18";
		movieName = "Movie18";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[17].getMovieCode(), "m18");
		assertEquals(movies[17].getMovieName(), "Movie18");
		
		movieCode = "m19";
		movieName = "Movie19";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[18].getMovieCode(), "m19");
		assertEquals(movies[18].getMovieName(), "Movie19");
		
		movieCode = "m20";
		movieName = "Movie20";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		assertEquals(movies[19].getMovieCode(), "m20");
		assertEquals(movies[19].getMovieName(), "Movie20");

		
		System.out.println("Testing addMovie exceptions\n");
		// Check if MovieLimitException is thrown
		movieCode = "m21";
		movieName = "Movie21";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		mm.discontinueMovie("m20");
		
		// Check if DuplicateMovieException is thrown
		movieCode = "m1";
		movieName = "Movie1";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		// Check if MovieInfoEmptyException is thrown
		movieCode = "";
		movieName = "";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		System.out.println("\n");
	}


	// Test the discontinueMovie method
	// Test normal, error, and boundary cases
	@Test
	public void testDiscontinueMovie()
	{
		String movieCode = "m1";
		String movieName = "Movie";
		Movie m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		Movie[] movies = mm.getMovies();
		
		movieCode = "m2";
		movieName = "Movie2";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		movieCode = "m3";
		movieName = "Movie3";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();
		
		movieCode = "m4";
		movieName = "Movie4";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		movies = mm.getMovies();

		movieCode = "m5";
		movieName = "Movie5";
		m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		mm.discontinueMovie("m1");
		movies = mm.getMovies();
		
		assertEquals(movies[0].getMovieCode(), "m2");
		
		mm.discontinueMovie("m5");
		movies = mm.getMovies();
		
		assertEquals(movies[2].getMovieCode(), "m4");
		
		System.out.println("Testing discontinueMovie exceptions\n");
		
		// Check if MovieNotFoundException is thrown	
		mm.discontinueMovie("m1");
		
		// Check if RentedMovieException is thrown
		movies = mm.getMovies();
		Renter r = new Renter(123, "Chris", "Gala");
		movies[0].rentMovie(r);
		mm.discontinueMovie("m2");
		
		// Check if EmptyMovieListException is thrown
		movies[0].returnRental(123);
		mm.discontinueMovie("m2");
		
		mm.discontinueMovie("m3");
		mm.discontinueMovie("m4");
		
		mm.discontinueMovie("m1");
		
		System.out.println("\n");
	}

	// Test the rentMovie method
	// Test normal, error and boundary cases
	@Test
	public void testRentMovie()
	{
		String movieCode = "m1";
		String movieName = "Movie";
		Movie m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		Movie[] movies = mm.getMovies();
		
		Renter r = new Renter(111, "Chris", "Gala");
		mm.rentMovie("m1", r);
		
		r = new Renter(222, "Chris", "Gala");
		mm.rentMovie("m1", r);
		
		r = new Renter(333, "Chris", "Gala");
		mm.rentMovie("m1", r);
		
		r = new Renter(444, "Chris", "Gala");
		mm.rentMovie("m1", r);
		
		r = new Renter(555, "Chris", "Gala");
		mm.rentMovie("m1", r);
		
		r = new Renter(666, "Chris", "Gala");
		mm.rentMovie("m1", r);
		
		r = new Renter(777, "Chris", "Gala");
		mm.rentMovie("m1", r);
		
		r = new Renter(888, "Chris", "Gala");
		mm.rentMovie("m1", r);
		
		r = new Renter(999, "Chris", "Gala");
		mm.rentMovie("m1", r);
		
		Renter[] renters = movies[0].getRenters();
		assertEquals(renters[0].getRenterId(), 111);
		assertEquals(renters[1].getRenterId(), 222);
		assertEquals(renters[2].getRenterId(), 333);
		assertEquals(renters[3].getRenterId(), 444);
		assertEquals(renters[4].getRenterId(), 555);
		assertEquals(renters[5].getRenterId(), 666);
		assertEquals(renters[6].getRenterId(), 777);
		assertEquals(renters[7].getRenterId(), 888);
		assertEquals(renters[8].getRenterId(), 999);
		
		System.out.println("Testing rentMovie exceptions\n");
		
		// Check if EmptyRenterNameException is thrown
		mm.rentMovie("m1", new Renter(345, "", ""));
		
		// Check if DuplicateRenterException is thrown
		r = new Renter(111, "Chris", "Gala");
		mm.rentMovie("m1", r);
		
		// Check if RenterLimitException is thrown
		r = new Renter(234, "Chris", "Gala");
		mm.rentMovie("m1", r);
		
		r = new Renter(345, "Chris", "Gala");
		mm.rentMovie("m1", r);
		
		// Check if MovieNotFoundException is thrown
		mm.rentMovie("m2", r);
		
		System.out.println("\n");
	}


	// Test the returnRental method
	// Test normal, error and boundary cases
	@Test
	public void testReturnRental()
	{
		String movieCode = "m1";
		String movieName = "Movie";
		Movie m = new Movie(movieCode, movieName);
		
		mm.addMovie(m);
		
		System.out.println("Testing returnRental exceptions\n");
		
		// Check if EmptyRenterListException is thrown
		mm.returnRental(123, "m1");
		
		Renter r = new Renter(111, "Chris", "Gala");
		mm.rentMovie("m1", r);
		
		r = new Renter(222, "Chris", "Gala");
		mm.rentMovie("m1", r);
		
		r = new Renter(333, "Chris", "Gala");
		mm.rentMovie("m1", r);
		
		Movie[] movies = mm.getMovies();
		Renter[] renters = movies[0].getRenters();
		assertEquals(renters[0].getRenterId(), 111);
		assertEquals(renters[1].getRenterId(), 222);
		assertEquals(renters[2].getRenterId(), 333);
		
		
		// Check if RenterNotFoundException is thrown
		mm.returnRental(123, "m1");
		
		// Check if MovieNotFoundException is thrown
		mm.returnRental(111, "m2");
		
		System.out.println("\n");
	}

}
