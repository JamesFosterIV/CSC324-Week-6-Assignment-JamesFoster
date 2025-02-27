import java.util.ArrayList;
import java.util.Scanner;

/**
 * A simple To-Do List application that allows users to:
 * 1. Add tasks
 * 2. View tasks
 * 3. Remove tasks
 * 4. Exit the program
 *
 * Uses an ArrayList to store tasks and Scanner for user input.
 */
public class   ToDoListApp {
    // Stores the list of tasks
    private static ArrayList<String> tasks = new ArrayList<>();
    private static ArrayList<String> completedTasks = new ArrayList<>();    //For viewing completed task

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner for user input
        int choice; // Stores user menu choice

        // Loop to continuously show menu until user exits
        do {
            // Display menu options
            System.out.println("\n--- To-Do List ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Ongoing Tasks");
            System.out.println("3. View Completed Tasks");
            System.out.println("4. Remove Task");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            // Read user input (menu choice)
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character left after nextInt()

            // Handle user choice using a switch statement
            switch (choice) {
                case 1 -> addTask(scanner); // Call method to add a task
                case 2 -> viewOngoingTasks(scanner); // Call method to display tasks
                case 3 -> viewCompletedTasks(); // Call method to remove a task
                case 4 -> removeTask(scanner);
                case 5 -> System.out.println("Exiting..."); // Exit message
                default -> System.out.println("Invalid choice. Try again."); // Handle invalid input
            }
        } while (choice != 5); // Loop until user selects option 4 (Exit)

        scanner.close(); // Close scanner to prevent memory leaks
    }

    /**
     * Method to complete task. It adds the task entered to the completedTasks array list, and then
     * deletes it from the tasks arraylist.
     * @param index used to access the task given
     *
     * @author James Foster
     */
    private static void completeTask(int index) {
        if (index > 0 && index <= tasks.size()) {
            completedTasks.add(tasks.get(index - 1));
            tasks.remove(index - 1); // Remove task (index is 1-based, ArrayList is 0-based)
        }
    }

    /**
     * Method to view the list of completed task. If there aren't any, it
     * tells the user and returns back to the main menu
     *
     * @author James Foster
     */
    public static void viewCompletedTasks() {
        if (completedTasks.isEmpty()) { // Check if the list is empty
            System.out.println("You haven't completed any Tasks! GET TO WORK!!!");
        } else {
            System.out.println("\nYour Completed Tasks:");
            // Loop through the list and display each task with a number
            for (int i = 0; i < completedTasks.size(); i++) {
                System.out.println((i + 1) + ". " + completedTasks.get(i));
            }
        }
    }


    /**
     * Method to add a new task to the list.
     * @param scanner Scanner object for user input.
     */
    private static void addTask(Scanner scanner) {
        System.out.print("Enter task: ");
        String task = scanner.nextLine(); // Read task from user
        tasks.add(task); // Add task to the list
        System.out.println("Task added!"); // Confirmation message
    }

    /**
     * Method to display all tasks in the list.
     */
    private static void viewOngoingTasks(Scanner scanner) {
        if (tasks.isEmpty()) { // Check if the list is empty
            System.out.println("No tasks available. Try adding a task first.");
        } else {
            System.out.println("\nYour Tasks:");
            // Loop through the list and display each task with a number
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }

            System.out.println("Would you like to complete a task? (Y/N)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                System.out.println("Which task would you like to complete?");
                int response = scanner.nextInt();
                completeTask(response);//complete the task
                System.out.println("Task completed!");
            }else if (choice.equalsIgnoreCase("N")){
                //will return back to main method...
            }
        }
    }

    /**
     * Method to remove a task from the list.
     * @param scanner Scanner object for user input.
     */
    private static void removeTask(Scanner scanner) {
        //List out the tasks...
        System.out.println("\nYour Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        if (tasks.isEmpty()) return; // If no tasks, exit method

        System.out.print("Enter task number to remove: ");
        int index = scanner.nextInt(); // Get task number from user

        // Validate the task number before removing
        if (index > 0 && index <= tasks.size()) {
            tasks.remove(index - 1); // Remove task (index is 1-based, ArrayList is 0-based)
            System.out.println("Task removed."); // Confirmation message
        } else {
            System.out.println("Invalid task number."); // Handle invalid input
        }
    }
}
