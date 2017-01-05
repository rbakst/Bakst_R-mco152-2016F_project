package shul;

import exceptions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class UseShul {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// ArrayList<Person> personsToBeKeptInShulClass = new ArrayList<>();
		int choice = 0;

		String connectionString = "jdbc:sqlserver://MININT-9UUBA0C\\SQLEXPRESS;username=ShulProject;password=ShulDatabase";

		try {
			Connection connection = DriverManager.getConnection(connectionString);
			connection.setAutoCommit(true);

			while (choice != 5) {
				choice = menu();

				switch (choice) {
				case 1:
					System.out.println("Adding a new Membership:");
					System.out.println("Is the membership address already in the system? (Y/N)");
					char answer = input.next().charAt(0);

					while (answer != 'Y' && answer != 'y' && answer != 'N' && answer != 'n') {
						System.out.println("Invalid Input.  Please answer Y/N.");
						answer = input.next().charAt(0);
					}

					if (answer == 'Y' || answer == 'y') {
						System.out.println("Do you have the Address ID? (Y/N)");
						answer = input.next().charAt(0);

						while (answer != 'Y' && answer != 'y' && answer != 'N' && answer != 'n') {
							System.out.println("Invalid Input.  Please answer Y/N.");
							answer = input.next().charAt(0);
						}
						if (answer == 'N' || answer == 'n') {

							// really this code should allow user to search for
							// address by some criteria
							// now it does nothing.

							// System.out
							// .println("Search for the Address by Last Name and
							// keep record of the Address ID: ");

							// ArrayList<Address> addresses =
							// searchForAddByLName(connection);

							try {
								Membership aMembership = addMembershipWithAddID(connection);

								System.out.println("Membership successfully added: ");
								System.out.println(aMembership.toString());
							} catch (MissingDataException e) {
								System.out.println(e.getMessage());
							} catch (Exception e) {
								System.out.println("Invalid Address State");
							}
						} else {
							try {
								Membership aMembership = addMembershipWithAddID(connection);

								System.out.println("Membership successfully added: ");
								System.out.println(aMembership.toString());
							} catch (MissingDataException e) {
								System.out.println(e.getMessage());
							}
						}

					} else {
						try {
							Membership aMembership = addMembershipWithNewAddress(connection);

							System.out.println("Membership successfully added: ");
							System.out.println(aMembership.toString());
						} catch (MissingDataException e) {
							System.out.println(e.getMessage());
						}

					}
					break;
				case 2:
					try {
						Person aPerson = addPersonToExistingMembership(connection);

						System.out.println("\n Person successfully added:");
						System.out.println(aPerson.toString());

					} catch (MissingDataException ex) {
						System.out.println(ex.getMessage());
					}
					break;
				case 3:
					Address add;
					try {
						add = addAddress(connection);
						System.out.println("New Address Successfully Added:\n" + add.toString());
					} catch (MissingDataException e) {
						System.out.println(e.getMessage());
					}

					break;
				case 4:
					ArrayList<Person> persons;
					try {
						persons = Person.getAllPeople(connection);
					

					if (!persons.isEmpty()) {

						StringBuilder info = new StringBuilder();
						info.append("People entered into the system: ");
						for (Person p : persons) {
							info.append(p.toString());
						}

						System.out.println(info.toString());
					} else
						System.out.println("There are no people entered in the system.\n");
					} catch (MissingDataException e) {
						System.out.println(e.getMessage());
					}

					break;
				case 5:
					try {
						AddressLabel.printAddressLabels(connection);
					} catch (FileNotFoundException | UnsupportedEncodingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					break;
				case 6:
					try {
						AddressLabel.printFemaleAddressLabels(connection);
					} catch (FileNotFoundException | UnsupportedEncodingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 7:
					connection.close();
					System.exit(0);
				default:

				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public static int menu() {
		System.out.println("1. Add a Membership");
		System.out.println("2. Add a Person to an existing membership");
		System.out.println("3. Add an Address");
		System.out.println("4. Display Persons");
		System.out.println("5. Print Address Labels for all People");
		System.out.println("6. Print Address Labels for Women's League");
		System.out.println("7. Exit");

		Scanner keyboard = new Scanner(System.in);
		int choice = keyboard.nextInt();

		while(choice < 1 || choice > 7){
			System.out.println("Please enter a valid Menu Option");;
			choice = keyboard.nextInt();
		}
		
		return choice;
	}

	public static Person addPersonToExistingMembership(Connection connection) throws SQLException, MissingDataException{
		Scanner keyboard = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String midInitial = null;
		LocalDate dob = null;

		System.out.println("Title:");
		String title = keyboard.next();
		System.out.println("First Name: ");
		String firstName = keyboard.next();
		System.out.println("Last Name: ");
		String lastName = keyboard.next();
		System.out.println("Is there a middle initial?");
		char answer = keyboard.next().charAt(0);
		if (answer == 'y' || answer == 'Y') {
			System.out.println("Middle Initial: ");
			midInitial = keyboard.next().substring(0, 0);
		}
		System.out.println("Do you have the DOB?");
		answer = keyboard.next().charAt(0);
		if (answer == 'y' || answer == 'Y') {
			System.out.println("Date of Birth (mm/dd/yyyy): ");
			dob = LocalDate.parse(keyboard.next(), formatter);
		}
		keyboard.nextLine();
		System.out.println("Gender: (m/f)");
		String gender = keyboard.next();
		System.out.println("EmailAddress: ");
		String emailAddress = keyboard.next();
		System.out.println("Membership ID: ");
		String membershipID = keyboard.next();

		
		Membership mem = Membership.getMembershipByID(membershipID, connection);
		Person aPerson = Person.addPersonToDB(mem, title, firstName, midInitial, lastName, dob, emailAddress,
				gender, connection);

		return aPerson;
	}

	public static Address addAddress(Connection connection) throws MissingDataException, SQLException {
		Scanner keyboard = new Scanner(System.in);
		String aptNum = null;
		System.out.println("Add a New Address:");
		System.out.println("Street Address:");
		String street = keyboard.nextLine();
		System.out.println("Is there an Apt Number?");
		char answer = keyboard.next().charAt(0);
		if (answer == 'y' || answer == 'Y') {
			System.out.println("Apt Number: ");
			aptNum = keyboard.next();
		}
		System.out.println("City: ");
		keyboard.nextLine();
		String city = keyboard.nextLine();
		System.out.println("State: ");
		String state = keyboard.nextLine();
		System.out.println("Zip Code: ");
		String zip = keyboard.nextLine();

		Address add = Address.addAddressToDB(street, aptNum, city, state, zip, connection);

		return add;
	}

	public static Membership addMembershipWithAddID(Connection connection) throws MissingDataException, SQLException{
		Scanner keyboard = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		System.out.println("Membership Type:  (FULL, ASSOCIATE, of FRIEND)");
		MembershipType memType = MembershipType.valueOf(keyboard.next().toUpperCase());
		System.out.println("AddressID:");
		int addID = keyboard.nextInt();

		Membership aMembership = Membership.addMembershipToDB(memType, Address.getAddressByID(addID, connection), connection);

		return aMembership;
	}

	public static Membership addMembershipWithNewAddress(Connection connection) throws MissingDataException, SQLException {
		Scanner keyboard = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		System.out.println("Membership Type:  (FULL, ASSOCIATE, or FRIEND)");
		MembershipType memType = MembershipType.valueOf(keyboard.next().toUpperCase());

		Address add = addAddress(connection);

		Membership aMembership = Membership.addMembershipToDB(memType, add, connection);

		return aMembership;

	}
	/*
	 * public ArrayList<Address> searchForAddByLName(Connection connection) {
	 * Scanner keyboard = new Scanner(System.in); System.out.println(
	 * "Please Enter the street Address: "); String lName = keyboard.next();
	 * 
	 * Person p =
	 * 
	 * Membership aMembership = getMembershipByLName(lName, connection); }
	 */
}
