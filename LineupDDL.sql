USE lineup;
# DDL for the Users table
CREATE TABLE IF NOT EXISTS Users (
	Email varchar(64) PRIMARY KEY,
    UserPassword varchar(64),
    UserType varchar(16),
    FirstName varchar(24),
    LastName varchar(24),
    PhoneNum varchar(10)
);

# DDL for the StaffMembers table
CREATE TABLE IF NOT EXISTS StaffMembers (
	EmployeeID varchar(32) PRIMARY KEY,
    Email varchar(64),
    FOREIGN KEY (Email) REFERENCES Users(Email)
);

# DDL for the Students table
# Not adding the FOREIGN KEY constraint for the GuardianEmail, because students are added by staff before they can be "claimed" by their parent/guardian
CREATE TABLE IF NOT EXISTS Students (
	StudentID varchar(32) PRIMARY KEY,
    FirstName varchar(24),
    LastName varchar(24),
    GuardianEmail varchar(64),
    SignedIn boolean
);

# DDL for the Enrollment table
CREATE TABLE IF NOT EXISTS TaughtBy (
	StudentID varchar(32),
    TeacherID varchar(32),
    FOREIGN KEY (StudentID) REFERENCES Students(StudentID),
    FOREIGN KEY (TeacherID) REFERENCES StaffMembers(EmployeeID)
);
