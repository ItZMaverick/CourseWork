public class Dec2Hex {
    public static void main(String args[]) {
        // Check if the user has passed any argument
        if (args.length == 0) {
            System.out.println("No argument passed. Please provide a decimal number.");
            return;  // Exit the program if no argument is passed
        }

        // Try to parse the argument as an integer
        int num;
        try {
            num = Integer.parseInt(args[0]);  // Parse the input argument to integer
        } catch (NumberFormatException e) {
            // Catch the exception if the input is not a valid integer
            System.out.println("Error: Invalid input. Please provide a valid decimal number.");
            return;  // Exit without throwing an exception
        }

        // If the number is 0, handle it explicitly
        if (num == 0) {
            System.out.println("Hexadecimal representation is: 0");
            return;
        }

        // Convert the decimal number to hexadecimal, handling both positive and negative numbers
        String hexadecimal = Integer.toHexString(num).toUpperCase();

        // Print the hexadecimal result
        System.out.println("Hexadecimal representation is: " + hexadecimal);
    }
}
