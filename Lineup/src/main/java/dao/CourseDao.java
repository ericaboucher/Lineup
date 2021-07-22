package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Course;
import beans.Student;
import beans.Teacher;

public class CourseDao {
    private static final String TABLE_COURSES = "Courses";
    private static final String COL_CODE = "CourseCode";
    private static final String COL_SUBJECT = "CourseSubject";
    private static final String COL_INSTRUCTOR = "InstructorID";
    
    private static final String TABLE_ENROLLMENT = "Enrollment";
    private static final String COL_STUDENT_ID = "StudentID";
    private static final String COL_CLASS_CODE = "CourseCode";
    
    public static List<Student> getEnrollment(String classCode) {
        List<Student> students = new ArrayList<Student>();

        try{
            // get connection to database
            Connection conn = DBConnection.getConnectionToDatabase();

            // sql query to get all students in a given course
            String sql = "select * from " + 
                    TABLE_ENROLLMENT + " where " + 
                    COL_CLASS_CODE + " =?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, classCode);

            // execute query and get the result set
            ResultSet set = stmt.executeQuery();

            while (set.next()){
                Student s = StudentDao.readStudent(set.getString(COL_STUDENT_ID));
                students.add(s);
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return students;
    }
    
    public static int enrollStudent(Student student, Course course) {
        int rowsAffected = 0;

        try{
            Connection conn = DBConnection.getConnectionToDatabase();

            //insert query
            String sql = "insert into " + 
                    TABLE_ENROLLMENT + " (" + 
                    COL_STUDENT_ID + ", " + 
                    COL_CLASS_CODE + ") values (?, ?);";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getId());
            stmt.setString(2, course.getCode());

            rowsAffected = stmt.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return rowsAffected;
    }
    
    public static int unenrollStudent(Student student, Course course) {
        int rowsAffected = 0;
        try{
            Connection conn = DBConnection.getConnectionToDatabase();

            String sql = "delete from " + TABLE_ENROLLMENT + " where " + COL_STUDENT_ID + "=? and " + COL_CLASS_CODE + " =?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getId());
            stmt.setString(2, course.getCode());

            rowsAffected = stmt.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return rowsAffected;
    }
    
    public static List<Course> getCourses() {
        List<Course> courses = new ArrayList<Course>();

        try{
            // get connection to database
            Connection conn = DBConnection.getConnectionToDatabase();

            // sql query to get all students
            String sql = "select * from " + 
                    TABLE_COURSES + ";";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // execute query and get the result set
            ResultSet set = stmt.executeQuery();

            while (set.next()){
                String code = set.getString(COL_CODE);
                String subject = set.getString(COL_SUBJECT);
                Teacher instructor = (Teacher) StaffDao.readStaffMember(set.getString(COL_INSTRUCTOR));
                courses.add(new Course(code, subject, instructor, getEnrollment(code)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return courses;
    }
    
    public static List<Course> getCourses(Teacher instructor) {
        List<Course> courses = new ArrayList<Course>();

        try{
            // get connection to database
            Connection conn = DBConnection.getConnectionToDatabase();

            // sql query to get all students
            String sql = "select * from " + 
                    TABLE_COURSES + " where " + 
                    COL_INSTRUCTOR + " =?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, instructor.getEmployeeId());

            // execute query and get the result set
            ResultSet set = stmt.executeQuery();

            while (set.next()){
                String code = set.getString(COL_CODE);
                String subject = set.getString(COL_SUBJECT);
                courses.add(new Course(code, subject, instructor, getEnrollment(code)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return courses;
    }
    
    public static Course readCourse(String code) {
        Course course = null;

        try{
            // get connection to db
            Connection conn = DBConnection.getConnectionToDatabase();

            // query to get the student by student ID (Primary key)
            String sql = "select * from " + 
                    TABLE_COURSES + " where " + 
                    COL_CODE + "=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, code);

            // execute query and get the result set
            ResultSet set = stmt.executeQuery();

            while (set.next()){
                String subject = set.getString(COL_SUBJECT);
                Teacher instructor = (Teacher) StaffDao.readStaffMember(set.getString(COL_INSTRUCTOR));
                course = new Course(code, subject, instructor, getEnrollment(code));
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return course;
    }

    public static int createCourse(Course course) {
        int rowsAffected = 0;

        try{
            Connection conn = DBConnection.getConnectionToDatabase();

            //insert query
            String sql = "insert into " + 
                    TABLE_COURSES + " (" + 
                    COL_CODE + ", " + 
                    COL_SUBJECT + ", " + 
                    COL_INSTRUCTOR + ") values (?, ?, ?);";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, course.getCode());
            stmt.setString(2, course.getSubject());
            stmt.setString(3, course.getInstructor().getEmployeeId());

            rowsAffected = stmt.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return rowsAffected;
    }

    public static int updateCourse(String code, String newSubject, Teacher newInstructor) {
        int rowsAffected = 0;

        try{
            Connection conn = DBConnection.getConnectionToDatabase();

            String sql = "update " + 
                    TABLE_COURSES + " set " + 
                    COL_SUBJECT + "=?, " + 
                    COL_INSTRUCTOR + "=? where " + 
                    COL_CODE + "=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, newSubject);
            stmt.setString(2, newInstructor.getEmployeeId());
            stmt.setString(3, code);

            rowsAffected = stmt.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return rowsAffected;
    }

    public static int deleteCourse(String code) {
        int rowsAffected = 0;
        try{
            Connection conn = DBConnection.getConnectionToDatabase();

            String sql = "delete from " + TABLE_COURSES + " where " + COL_CODE + "=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, code);

            rowsAffected = stmt.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return rowsAffected;
    }
}