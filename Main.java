import model.Student;
import service.StudentManager;
import service.Loader;
import util.StudentNotFoundException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        StudentManager sm = new StudentManager();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nCapstone Student Menu");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search by Name");
            System.out.println("4. Delete by Name");
            System.out.println("5. Sort by Marks");
            System.out.println("6. Save and Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            try {
                switch (ch) {
                    case 1:
                        System.out.print("Enter Roll No: "); int roll = sc.nextInt(); sc.nextLine();
                        System.out.print("Enter Name: "); String name = sc.nextLine();
                        System.out.print("Enter Email: "); String email = sc.nextLine();
                        System.out.print("Enter Course: "); String course = sc.nextLine();
                        System.out.print("Enter Marks: "); double marks = sc.nextDouble(); sc.nextLine();
                        Thread t1 = new Thread(new Loader("Adding student"));
                        t1.start(); t1.join();
                        sm.addStudent(new Student(roll, name, email, course, marks));
                        System.out.println("Student Added.");
                        break;
                    case 2:
                        for (Student s : sm.viewAllStudents()) {
                            s.displayInfo();
                            System.out.println("-----");
                        }
                        break;
                    case 3:
                        System.out.print("Enter name to search: ");
                        String searchName = sc.nextLine();
                        Student found = sm.searchStudent(searchName);
                        found.displayInfo();
                        break;
                    case 4:
                        System.out.print("Enter name to delete: ");
                        String delName = sc.nextLine();
                        Thread t2 = new Thread(new Loader("Deleting student"));
                        t2.start(); t2.join();
                        sm.deleteStudent(delName);
                        System.out.println("Student deleted.");
                        break;
                    case 5:
                        sm.sortByMarks();
                        for (Student s : sm.viewAllStudents()) {
                            s.displayInfo();
                            System.out.println("-----");
                        }
                        break;
                    case 6:
                        Thread t3 = new Thread(new Loader("Saving and exiting"));
                        t3.start(); t3.join();
                        sm.saveToFile();
                        exit = true;
                        System.out.println("Data saved. Exiting.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        sc.close();
    }
}
