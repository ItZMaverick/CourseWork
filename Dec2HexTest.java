import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class Dec2HexTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));  // Redirect System.out to capture output
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);  // Reset System.out back to its original state
    }

    @Test
    public void testValidDecimalInput() {
        Dec2Hex.main(new String[]{"255"});
        assertEquals("Hexadecimal representation is: FF", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testZeroInput() {
        Dec2Hex.main(new String[]{"0"});
        assertEquals("Hexadecimal representation is: 0", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testNegativeDecimalInput() {
        Dec2Hex.main(new String[]{"-10"});
        assertEquals("Hexadecimal representation is: FFFFFFF6", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testInvalidInput() {
        Dec2Hex.main(new String[]{"abc"});
        assertEquals("Error: Invalid input. Please provide a valid decimal number.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testNoArgumentPassed() {
        Dec2Hex.main(new String[]{});
        assertEquals("No argument passed. Please provide a decimal number.", outputStreamCaptor.toString().trim());
    }
}
