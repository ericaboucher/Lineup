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
            //problem
        }
        this.password = pass;
    }
    public User(String email, String pass, String fName, String lName) {
        this(email, pass);
        firstName = fName;
        lastName = lName;
    }
    public User(String email, String pass, String phoneNum) {
        this(email, pass);
        if(validatePhoneNumber(phoneNum)) {
            phoneNumber = phoneNum;
        } else {
            // problem
        }
    }
    
    // Accessors
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPassword() { return password;}
    
    
    public String getFirstNameFirst() { return firstName + " " + lastName; }
    public String getLastNameFirst() { return lastName + ", " + firstName; }
    
    // Modifiers
    public void editEmail(String newEmail) { email = newEmail; }
    public void editPassword(String newPassword) { password = newPassword; }
    public void editFirstName(String newName) { firstName = newName; }
    public void editLastName(String newName) { lastName = newName; }
    
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
