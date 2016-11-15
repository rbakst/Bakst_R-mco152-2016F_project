Create database Shul
go

use Shul
go


create table FAddress(
AddressID INT not null, 
Street VARCHAR(30) not null,
AptNum VARCHAR(5),
City VARCHAR(45) not null,
AddrState Char(2) not null,
Zip CHAR(9) not null,
constraint [PK_AddressID] 
	Primary Key (AddressID)
					)

create table Friend(
FriendID int not null,
FName VARCHAR(45) not null,
MidInit CHAR(1),
LName VARCHAR(45) not null,
DOB Date,
AddressID int,
EmailAddress varchar(40),
Gender char(1) not null,
Title varchar(5),
isMember bit not null,
constraint [PK_PersonID]
		Primary Key (FriendID),
constraint [FK_Friend_Address]
		Foreign Key (AddressID) REFERENCES FAddress (AddressID),
constraint [CHK_Gender]
		check (Gender IN ('F', 'M'))
)


create table Membership (
				MembershipID int not null,
				MembershipType VARCHAR(11) not null,
				MembershipStartDate Date not null
				constraint [PK_MembershipID] 
					Primary Key (MembershipID),
				constraint [CHK_MembershipType]
					check (MembershipType IN('FULL MEMBERSHIP', 
											'ASSOCIATE MEMBERSHIP', 'FRIEND'))
				)

create table Friend_Membership_Link(
FriendID int not null,
MembershipID int not null,
constraint [PK_Person_Membership_Link]
	Primary Key (FriendID, MembershipID),
constraint [FK_Person_Link] 
	Foreign Key (FriendID) REFERENCES Friend (FriendID),
constraint [FK_Membership_Link] 
	Foreign Key (MembershipID) REFERENCES Membership (MembershipID)
)				



--create table Pledge(PledgeID INT not null,
--				FriendID INT not null,
--				PledgeDescription VARCHAR(50) not null,
--				AmtPledged DECIMAL(6,2) not null,
--				DateOfPledge DATE not null,
--				constraint [PK_PledgeID] 
--					Primary Key (PledgeID),
--				constraint [FK_Pledge_Friend]
--					Foreign Key (FriendID) References friend (FriendID)
--					)

--create table Payment(PaymentID INT not null,
--					FriendID INT not null,
--					PledgeID INT not null,
--					AmtPaid DECIMAL(6,2) not null,
--					FormOfPayment Varchar(50) not null,
--					DateOfPayment DATE not null,
--					constraint [PK_PaymentID]
--						Primary Key PaymentID,
--					constraint [FK_Payment_Friend]
--						Foreign Key FriendID,
--					constraint [FK_Payment_Address]
--						Foreign Key AddressID,
--					constraint [FK_Payment_Pledge]
--						Foreign Key PledgeID,
--					constraint [CHK_AmtPaid]
--						check (AmtPaid > 0)
--					)

					
					
--select * from Membership
--select * from fAddress
--select * from friend
--select * from person_membership_link