import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;

import java.util.Scanner;

import java.util.ArrayList;

import java.time.LocalDate;

public class Nat {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ArrayList<Task> taskList;
    private int numOfItems;
    private Scanner scanner;
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String SPACER = "    ";

    /**
     * Constructor for the Nat class.
     * Initializes the task list, sets the number of items to zero,
     * and prepares the scanner for user input.
     */
    public Nat() {
        this.taskList = new ArrayList<>();
        this.numOfItems = 0;
        this.scanner = new Scanner(System.in);
    }

    /**
     * The main method serves as the entry point for the program.
     * It initializes the application by creating an instance of the Nat
     * class and starts its execution loop.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        // LocalDate date = LocalDate.of(2019, 10, 15);
        // System.out.println(date.getMonth());
        Nat NATcb = new Nat();
        NATcb.run();
    }

    /**
     * Starts the main program execution loop for the Nat application.
     */
    public void run() {
        // Print program startup information
        this.printLogo();
        this.printGreeting();

        // Load the saved .txt file task list
        this.performLoad();

        // Prompt user for a command
        String command = this.scanner.nextLine();

        // Continue running until the user specifies "bye"
        while (!command.equals("bye")) {
            String[] commandParts = command.split(" ", 2);
            switch (commandParts[0]) {
                case "list":
                    this.performListCommand();
                    break;
                case "todo":
                    if (commandParts.length < 2) {
                        System.out.println(SPACER + " Invalid format. Use: todo <task name>");
                    } else {
                        this.performToDoCommand(commandParts[1]);
                    }
                    break;
                case "deadline":
                    this.performDeadlineCommand(commandParts[1]);
                    break;
                case "event":
                    this.performEventCommand(commandParts[1]);
                    break;
                case "mark":
                    this.performMarkCommand(commandParts);
                    break;
                case "unmark":
                    this.performUnmarkCommand(commandParts);
                    break;
                case "delete":
                    this.performDeleteCommand(commandParts);
                    break;
                default:
                    System.out.println(SPACER + " Oh noo! I don't know what you mean; please try again!\n" + HORIZONTAL_LINE);
                    break;
            }

            command = this.scanner.nextLine();
            System.out.println(HORIZONTAL_LINE);
        }

        // Save the loaded task list into a .txt file
        this.performSave();

        // Exit the program
        this.printGoodbye();
    }

    private void performListCommand() {
        System.out.println(SPACER + " Here are the tasks in your list:");
        for (int i = 0; i < this.numOfItems; i++) {
            int printIndex = i + 1;
            System.out.println(SPACER + " " + printIndex + "." + this.taskList.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private void performToDoCommand(String newTask) {
        if (newTask == null || newTask.trim().isEmpty()) {
            System.out.println(SPACER + "Invalid format. Use: todo <task name>");
            return;
        }

        String taskName = newTask.trim();
        this.performAddTaskCommand(new ToDo(taskName));
    }

    private void performDeadlineCommand(String newTask) {
        String[] taskParts = newTask.split(" /by ", 2);
        if (taskParts.length < 2) {
            System.out.println(SPACER + "Invalid format. Use: deadline <task name> /by <due date>");
            return;
        }
        String taskName = taskParts[0].trim();
        String dueDate = taskParts[1].trim();
        this.performAddTaskCommand(new Deadline(taskName, dueDate));
    }

    private void performEventCommand(String newTask) {
        String[] taskParts = newTask.split(" /from ", 2);
        if (taskParts.length < 2 || !taskParts[1].contains(" /to ")) {
            System.out.println(SPACER + "Invalid format. Use: event <task name> /from <start date> /to <end date>");
            return;
        }

        String taskName = taskParts[0].trim();
        String[] dateParts = taskParts[1].split(" /to ", 2);
        String startDate = dateParts[0].trim();
        String endDate = dateParts[1].trim();
        this.performAddTaskCommand(new Event(taskName, startDate, endDate));
    }

    // Use when loading in saved tasks (w/o message)
    private void addTask(Task newTask) {
        this.taskList.add(newTask);
        this.numOfItems++;
    }

    private void performAddTaskCommand(Task newTask) {
        this.taskList.add(newTask);
        this.numOfItems++;
        System.out.println(SPACER + " Got it. I've added this task:\n"
                + SPACER + "   " + newTask + "\n"
                + SPACER + " Now you have " + this.numOfItems + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private void performMarkCommand(String[] commandParts) {
        if (commandParts.length == 2) {
            int index = Integer.parseInt(commandParts[1]) - 1;
            this.taskList.get(index).markAsDone();
            System.out.println(SPACER + " Nice! I've marked this task as done:");
            System.out.println(SPACER + "   " + this.taskList.get(index));
        } else {
            System.out.println(SPACER + "Invalid format. Use: mark <task number>");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private void performUnmarkCommand(String[] commandParts) {
        if (commandParts.length == 2) {
            int index = Integer.parseInt(commandParts[1]) - 1;
            this.taskList.get(index).unmarkAsDone();
            System.out.println(SPACER + " Boo! I've marked this task as not done yet:");
            System.out.println(SPACER + "   " + this.taskList.get(index));
        } else {
            System.out.println(SPACER + "Invalid format. Use: unmark <task number>");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private void performDeleteCommand(String[] commandParts) {
        if (commandParts.length == 2) {
            int index = Integer.parseInt(commandParts[1]) - 1;
            String removedTask = this.taskList.get(index).toString();
            this.taskList.remove(index);
            this.numOfItems--;
            System.out.println(SPACER + " Disappeario! I've removed this task:\n"
                    + SPACER + "   " + removedTask + "\n"
                    + SPACER + " Now you have " + this.numOfItems + " tasks in the list.");

        } else {
            System.out.println(SPACER + "Invalid format. Use: delete <task number>");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private void performLoad() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/data/data.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] taskParts = line.split(" \\| ");
                if (taskParts.length < 3) {
                    System.out.println(SPACER + " Oops! Warning: Invalid task format in data file. Skipping...");
                    line = reader.readLine();
                    continue;
                }

                String taskType = taskParts[0];
                boolean isDone = taskParts[1].equals("1");
                String taskName = taskParts[2];

                switch (taskType) {
                    case "T":
                        this.addTask(new ToDo(taskName));
                        break;
                    case "D":
                        if (taskParts.length == 4) {
                            String dueDate = taskParts[3];  // Due date for the deadline
                            this.addTask(new Deadline(taskName, dueDate));
                        } else {
                            System.out.println(SPACER + " Oops! Warning: Invalid deadline task format. Skipping...");
                            line = reader.readLine();
                            continue;
                        }
                        break;
                    case "E":
                        if (taskParts.length == 5) {
                            String startDate = taskParts[3];  // Start date for the event
                            String dueDate = taskParts[4];  // Due date for the event
                            this.addTask(new Event(taskName, startDate, dueDate));
                        } else {
                            System.out.println(SPACER + " Oops! Warning: Invalid event task format. Skipping...");
                            System.out.println(HORIZONTAL_LINE);
                            line = reader.readLine();
                            continue;
                        }
                        break;
                    default:
                        continue;
                }

                // Mark the new task as done according to it's boolean
                if (isDone) {
                    this.taskList.get(this.numOfItems - 1).markAsDone();
                }

                // Read the next line
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(SPACER + " Nay! An error occurred while loading tasks: " + e.getMessage());
        }

    }

    private void performSave() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/data/data.txt"))) {
            for (Task task : this.taskList) {
                if (task != null) {
                    writer.write(task.toSaveFormat());
                    writer.newLine();
                }
            }
            System.out.println(SPACER + " Yay! Tasks successfully saved to tasks.txt");
        } catch (IOException e) {
            System.out.println(SPACER + " Nay! An error occurred while saving tasks: " + e.getMessage());
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private void printLogo() {
        String logo = SPACER + " ____  _____       _     _________  \n"
                + SPACER + "|_   \\|_   _|     / \\   |  _   _  | \n"
                + SPACER + "  |   \\ | |      / _ \\  |_/ | | \\_| \n"
                + SPACER + "  | |\\ \\| |     / ___ \\     | |     \n"
                + SPACER + " _| |_\\   |_  _/ /   \\ \\_  _| |_    \n"
                + SPACER + "|_____|\\____||____| |____||_____|   \n";
        System.out.println(SPACER + "Hello from\n" + logo + HORIZONTAL_LINE);
    }

    private void printGreeting() {
        System.out.println(SPACER + " Good day! I'm Nat!\n"
                + SPACER + " How may I assist you today?\n"
                + HORIZONTAL_LINE);
    }

    private void printGoodbye() {
        System.out.println(SPACER + " Byebye! Come back soon!\n"
                + HORIZONTAL_LINE);
    }
}
