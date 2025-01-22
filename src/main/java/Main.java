import java.util.Scanner;

public class Main {

    public static final Scanner keyboard = new Scanner(System.in);

    /**
     * The main entry point of The Password Factory application.
     * This method initializes the generator, displays a colorful banner,
     * runs the main program loop, and closes the input scanner.
     *
     * @param args Command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        Generator generator = new Generator(keyboard);
        displayBanner();
        generator.mainLoop();
        keyboard.close();
    }

    private static void displayBanner() {
        String banner =
            "\n\033[1;35m" + // Bright Magenta
            "┌───────────────────────────────────────┐\n" +
            "│                                       │\n" +
            "│         The Password Factory          │\n" +
            "│                 v2.0                  │\n" +
            "│                                       │\n" +
            "│     Generate Secure Passwords &       │\n" +
            "│       Check Password Strength         │\n" +
            "│                                       │\n" +
            "└───────────────────────────────────────┘\n" +
            "\033[0m"; // Reset color
        System.out.println(banner);
    }
}