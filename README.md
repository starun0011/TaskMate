# 📝 TaskMate - JDBC Task Management System

TaskMate is a **console-based Task Management System** built using **Java, JDBC, and MySQL**.  
It allows users to add, view, update, and delete tasks with ease. The project uses **PreparedStatement** to prevent SQL Injection and ensure secure database interaction.

---

## 🚀 Features
- ➕ **Add Task** – Insert new tasks with title, description, and status (pending/completed).
- 📋 **View All Tasks** – Display all tasks in a formatted table.
- 🔍 **View Task by Status** – Filter tasks by *pending* or *completed*.
- ✏️ **Update Task** – Modify task status or due date.
- ❌ **Delete Task** – Remove tasks by ID.
- ✅ **User-Friendly CLI** – Interactive console menu.

---

## 🛠️ Technologies Used
- **Java (Core + JDBC)**
- **MySQL Database**
- **PreparedStatement** (for SQL queries)
- **Scanner API** (for user input)

---

## 📂 Project Structure
```
src
│
├── dbbackend
│   ├── TaskMateDBBackend.java # Handles database operations (CRUD)
├── main
    ├──TaskMate.java # Main class (user interface / menu)
```
---

## 🗄️ Database Setup
Before running the project, create a MySQL database and table.

```sql
CREATE DATABASE taskmate_db;

USE taskmate_db;

CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    status ENUM('pending','completed') DEFAULT 'pending',
    due_date DATE DEFAULT CURDATE()
);
```
## ▶️ How to Run

1) Clone the repository:
```
git clone https://github.com/your-username/TaskMate.git
cd TaskMate
```
2) Open project in your IDE (IntelliJ/Eclipse/VS Code).

3) Update DB credentials in TaskMateDBBackend.java:
```
private final String URL = "jdbc:mysql://localhost:3306/taskmate_db";
private final String USERNAME = "root";
private final String PASSWORD = "your-password";
```
4) Run TaskMate.java.

5) Use the menu to manage tasks:
```
=== Task Tracker ===
1. Add Task
2. View All Tasks
3. View Tasks by Status
4. Update Task
5. Delete Task
6. Exit
```
---
## 📌 Future Improvements

a) Add user authentication system.

b) Support task priority (Low/Medium/High).

c) Export tasks to CSV/Excel.

d) Create a Swing/JavaFX GUI or Web version using Spring Boot.

---
## 🤝 Contributing

Feel free to fork this repository and improve it. Pull requests are welcome!
---
## 👨‍💻 Author

Tarun Soni
Java Developer | Backend Enthusiast
