package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Staff;
import beans.User;

public class StaffDao {

    private static final String TABLE_NAME = "staff";
    private static final String COL_ID = "id";
    private static final String COL_EMAIL= "email";

    public static List<Staff> readAllStaff() {
        List<Staff> allStaff = new ArrayList<Staff>();

        try{
            // get connection to database
            Connection conn = DBConnection.getConnectionToDatabase();

            // sql query to get all students
            String sql = "select * from " + TABLE_NAME + ";";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // execute query and get the result set
            ResultSet set = stmt.executeQuery();

            while (set.next()){
                String id = set.getString(COL_ID);
                String email = set.getString(COL_EMAIL);
                User u = UserDao.readUser(email);
                allStaff.add(new Staff(id, u.getEmail(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getPhoneNum()));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return allStaff;
    }

    public static Staff readStaffMember(String id) {
        Staff staffMember = null;

        try{
            // get connection to db
            Connection conn = DBConnection.getConnectionToDatabase();

            // query to get the staff member by employee ID (Primary key)
            String sql = "select * from " + TABLE_NAME + " where " + COL_ID + "=?;";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id.toString());

            // execute query and get the result set
            ResultSet set = stmt.executeQuery();

            while (set.next()){
                String email = set.getString(COL_EMAIL);
                User u = UserDao.readUser(email);
                staffMember = new Staff(id, u.getEmail(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getPhoneNum());
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return staffMember;
    }

    public static int createStaffMember(Staff staffMember) {
        int rowsAffected = 0;

        try{
            Connection conn = DBConnection.getConnectionToDatabase();

            //insert query
            String sql = "insert into " + 
                    TABLE_NAME + " (" + 
                    COL_ID + ", " + 
                    COL_EMAIL + ") values (?, ?);";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, staffMember.getEmployeeId());
            stmt.setString(2, staffMember.getEmail());

            rowsAffected = stmt.executeUpdate();
            
        }catch (SQLException exception){
            exception.printStackTrace();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return rowsAffected;
    }

    public static int updateStaffMember(String id, String newEmail) {
        int rowsAffected = 0;

        try{
            Connection conn = DBConnection.getConnectionToDatabase();

            String sql = "update " + 
                    TABLE_NAME + " set " + 
                    COL_EMAIL + "=? where " + 
                    COL_ID + "=?;";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newEmail);
            stmt.setString(2, id);

            rowsAffected = stmt.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return rowsAffected;
    }

    public static int deleteStaffMember(String id) {
        int rowsAffected = 0;
        try{
            Connection conn = DBConnection.getConnectionToDatabase();

            String sql = "delete from " + TABLE_NAME + " where " + COL_ID + "=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id.toString());

            rowsAffected = stmt.executeUpdate();
            
        }catch (SQLException exception){
            exception.printStackTrace();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return rowsAffected;
    }
}
