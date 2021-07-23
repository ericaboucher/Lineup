package beans;

import java.util.UUID;

public class Staff extends User {
    private final UUID employeeId;
    
    // Constructor, used when creating a completely new Staff member
    public Staff(String email, String password, String firstName, String lastName, String phoneNum) {
        super(email, password, User.STAFF, firstName, lastName, phoneNum);
        employeeId = UUID.randomUUID();
    }
    // Constructor, used when loading a Staff member from the database
    public Staff(String employeeId, String email, String password, String firstName, String lastName, String phoneNum) {
        super(email, password, User.STAFF, firstName, lastName, phoneNum);
        this.employeeId = UUID.fromString(employeeId);
    }
    
    // Accessor
    public String getEmployeeId() { return employeeId.toString(); }

}