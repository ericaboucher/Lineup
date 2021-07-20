package beans;

import java.util.UUID;

public class Student{
	private UUID studentId;
    private final String firstName;
    private final String lastName;
    private final String guardianEmail;
    private boolean signedIn;
    
    // Used when creating a new student from scratch
    public Student(String firstName, String lastName, String guardianEmail) {
        studentId = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.guardianEmail = guardianEmail;
        signedIn = false;
    }
    
    // Used when creating a Student object from the database
    public Student(String studentId, String firstName, String lastName, String guardianEmail, boolean signedIn) {
        this(firstName, lastName, guardianEmail);
        this.studentId = UUID.fromString(studentId);
        this.signedIn = signedIn;
    }
    
    // Accessors
    public String getId() { return studentId.toString(); }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getGuardianEmail() { return guardianEmail; }
    public boolean isSignedIn() { return signedIn; }
    
    // Public sign-in/sign-out methods
    public boolean signIn() {
        if(signedIn == true) {
            return false;
        } else {
            signedIn = true;
            return true;
        }
    }
    
    public boolean signOut() {
        if(signedIn == false) {
            return false;
        } else {
            signedIn = false;
            return true;
        }
    }
}