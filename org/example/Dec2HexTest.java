package org.example;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.util.logging.*;

import static org.junit.Assert.assertEquals;

public class Dec2HexTest {

    private final ByteArrayOutputStream logCaptor = new ByteArrayOutputStream();
    private final Logger logger = Logger.getLogger(Dec2Hex.class.getName()); //use dec2hex logger
    private Handler logHandler;

    // Custom formatter to capture only message text without log levels or timestamps
    private static class SimpleMessageFormatter extends Formatter {
        @Override
        public String format(LogRecord theRecord) {
            return theRecord.getMessage() + System.lineSeparator();
        }
    }

    @Before
    public void setUp() {
        // Set up a StreamHandler to capture log output with a simple message formatter
        logHandler = new StreamHandler(logCaptor, new SimpleMessageFormatter());
        logHandler.setLevel(Level.ALL);  // Make sure all log levels are captured
        logger.addHandler(logHandler);
        logger.setLevel(Level.ALL);  // Ensure logger captures all levels
    }

    @After
    public void tearDown() {
        // Remove the log handler after each test
        logger.removeHandler(logHandler);
    }

    @Test
    public void testValidDecimalInput() {
        Dec2Hex.main(new String[]{"255"});
        logHandler.flush();  // Ensure all log messages are flushed
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
