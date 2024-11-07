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

        // Array for hexadecimal characters
        char[] ch = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        // Use StringBuilder for efficient string concatenation
        StringBuilder hexadecimal = new StringBuilder();

        // Convert the decimal number to hexadecimal
        while (num != 0) {
            int rem = num % 16;  // Get the remainder (hexadecimal digit)
            hexadecimal.insert(0, ch[rem]);  // Add the hex digit to the front of the string
            num = num / 16;  // Divide the number by 16 to get the next digit
        }

        // Print the hexadecimal result
        System.out.println("Hexadecimal representation is: " + hexadecimal.toString());
    }
}
