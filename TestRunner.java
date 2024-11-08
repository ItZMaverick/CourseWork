import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.logging.Logger;

public class TestRunner {
    private static final Logger logger = Logger.getLogger(Dec2Hex.class.getName());
    public static void main(String[] args) {
        // Run the tests and get the result
        Result result = JUnitCore.runClasses(Dec2HexTest.class);  // Add more test classes here if needed

        // Print out the results
        for (Failure failure : result.getFailures()) {
            logger.info(failure.toString());
        }

        // Print the overall result (whether tests were successful or not)
        if (result.wasSuccessful()) {
            logger.info("All tests passed!");
        } else {
            logger.info("Some tests failed.");
        }
    }
}

