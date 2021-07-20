package beans;

public class Student{
    private final UUID studentID;
    private final String firstName;
    private final String lastName;
    private boolean signedIn;
    
    public Student(String fName, String lName) {
        studentId = new UUID();
        firstName = fName;
        lastName = lName;
        signedIn = false;
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