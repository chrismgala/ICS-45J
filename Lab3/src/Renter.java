// Renter.java


// Chris Gala 64338761
// Wai Phyo 60902242

public class Renter
{
	private int renterId;
	private String firstName;
	private String lastName;
	
	public Renter(int renterId, String firstName, String lastName)
	{
		this.renterId = renterId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getRenterId()
	{
		return renterId;
	}
	
	public void setRenterId(int renterId)
	{
		this.renterId = renterId;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
}
