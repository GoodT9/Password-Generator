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
   

    public void mainLoop() {
        System.out.println("Welcome to Good Password Services. How can we be of service today?? :)");
        printMenu();

        String userOption = "-1";

        while (!userOption.equals("4")) {
            userOption = keyboard.next();

            switch (userOption) {
                case "1" -> {
                    requestPassword();
                    printMenu();
                }
                case "2" -> {
                    checkPassword();
                    printMenu();
                }
                case "3" -> {
                    printUsefulInfo();
                    printMenu();
                }
                case "4" -> printQuitMessage();
                default -> {
                    System.out.println("Kindly select one of the available commands");
                    printMenu();
                }
            }
        }
    }





    private Password GeneratePassword(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("Password length must be at least 1");
        }
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


/**
 * Prints a comprehensive list of password security tips to the console.
 * This method provides users with best practices for creating and managing secure passwords.
 * The tips cover various aspects of password security, including length, complexity,
 * uniqueness, and general security practices.
 *
 * This method does not take any parameters and does not return any value.
 * It directly prints the information to the console using System.out.println().
 */
public void printUsefulInfo() {
    System.out.println("\n=== Password Security Tips ===");                      // Prints the header for the security tips
    System.out.println("1. Use a minimum password length of 8 or more characters if permitted");  // Advises on minimum password length
    System.out.println("2. Include lowercase and uppercase alphabetic characters, numbers and symbols if permitted");  // Recommends using a mix of character types
    System.out.println("3. Generate passwords randomly where feasible");         // Suggests using random password generation
    System.out.println("4. Avoid using the same password twice (e.g., across multiple user accounts and/or software systems)");  // Warns against password reuse
    System.out.println("5. Avoid character repetition, keyboard patterns, dictionary words, letter or number sequences,\n   usernames, relative or pet names, romantic links (current or past) and biographical information (e.g., ID numbers, ancestors' names or dates).");  // Lists patterns and information to avoid in passwords
    System.out.println("6. Avoid using information that the user's colleagues and/or acquaintances might know to be associated with the user");  // Advises against using personal information
    System.out.println("7. Do not use passwords which consist wholly of any simple combination of the aforementioned weak components");  // Warns against combining weak password elements
    System.out.println("8. Use a unique password for each of your important accounts");  // Emphasizes the importance of unique passwords
    System.out.println("9. Use a password manager to generate and store complex passwords securely");  // Recommends using a password manager
    System.out.println("10. Enable two-factor authentication (2FA) whenever possible for additional security");  // Suggests enabling 2FA for extra security
    System.out.println("11. Regularly update your passwords, especially if you suspect they might have been compromised");  // Advises on password update frequency
    System.out.println("12. Avoid sharing your passwords with others, even if they claim to be from IT support");  // Warns against sharing passwords
    System.out.println("13. Do not use the same password for multiple accounts if the accounts are not related to each other");  // Reiterates the importance of unique passwords
    System.out.println("14. Consider using passphrases: long sequences of random words that are easy to remember but hard to crack");  // Suggests using passphrases
    System.out.println("15. Set up security questions with answers that are not easily guessable or found on social media");  // Advises on setting up secure security questions
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
    System.out.println("\n==========================================");
    System.out.println("   Password Generator and Checker Menu");
    System.out.println("==========================================");
    System.out.println("1. Generate a new password");
    System.out.println("2. Check password strength");
    System.out.println("3. Display password security information");
    System.out.println("4. Quit");
    System.out.println("==========================================");
    System.out.print("Please enter your choice (1-4): ");
}
    public void printQuitMessage() {
        System.out.println("Closing the program bye bye!");
    }
}
