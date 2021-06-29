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
  public User readUser(String id) {

//    User user = null;
//
//    try{
//
//      // get connection to db
//      Connection conn = DBConnection.getConnectionToDatabase();
//
//      // query to get the user
//      String sql = "select * from user";
//
//    }catch (SQLException exception){
//
//      exception.printStackTrace();
//
//    }catch (Exception exception){
//
//      exception.printStackTrace();
//
//    }
//
  return null;
  }

  @Override
  public void createUser(User user) {
    // TODO Auto-generated method stub

  }

  @Override
  public void updateUser(User user) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteUser(String id) {
    // TODO Auto-generated method stub

  }

  @Override
  public void createOrUpdateUser(User user) {
    // TODO Auto-generated method stub

  }

}
