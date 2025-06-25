package Day2;

import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    private long id;
    private String name;
    private float marks;
    public Student(long id, String name, float marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getMarks() {
        return marks;
    }
    public void setMarks(float marks) {
        this.marks = marks; 
    }
    @Override
    public String toString() {
        return "ID: "+id+ " Name: "+name+ " Marks: "+marks;
    }

    public static void addStudent(ArrayList<Student> std, Student student) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter student ID:");
        student.id = sc.nextLong();
        System.out.println("Enter student name: ");
        student.name = sc.next();
        System.out.println("Enter student marks: ");
        student.marks = sc.nextFloat();
        std.add(student);
    }
    public static void displayStudents(ArrayList<Student> std) {
        if (std.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : std) {
                System.out.println(student);
            }
        }
    }
    public static void removeStudent(ArrayList<Student> std, long id) {
        for (int i = 0; i < std.size(); i++) {
            if (std.get(i).getId() == id) {
                std.remove(i);
                System.out.println("Student with ID " + id + " removed.");
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }
    public static void updateStudent(ArrayList<Student> std, long id) {
        Scanner sc = new Scanner(System.in);
        for (Student student : std) {
            if (student.getId() == id) {
                System.out.println("Enter new name: ");
                student.setName(sc.next());
                System.out.println("Enter new marks: ");
                student.setMarks(sc.nextFloat());
                System.out.println("Student updated successfully.");
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("Student Management System:\n1. Add Student\n2. Display Students\n3. Remove Student\n4. Update Student\n5. Exit");
            System.out.print("Choose an option (1-5): ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent(students, new Student(0, "", 0));
                    break;
                case 2:
                    displayStudents(students);
                    break;
                case 3:
                    System.out.print("Enter student ID to remove: ");
                    long removeId = sc.nextLong();
                    removeStudent(students, removeId);
                    break;
                case 4:
                    System.out.print("Enter student ID to update: ");
                    long updateId = sc.nextLong();
                    updateStudent(students, updateId);
                    break;
                case 5:
                    run = false;
                    System.out.println("Exiting Student Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please select 1-5.");
            }
        }
        sc.close();
        
    }

}
