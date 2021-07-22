package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Guardian;
import beans.User;
import beans.Staff;

public class UserDao {
    
    private static final String TABLE_NAME = "Users";
    private static final String COL_EMAIL = "Email";
    private static final String COL_PASSWORD = "UserPassword";
    private static final String COL_USER_TYPE = "UserType";
    private static final String COL_FIRST_NAME = "FirstName";
    private static final String COL_LAST_NAME = "LastName";
    private static final String COL_PHONE_NUM = "PhoneNum";

    public static List<User> readUsers() {
        List<User> users = new ArrayList<User>();

        try{
            // get connection to database
            Connection conn = DBConnection.getConnectionToDatabase();

            // sql query to get all users
            String sql = "select * from " + 
                    TABLE_NAME + ";";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // execute query , get resultset and return users
            ResultSet set = stmt.executeQuery();

            while (set.next()){
                String email = set.getString(COL_EMAIL);
                String password = set.getString(COL_PASSWORD);
                String userType = set.getString(COL_USER_TYPE);
                String firstName = set.getString(COL_FIRST_NAME);
                String lastName = set.getString(COL_LAST_NAME);
                String phoneNum = set.getString(COL_PHONE_NUM);
                if(userType.equals(User.GUARDIAN)) {
                    users.add(new Guardian(email, password, firstName, lastName, phoneNum));
                } else if (userType.equals(User.STAFF)) {
                    users.add(new Staff(email, password, firstName, lastName, phoneNum));
                } else {
                    //invalid user type
                }
            }
        }	catch (SQLException exception){
            exception.printStackTrace();
        }

        return users;
    }

    public static User readUser(String email) {
        try{
            // get connection to db
            Connection conn = DBConnection.getConnectionToDatabase();

            // query to get the user by email(Primary key)
            String sql = "select * from " + 
                    TABLE_NAME + " where " + 
                    COL_EMAIL + "=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet set = stmt.executeQuery();

            while (set.next()){
                String password = set.getString(COL_PASSWORD);
                String userType = set.getString(COL_USER_TYPE);
                String firstName = set.getString(COL_FIRST_NAME);
                String lastName = set.getString(COL_LAST_NAME);
                String phoneNum = set.getString(COL_PHONE_NUM);
                if(userType.equals(User.GUARDIAN)) {
                    return new Guardian(email, password, firstName, lastName, phoneNum);
                } else if (userType.equals(User.STAFF)) {
                    return new Staff(email, password, firstName, lastName, phoneNum);
                } else {
                    //invalid user type
                }
            }

        }catch (SQLException exception){

            exception.printStackTrace();

        }catch (Exception exception){

            exception.printStackTrace();

        }
        return null;
    }

    public static int createUser(User user) {
        int rowsAffected = 0;

        try{
            Connection conn = DBConnection.getConnectionToDatabase();

            //insert query
            String sql = "insert into " + 
                    TABLE_NAME +  " (" + 
                    COL_EMAIL + ", " + 
                    COL_PASSWORD + ", " + 
                    COL_USER_TYPE + ", " + 
                    COL_FIRST_NAME + ", " + 
                    COL_LAST_NAME + ", " + 
                    COL_PHONE_NUM + ") values (?, ?, ?, ?, ?, ?);";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getUserType());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getPhoneNum());

            rowsAffected = stmt.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();

        }catch (Exception exception){
            exception.printStackTrace();
        }
        return rowsAffected;
    }

    public static int updateUser(String firstName, String lastName, String newEmail, String phoneNum, String password,
            String oldEmail) {
        int rowsAffected = 0;

        try{
            Connection conn = DBConnection.getConnectionToDatabase();

            String sql = "update " + TABLE_NAME + " set " + 
                    COL_PASSWORD +  "=?, " + 
                    COL_FIRST_NAME +  "=?, " + 
                    COL_LAST_NAME + "=?, " + 
                    COL_PHONE_NUM + "=?, " + 
                    COL_EMAIL + "=? where " + 
                    COL_EMAIL + "=?;";
            
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, password);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, phoneNum);
            stmt.setString(5, newEmail);
            stmt.setString(6, oldEmail);

            rowsAffected = stmt.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        System.out.println("Rows affected: " + rowsAffected);
        return rowsAffected;
    }

    public static int deleteUser(String email) {
        int rowsAffected = 0;
        Connection conn = null;
        try{
            conn = DBConnection.getConnectionToDatabase();

            String sql = "delete from " + 
                    TABLE_NAME + " where " + 
                    COL_EMAIL + "=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            rowsAffected = stmt.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }catch (Exception exception){
            exception.printStackTrace();
        } 
        return rowsAffected;
    }

    public static boolean validateUser(String email, String password) {
        boolean isValidUser = false;
        try {
            //get db connection
            Connection conn = DBConnection.getConnectionToDatabase();

            //select query
            String sql = "select * from " + 
                    TABLE_NAME + " where " + 
                    COL_EMAIL + "=? and " + 
                    COL_PASSWORD + "=?";

            //set parameters with PreparedStatement
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet set = stmt.executeQuery();
            while(set.next()) {
                isValidUser = true;
            }
        }	
        catch(SQLException e) {
            e.printStackTrace();
        }
        return isValidUser;
    }
}