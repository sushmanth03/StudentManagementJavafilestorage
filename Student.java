public class Student implements Comparable<Student> {
    private int id;
    private String name;
    private int age;
    private String course;

    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name == null ? "" : name.trim();
        this.age = age;
        this.course = course == null ? "" : course.trim();
    }

    public boolean isValid() {
        return id > 0 && !name.isEmpty() && age > 0;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name == null ? "" : name.trim(); }
    public void setAge(int age) { this.age = age; }
    public void setCourse(String course) { this.course = course == null ? "" : course.trim(); }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Age: %d | Course: %s", id, name, age, course);
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }
}
