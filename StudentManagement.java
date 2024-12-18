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
    }

    // Tìm kiếm sinh viên theo ID
    public Student searchStudent(String id) {
        for (Student student : students.toArray()) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;  // Nếu không tìm thấy sinh viên
    }

    // Hiển thị tất cả sinh viên
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student student : students.toArray()) {
            System.out.println(student);
        }
    }

    // Sắp xếp sinh viên theo điểm giảm dần
    public void sortStudentsByGrade() {
        Student[] studentArray = students.toArray();

        // Đo thời gian cho Bubble Sort
        Student[] bubbleSortedArray = studentArray.clone();
        long bubbleStartTime = System.nanoTime();
        bubbleSort(bubbleSortedArray);
        long bubbleEndTime = System.nanoTime();
        long bubbleDuration = bubbleEndTime - bubbleStartTime;

        // Đo thời gian cho Quick Sort
        Student[] quickSortedArray = studentArray.clone();
        long quickStartTime = System.nanoTime();
        quickSort(quickSortedArray, 0, quickSortedArray.length - 1);
        long quickEndTime = System.nanoTime();
        long quickDuration = quickEndTime - quickStartTime;

        // Cập nhật lại stack sau khi Quick Sort
        students = new StudentStack();
        for (Student student : quickSortedArray) {
            students.push(student);
        }

        // Hiển thị thời gian chạy
        System.out.println("Bubble Sort runtime: " + bubbleDuration + " ns");
        System.out.println("Quick Sort runtime: " + quickDuration + " ns");
    }

    // Triển khai thuật toán Bubble Sort
    private void bubbleSort(Student[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].getGrade() < array[j + 1].getGrade()) {
                    // Hoán đổi nếu phần tử không đúng thứ tự
                    Student temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Triển khai thuật toán Quick Sort
    private void quickSort(Student[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(Student[] array, int low, int high) {
        Student pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j].getGrade() >= pivot.getGrade()) {
                i++;
                Student temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        Student temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

}
