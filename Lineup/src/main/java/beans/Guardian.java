package beans;

import java.util.ArrayList;

import dao.StudentDao;

public class Guardian extends User {
    private ArrayList<Student> children;

    public Guardian(String email, String password, String firstName, String lastName, String phoneNum) {
        super(email, password, User.GUARDIAN, firstName, lastName, phoneNum);
        initChildrenList();
    }

    // Modifiers
    public boolean signChildIn(Student child) {
        return child.signIn();
    }
    public boolean signChildIn(String studentID) {
    	return StudentDao.readStudent(studentID).signIn();
    }
    
    public boolean signChildOut(Student child) {
        return child.signOut();
    }
    public boolean signChildOut(String studentID) {
    	return StudentDao.readStudent(studentID).signOut();
    }
    
    public boolean addChild(Student child) {
    	return children.add(child);
    }
    public boolean addChild(String studentID) {
    	return children.add(StudentDao.readStudent(studentID));
    }
    
    // Accessor
    public ArrayList<Student>getChildren() {
        return new ArrayList<Student>(children);
    }

    // Private helper method
    private void initChildrenList() {
        children = new ArrayList<Student>(StudentDao.readStudents(this.getEmail()));
    }
}