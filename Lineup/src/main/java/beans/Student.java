package beans;

import java.util.ArrayList;
import java.util.UUID;

import services.Observed;
import services.Observer;

public class Student implements Observed {
	private final UUID studentId;
    private final String firstName;
    private final String lastName;
    private final String guardianEmail;
    private boolean signedIn;
    
    // Needed for the Observer pattern
    private ArrayList<Observer> observerList;
    
    // Used when creating a new student from scratch
    public Student(String firstName, String lastName, String guardianEmail) {
        studentId = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.guardianEmail = guardianEmail;
        signedIn = false;
        
        // Initialize the observer list
        observerList = new ArrayList<Observer>();
    }
    
    // Used when creating a Student object from the database
    public Student(String studentId, String firstName, String lastName, String guardianEmail, boolean signedIn) {
        this.studentId = UUID.fromString(studentId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.guardianEmail = guardianEmail;
        this.signedIn = signedIn;
        
        // Initialize the observer list
        observerList = new ArrayList<Observer>();
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
            sendUpdate();
            return true;
        }
    }
    
    public boolean signOut() {
        if(signedIn == false) {
            return false;
        } else {
            signedIn = false;
            sendUpdate();
            return true;
        }
    }

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