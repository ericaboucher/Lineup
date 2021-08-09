package beans;

import dao.StaffDao;

public class Staff extends User {
    private final Integer employeeId;
    
    // Used to assign IDs
    private static int highestID;
    
    // Constructor, used when creating a completely new Staff member
    public Staff(String email, String password, String firstName, String lastName, String phoneNum) {
        super(email, password, UserType.STAFF, firstName, lastName, phoneNum);
        employeeId = newID();
    }
    // Constructor, used when loading a Staff member from the database
    public Staff(int employeeId, String email, String password, String firstName, String lastName, String phoneNum) {
        super(email, password, UserType.STAFF, firstName, lastName, phoneNum);
        this.employeeId = employeeId;
    }
    
    // Accessor
    public int getEmployeeId() { return employeeId; }
    
    // ID Generator
    private static Integer newID() {
        highestID = StaffDao.getHighestId();
        return new Integer(++highestID);
    }
}