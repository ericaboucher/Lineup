package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Student;

public class StudentDao {

    private static final String TABLE_NAME = "student";
    private static final String COL_ID = "id";
    private static final String COL_FIRST_NAME = "firstName";
    private static final String COL_LAST_NAME = "lastName";
    private static final String COL_GUARDIAN_EMAIL = "guardian";
    private static final String COL_SIGNED_IN = "signedIn";

    public static List<Student> readStudents() {
        List<Student> students = new ArrayList<Student>();

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
                String firstName = set.getString(COL_FIRST_NAME);
                String lastName = set.getString(COL_LAST_NAME);
                String guardianEmail = set.getString(COL_GUARDIAN_EMAIL);
                boolean signedIn = set.getBoolean(COL_SIGNED_IN);
                students.add(new Student(id, firstName, lastName, guardianEmail, signedIn));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return students;
    }
    
    public static List<Student> readStudents(String guardianEmail) {
        List<Student> students = new ArrayList<Student>();

        try{
            // get connection to database
            Connection conn = DBConnection.getConnectionToDatabase();

            // sql query to get all students
            String sql = "select * from " + TABLE_NAME + " where " + COL_GUARDIAN_EMAIL + " =?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, guardianEmail);

            // execute query and get the result set
            ResultSet set = stmt.executeQuery();

            while (set.next()){
                String id = set.getString(COL_ID);
                String firstName = set.getString(COL_FIRST_NAME);
                String lastName = set.getString(COL_LAST_NAME);
                boolean signedIn = set.getBoolean(COL_SIGNED_IN);
                students.add(new Student(id, firstName, lastName, guardianEmail, signedIn));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return students;
    }

    public static Student readStudent(String id) {
        Student student = null;

        try{
            // get connection to db
            Connection conn = DBConnection.getConnectionToDatabase();

            // query to get the student by student ID (Primary key)
            String sql = "select * from student where id=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id.toString());

            // execute query and get the result set
            ResultSet set = stmt.executeQuery();

            while (set.next()){
                String firstName = set.getString(COL_FIRST_NAME);
                String lastName = set.getString(COL_LAST_NAME);
                String guardianEmail = set.getString(COL_GUARDIAN_EMAIL);
                boolean signedIn = set.getBoolean(COL_SIGNED_IN);
                student = new Student(id, firstName, lastName, guardianEmail, signedIn);
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return student;
    }

    public static int createStudent(Student student) {
        int rowsAffected = 0;

        try{
            Connection conn = DBConnection.getConnectionToDatabase();

            //insert query
            String sql = "insert into student (id, firstName, lastName, guardianEmail, signedIn) values (?, ?, ?, ?, ?);";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getId());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getLastName());
            stmt.setString(4, student.getGuardianEmail());
            stmt.setBoolean(5, student.isSignedIn());

            rowsAffected = stmt.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return rowsAffected;
    }

    public static int updateStudent(String id, String newFirstName, String newLastName, String newGuardian, Boolean signedIn) {
        int rowsAffected = 0;

        try{
            Connection conn = DBConnection.getConnectionToDatabase();

            String sql = "update student set firstName=?, lastName=?, guardian=?, signedIn=? where id=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, newFirstName);
            stmt.setString(2, newLastName);
            stmt.setString(3, newGuardian);
            stmt.setBoolean(4, signedIn);
            stmt.setString(5, id);

            rowsAffected = stmt.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return rowsAffected;
    }

    public static int deleteStudent(String id) {
        int rowsAffected = 0;
        try{
            Connection conn = DBConnection.getConnectionToDatabase();

            String sql = "delete from student where id=?;";
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
