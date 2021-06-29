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

public class ApplicationDao implements ApplicationService {

  @Override
  public List<User> readUsers() {
    User user = null;
    List<User> users = new ArrayList<User>();

    try{

      // get connection to database
      Connection conn = DBConnection.getConnectionToDatabase();

      // sql query to get all users
      String sql = "select * from users;";
      PreparedStatement stmt = conn.prepareStatement(sql);

      // execute query , get resultset and return users
      ResultSet set = stmt.executeQuery();

      while (set.next()){

        user = new Guardian();
        user.editEmail(set.getString("email"));
        user.editFirstName(set.getString("firstName"));
        user.editLastName(set.getString("lastName"));
        user.editPassword(set.getString("password"));

      }

    }catch (SQLException exception){

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
        user.editEmail(set.getString("Email"));
        user.editFirstName(set.getString("FirstName"));
        user.editLastName(set.getString("LastName"));
        user.editPassword(set.getString("Password"));

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
      String sql = "insert into users (email, password, userType, firstName, lastName, phoneNum) values (?, ?, ?, ?, ?, ?);";
      
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, user.getEmail());
      stmt.setString(2, user.getPassword());
      stmt.setString(3, user.getUserType());
      stmt.setString(4, user.getFirstName());
      stmt.setString(5, user.getLastName());
      stmt.setString(6, user.getPhoneNumber());

      rowsAffected = stmt.executeUpdate();

    }catch (SQLException exception){
      exception.printStackTrace();
      
    }catch (Exception exception){
    	exception.printStackTrace();
    }
    
    return rowsAffected;
  }

  @Override
  public void updateUser(User user) {

    try{

      Connection conn = DBConnection.getConnectionToDatabase();

      String sql = "update users set FirstName=?, LastName=?, password=? where Email=?;";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, user.getFirstName());
      stmt.setString(2, user.getLastName());
      stmt.setString(3, user.getPassword());
      stmt.setString(4, user.getEmail());

      stmt.execute();

    }catch (SQLException exception){

      exception.printStackTrace();

    }catch (Exception exception){

      exception.printStackTrace();

    }

  }

  @Override
  public void deleteUser(String email) {

    try{

      Connection conn = DBConnection.getConnectionToDatabase();

      String sql = "delete from users where Email=?;";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, email);

      stmt.execute();

    }catch (SQLException exception){

      exception.printStackTrace();

    }catch (Exception exception){

      exception.printStackTrace();

    }

  }

  @Override
  public void createOrUpdateUser(User user) {

User localUser = readUser(user.getEmail());
if (localUser == null) {
  createUser(user);
} else {
  updateUser(user);
}

  }

}
