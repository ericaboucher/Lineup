package beans;

import java.util.ArrayList;

import dao.TaughtByDao;
import services.Observer;

public class Teacher extends Staff implements Observer {

    ArrayList<Student> students;
    
    // Constructor, used when creating a completely new Teacher
    public Teacher(String email, String password, String firstName, String lastName, String phoneNum) {
        super(email, password, firstName, lastName, phoneNum);
        super.setUserType(User.TEACHER);
        initClassList();
    }
    // Constructor, used when loading a Teacher from the database
    public Teacher(String employeeId, String email, String password, String firstName, String lastName, String phoneNum) {
        super(employeeId, email, password, firstName, lastName, phoneNum);
        super.setUserType(User.TEACHER);
        initClassList();
    }
    // Copy Constructor
    public Teacher(Teacher toCopy) {
        this(toCopy.getEmployeeId(), toCopy.getEmail(), toCopy.getPassword(), toCopy.getFirstName(), toCopy.getLastName(), toCopy.getPhoneNum());
        students = toCopy.getClassList();
    }
    
    // Accessor
    public ArrayList<Student> getClassList() {
        return new ArrayList<Student>(students);
    }
    
    // Private helper method
    private void initClassList() {
    	students = new ArrayList<Student>(TaughtByDao.getClassList(this));
    }
    
	@Override
	public void update(Student student) {
		for (Student s : students) {
			if (s.getId() == student.getId()) {
				if(student.isSignedIn()) {
					s.signIn();
				} else {
					s.signOut();
				}
			}
		}
	}
}