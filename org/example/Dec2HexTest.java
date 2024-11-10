package org.example;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.util.logging.*;

import static org.junit.Assert.assertEquals;

public class Dec2HexTest {

    private final ByteArrayOutputStream logCaptor = new ByteArrayOutputStream();
    private final Logger testLogger = Logger.getLogger(Dec2HexTest.class.getName()); // SonarQube wants this logger name
    private final Logger dec2HexLogger = Logger.getLogger(Dec2Hex.class.getName()); // This logger is used in Dec2Hex for logging
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

        // Add the handler to the Dec2Hex logger to capture logs from Dec2Hex
        dec2HexLogger.addHandler(logHandler);
        dec2HexLogger.setLevel(Level.ALL);  // Ensure logger captures all levels

        // Add the handler to the test logger (though it's not required for capturing Dec2Hex logs)
        testLogger.addHandler(logHandler);
        testLogger.setLevel(Level.ALL);  // Ensure test logger captures all levels
    }

    @After
    public void tearDown() {
        // Remove the log handler after each test to prevent multiple handler issues
        dec2HexLogger.removeHandler(logHandler);
        testLogger.removeHandler(logHandler);
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
