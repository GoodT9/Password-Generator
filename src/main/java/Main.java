import java.util.Scanner;

public class Main {

    // if you have any errors running this code, run mvn clean install -e and run again
    public static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        Generator generator = new Generator(keyboard);
        generator.mainLoop();
        keyboard.close();
    }

}