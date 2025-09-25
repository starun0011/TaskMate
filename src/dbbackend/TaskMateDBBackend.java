package dbbackend;

import java.sql.*;
import java.util.Scanner;

public class TaskMateDBBackend {

    private Connection connection;

    public TaskMateDBBackend() {
        try {
            // Making these value private constants;
            String URL = "jdbc:mysql://localhost:3306/taskmate_db";
            String USERNAME = "USERNAME";
            String PASSWORD = "USERPASSWORD";
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection established successfully!!");
        } catch (SQLException e) {
            System.out.println("Cannot connect to the database!");
            System.out.println(e.getMessage());
        }
    }

    public void addTask(Scanner sc) {
        // id(int), title(var), description(var), status(enum), due_date(date);
        sc.nextLine();
        System.out.println("Enter the Title: ");
        String title = sc.nextLine();
        System.out.println("Enter the Description: ");
        String description = sc.nextLine();
        String status;
        while(true) {
            System.out.println("Enter the status: ");
            status = sc.nextLine().trim().toLowerCase();
            if(status.equals("pending") || status.equals("completed")  ){
                break;
            }
            else {
                System.out.println("Status can be pending or completed nothing else");
            }
        }
        String query = "INSERT INTO tasks (title, description, status) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, status);
            int rowEffected = preparedStatement.executeUpdate();
            if (rowEffected > 0) System.out.println("Task Added..");
            else System.out.println("Some error occurred..");
        } catch (SQLException e) {
            System.out.println("DATABASE ERROR: "+e.getMessage());
        }
    }

    private void printTask(ResultSet resultSet) throws SQLException {
        System.out.println("+--|  ID |---|  Title   |---|    Description   |---|  Status   |---|  Due Date   |--+");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            String status = resultSet.getString("status");
            String dueDate = resultSet.getString("due_date");
            System.out.println("+--|  " + id + " |---|  " + title + "   |---|    " + description + "   |---|  " + status + "   |---|  " + dueDate + "   |--");
        }
    }

    public void viewAllTask() {
        String query = "SELECT * FROM tasks;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()){

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No Tasks Available. Please Add Some Tasks.");
            } else {
                printTask(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("DATABASE ERROR: "+e.getMessage());
        }
    }

    public void viewTaskByStatus(Scanner sc) {
        sc.nextLine();
        int input;
        String statusInput;
        while (true) {
            System.out.println("Select from below: \na)Choose 1 for pending tasks.\nb)Choose 2 for completed tasks.");
            input = sc.nextInt();
            if((input == 1 )|| (input ==2)){
                break;
            }
            else System.out.println("Wrong input!!");
        }
        statusInput = input == 1? "pending" : "completed";
        String query = "SELECT * FROM tasks WHERE status = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1,statusInput);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.isBeforeFirst()) System.out.println("No "+statusInput+" is Available!!");
                else {
                    printTask(resultSet);
                }
            }

        } catch (SQLException e) {
            System.out.println("DATABASE ERROR: "+e.getMessage());
        }
    }

    public void updateTask(Scanner sc) {
        sc.nextLine(); // clear buffer
        System.out.println("Press 1 for updating task status.\nPress 2 for updating task due Date.");
        int input = sc.nextInt();
        sc.nextLine(); // clear buffer after nextInt()

        if (input == 1) {
            System.out.print("Enter the ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // clear buffer

            String status;
            while (true) {
                System.out.print("Enter status (pending/completed): ");
                status = sc.nextLine().trim().toLowerCase();
                if (status.equals("pending") || status.equals("completed")) break;
                System.out.println("Invalid input. Please enter pending or completed.");
            }

            String query = "UPDATE tasks SET status = ? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, status);
                ps.setInt(2, id);
                int rowsEffected = ps.executeUpdate();
                if (rowsEffected != 0) {
                    System.out.println("Task Status Updated..");
                } else {
                    System.out.println("No task found with ID " + id);
                }
            } catch (SQLException e) {
                System.out.println("DATABASE ERROR: " + e.getMessage());
            }

        } else if (input == 2) {
            System.out.print("Enter the ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // clear buffer

            System.out.print("Enter the Due Date (i.e, YYYY-MM-DD): ");
            String dueDate = sc.nextLine();

            String query = "UPDATE tasks SET due_date = ? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setDate(1, Date.valueOf(dueDate));
                ps.setInt(2, id);
                int rowsEffected = ps.executeUpdate();
                if (rowsEffected != 0) {
                    System.out.println("Due Date Updated..");
                } else {
                    System.out.println("No task found with ID " + id);
                }
            } catch (SQLException e) {
                System.out.println("DATABASE ERROR: " + e.getMessage());
            }

        } else {
            System.out.println("Please enter 1 or 2 as per your choice.");
        }
    }


    public void deleteTask(Scanner sc)  {

        System.out.print("Enter the ID of the task to delete: ");
        sc.nextLine();
        int id = sc.nextInt();
        String query = "DELETE FROM tasks WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query) ){

            preparedStatement.setInt(1, id);
            int rowsEffected = preparedStatement.executeUpdate();

            if (rowsEffected != 0) {
                System.out.println("Task Deleted Successfully..");
            } else {
                System.out.println("No Task Found with ID " + id);
            }
        } catch (SQLException e) {
            System.out.println("DATABASE ERROR: "+e.getMessage());
        }

    }


}