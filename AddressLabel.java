package shul;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;

import exceptions.MissingDataException;

public class AddressLabel {
	private String formattedName;
	private String AddressLine1;
	private String AddressLine2;
	/*
	public AddressLabel(Person a, Person b) {
		StringBuilder info = new StringBuilder();

		info.append(a.getTitle());
		info.append(" ");
		info.append(a.getFirstName());
		info.append(" ");
		if (!(a.getMidInitial() == null)) {
			info.append(a.getMidInitial());
			info.append(" ");
		}
		info.append("and");
		info.append(b.getTitle());
		info.append(" ");
		info.append(b.getFirstName());
		info.append(" ");
		if (!(b.getMidInitial() == null)) {
			info.append(b.getMidInitial());
			info.append(" ");
		}
		info.append(b.getLastName());

		this.formattedName = info.toString();

		Membership aMembership = a.getMembershipByID(a.getMembershipID());

	}

	public AddressLabel() {

	}*/


	public static void printAddressLabels(Connection connection) throws SQLException, IOException {
		String fileName = LocalDate.now().toString() + "AddLabels.txt";
		PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));
	    
		String qry = "select membershipid from membership";


		PreparedStatement statement = connection.prepareStatement(qry, ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);

		ResultSet rsMem = statement.executeQuery();;
		String aptNum = "",st ="",city="",state="", zip="";

		AddressLabel a = new AddressLabel();
		
		while (rsMem.next()) {
			String qry2 = "select * from person p join Membership m on p.MembershipID = m.MembershipID join address a on a.addressid = m.MembershipAddress where m.membershipid = ?";

			PreparedStatement statement2 = connection.prepareStatement(qry2, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);


			
			statement2.setString(1, rsMem.getString("MembershipID"));
			ResultSet ppl = statement2.executeQuery();

			ppl.last();
			// 2 rows
			if (ppl.getRow() == 2) {
				ppl.first();
				String title1 = ppl.getString("Title");
				String fname1 = ppl.getString("fname");
				String lname = ppl.getString("lname");
				
				ppl.next();
				
				String title2 = ppl.getString("Title");
				String fname2 = ppl.getString("fname");
				
				
				a.formattedName = title1 + " and " + title2 + " " + fname1 + " and " + fname2 + " " + lname;
			
			} else {
				ppl.first();
				String title = ppl.getString("Title");
				String fname = ppl.getString("fname");
				String lname = ppl.getString("lname");
				
				a.formattedName = title + " " + fname + " " + lname;
				
			}

			st = ppl.getString("street");
			aptNum = ppl.getString("aptNum");
			city = ppl.getString("city");
			state = ppl.getString("addrstate");
			zip = ppl.getString("zip");

	if (aptNum.equals("null")) {
		a.AddressLine1 = st;
		
	}else{
		a.AddressLine1 =st +" Apt" + aptNum;
	}
	a.AddressLine2 = city + ", " + state + " " + zip;
	
	
	writer.println(a.formattedName);
	writer.println(a.AddressLine1);
	writer.println(a.AddressLine2);
	writer.println("");
		}
		
		
		writer.close();
		
		File f = new File(fileName);
		Desktop d = Desktop.getDesktop();
		d.open(f);
}
	
	
	public static void printFemaleAddressLabels(Connection connection) throws SQLException, IOException {
		String fileName = LocalDate.now().toString() + "FemaleAddLabels.txt";
		PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));
	    
		String qry = "select membershipid from membership";


		PreparedStatement statement = connection.prepareStatement(qry, ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);

		ResultSet rsMem = statement.executeQuery();;
		String aptNum = "",st ="",city="",state="", zip="";

		AddressLabel a = new AddressLabel();
		
		while (rsMem.next()) {
			String qry2 = "select * from person p join Membership m on p.MembershipID = m.MembershipID join address a on a.addressid = m.MembershipAddress where p.gender = 'f' and m.membershipid = ?";

			PreparedStatement statement2 = connection.prepareStatement(qry2, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);


			
			statement2.setString(1, rsMem.getString("MembershipID"));
			ResultSet ppl = statement2.executeQuery();

			//ppl.last();
			
				ppl.first();
				String title = ppl.getString("Title");
				String fname = ppl.getString("fname");
				String lname = ppl.getString("lname");
				
				a.formattedName = title + " " + fname + " " + lname;
				
			

			st = ppl.getString("street");
			aptNum = ppl.getString("aptNum");
			city = ppl.getString("city");
			state = ppl.getString("addrstate");
			zip = ppl.getString("zip");

	if (aptNum.equals("null")) {
		a.AddressLine1 = st;
		
	}else{
		a.AddressLine1 =st +" Apt" + aptNum;
	}
	a.AddressLine2 = city + ", " + state + " " + zip;
	
	
	writer.println(a.formattedName);
	writer.println(a.AddressLine1);
	writer.println(a.AddressLine2);
	writer.println("");
		}
		
		
		writer.close();
		
		File f = new File(fileName);
		Desktop d = Desktop.getDesktop();
		d.open(f);
}
}



