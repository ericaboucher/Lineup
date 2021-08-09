package beans;

import java.util.ArrayList;

import dao.StudentDao;
import services.Observed;
import services.Observer;

public class Student implements Observed {
	private final Integer studentId;
    private final String firstName;
    private final String lastName;
    private final String guardianEmail;
    private boolean signedIn;
    
    // Needed for the Observer pattern
    private ArrayList<Observer> observerList;
    
    // Used to assign IDs
    private static int highestID;
    
    // Used when creating a new student from scratch
    public Student(String firstName, String lastName, String guardianEmail) {
        studentId = newID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.guardianEmail = guardianEmail;
        signedIn = false;
    }
    
    // Used when creating a Student object from the database
    public Student(int studentId, String firstName, String lastName, String guardianEmail, boolean signedIn) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.guardianEmail = guardianEmail;
        this.signedIn = signedIn;
    }
    
    // Accessors
    public int getId() { return studentId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getGuardianEmail() { return guardianEmail; }
    public boolean isSignedIn() { return signedIn; }
    
    // ID Generator
    private static Integer newID() {
        highestID = StudentDao.getHighestId();
        return new Integer(++highestID);
    }
    
    // Public sign-in/sign-out methods
    public boolean signIn() {
        if(signedIn == true) {
            return false;
        } else {
            signedIn = true;
            this.sendUpdate();
            return true;
        }
    }
    
    public boolean signOut() {
        if(signedIn == false) {
            return false;
        } else {
            signedIn = false;
            this.sendUpdate();
            return true;
        }
    }
    
    // Used for the Observer pattern
    @Override
    public void watch(Observer o) {
        observerList.add(o);
    }

    @Override
    public void unWatch(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void sendUpdate() {
        for (Observer o : observerList) {
            o.update(this);
        }
    }
}