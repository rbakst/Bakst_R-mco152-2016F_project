package shul;

import java.time.LocalDate;

public class Membership 
{
	private Integer membershipID;
	private Integer personID;
	private LocalDate startDate;
	
	private static Integer lastAssignedID = 0;
	
	public Membership(Integer personID, LocalDate startDate)
	{
		this.membershipID = ++lastAssignedID;
		this.personID = personID;
		this.startDate = startDate;
	}

	public Integer getPersonID() {
		return personID;
	}

	public void setPersonID(Integer personID) {
		this.personID = personID;
	}

	public Integer getMembershipID() {
		return membershipID;
	}

	public LocalDate getStartDate() {
		return startDate;
	}
}
