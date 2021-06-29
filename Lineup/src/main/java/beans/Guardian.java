package beans;

import java.util.ArrayList;

public class Guardian extends User {
    private ArrayList<Integer> children;
    
    // Constructors
    public Guardian() {
        initChildrenList();
    }
    public Guardian(String email, String pass) {
        super(email, pass, User.GUARDIAN);
        initChildrenList();
    }
    public Guardian(String email, String pass, String phoneNum) {
        super(email, pass, User.GUARDIAN, phoneNum);
        initChildrenList();
    }
    public Guardian(String email, String pass, String fName, String lName){
        super(email, pass, User.GUARDIAN, fName, lName);
        initChildrenList();
    }
    public Guardian(String email, String pass, String fName, String lName, String phoneNum) {
        super(email, pass, User.GUARDIAN, fName, lName, phoneNum);
        initChildrenList();
    }

    // Modifiers
    public boolean signChildIn(Integer child) {
        return false;
    }
    public boolean signChildOut(Integer child) {
        return false;
    }
    public boolean addChild(Integer child) {
        children.add(child);
        return true;
    }

    private void initChildrenList() {
        children = new ArrayList<Integer>();
    }
}