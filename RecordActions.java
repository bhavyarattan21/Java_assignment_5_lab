package util;

import model.Student;
import java.util.List;

public interface RecordActions {
    void addStudent(Student student) throws Exception;
    void deleteStudent(String name) throws Exception;
    void updateStudent(int rollNo, Student updated) throws Exception;
    Student searchStudent(String name) throws Exception;
    List<Student> viewAllStudents();
}
