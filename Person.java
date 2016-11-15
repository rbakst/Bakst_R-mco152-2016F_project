package shul;
import java.time.LocalDate;

import exceptions.*;

public class Person implements Comparable<Person>
{
	private Integer personID;
	private String title;
	private String firstName;
	private char midInitial;
	private String lastName;
	private LocalDate DOB;
	private Address address;
	private char gender;
	private String emailAddress;
	
	private static Integer lastAssignedID = 0;

	public Person(String title, String firstName, char midInitial, String lastName, LocalDate DOB, Address address, 
			char gender, String emailAddress) throws MissingDataException
	{
		this.personID = ++lastAssignedID;
		
		if(personID == null || firstName == null || lastName == null || address == null)
		{
			throw new MissingDataException();
		}
		this.title = title;
		
		this.firstName = firstName;
		this.midInitial = midInitial;
		this.lastName = lastName;
		this.DOB = DOB;
		this.address = address;
		this.gender = gender;
		this.emailAddress = emailAddress;
	}
	
	public Person(String title, String firstName, char midInitial, String lastName, LocalDate DOB, String street, String city, String aptNum, 
			String state, String zip, char gender, String emailAddress) throws MissingDataException
	{
		this(title, firstName, midInitial, lastName, DOB, new Address(street, city, aptNum, state, zip), gender, emailAddress);
	}

	public Person (Person p)throws MissingDataException
	{
		this(p.title, p.firstName, p.midInitial, p.lastName, p.DOB, p.address, p.gender, p.emailAddress);
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public char getMidInitial() {
		return midInitial;
	}

	public void setMidInitial(char midInitial) {
		this.midInitial = midInitial;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Integer getPersonID() {
		return personID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDOB()
	{
		return DOB;
	}
	public Address getAddress() throws MissingDataException{
		return new Address(address);
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public String toString()
	{
		StringBuilder info = new StringBuilder();
		
		info.append("\n\n" + title + ". ");
		info.append(firstName + " " + midInitial + " "+ lastName);
		info.append("\nDOB: " + DOB);
		info.append("\n" + address);
		info.append("\ngender: "+ gender);
		info.append("\nemail: " + emailAddress);
		
		return info.toString();
		
	}
	
	public int compareTo(Person p)
	{
		return this.lastName.compareTo(p.lastName);
	}
	
	public boolean equals(Object other)
	{
		if (other == null || !(other.getClass().equals(this.getClass())))
		{
			return false;
		}
		
		else 
		{
			//typecast object to be a person
			Person p = (Person) other;
			
			if (this.firstName.equalsIgnoreCase(p.firstName))
			{
				if(this.lastName.equalsIgnoreCase(p.lastName))
				{
					if (this.DOB.equals(p.DOB))
					{
						return true;
					}
				}
			}
			//if one of the conditions were not true
			return false;
		}
		
	}
	
}



