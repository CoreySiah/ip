public class Nat {
    public static void main(String[] args) {
        Nat natChatbot = new Nat();
        natChatbot.printLogo();
        natChatbot.printGreeting();
        natChatbot.printGoodbye();
    }

    public void printLogo() {
        String logo = " ____  _____       _     _________  \n"
                + "|_   \\|_   _|     / \\   |  _   _  | \n"
                + "  |   \\ | |      / _ \\  |_/ | | \\_| \n"
                + "  | |\\ \\| |     / ___ \\     | |     \n"
                + " _| |_\\   |_  _/ /   \\ \\_  _| |_    \n"
                + "|_____|\\____||____| |____||_____|   \n"
                + "                                    \n"
                + "____________________________________________________________";
        System.out.println("Hello from\n" + logo);
    }

    public void printGreeting() {
        System.out.println(" Hello! I'm Nat!\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");
    }

    public void printGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
