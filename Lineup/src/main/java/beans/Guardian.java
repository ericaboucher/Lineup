package beans;

import java.util.ArrayList;

import dao.StudentDao;

public class Guardian extends User {
    private ArrayList<Student> children;

    public Guardian(String email, String password, String firstName, String lastName, String phoneNum) {
        super(email, password, UserType.GUARDIAN, firstName, lastName, phoneNum);
        initChildrenList();
    }

    // Modifiers
    public boolean signChildIn(Student child) {
        return child.signIn();
    }
//    public boolean signChildIn(int studentID) {
//        return StudentDao.readStudent(studentID).signIn();
//    }
    public boolean signChildOut(Student child) {
        return child.signOut();
    }
//    public boolean signChildOut(int studentID) {
//        return StudentDao.readStudent(studentID).signOut();
//    }
    public boolean addChild(Student child) {
        children.add(child);
        return true;
    }
//    public boolean addChild(int studentID) {
//        return children.add(StudentDao.readStudent(studentID));
//    }
//    
//    // Accessor
    public ArrayList<Student> getChildren() {
        return new ArrayList<Student>(children);
    }

    // Private helper method
    private void initChildrenList() {
        children = new ArrayList<Student>(StudentDao.readStudents(this.getEmail()));
    }
}