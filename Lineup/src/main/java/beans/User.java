package beans;

public abstract class User {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String userType;
    
    // For determining user type
    public static final String GUARDIAN = "guardian";
    public static final String STAFF = "staff";
    public static final String TEACHER = "teacher";
    
    //Constructors
    public User(String email, String password, String userType) {
        if(validateEmail(email)) {
            this.email = email;
        } else {
            //problem, probably fatal
        }
        this.password = password;
    }
    public User(String email, String password, String userType, String phoneNum) {
        this(email, password, userType);
        if(validatePhoneNumber(phoneNum)) {
            this.phoneNum = phoneNum;
        } else {
            // problem, but not fatal
        }
    }
    public User(String email, String password, String userType, String firstName, String lastName) {
        this(email, password, userType);
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public User(String email, String password, String userType, String firstName, String lastName, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        if(validatePhoneNumber(phoneNumber)) {
            phoneNum = phoneNumber;
        } else {
            //problem, but not fatal
        }
    }
    public User(User toCopy) {
        this(toCopy.getEmail(), toCopy.getPassword(), toCopy.getUserType(), toCopy.getFirstName(), toCopy.getLastName(), toCopy.getPhoneNum());
    }
    
    // Accessors
    public String getEmail() { return email; }
    public String getUserType() { return userType; }
    public String getFirstNameFirst() { return firstName + " " + lastName; }
    public String getLastNameFirst() { return lastName + ", " + firstName; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhoneNum() { return phoneNum; }
    public String getPassword() { return password; }
    
    // Modifiers
    protected void setUserType(String newType) { userType = newType; }
    public void editFirstName(String newFirstName) { firstName = newFirstName; }
    public void editLastName(String newLastName) { lastName = newLastName; }
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
            phoneNum = newNumber;
            return true;
        }
        return false;
    }
    
    // Verification
    public boolean verifyEmail(String e) { return e.equals(email); }
    public boolean logIn(String user, String password) {  return (user.equals(email) && password.equals(password)); }

    // Data validation
    private static boolean validateEmail(String e) {
        return e.matches("[a-zA-Z_0-9]+@[a-zA-Z_0-9]+[.]com");
    }
    private static boolean validatePhoneNumber(String num) {
        return num.matches("[0-9]{3}[-][0-9]{3}[-][0-9]{4}");
    }
}