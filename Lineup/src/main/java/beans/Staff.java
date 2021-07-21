package beans;

public class Staff extends User {
    // Constructors
    public Staff(String email, String password) {
        super(email, password, User.STAFF);
    }
    public Staff(String email, String password, String phoneNum) {
        super(email, password, User.STAFF, phoneNum);
    }
    public Staff(String email, String password, String firstName, String lastName){
        super(email, password, User.STAFF, firstName, lastName);
    }
    public Staff(String email, String password, String firstName, String lastName, String phoneNum) {
        super(email, password, User.STAFF, firstName, lastName, phoneNum);
    }
    public Staff(Staff toCopy) {
        super(toCopy);
    }
}