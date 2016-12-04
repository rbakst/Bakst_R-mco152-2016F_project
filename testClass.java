import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import exceptions.*;
import shul.*;

public class testClass {
	Person p;
	
	@Before
	public void setupPersonObject() throws MissingDataException{
		 p = new Person("Mrs.", "Rachel", 'a', "Bakst", LocalDate.now(), 'F', "racko3000@earthlink.net", "m1" );
		 
	}
	
	@Test
	public void testPersonIDPrecededWithP(){
		
		assertEquals(p.getPersonID(), "p.*");
		
	}
	
	

	public static void main(String[] args){
		try{
		Person p = new Person("Mrs.", "Rachel", 'a', "Bakst", new LocalDate("2007-12-03"), 'F', "racko3000@earthlink.net", "m1" );
		
		if(p.getPersonID()== "p.*"){
			System.out.println("true");
		}
		else{
			System.out.println("false");
		}
		}catch(MissingDataException ex){}
		
		
	}
}
