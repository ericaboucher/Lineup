package beans;

public class Teacher extends Staff {

    // Constructor, used when creating a completely new Teacher
    public Teacher(String email, String password, String firstName, String lastName, String phoneNum) {
        super(email, password, firstName, lastName, phoneNum);
        super.setUserType(User.TEACHER);
    }
    // Constructor, used when loading a Teacher from the database
    public Teacher(String employeeId, String email, String password, String firstName, String lastName, String phoneNum) {
        super(employeeId, email, password, firstName, lastName, phoneNum);
        super.setUserType(User.TEACHER);
    }
}
