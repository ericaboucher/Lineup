package beans;

import java.util.ArrayList;

import dao.StudentDao;

public class Guardian extends User {
    private ArrayList<Student> children;
    
    // Constructors
//    public Guardian(String email, String password) {
//        super(email, password, User.GUARDIAN);
//        initChildrenList();
//    }
//    public Guardian(String email, String password, String phoneNum) {
//        super(email, password, User.GUARDIAN, phoneNum);
//        initChildrenList();
//    }
//    public Guardian(String email, String password, String firstName, String lastName){
//        super(email, password, User.GUARDIAN, firstName, lastName);
//        initChildrenList();
//    }
    public Guardian(String email, String password, String firstName, String lastName, String phoneNum) {
        super(email, password, User.GUARDIAN, firstName, lastName, phoneNum);
        initChildrenList();
    }
//    public Guardian(Guardian toCopy) {
//        super(toCopy);
//        this.children = toCopy.getChildren();
//    }

    // Modifiers
    public boolean signChildIn(Student child) {
        return child.signIn();
    }
    public boolean signChildOut(Student child) {
        return child.signOut();
    }
    public boolean addChild(Student child) {
        children.add(child);
        return true;
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