package shul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import exceptions.MissingDataException;

public class Membership {
	private String membershipID;
	private MembershipType type;
	private LocalDate startDate;
	private Address address;

	public Membership(String membershipID, MembershipType type, LocalDate startDate, Address address)
			throws MissingDataException {
		if (type == null || address == null) {
			throw new MissingDataException();
		}

		this.membershipID = membershipID;
		this.type = type;
		this.startDate = startDate;
		this.address = address;

	}

	public Membership(Membership m) throws MissingDataException {
		this(m.membershipID ,m.type, m.startDate, m.address);
	}

	public static Membership addMembershipToDB(MembershipType type, Address address,
			Connection connection) throws MissingDataException, SQLException {
		
		if(type == null){
			throw new MissingDataException();
		}
		
		
		Integer lastAssignedID = getLastAssignedID(connection);
		String membershipID = String.format("M%04d", ++lastAssignedID); 

		
		// enter new address into database
		String sqlInsert = "insert into membership values(?,?,?,?)";

		PreparedStatement statement = connection.prepareStatement(sqlInsert);
statement.setString(1, membershipID);
statement.setString(2, type.toString());
statement.setInt(3, address.getAddressID());
statement.setString(4, LocalDate.now().toString());		
		
		statement.executeUpdate();
		statement.close();

		return new Membership(membershipID, type,LocalDate.now(), address);
	}
	
	public static Integer getLastAssignedID(Connection connection) throws SQLException {
		String sqlSelect = "Select MAX(MembershipID) from Membership";
		Statement stmt = connection.createStatement();
		ResultSet set = stmt.executeQuery(sqlSelect);

		// if result set is not empty
		if (set.next()) {
			return set.getInt(1);

		} else
			return 0;
	}
	
	
	public static Membership getMembershipByID(String memID, Connection connection) throws SQLException, MissingDataException{
			String sqlSelect = "select * from membership where membershipID = '" + memID + "'";

			PreparedStatement statement = connection.prepareStatement(sqlSelect, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			ResultSet rs = statement.executeQuery();
			
			
			// if result set is not empty
			if (rs.first()) {
				rs.first();
				String type = rs.getString("MembershipType");
				Integer addID = rs.getInt("MembershipAddress");
				String startDate = rs.getString("MembershipStartDate");
				
				

				return new Membership(memID, MembershipType.valueOf(type), LocalDate.parse(startDate), Address.getAddressByID(addID, connection));
			} else
				throw new MissingDataException();
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

	public String toString() {
		StringBuilder info = new StringBuilder();

		info.append("\nMembership ID: " + membershipID);
		info.append("\nMembership Type: " + type);
		info.append("\nStart Date: " + startDate);
		info.append("\n");
		info.append(address);
		info.append("\n");
		return info.toString();

	}
}
