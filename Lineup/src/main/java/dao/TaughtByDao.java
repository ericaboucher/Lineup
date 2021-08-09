package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Student;
import beans.Teacher;

public class TaughtByDao {
    
    private static final String TABLE_NAME = "TaughtBy";
    private static final String COL_STUDENT_ID = "StudentID";
    private static final String COL_TEACHER_ID = "TeacherID";
    
    public static List<Student> getClassList(Teacher teacher) {
        List<Student> students = new ArrayList<Student>();

        try{
            // get connection to database
            Connection conn = DBConnection.getConnectionToDatabase();

            // sql query to get all students in a given course
            String sql = "select * from " + 
                    TABLE_NAME + " where " + 
                    COL_TEACHER_ID + " =?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, teacher.getEmployeeId());

            // execute query and get the result set
            ResultSet set = stmt.executeQuery();

            while (set.next()){
                Student s = StudentDao.readStudent(set.getInt(COL_STUDENT_ID));
                students.add(s);
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return students;
    }
    
    public static int enrollStudent(Student student, Teacher teacher) {
        int rowsAffected = 0;

        try{
            Connection conn = DBConnection.getConnectionToDatabase();

            //insert query
            String sql = "insert into " + 
                    TABLE_NAME + " (" + 
                    COL_STUDENT_ID + ", " + 
                    COL_TEACHER_ID + ") values (?, ?);";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, student.getId());
            stmt.setInt(2, teacher.getEmployeeId());

            rowsAffected = stmt.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return rowsAffected;
    }
    
    public static int unenrollStudent(Student student, Teacher teacher) {
        int rowsAffected = 0;
        try{
            Connection conn = DBConnection.getConnectionToDatabase();

            String sql = "delete from " + 
            		TABLE_NAME + " where " + 
            		COL_STUDENT_ID + "=? and " + 
            		COL_TEACHER_ID + " =?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, student.getId());
            stmt.setInt(2, teacher.getEmployeeId());

            rowsAffected = stmt.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return rowsAffected;
    }
}