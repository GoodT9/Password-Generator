import java.util.Scanner;

public class Main {
    // if you have any errors running this code, run mvn clean install -e and run again
    public static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        displayColorfulBanner();  // Add this line to display the banner
        
        Scanner keyboard = new Scanner(System.in);
        Generator generator = new Generator(keyboard);
        generator.mainLoop();
        keyboard.close();
    }

    private static void displayColorfulBanner() {
        String banner = 
            "\n\033[1;35m" + // Bright Magenta
            "┌───────────────────────────────────────┐\n" +
            "│                                       │\n" +
            "│          Password Generator           │\n" +
            "│                 v1.0                  │\n" +
            "│                                       │\n" +
            "│     Generate Secure Passwords &       │\n" +
            "│       Check Password Strength         │\n" +
            "│                                       │\n" +
            "└───────────────────────────────────────┘\n" +
            "\033[0m"; // Reset color
        
        System.out.println(banner);
    }
}