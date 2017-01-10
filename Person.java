package shul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import exceptions.*;

public class Person implements Comparable<Person> {
	private String personID;
	private String title;
	private String firstName;
	private String midInitial;
	private String lastName;
	private LocalDate DOB;
	private char gender;
	private String emailAddress;
	private String membershipID;

	public Person(String personID, String membershipID, String title, String firstName, String midInitial,
			String lastName, LocalDate DOB, String emailAddress, char gender) throws MissingDataException {

		// how to enforce gender
		if (firstName == null || lastName == null || membershipID == null) {
			throw new MissingDataException();
		}
		
		
		this.personID = personID;
		this.title = title;
		this.firstName = firstName;
		this.midInitial = midInitial;
		this.lastName = lastName;
		this.DOB = DOB;
		this.gender = gender;
		this.emailAddress = emailAddress;
		this.membershipID = membershipID;
	}

	// test localdate?
	public Person(Person p) throws MissingDataException {
		this(p.personID, p.membershipID, p.title, p.firstName, p.midInitial, p.lastName, p.DOB, p.emailAddress,
				p.gender);
	}

	public static Person addPersonToDB(Membership mem, String title, String firstName, String midInitial,
			String lastName, LocalDate DOB, String emailAddress, String gender, Connection connection)
					throws MissingDataException, SQLException {

		Integer lastAssignedID = getLastAssignedID(connection);

		String personID = String.format("P%04d", ++lastAssignedID);	

		String sqlInsert = "insert into person Values (?,?,?,?,?,?,?,?,?)";

		PreparedStatement statement = connection.prepareStatement(sqlInsert);
statement.setString(1, personID);
statement.setString(2, mem.getMembershipID());
statement.setString(3, title);
statement.setString(4, firstName);
statement.setString(5, midInitial);
statement.setString(6, lastName);
if(DOB != null){
statement.setString(7, DOB.toString());
}else statement.setString(7, null); 
statement.setString(8, emailAddress);
statement.setString(9, gender);

		statement.executeUpdate();
		statement.close();

		return new Person(personID, mem.getMembershipID(), title, firstName, midInitial, lastName, DOB, emailAddress, gender.charAt(0));
	}

	public static int getLastAssignedID(Connection connection) throws SQLException {
		String sqlSelect = "Select Right(MAX(PersonID), 4) from Person";

		Statement stmt = connection.createStatement();
		ResultSet set = stmt.executeQuery(sqlSelect);

		// if result set is not empty
		if (set.next()) {
			return set.getInt(1);

		} else
			return 0;

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMidInitial() {
		return midInitial;
	}

	public void setMidInitial(String midInitial) {
		this.midInitial = midInitial;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPersonID() {
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

	public LocalDate getDOB() {
		return DOB;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getMembershipID() {
		return this.membershipID;
	}

	public void setMembershipID(String membershipID) {
		this.membershipID = membershipID;
	}

	public static ArrayList<Person> getAllPeople(Connection connection) throws SQLException, MissingDataException {

		String sqlSelect = "select * from person order by MembershipID, LName, Gender desc";

		PreparedStatement statement = connection.prepareStatement(sqlSelect);
		// execute the statement
		ResultSet rs = statement.executeQuery();

		ArrayList<Person> allPersons = new ArrayList<Person>();
		String personID, title, fName, midInit, lName, dob, email, membershipID;
		char gender;
		LocalDate DOB;
		while (rs.next()) { // loop through the results set
		
			personID = rs.getString("PersonID");

			membershipID = rs.getString("MembershipID");
			title = rs.getString("Title");
			fName = rs.getString("FName");
			midInit = rs.getString("MidInit");
			lName = rs.getString("LName");
			dob = rs.getString("DOB");
			email = rs.getString("EmailAddress");
			gender = rs.getString("Gender").charAt(0);	
			if(dob != null){
			DOB = LocalDate.parse(dob);
			}else DOB = null;
			
			allPersons.add(new Person(personID, membershipID, title, fName, midInit, lName, DOB, email, gender));
		}
		return allPersons;
	}

	public String toString() {
		StringBuilder info = new StringBuilder();

		info.append("\n\n" + title + " ");
		info.append(firstName + " ");
		if (midInitial != null) {
			info.append(midInitial + "");
		}
		info.append(lastName);
		if (DOB != null) {
			info.append("\nDOB: " + DOB);
		} else
			info.append("\nDOB: ");
		info.append("\ngender: " + gender);
		if (emailAddress != null) {
			info.append("\nemail: " + emailAddress);
		} else
			info.append("\nemail: ");

		info.append("\nMembershipID: " + membershipID + "\n");

		return info.toString();

	}

	public int compareTo(Person p) {
		// based on id?
		return this.personID.compareTo(p.personID);
	}

	public boolean equals(Object other) {
		if (other == null || !(other.getClass().equals(this.getClass()))) {
			return false;
		}

		else {
			// typecast object to be a person
			Person p = (Person) other;

			if (this.personID.equals(p.personID)) {
				return true;
			}

			return false;
		}

	}

}
