import java.util.Scanner;

public class Nat {
    private Task[] taskList;
    private int numOfItems;
    private Scanner scanner;
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String SPACER = "    ";

    public Nat() {
        this.taskList = new Task[100];
        this.numOfItems = 0;
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Nat NATcb = new Nat();
        NATcb.run();
    }

    public void run() {
        // Print program startup information
        this.printLogo();
        this.printGreeting();

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
                    String taskName = commandParts[1];
                    this.performAddTaskCommand(new ToDo(taskName));
                    break;
                case "deadline":
                    String taskName = commandParts[1].split(" /by ")[0];
                    String dueDate = commandParts[1].split(" /by ")[1];
                    this.performAddTaskCommand(new Deadline(taskName, dueDate));
                    break;
                case "mark":
                    this.performMarkCommand(commandParts);
                    break;
                case "unmark":
                    this.performUnmarkCommand(commandParts);
                    break;
                default:
                    System.out.println(SPACER + " added: " + command + "\n" + HORIZONTAL_LINE);
                    this.taskList[this.numOfItems] = new ToDo(command);
                    this.numOfItems++;
                    break;
            }

            command = this.scanner.nextLine();
            System.out.println(HORIZONTAL_LINE);
        }

        // Exit the program
        this.printGoodbye();
    }

    private void performListCommand() {
        System.out.println(SPACER + " Here are the tasks in your list:");
        for (int i = 0; i < this.numOfItems; i++) {
            int printIndex = i + 1;
            System.out.println(SPACER + " " + printIndex + "." + this.taskList[i]);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private void performAddTaskCommand(Task newTask) {
        this.taskList[this.numOfItems] = newTask;
        this.numOfItems++;
        System.out.println(SPACER + " Got it. I've added this task:\n"
                + SPACER + " " + newTask + "\n"
                + SPACER + " Now you have " + this.numOfItems + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private void performMarkCommand(String[] commandParts) {
        if (commandParts.length == 2) {
            int index = Integer.parseInt(commandParts[1]) - 1;
            this.taskList[index].markAsDone();
            System.out.println(SPACER + " Nice! I've marked this task as done:");
            System.out.println(SPACER + "   " + this.taskList[index]);
        } else {
            System.out.println(SPACER + "Invalid format. Use: mark <task number>");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private void performUnmarkCommand(String[] commandParts) {
        if (commandParts.length == 2) {
            int index = Integer.parseInt(commandParts[1]) - 1;
            this.taskList[index].unmarkAsDone();
            System.out.println(SPACER + " Boo! I've marked this task as not done yet:");
            System.out.println(SPACER + "   " + this.taskList[index]);
        } else {
            System.out.println(SPACER + "Invalid format. Use: unmark <task number>");
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
