package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.util.logging.*;

import static org.junit.Assert.assertEquals;

public class Dec2HexTest {

    private final ByteArrayOutputStream logCaptor = new ByteArrayOutputStream();
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

        // Attach the handler to Dec2Hex logger to capture logs from Dec2Hex
        Logger dec2HexLogger = Logger.getLogger(Dec2Hex.class.getName()); // Get logger from Dec2Hex
        dec2HexLogger.addHandler(logHandler);
        dec2HexLogger.setLevel(Level.ALL);  // Ensure it captures all log levels
    }

    @After
    public void tearDown() {
        // Remove the log handler after each test to prevent multiple handler issues
        Logger dec2HexLogger = Logger.getLogger(Dec2Hex.class.getName()); // Get logger from Dec2Hex
        dec2HexLogger.removeHandler(logHandler);  // Clean up handler from Dec2Hex logger
    }

    @Test
    public void testValidDecimalInput() {
        // Run the main method with a valid decimal input
        Dec2Hex.main(new String[]{"255"});
        logHandler.flush();  // Ensure all log messages are flushed
        assertEquals("HEX = FF", logCaptor.toString().trim());  // Check the expected log message
    }

    @Test
    public void testZeroInput() {
        // Run the main method with zero as input
        Dec2Hex.main(new String[]{"0"});
        logHandler.flush();
        assertEquals("HEX = 0", logCaptor.toString().trim());  // Check the expected log message
    }

    @Test
    public void testNegativeDecimalInput() {
        // Run the main method with a negative decimal input
        Dec2Hex.main(new String[]{"-10"});
        logHandler.flush();
        assertEquals("HEX = FFFFFFF6", logCaptor.toString().trim());  // Check the expected log message
    }

    @Test
    public void testInvalidInput() {
        // Run the main method with invalid input (non-numeric)
        Dec2Hex.main(new String[]{"abc"});
        logHandler.flush();
        assertEquals("ERROR: InputNotValid - Invalid number format", logCaptor.toString().trim());  // Check the expected log message
    }

    @Test
    public void testNoArgumentPassed() {
        // Run the main method with no arguments
        Dec2Hex.main(new String[]{});
        logHandler.flush();
        assertEquals("Error: InputNotValid - No argument provided", logCaptor.toString().trim());  // Check the expected log message
    }
}
