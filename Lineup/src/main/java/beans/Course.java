package beans;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String code;
    private String subject;
    private Teacher instructor;
    private ArrayList<Student> students;
    
    // Constructor used when creating a new class
    public Course(String code, String subject, Teacher instructor) {
        this.code = code;
        this.subject = subject;
        this.instructor = new Teacher(instructor);
    }
    // Constructor used when loading a class from the database
    public Course(String code, String subject, Teacher instructor, List<Student> students) {
        this(code, subject, instructor);
        this.students = new ArrayList<Student>(students);
    }
    // Copy Constructor
    public Course(Course toCopy) {
        this(toCopy.getCode(), toCopy.getSubject(), toCopy.getInstructor(), toCopy.getStudents());
    }
    
    // Accessors
    public String getCode() { return code; }
    public String getSubject() { return subject; }
    public Teacher getInstructor() { return new Teacher(instructor); }
    public List<Student> getStudents() { return new ArrayList<Student>(students); }
    
    // Mutators
    public void setInstructor(Teacher instructor) { this.instructor = instructor; }
    public void addStudent(Student student) { students.add(student); }
    public void removeStudent(Student student) { students.remove(student); }
}
