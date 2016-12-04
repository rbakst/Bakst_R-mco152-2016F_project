package shul;
import exceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class UseShul {

	public static void main(String [] args) 
	{

		ArrayList<Person> personsToBeKeptInShulClass = new ArrayList<>();
		int choice = 0;
		while (choice != 3)
		{
			choice = menu();
			
			switch(choice)
			{
			case 1:
				try{
					addPerson(personsToBeKeptInShulClass);
				}catch(MissingDataException ex)
				{
					System.out.println(ex.getMessage());
				}
				break;
			case 2:
				System.out.println(personsToBeKeptInShulClass);
				break;
			case 3:
				System.exit(0);
				default:
					
			}
		}
		
	}
	
	public static int menu()
	{
		System.out.println("1. Add a Person");
		System.out.println("2. Display Persons");
		System.out.println("3. Exit");
		
		Scanner keyboard = new Scanner(System.in);
		int choice = keyboard.nextInt();
		
		return choice;
	}
	
	public static void addPerson(ArrayList<Person> personsToBeKeptInShulClass) throws MissingDataException
	{
		Scanner keyboard = new Scanner (System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		System.out.println("Title:");
		String title = keyboard.next();
		System.out.println("First Name: ");
		String firstName = keyboard.next();
		System.out.println("Last Name: ");
		String lastName = keyboard.next();
		System.out.println("Middle Initial: ");
		char midInitial = keyboard.next().charAt(0);
		System.out.println("Date of Birth (mm/dd/yyyy): ");
		LocalDate DOB = LocalDate.parse(keyboard.next(), formatter);
		keyboard.nextLine();
		System.out.println("Gender: (m/f)");
		char gender = keyboard.next().charAt(0);
		System.out.println("EmailAddress: ");
		String emailAddress = keyboard.next();
		System.out.println("Membership ID: ");
		String membershipID = keyboard.next();
		
		Person aPerson = new Person(title,firstName,midInitial,lastName,DOB, gender,emailAddress, membershipID);
		personsToBeKeptInShulClass.add(aPerson);
		
	
	}
	

}
