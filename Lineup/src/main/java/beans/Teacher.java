package beans;

import java.util.ArrayList;

import dao.CourseDao;

public class Teacher extends Staff {

    ArrayList<Course> coursesTaught;
    
    // Constructor, used when creating a completely new Teacher
    public Teacher(String email, String password, String firstName, String lastName, String phoneNum) {
        super(email, password, firstName, lastName, phoneNum);
        super.setUserType(User.TEACHER);
        initCoursesTaught();
    }
    // Constructor, used when loading a Teacher from the database
    public Teacher(String employeeId, String email, String password, String firstName, String lastName, String phoneNum) {
        super(employeeId, email, password, firstName, lastName, phoneNum);
        super.setUserType(User.TEACHER);
        initCoursesTaught();
    }
    // Copy Constructor
    public Teacher(Teacher toCopy) {
        this(toCopy.getEmployeeId(), toCopy.getEmail(), toCopy.getPassword(), toCopy.getFirstName(), toCopy.getLastName(), toCopy.getPhoneNum());
        coursesTaught = toCopy.getCoursesTaught();
    }
    
    // Accessor
    public ArrayList<Course> getCoursesTaught() {
        return new ArrayList<Course>(coursesTaught);
    }
    
    // Private helper method
    private void initCoursesTaught() {
        coursesTaught = new ArrayList<Course>(CourseDao.getCourses(this));
    }
}