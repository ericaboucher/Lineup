package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Guardian;
import beans.User;
import services.ApplicationService;

public class UserDao implements ApplicationService {

    @Override
    public List<User> readUsers() {
        User user = null;
        List<User> users = new ArrayList<User>();

        try{
            // get connection to database
            Connection conn = DBConnection.getConnectionToDatabase();

            // sql query to get all users
            String sql = "select * from user;";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // execute query , get resultset and return users
            ResultSet set = stmt.executeQuery();

            while (set.next()){
                user = new Guardian();
                user.editEmail(set.getString("email"));
                user.editPassword(set.getString("password"));
                user.setUserType(set.getString("userType"));
                user.editFirstName(set.getString("firstName"));
                user.editLastName(set.getString("lastName"));
                user.editPhoneNumber(set.getString("phoneNum"));
            }
        }	catch (SQLException exception){
            exception.printStackTrace();
        }

        return users;
    }

    @Override
    public User readUser(String email) {

        User user = null;

        try{

            // get connection to db
            Connection conn = DBConnection.getConnectionToDatabase();

            // query to get the user by email(Primary key)
            String sql = "select * from user where email=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet set = stmt.executeQuery();

            while (set.next()){

                user = new Guardian();
                user.editEmail(set.getString("email"));
                user.editPassword(set.getString("password"));
                user.setUserType(set.getString("userType"));
                user.editFirstName(set.getString("firstName"));
                user.editLastName(set.getString("lastName"));
                user.editPhoneNumber(set.getString("phoneNum"));

            }

        }catch (SQLException exception){

            exception.printStackTrace();

        }catch (Exception exception){

            exception.printStackTrace();

        }

        return user;
    }

    @Override
    public int createUser(User user) {
        int rowsAffected = 0;

        try{

            Connection conn = DBConnection.getConnectionToDatabase();

            //insert query
            String sql = "insert into user (email, password, userType, firstName, lastName, phoneNum) values (?, ?, ?, ?, ?, ?);";

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

    @Override
    public int updateUser(String firstName, String lastName, String newEmail, String phoneNum, String password,
            String oldEmail) {
        int rowsAffected = 0;

        try{
            Connection conn = DBConnection.getConnectionToDatabase();

            String sql = "update user set password=?, firstName=?, lastName=?, phoneNum=?, email=? where email=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, password);
            //stmt.setString(2, user.getUserType());
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
    
    @Override
    public int deleteUser(String email) {
        int rowsAffected = 0;
        Connection conn = null;
        try{
            conn = DBConnection.getConnectionToDatabase();

            String sql = "delete from user where email=?";
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

    public boolean validateUser(String email, String password) {
        boolean isValidUser = false;
        try {
            //get db connection
            Connection conn = DBConnection.getConnectionToDatabase();

            //select query
            String sql = "select * from user where email=? and password=?";

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