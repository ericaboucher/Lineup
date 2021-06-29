package beans;

import java.util.regex.Pattern;

public abstract class User {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    
    public User() {
      super();
    }
    
    //Constructors
    public User(String email, String pass) {
        if(validateEmail(email)) {
            this.email = email;
        } else {
            //problem, probably fatal
        }
        this.password = pass;
    }
    public User(String email, String pass, String phoneNum) {
        this(email, pass);
        if(validatePhoneNumber(phoneNum)) {
            phoneNumber = phoneNum;
        } else {
            // problem, but not fatal
        }
    }
    public User(String email, String pass, String fName, String lName) {
        this(email, pass);
        firstName = fName;
        lastName = lName;
    }
    public User(String email, String pass, String fName, String lName, String phoneNum) {
        this(email, pass, fName, lName);
        if(validatePhoneNumber(phoneNum)) {
            phoneNumber = phoneNum;
        } else {
            //problem, but not fatal
        }
    }
    
    // Accessors
    protected String getEmail() { return email; }
    protected String getFirstNameFirst() { return firstName + " " + lastName; }
    protected String getLastNameFirst() { return lastName + ", " + firstName; }
    protected String getPhoneNumber() { return phoneNumber; }
    protected String getPassword() { return password; }
    
    // Modifiers
    public void editFirstName(String newName) { firstName = newName; }
    public void editLastName(String newName) { lastName = newName; }
    public void editPassword(String newPassword) { password = newPassword; }
    public boolean editEmail(String newEmail) {
        if (validateEmail(newEmail)) {
            email = newEmail;
            return true;
        }
        return false;
    }
    public boolean editPhoneNumber(String newNumber) {
        if (validatePhoneNumber(newNumber)) {
            phoneNumber = newNumber;
            return true;
        }
        return false;
    }
    
    // Verification
    public boolean verifyEmail(String e) { return e.equals(email); }
    public boolean logIn(String user, String pass) {  return (user.equals(email) && pass.equals(password)); }
    //public boolean logOut() { return true; }

    // Data validation
    private static boolean validateEmail(String e) {
        return e.matches("[a-zA-Z_0-9]+@[a-zA-Z_0-9]+[.]com");
    }
    private static boolean validatePhoneNumber(String num) {
        return num.matches("[0-9]{3}[-][0-9]{3}[-][0-9]{4}");
    }
}
