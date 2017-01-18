// Movie.java

public class Movie
{
	private int duration_min;
	private int release_year;
	private double price_USD;
	private String title;
	private Director director;
	
	/*Constructor*/
	
	public Movie()
	{
		
	}
	
	public Movie(int duration_min, int release_year, int price_USD,
		String title, Director director)
	{
		this.duration_min = duration_min;
		this.release_year = release_year;
		this.price_USD = price_USD;
		this.title = title;
		this.director = director;
	}
	
	/*Copy Constructor*/
	public Movie(Movie movie)
	{
		this.duration_min = movie.duration_min;
		this.release_year = movie.release_year;
		this.price_USD = movie.price_USD;
		this.title = movie.title;
		this.director = movie.director;
	}
	
	/*Getters*/
	public int getDurationMin()
	{
		return duration_min;
	}
	
	public int getReleaseYear()
	{
		return release_year;
	}
	
	public double getPriceUSD()
	{
		return price_USD;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public Director getDirector()
	{
		return director;
	}
	
	/*Setters*/
	
	public void setDurationMin(int duration_min)
	{
		this.duration_min = duration_min;
	}
	
	public void setReleaseYear(int release_year)
	{
		this.release_year = release_year;
	}
	
	public void setPriceUSD(double price_USD)
	{
		this.price_USD = price_USD;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setDirector(Director director)
	{
		this.director = director;
	}
	
	public void setDirector(String first_name, String last_name, int birth_year, 
		int num_of_directed_movies)
	{
		director.setFirstName(first_name);
		director.setLastName(last_name);
		director.setBirthYear(birth_year);
		director.setNumberOfDirectedFilms(num_of_directed_movies);
	}
	
	@Override
	public String toString()
	{
		return "Title: "+title+"\n"+
				"Published in: "+release_year+"\n"
				+"Price: $"+price_USD+"\n"
				+"Written by: "+director.getFirstName()+" "+director.getLastName()+
				", who was born in "+director.getBirthYear()+" and has "+director.getNumberOfDirectedFilms()+" releases";
	}
	
}
