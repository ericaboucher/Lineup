package beans;

import java.util.ArrayList;

public class Guardian extends User {
    private ArrayList<Integer> children;
    
    // Constructors
    public Guardian(String email, String pass) {
        super(email, pass);
        children = new ArrayList<Integer>();
    }
    public Guardian(String email, String pass, String fName, String lName){
        super(email, pass, fName, lName);
        children = new ArrayList<Integer>();
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
}
    private ArrayList<Integer> children;
    
    // Constructors
    public Guardian(String email, String pass) {
        super(email, pass);
        children = new ArrayList<Integer>();
    }
    public Guardian(String email, String pass, String fName, String lName){
        super(email, pass, fName, lName);
        children = new ArrayList<Integer>();
    }
    
    // Modifiers
    public boolean signChildIn(Integer child) {
        return children.contains(child);
    }
    public boolean signChildOut(Integer child) {
        return children.contains(child);
    }
    public boolean addChild(Integer child) {
        children.add(child);
        return true;
    }
}