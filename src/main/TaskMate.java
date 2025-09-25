package main;

import dbbackend.TaskMateDBBackend;

import java.util.Scanner;

public class TaskMate {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        TaskMateDBBackend backend = new TaskMateDBBackend();
        boolean isRunning = true;
        while (isRunning) {
            System.out.println();
            System.out.println("""
                    === Task Tracker ===
                    1. Add Task
                    2. View All Tasks
                    3. View Tasks by Status
                    4. Update Task
                    5. Delete Task
                    6. Exit""");
            int userInput = sc.nextInt();
            switch (userInput){
                case 1 -> // Add Task
                        backend.addTask(sc);
                case 2 -> // View All Tasks
                        backend.viewAllTask();
                case 3 -> // View Tasks by Status
                        backend.viewTaskByStatus(sc);
                case 4 -> // Update Task
                        backend.updateTask(sc);
                case 5 -> // Delete Task
                        backend.deleteTask(sc);
                case 6 -> {
                    // Exit
                    isRunning = false;
                    System.out.println("Thanks for using me");
                }
            }
        }
    }
}