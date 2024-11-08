import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Handler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import static org.junit.Assert.assertEquals;

public class Dec2HexTest {

    private final ByteArrayOutputStream logCaptor = new ByteArrayOutputStream();
    private final Logger logger = Logger.getLogger(Dec2Hex.class.getName());
    private Handler logHandler;

    // Custom formatter to capture only message text without log levels or timestamps
    private static class SimpleMessageFormatter extends Formatter {
        @Override
        public String format(LogRecord record) {
            return record.getMessage() + System.lineSeparator();
        }
    }

    @Before
    public void setUp() {
        // Set up a StreamHandler to capture log output with a simple message formatter
        logHandler = new StreamHandler(logCaptor, new SimpleMessageFormatter());
        logger.addHandler(logHandler);
    }

    @After
    public void tearDown() {
        // Remove the log handler after each test
        logger.removeHandler(logHandler);
    }

    @Test
    public void testValidDecimalInput() {
        Dec2Hex.main(new String[]{"255"});
        logHandler.flush();
        assertEquals("HEX = FF", logCaptor.toString().trim());
    }

    @Test
    public void testZeroInput() {
        Dec2Hex.main(new String[]{"0"});
        logHandler.flush();
        assertEquals("HEX = 0", logCaptor.toString().trim());
    }

    @Test
    public void testNegativeDecimalInput() {
        Dec2Hex.main(new String[]{"-10"});
        logHandler.flush();
        assertEquals("HEX = FFFFFFF6", logCaptor.toString().trim());
    }

    @Test
    public void testInvalidInput() {
        Dec2Hex.main(new String[]{"abc"});
        logHandler.flush();
        assertEquals("ERROR: InputNotValid - Invalid number format", logCaptor.toString().trim());
    }

    @Test
    public void testNoArgumentPassed() {
        Dec2Hex.main(new String[]{});
        logHandler.flush();
        assertEquals("Error: InputNotValid - No argument provided", logCaptor.toString().trim());
    }
}
