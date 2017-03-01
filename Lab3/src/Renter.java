// Renter.java
// This class represents the Renter entity. The constructor makes a new renter using the renterId, firstName, and lastName.
// It also includes all the necessary Getters / Setters.

// Chris Gala 64338761
// Wai Phyo 60902242

public class Renter
{
	private int renterId;
	private String firstName;
	private String lastName;
	
	// Construct a new Renter given their renter id, first name, and last name.
	public Renter(int renterId, String firstName, String lastName)
	{
		this.renterId = renterId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	// Get the Renter's id.
	public int getRenterId()
	{
		return renterId;
	}
	
	// Set the Renter's id to renterId.
	public void setRenterId(int renterId)
	{
		this.renterId = renterId;
	}
	
	// Get the Renter's first name.
	public String getFirstName()
	{
		return firstName;
	}
	
	// Set the Renter's first name to firstName.
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	// Get the Renter's last name.
	public String getLastName()
	{
		return lastName;
	}
	
	// Set the Renter's last name to lastName.
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
}
