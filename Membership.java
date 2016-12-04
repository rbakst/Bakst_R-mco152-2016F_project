package shul;

import java.time.LocalDate;

import exceptions.MissingDataException;

public class Membership 
{
	private String membershipID;
	private MembershipType type;
	private LocalDate startDate;
	private Address address;
	
	
	private static Integer lastAssignedID = 0;
	
	public Membership(MembershipType type, Address address) throws MissingDataException
	{
		if (type == null || address == null )
		{
			throw new MissingDataException();
		}
		
		this.membershipID = "m" + ++lastAssignedID;
		this.type = type;
		this.startDate = LocalDate.now();
		this.address = address;
		
	}

	public Membership(MembershipType type, String street, String city, String aptNum, String state, 
			String zipcode)throws MissingDataException
	{
		this(type, new Address(street, city, aptNum, state, zipcode));
	}
	
	public Membership(Membership m)throws MissingDataException
	{
		this(m.type, m.address);
	}

	public String getMembershipID() {
		return membershipID;
	}

	public MembershipType getType() {
		return type;
	}

	public void setType(MembershipType type) {
		this.type = type;
	}

	public LocalDate getStartDate() {
		return startDate;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String toString()
	{
		StringBuilder info = new StringBuilder();
		
		info.append("Membership ID: " + membershipID);
		info.append("Membership Type: " + type);
		info.append("Start Date: " + startDate);
		info.append(address);
		
		return info.toString();
		
	}
}
