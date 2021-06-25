package beans;

public abstract class User {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    
    //Constructors
    public User(String email, String pass) {
        this.email = email;
        this.password = pass;
    }
    public User(String email, String pass, String fName, String lName) {
        this(email, pass);
        firstName = fName;
        lastName = lName;
    }
    
    // Accessors
    public String getEmail() { return email; }
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

}
