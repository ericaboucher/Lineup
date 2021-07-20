package beans;

public class Student{
    private final UUID studentID;
    private final String firstName;
    private final String lastName;
    private final Guardian guardian;
    private boolean signedIn;
    
    // Used when creating a new student from scratch
    public Student(String firstName, String lastName, Guardian guardian) {
        studentId = new UUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.guardian = new Guardian(guardian);
        signedIn = false;
    }
    
    //Used when creating a Student object from the database
    public Student(UUID studentId, String firstName, String lastName, Guardian guardian, boolean signedIn) {
        this(firstName, lastName, guardian);
        this.studentId = studentId;
        this.signedIn = signedId;
    }
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