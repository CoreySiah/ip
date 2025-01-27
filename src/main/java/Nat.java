import java.util.Scanner;

public class Nat {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String SPACER = "    ";

    public static void main(String[] args) {
        Nat natChatbot = new Nat();
        natChatbot.printLogo();
        natChatbot.printGreeting();

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            System.out.println(SPACER + " " + command);
            System.out.println(SPACER + HORIZONTAL_LINE);
            command = scanner.nextLine();
        }
        natChatbot.printGoodbye();
    }

    public void printLogo() {
        String logo = SPACER + " ____  _____       _     _________  \n"
                + SPACER + "|_   \\|_   _|     / \\   |  _   _  | \n"
                + SPACER + "  |   \\ | |      / _ \\  |_/ | | \\_| \n"
                + SPACER + "  | |\\ \\| |     / ___ \\     | |     \n"
                + SPACER + " _| |_\\   |_  _/ /   \\ \\_  _| |_    \n"
                + SPACER + "|_____|\\____||____| |____||_____|   \n";
        System.out.println(SPACER + "Hello from\n" + logo + SPACER + HORIZONTAL_LINE);
    }

    public void printGreeting() {
        System.out.println(SPACER + " Good day! I'm Nat!\n"
                + SPACER + " How may I assist you today?\n"
                + SPACER + HORIZONTAL_LINE);
    }

    public void printGoodbye() {
        System.out.println(SPACER + " Byebye! Come back soon!\n"
                + SPACER + HORIZONTAL_LINE);
    }
}
