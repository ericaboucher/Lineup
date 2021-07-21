package beans;

import java.util.ArrayList;

public class Teacher extends Staff {

    ArrayList<Course> classesTaught;
    
    // Constructor, used when creating a completely new Teacher
    public Teacher(String email, String password, String firstName, String lastName, String phoneNum) {
        super(email, password, firstName, lastName, phoneNum);
        super.setUserType(User.TEACHER);
        initClassesTaught();
    }
    // Constructor, used when loading a Teacher from the database
    public Teacher(String employeeId, String email, String password, String firstName, String lastName, String phoneNum) {
        super(employeeId, email, password, firstName, lastName, phoneNum);
        super.setUserType(User.TEACHER);
        initClassesTaught();
    }
    // Copy Constructor
    public Teacher(Teacher toCopy) {
        this(toCopy.getEmployeeId(), toCopy.getEmail(), toCopy.getPassword(), toCopy.getFirstName(), toCopy.getLastName(), toCopy.getPhoneNum());
        classesTaught = toCopy.getClassesTaught();
    }
    
    // Accessor
    public ArrayList<Course> getClassesTaught() {
        return new ArrayList<Course>(classesTaught);
    }
    
    // Private helper method
    private void initClassesTaught() {
        
    }
}