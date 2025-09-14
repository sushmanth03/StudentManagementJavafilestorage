import java.util.*;
import java.io.*;

public class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();
    private Scanner scanner;
    private static final String FILE_NAME = "students.csv";

    public static void main(String[] args) {
        new StudentManagementSystem().run();
    }

    private void run() {
        scanner = new Scanner(System.in);
        loadFromFile(); // load students when program starts

        while (true) {
            showMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": addStudent(); break;
                case "2": updateStudent(); break;
                case "3": searchStudent(); break;
                case "4": sortStudents(); break;
                case "5": displayAll(); break;
                case "6": exitProgram(); break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n=== Student Management System ===");
        System.out.println("1. Add student");
        System.out.println("2. Update student by ID");
        System.out.println("3. Search student by ID or name");
        System.out.println("4. Sort students by ID");
        System.out.println("5. Display all students");
        System.out.println("6. Exit");
        System.out.print("Choose: ");
    }

    private void addStudent() {
        try {
            System.out.print("Enter ID (positive integer): ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Enter age: ");
            int age = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter course: ");
            String course = scanner.nextLine().trim();

            Student s = new Student(id, name, age, course);
            if (!s.isValid()) {
                System.out.println("Invalid data. Skipping.");
                return;
            }
            if (findById(id) != null) {
                System.out.println("ID already exists. Skipping.");
                return;
            }
            students.add(s);
            saveToFile();
            System.out.println("Student added.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Operation cancelled.");
        }
    }

    private void updateStudent() {
        try {
            System.out.print("Enter ID of student to update: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            Student s = findById(id);
            if (s == null) { System.out.println("Student not found."); return; }

            System.out.println("Leave input empty to keep current value.");
            System.out.print("New name (" + s.getName() + "): ");
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) s.setName(name);

            System.out.print("New age (" + s.getAge() + "): ");
            String ageLine = scanner.nextLine().trim();
            if (!ageLine.isEmpty()) {
                int age = Integer.parseInt(ageLine);
                if (age > 0) s.setAge(age);
            }

            System.out.print("New course (" + s.getCourse() + "): ");
            String course = scanner.nextLine();
            if (!course.trim().isEmpty()) s.setCourse(course);

            if (!s.isValid()) {
                System.out.println("Update produced invalid data.");
            } else {
                saveToFile();
                System.out.println("Student updated.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Operation cancelled.");
        }
    }

    private Student findById(int id) {
        for (Student s : students) if (s.getId() == id) return s;
        return null;
    }

    private void searchStudent() {
        System.out.print("Search by (1) ID or (2) name? ");
        String opt = scanner.nextLine().trim();
        if (opt.equals("1")) {
            try {
                System.out.print("Enter ID: ");
                int id = Integer.parseInt(scanner.nextLine().trim());
                Student s = findById(id);
                System.out.println(s == null ? "Not found." : s);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        } else if (opt.equals("2")) {
            System.out.print("Enter name or substring: ");
            String q = scanner.nextLine().trim().toLowerCase();
            boolean found = false;
            for (Student s : students) {
                if (s.getName().toLowerCase().contains(q)) {
                    System.out.println(s);
                    found = true;
                }
            }
            if (!found) System.out.println("No matching students.");
        } else {
            System.out.println("Invalid option.");
        }
    }

    private void sortStudents() {
        if (students.isEmpty()) { System.out.println("No students to sort."); return; }
        Collections.sort(students);
        saveToFile();
        System.out.println("Sorted by ID and saved.");
    }

    private void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No valid student data present.");
            return;
        }
        for (Student s : students) System.out.println(s);
    }

    private void exitProgram() {
        System.out.println("Exiting.");
        scanner.close();
        System.exit(0);
    }

    // ===== File handling =====
    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s.getId() + "," + s.getName() + "," + s.getAge() + "," + s.getCourse());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return; // no file yet

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int age = Integer.parseInt(parts[2].trim());
                    String course = parts[3].trim();
                    Student s = new Student(id, name, age, course);
                    if (s.isValid()) students.add(s);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}
