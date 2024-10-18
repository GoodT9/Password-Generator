import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class GeneratorTest {
    private Generator generator;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream originalIn = new ByteArrayInputStream(new byte[0]);

    @BeforeEach

    public void setUp() {
        System.setOut(new PrintStream(outContent));
        generator = new Generator(new Scanner(System.in));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testMainLoopQuit() {
        String input = "4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        generator.mainLoop();
        assertTrue(outContent.toString().contains("Closing the program bye bye!"));
    }


    @Test
    public void testMainLoopInvalidInput() {
        String input = "5\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        generator.mainLoop();
        assertTrue(outContent.toString().contains("Kindly select one of the available commands"));
    }

    @Test
    public void testPrintUsefulInfo() {
        generator.printUsefulInfo();
        String output = outContent.toString();
        assertTrue(output.contains("Use a minimum password length of 8 or more characters if permitted"));
        assertTrue(output.contains("Include lowercase and uppercase alphabetic characters, numbers and symbols if permitted"));
        assertTrue(output.contains("Generate passwords randomly where feasible"));
    }

    @Test
    public void testRequestPassword() {
        String input = "yes\nyes\nyes\nyes\n12\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        generator.requestPassword();
        String output = outContent.toString();
        assertTrue(output.contains("Enter the desired password length:"));
        assertTrue(output.contains("Your generated password ->"));
    }

    @Test
    public void testCheckPassword() {
        String input = "TestPassword123!\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        generator.checkPassword();
        String output = outContent.toString();
        assertTrue(output.contains("Enter your password:"));
        assertTrue(output.contains("Score:"));
    }

    @Test
    public void testPrintMenu() {
        generator.printMenu();
        String output = outContent.toString();
        assertTrue(output.contains("Enter 1 - Password Generator"));
        assertTrue(output.contains("Enter 2 - Password Strength Check"));
        assertTrue(output.contains("Enter 3 - Useful Information"));
        assertTrue(output.contains("Enter 4 - Quit"));
    }

    @Test
    public void testGeneratePassword() {
        Generator gen = new Generator(true, true, true, true);
        Password password = gen.GeneratePassword(12);
        assertEquals(12, password.getPassword().length());
    }

    @Test
    public void testIsInclude() {
        assertTrue(generator.isInclude("yes"));
        assertTrue(generator.isInclude("YES"));
        assertFalse(generator.isInclude("no"));
        assertFalse(generator.isInclude("NO"));
    }

    @Test
    public void testPasswordRequestError() {
        generator.PasswordRequestError("maybe");
        assertTrue(outContent.toString().contains("You have entered something incorrect"));
        
        outContent.reset();
        generator.PasswordRequestError("yes");
        assertEquals("", outContent.toString().trim());
    }


    @Test
    public void testCheckPasswordError() {
        generator.checkPasswordError("no");
        assertTrue(outContent.toString().contains("You have entered something incorrect"));
        
        outContent.reset();
        generator.checkPasswordError("TestPassword123!");
        assertEquals("", outContent.toString().trim());
    
}