import java.util.Scanner;

public class Generator {
    Alphabet alphabet;
    public static Scanner keyboard;

    public Generator(Scanner scanner) {
        keyboard = scanner;
    }

    public Generator(boolean IncludeUpper, boolean IncludeLower, boolean IncludeNum, boolean IncludeSym) {
        alphabet = new Alphabet(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
    }

    
    /**
     * Initiates and manages the main program loop for the Password Generator application.
     * This method displays a welcome message, presents a menu of options to the user,
     * and processes user input until the user chooses to quit the program.
     * 
     * The loop continues until the user selects option '4' to quit. For each valid option,
     * the corresponding method is called and the menu is reprinted. Invalid inputs are
     * handled by prompting the user to select a valid option.
     * 
     * No parameters are required as it uses class-level variables and methods.
     * 
     * This method does not return a value; it runs until the user chooses to exit.
     */
    public void mainLoop() {
        System.out.println("Welcome to Good Password Services. How can we be of service today?? :)");  // Display welcome message
        printMenu();  // Display the main menu options

        String userOption = "-1";  // Initialize user option, "-1" serves as a default value

        while (!userOption.equals("4")) {  // Continue loop until user chooses to quit (option "4")
            userOption = keyboard.next();  // Read user input for menu selection

            switch (userOption) {  // Process user's menu selection
                case "1" -> {  // Option 1: Generate a new password
                    requestPassword();  // Call method to handle password generation
                    printMenu();  // Redisplay menu after operation
                }
                case "2" -> {  // Option 2: Check password strength
                    checkPassword();  // Call method to evaluate password strength
                    printMenu();  // Redisplay menu after operation
                }
                case "3" -> {  // Option 3: Display useful password information
                    printUsefulInfo();  // Call method to show password guidelines
                    printMenu();  // Redisplay menu after operation
                }
                case "4" -> printQuitMessage();  // Option 4: Exit the program
                default -> {  // Handle invalid input
                    System.out.println("Kindly select one of the available commands");  // Prompt for valid input
                    printMenu();  // Redisplay menu for user to choose again
                }
            }
        }
    }









    private Password GeneratePassword(int length) {
        final StringBuilder pass = new StringBuilder("");

        final int alphabetLength = alphabet.getAlphabet().length();

        int max = alphabetLength - 1;
        int min = 0;
        int range = max - min + 1;

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * range) + min;
            pass.append(alphabet.getAlphabet().charAt(index));
        }

        return new Password(pass.toString());
    }

    public void printUsefulInfo() {                                            // Method to display password security guidelines
        System.out.println();                                                   // Print a blank line for better readability
        System.out.println("Use a minimum password length of 8 or more characters if permitted");  // Advise on minimum password length
        System.out.println("Include lowercase and uppercase alphabetic characters, numbers and symbols if permitted");  // Suggest character types to include
        System.out.println("Generate passwords randomly where feasible");       // Emphasize the importance of randomness
        System.out.println("Avoid using the same password twice (e.g., across multiple user accounts and/or software systems)");  // Warn against password reuse
        System.out.println("Avoid character repetition, keyboard patterns, dictionary words, letter or number sequences," +  // List patterns to avoid in passwords
                "\nusernames, relative or pet names, romantic links (current or past) " +
                "and biographical information (e.g., ID numbers, ancestors' names or dates).");
        System.out.println("Avoid using information that the user's colleagues and/or " +  // Caution against using personal information
                "acquaintances might know to be associated with the user");
        System.out.println("Do not use passwords which consist wholly of any simple combination of the aforementioned weak components"); 
    }


        

    public void requestPassword() {
        boolean IncludeUpper = false;
        boolean IncludeLower = false;
        boolean IncludeNum = false;
        boolean IncludeSym = false;

        boolean correctParams;

        System.out.println("Enter the desired password length:");

        do {
            String input;
            correctParams = false;

            do {
                System.out.println("Do you want Lowercase letters \"abcd...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeLower = true;

            do {
                System.out.println("Do you want Uppercase letters \"ABCD...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeUpper = true;

            do {
                System.out.println("Do you want Numbers \"1234...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeNum = true;

            do {
                System.out.println("Do you want Symbols \"!@#$...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeSym = true;

            //No Pool Selected
            if (!IncludeUpper && !IncludeLower && !IncludeNum && !IncludeSym) {
                System.out.println("You have selected no characters to generate your " +
                        "password, at least one of your answers should be Yes\n");
                correctParams = true;
            }

        } while (correctParams);

        System.out.println("Great! Now enter the length of the password");
        int length = keyboard.nextInt();

        final Generator generator = new Generator(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
        final Password password = generator.GeneratePassword(length);

        System.err.println("Your generated password -> " + password);
    }





    boolean isInclude(String Input) {
        if (Input.equalsIgnoreCase("yes")) {
            return true;
        } 
        else {
            return false;
        }
    }

    public void PasswordRequestError(String i) {
        if (!i.equalsIgnoreCase("yes") && !i.equalsIgnoreCase("no")) {
            System.out.println("You have entered something incorrect let's go over it again \n");
        }
    }

    public void checkPassword() {
        String input;

        System.out.print("\nEnter your password:");
        input = keyboard.next();

        final Password p = new Password(input);

        System.out.println(p.calculateScore());
    }

    public void printMenu() {
        System.out.println();
        System.out.println("Enter 1 - Password Generator");
        System.out.println("Enter 2 - Password Strength Check");
        System.out.println("Enter 3 - Useful Information");
        System.out.println("Enter 4 - Quit");
        System.out.print("Choice:");
    }

    public void printQuitMessage() {
        System.out.println("Closing the program bye bye!");
    }
}
