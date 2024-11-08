import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        // Run the tests and get the result
        Result result = JUnitCore.runClasses(Dec2HexTest.class);  // Add more test classes here if needed

        // Print out the results
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        // Print the overall result (whether tests were successful or not)
        if (result.wasSuccessful()) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}

