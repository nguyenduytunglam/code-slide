import java.util.Arrays;

public class StudentStack {
    private Student[] stack;
    private int top;
    public StudentStack() {
        stack = new Student[100];  // Kích thước tối đa cho stack là 100
        top = -1;
    }

    // Phương thức push để thêm sinh viên vào stack
    public void push(Student student) {
        if (top == stack.length - 1) {
            // Tăng kích thước stack nếu đã đầy
            stack = Arrays.copyOf(stack, stack.length * 2);
        }
        stack[++top] = student;
    }
    // Phương thức pop để lấy và loại bỏ sinh viên khỏi stack
    public Student pop() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return null;
        } else {
            return stack[top--];
        }
    }
    // Phương thức peek (hoặc top) để xem sinh viên ở đầu stack mà không loại bỏ
    public Student peek() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return null;
        } else {
            return stack[top];
        }
    }
    // Phương thức kiểm tra stack có rỗng không
    public boolean isEmpty() {
        return top == -1;
    }
    // Phương thức chuyển đổi stack thành mảng để duyệt qua
    public Student[] toArray() {
        Student[] studentsArray = new Student[top + 1];
        System.arraycopy(stack, 0, studentsArray, 0, top + 1);
        return studentsArray;
    }
    // Phương thức lấy kích thước của stack
    public int size() {
        return top + 1;
    }
}
