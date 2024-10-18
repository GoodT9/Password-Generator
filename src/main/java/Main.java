import java.util.Scanner;

public class Main {

    public static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        printColorfulAnimal();
        printColorfulBanner();
        Generator generator = new Generator(keyboard);
        generator.mainLoop();
        keyboard.close();
    }

    private static void printColorfulBanner() {
        System.out.println("\u001B[36m");
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║ Home of the Good Password Generator v1.0 ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("\u001B[0m");
    }
    private static void printColorfulAnimal() {
        System.out.println("\u001B[36m");
        System.out.println("   .-\"\"\"\"-. ");
        System.out.println("  / -   -  \\  ");
        System.out.println(" |  .-. .-  |  ");
        System.out.println(" |  \\o| |o (  ");
        System.out.println(" \\     ^   | ---- GREETINGS TO YOU!   ");
        System.out.println(" '.  )--'  /  ");
        System.out.println("   '-...-'`  ");
        System.out.println("\u001B[0m");
    }
}






















    







