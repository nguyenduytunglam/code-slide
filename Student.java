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
