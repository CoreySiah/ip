import java.util.Scanner;

public class Nat {
    private String[] toDoList;
    private int numOfItems;
    private Scanner scanner;
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String SPACER = "    ";

    public Nat() {
        this.toDoList = new String[100];
        this.numOfItems = 0;
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Nat natCB = new Nat();
        natCB.printLogo();
        natCB.printGreeting();

        String command = natCB.scanner.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                for (int i = 0; i < natCB.numOfItems; i++) {
                    int index = i + 1;
                    System.out.println(SPACER + " " + index + ". " + natCB.toDoList[i]);
                }
                System.out.println(HORIZONTAL_LINE);
            } else {
                System.out.println(SPACER + " added: " + command + "\n" + HORIZONTAL_LINE);
                natCB.toDoList[natCB.numOfItems] = command;
                natCB.numOfItems++;
            }

            command = natCB.scanner.nextLine();
            System.out.println(HORIZONTAL_LINE);
        }

        natCB.printGoodbye();
    }

    public void printLogo() {
        String logo = SPACER + " ____  _____       _     _________  \n"
                + SPACER + "|_   \\|_   _|     / \\   |  _   _  | \n"
                + SPACER + "  |   \\ | |      / _ \\  |_/ | | \\_| \n"
                + SPACER + "  | |\\ \\| |     / ___ \\     | |     \n"
                + SPACER + " _| |_\\   |_  _/ /   \\ \\_  _| |_    \n"
                + SPACER + "|_____|\\____||____| |____||_____|   \n";
        System.out.println(SPACER + "Hello from\n" + logo + HORIZONTAL_LINE);
    }

    public void printGreeting() {
        System.out.println(SPACER + " Good day! I'm Nat!\n"
                + SPACER + " How may I assist you today?\n"
                + HORIZONTAL_LINE);
    }

    public void printGoodbye() {
        System.out.println(SPACER + " Byebye! Come back soon!\n"
                + HORIZONTAL_LINE);
    }
}
