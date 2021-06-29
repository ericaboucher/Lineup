package beans;

public abstract class User {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String userType;
    
    // For determining user type
    public static final String GUARDIAN = "guardian";
    public static final String STAFF = "staff";
    
    //Constructors
    public User() {}
    public User(String email, String pass, String type) {
        if(validateEmail(email)) {
            this.email = email;
        } else {
            //problem, probably fatal
        }
        this.password = pass;
    }
    public User(String email, String pass, String type, String phoneNum) {
        this(email, pass, type);
        if(validatePhoneNumber(phoneNum)) {
            phoneNumber = phoneNum;
        } else {
            // problem, but not fatal
        }
    }
    public User(String email, String pass, String type, String fName, String lName) {
        this(email, pass, type);
        firstName = fName;
        lastName = lName;
    }
    public User(String email, String pass, String type, String fName, String lName, String phoneNum) {
        this(email, pass, type, fName, lName);
        if(validatePhoneNumber(phoneNum)) {
            phoneNumber = phoneNum;
        } else {
            //problem, but not fatal
        }
    }
    
    // Accessors
    public String getEmail() { return email; }
    public String getUserType() { return userType; }
    public String getFirstNameFirst() { return firstName + " " + lastName; }
    public String getLastNameFirst() { return lastName + ", " + firstName; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getPassword() { return password; }
    
    // Modifiers
    public void setUserType(String newType) { userType = newType; }
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
