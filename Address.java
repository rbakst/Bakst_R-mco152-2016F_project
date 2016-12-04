package shul;
import exceptions.*;

public class Address 
{
	private Integer addressID;
	private String street;
	private String aptNum;
	private String city;
	private USState state;
	private String zip;
	
	private static Integer lastAssignedID = 0;
	
	public Address(String street,  String aptNum, String city, String state, String zip) throws MissingDataException
	{
		this.addressID = ++lastAssignedID;
		//ensure that no field of the address is null
		if(street == null || city == null || state == null || zip == null)
		{
			throw new MissingDataException();
		}
		//assign the values, construct the address
		
		this.street = street; 
		this.aptNum = aptNum;
		this.city = city;
		//remove whitespace
		String newState = state.replaceAll("\\s", "");
		this.state = USState.valueOf(newState.toUpperCase());
		this.zip = zip;
	}
	
	//copy constructor
	public Address(Address a) throws MissingDataException
	{
		this(a.street, a.aptNum, a.city, a.state.name(), a.zip);
	}	
	
	public String getStreet()
	{
		return this.street;
	}
	
	public String getAptNum()
	{
		return this.aptNum;
	}
	
	public String getCity()
	{
		return this.city;
	}
	
	public USState getState()
	{
		return this.state;
	}
	
	public String getZip()
	{
		return this.zip;
	}
	
	public void setStreet(String street)
	{
		this.street = street;
	}
	
	public void setAptNum(String aptNum)
	{
		this.aptNum = aptNum;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public void setState(String state)
	{
		String newState = state.replaceAll("\\s", "");
		this.state = USState.valueOf(newState);
	}
	
	public void setZip(String zip)
	{
		this.zip = zip;
	}
	
	public String toString(){
		
		StringBuilder info  = new StringBuilder();
		
		info.append(this.street);
		info.append("\n");
		info.append(this.city);
		info.append(", ");
		info.append(this.state.getSymbol());
		info.append(" ");
		info.append(zip);
		
		return info.toString();
	}
	
}
