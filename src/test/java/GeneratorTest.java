import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GeneratorTest {

    private Generator generator;

    @BeforeEach
    void setUp() {
        // Create a new Generator instance before each test
        generator = new Generator(new Scanner(System.in));
    }
    @Test
    void testIsInclude() {
        assertTrue(generator.isInclude("yes"));
        assertTrue(generator.isInclude("YES"));
        assertFalse(generator.isInclude("no"));
        assertFalse(generator.isInclude("NO"));
    }

    /**
     * Tests the checkPassword method of the Generator class.
     * This test simulates user input for a password, captures the output,
     * and verifies that the password strength check is performed correctly.
     * 
     * The test performs the following steps:
     * 1. Simulates user input with a strong password.
     * 2. Redirects System.in to provide the simulated input.
     * 3. Creates a new Generator instance with the simulated input.
     * 4. Redirects System.out to capture the printed result.
     * 5. Calls the checkPassword method.
     * 6. Verifies that the output contains the expected strength message.
     */
    @Test
    void testCheckPassword() {
        String input = "TestPassword123!";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        generator = new Generator(new Scanner(System.in));

        // Redirect System.out to capture printed result
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        generator.checkPassword();

        String output = outContent.toString();
        assertTrue(output.contains("Your password is strong"));
    }

    /**
     * Tests the requestPassword method of the Generator class.
     * This test simulates user input for password generation options,
     * captures the output, and verifies that a password is generated.
     * 
     * The test performs the following steps:
     * 1. Simulates user input for including uppercase, lowercase, numbers, and symbols.
     * 2. Sets the password length to 12 characters.
     * 3. Calls the requestPassword method.
     * 4. Verifies that the output contains the expected password generation message.
     */
    @Test
    void testRequestPassword() {
        String input = "yes\nyes\nyes\nyes\n12\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        generator = new Generator(new Scanner(System.in));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        generator.requestPassword();

        String output = outContent.toString();
        assertTrue(output.contains("Your generated password ->"));
    }
}