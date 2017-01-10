package shul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exceptions.*;

public class Address {
	private Integer addressID;
	private String street;
	private String aptNum;
	private String city;
	private USState state;
	private String zip;


	public Address(Integer addressID, String street, String aptNum, String city, String state, String zip)
			throws MissingDataException {

		// addressID = 2;
		// ensure that no field of the address is null
		if (addressID == null || street == null || city == null || state == null || zip == null) {
			throw new MissingDataException();
		}

		// assign the values, construct the address
		this.addressID = addressID;
		this.street = street;
		this.aptNum = aptNum;
		this.city = city;
		// remove whitespace
		String newState = state.replaceAll("\\s", "").toUpperCase();
		this.state = USState.valueOf(newState);
		this.zip = zip;
	}

	// copy constructor
	public Address(Address a, Connection connection) throws MissingDataException {
		this(a.addressID, a.street, a.aptNum, a.city, a.state.name(), a.zip);
	}

	public static Address addAddressToDB(String street, String aptNum, String city, String state, String zip,
			Connection connection) throws MissingDataException, SQLException {
		if (street == null || city == null || state == null || zip == null) {
			throw new MissingDataException();
		}
		
		Integer lastAssignedID = getLastAssignedID(connection);
		Integer addressID = ++lastAssignedID;
		USState newState = USState.valueOf(state.replaceAll("\\s", "").toUpperCase());

		String sqlInsert = "insert into person Values (?,?,?,?,?,?,?,?,?)";

		PreparedStatement statement = connection.prepareStatement(sqlInsert);
		statement.setInt(1, addressID);
		statement.setString(2, street);
		statement.setString(3, aptNum);
		statement.setString(4, city);
		statement.setString(5, newState.toString());
		statement.setString(6, zip);

		statement.executeUpdate();
		statement.close();

		return new Address(addressID, street, aptNum, city, state, zip);
	}

	public static Integer getLastAssignedID(Connection connection) throws SQLException {
		String sqlSelect = "Select MAX(AddressID) from Address";

		Statement stmt = connection.createStatement();
		ResultSet set = stmt.executeQuery(sqlSelect);

		// if result set is not empty
		if (set.next()) {
			return set.getInt(1);

		} else
			return 0;
	}

	public String getStreet() {
		return this.street;
	}

	public Integer getAddressID() {
		return this.addressID;
	}

	public String getAptNum() {
		return this.aptNum;
	}

	public String getCity() {
		return this.city;
	}

	public USState getState() {
		return this.state;
	}

	public String getZip() {
		return this.zip;
	}

	public static Address getAddressByID(int addID, Connection connection) throws SQLException, MissingDataException {
		// enter new address into database
		String sqlSelect = "select * from address where addressID = " + addID;

		PreparedStatement statement = connection.prepareStatement(sqlSelect, ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);

		// execute the statement
		ResultSet rs = statement.executeQuery();

		// if result set is not empty
		if (rs.first()) {
			rs.first();
			String street = rs.getString("Street");
			String aptNum = rs.getString("AptNum");
			String city = rs.getString("City");
			String state = rs.getString("AddrState");
			String zip = rs.getString("Zip");

			state = USState.get(state).toString();

			return new Address(addID, street, aptNum, city, state, zip);
		} else
			throw new MissingDataException();
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setAptNum(String aptNum) {
		this.aptNum = aptNum;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		String newState = state.replaceAll("\\s", "").toUpperCase();
		this.state = USState.valueOf(newState);
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String toString() {

		StringBuilder info = new StringBuilder();
		info.append("Address ID: ");
		info.append(this.addressID);
		info.append("\n");
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
