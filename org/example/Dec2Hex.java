package org.example;
import java.util.logging.Logger;

public class Dec2Hex {

    // Static logger for use in the main method
    private static final Logger logger = Logger.getLogger(Dec2Hex.class.getName());

    public static void main(String[] args) {
        // Check if the user has passed any argument
        if (args.length == 0) {
            logger.info("Error: InputNotValid - No argument provided");
            return;  // Exit the program if no argument is passed
        }

        // Try to parse the argument as an integer
        int num;
        try {
            num = Integer.parseInt(args[0]);  // Parse the input argument to integer
        } catch (NumberFormatException e) {
            // Catch the exception if the input is not a valid integer
            logger.info("ERROR: InputNotValid - Invalid number format");
            return;  // Exit without throwing an exception
        }
        // Convert the number to hexadecimal and log it
        String hex = Integer.toHexString(num).toUpperCase();


        if (num != 0) {  // Log HEX for any non-zero input
	    if (logger.isLoggable(java.util.logging.Level.INFO)){
            	logger.info(String.format("HEX = %s", hex));
	    }
        } else {
            // Log a special message for zero (could be different from other numbers)
            logger.info("HEX = 0");
        }


        }
}
