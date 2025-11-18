package service;

import model.Student;
import util.RecordActions;
import util.StudentNotFoundException;
import java.io.*;
import java.util.*;

public class StudentManager implements RecordActions {
    private List<Student> students = new ArrayList<>();
    private final String fileName = "students.txt";

    public StudentManager() {
        loadFromFile();
    }

    @Override
    public void addStudent(Student student) throws Exception {
        for (Student s : students)
            if (s.getRollNo() == student.getRollNo())
                throw new Exception("Duplicate roll number");
        students.add(student);
    }

    @Override
    public void deleteStudent(String name) throws Exception {
        boolean removed = students.removeIf(s -> s.getName().equalsIgnoreCase(name));
        if (!removed) throw new StudentNotFoundException("No Student found named " + name);
    }

    @Override
    public void updateStudent(int rollNo, Student updated) throws Exception {
        boolean found = false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRollNo() == rollNo) {
                students.set(i, updated);
                found = true;
                break;
            }
        }
        if (!found) throw new StudentNotFoundException("No Student found with roll no " + rollNo);
    }

    @Override
    public Student searchStudent(String name) throws Exception {
        for (Student s : students)
            if (s.getName().equalsIgnoreCase(name))
                return s;
        throw new StudentNotFoundException("No Student found named " + name);
    }

    @Override
    public List<Student> viewAllStudents() {
        return new ArrayList<>(students);
    }

    public void sortByMarks() {
        students.sort((a, b) -> Double.compare(b.getMarks(), a.getMarks()));
    }

    public void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String record;
            while ((record = br.readLine()) != null)
                students.add(Student.fromString(record));
        } catch (IOException e) { }
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Student s : students)
                bw.write(s.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
