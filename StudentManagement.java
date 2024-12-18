import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagement management = new StudentManagement();

        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Tạo ngẫu nhiên sinh viên
        management.addRandomStudents(numStudents);

        while (true) {
            // Menu
            System.out.println("\n---- Student Management ----");
            System.out.println("1. Add a student");
            System.out.println("2. Remove a student");
            System.out.println("3. Update a student");
            System.out.println("4. Search for a student");
            System.out.println("5. Display all students");
            System.out.println("6. Sort students by grade");
            System.out.println("7. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add a student
                    System.out.print("Enter student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student grade: ");
                    double grade = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    Student newStudent = new Student(id, name, grade);
                    management.addStudent(newStudent);
                    break;

                case 2:
                    // Remove a student
                    System.out.print("Enter student ID to remove: ");
                    String removeId = scanner.nextLine();
                    management.removeStudent(removeId);
                    break;

                case 3:
                    // Update a student
                    System.out.print("Enter student ID to update: ");
                    String updateId = scanner.nextLine();
                    System.out.print("Enter new student name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new student grade: ");
                    double newGrade = scanner.nextDouble();
                    management.updateStudent(updateId, newName, newGrade);
                    break;

                case 4:
                    // Search for a student
                    System.out.print("Enter student ID to search: ");
                    String searchId = scanner.nextLine();
                    Student student = management.searchStudent(searchId);
                    if (student != null) {
                        System.out.println("Student found: " + student);
                    } else {
System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    // Display all students
                    management.displayAllStudents();
                    break;

                case 6:
                    // Sort students by grade
                    management.sortStudentsByGrade();
                    System.out.println("Students sorted by grade.");
                    break;

                case 7:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
public class Student {
    private String id;  // ID sinh viên (String)
    private String name;  // Tên sinh viên
    private double grade;  // Điểm sinh viên
    public Student(String id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getGrade() {
        return grade;
    }
    // Phương thức tính thứ hạng dựa trên điểm (theo bảng mới)
    public String getRanking() {
        if (grade >= 9.0) {
            return "Excellent";
        } else if (grade >= 7.5) {
            return "Very Good";
        } else if (grade >= 6.5) {
            return "Good";
        } else if (grade >= 5.0) {
            return "Medium";
        } else {
            return "Fail";
        }
    }
    @Override
    public String toString() {
        // Sử dụng String.format để đảm bảo điểm được hiển thị với 2 chữ số thập phân
        return "ID: " + id + ", Name: " + name + ", Grade: " + String.format("%.2f", grade) + ", Rank: " + getRanking();
    }

}
import java.util.Arrays;
import java.util.Random;

public class StudentManagement {
    private StudentStack students;

    public StudentManagement() {
        this.students = new StudentStack();
    }

    // Thêm một sinh viên
    public void addStudent(Student student) {
        students.push(student);
    }
    // Tạo sinh viên ngẫu nhiên
    public Student generateRandomStudent() {
        Random random = new Random();
        String id = "ID" + (random.nextInt(1000) + 1); // Tạo ID ngẫu nhiên
        String name = "Student" + (random.nextInt(1000) + 1); // Tạo tên sinh viên ngẫu nhiên
        double grade = 5 + (10 - 5) * random.nextDouble(); // Tạo điểm ngẫu nhiên từ 5 đến 10

        // Làm tròn điểm đến 1 chữ số thập phân
        grade = Math.round(grade * 10.0) / 10.0;

        // Tạo đối tượng sinh viên với thông tin ngẫu nhiên
        return new Student(id, name, grade);
    }

    // Thêm nhiều sinh viên ngẫu nhiên
    public void addRandomStudents(int numberOfStudents) {
        for (int i = 0; i < numberOfStudents; i++) {
            Student randomStudent = generateRandomStudent();
            addStudent(randomStudent); // Thêm sinh viên vào stack
        }
        System.out.println(numberOfStudents + " random students added successfully.");
        // Hiển thị thông tin của tất cả sinh viên đã thêm
        displayAllStudents();
    }


    // Xóa một sinh viên
    public void removeStudent(String id) {
        StudentStack tempStack = new StudentStack();
        boolean found = false;

        while (!students.isEmpty()) {
            Student student = students.pop();
            if (student.getId().equals(id)) {
                found = true;
            } else {
                tempStack.push(student);
            }
        }

        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }

        if (found) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    // Cập nhật thông tin sinh viên
    public void updateStudent(String id, String name, double grade) {
        StudentStack tempStack = new StudentStack();
        boolean updated = false;

        while (!students.isEmpty()) {
            Student student = students.pop();
            if (student.getId().equals(id)) {
                student = new Student(id, name, grade);  // Cập nhật sinh viên
                tempStack.push(student);
                updated = true;
            } else {
                tempStack.push(student);
            }
        }

        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }

        if (updated) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
