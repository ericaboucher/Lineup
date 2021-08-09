package beans;

public abstract class User {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private UserType userType;
    
    // For determining user type
//    public static final String GUARDIAN = "guardian";
//    public static final String STAFF = "staff";
//    public static final String TEACHER = "teacher";
    
    //Constructors
    public User(String email, String password, String userType) {
        if(validateEmail(email)) {
            this.email = email;
        } else {
            //problem, probably fatal
        }
        this.password = password;
    }
//    public User(String email, String password, String userType, String phoneNum) {
//        this(email, password, userType);
//        if(validatePhoneNumber(phoneNum)) {
//            this.phoneNum = phoneNum;
//        } else {
//            // problem, but not fatal
//        }
//    }
//    public User(String email, String password, String userType, String firstName, String lastName) {
//        this(email, password, userType);
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }
    public User(String email, String password, UserType userType, String firstName, String lastName, String phoneNumber) throws IllegalArgumentException {
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        if(validatePhoneNumber(phoneNumber)) {
            phoneNum = phoneNumber;
        } else {
            throw new IllegalArgumentException();
        }
    }
//    public User(User toCopy) {
//        this(toCopy.getEmail(), toCopy.getPassword(), toCopy.getUserType(), toCopy.getFirstName(), toCopy.getLastName(), toCopy.getPhoneNum());
//    }
    
    // Accessors
    public String getEmail() { return email; }
    public UserType getUserType() { return userType; }
//    public String getFirstNameFirst() { return firstName + " " + lastName; }
//    public String getLastNameFirst() { return lastName + ", " + firstName; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhoneNum() { return phoneNum; }
    public String getPassword() { return password; }
    
    // Modifiers
    protected void setUserType(UserType newType) { userType = newType; }
//    public void setFirstName(String newFirstName) { firstName = newFirstName; }
//    public void setLastName(String newLastName) { lastName = newLastName; }
//    public void setPassword(String newPassword) { password = newPassword; }
//    public boolean setEmail(String newEmail) {
//        if (validateEmail(newEmail)) {
//            email = newEmail;
//            return true;
//        }
//        return false;
//    }
//    public boolean setPhoneNumber(String newNumber) {
//        if (validatePhoneNumber(newNumber)) {
//            phoneNum = newNumber;
//            return true;
//        }
//        return false;
//    }
    
    // Verification
//    public boolean verifyEmail(String e) { return e.equals(email); }
//    public boolean logIn(String user, String password) {  return (user.equals(email) && password.equals(password)); }

    // Data validation
    private static boolean validateEmail(String e) {
        return e.matches("[a-zA-Z_0-9]+@[a-zA-Z_0-9]+[.]com");
    }
    private static boolean validatePhoneNumber(String num) {
        return num.matches("[0-9]{3}[-][0-9]{3}[-][0-9]{4}");
    }
    
    public enum UserType {
        GUARDIAN("GUARDIAN"),
        STAFF("STAFF"),
        TEACHER("TEACHER");
        
        String description;
        
        UserType(String description) {
            this.description = description;
        }
        
        public String toString() { return this.description; }
    }
}