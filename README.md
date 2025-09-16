# Student Management System (Java Console + File Storage)

A simple **Java console-based project** for managing student records.  
This project demonstrates **Core Java + OOP principles** and adds **file storage** so that student data persists even after program exit.

---

## 📌 Features
- Add new students (with input validation).
- Update existing student details by ID.
- Search students by ID or by name.
- Sort students by ID.
- Display all students.
- Data is saved to `students.csv` for persistence.

---

## 📂 Project Structure
StudentManagementSystem/
│
├── Student.java # Student class (fields, methods, Comparable)
├── StudentManagementSystem.java # Main console program with menu and file storage
├── students.csv # Auto-created CSV file storing records
└── README.md



---

## ⚙️ How It Works
1. On startup, the program **loads student data** from `students.csv` (if the file exists).
2. A menu is shown in the console with options to add, update, search, sort, or display students.
3. Any changes are **immediately saved** back to `students.csv`.
4. On exit, data remains in the file.
5. On next run, all previously saved students are available.

---

## ▶️ How to Run
1. Install **Java JDK** (8 or higher).  
2. Clone this repository:
   ```bash
   git clone https://github.com/your-username/StudentManagementSystem.git
   cd StudentManagementSystem


Compile the files:
javac Student.java StudentManagementSystem.java


Run the program:
java StudentManagementSystem


🖥️ Sample Run:

=== Student Management System ===
1. Add student
2. Update student by ID
3. Search student by ID or name
4. Sort students by ID
5. Display all students
6. Exit
Choose: 1
Enter ID (positive integer): 101
Enter name: Alice
Enter age: 20
Enter course: CSE
Student added.

Choose: 6
Exiting.

# Restart program
Choose: 5
ID: 101 | Name: Alice | Age: 20 | Course: CSE





📖 Skills Demonstrated

Core Java programming.

Object-Oriented Programming (Encapsulation, Abstraction, Modular Design).

Collections (ArrayList).

File I/O (CSV read/write).

CRUD operations in console-based applications.
