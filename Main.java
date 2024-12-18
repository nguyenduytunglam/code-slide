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
