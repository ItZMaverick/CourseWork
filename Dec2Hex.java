public class Dec2Hex {
    public static void main(String args[]) {
        // Check if the user has passed any argument
        if (args.length == 0) {
            System.out.println("No argument passed. Please provide a decimal number.");
            return;  // Exit the program 
        }

        // Try to parse the argument as an integer
        int num = 0;
        try {
            num = Integer.parseInt(args[0]);  // Parse the input argument to integer
        } catch (NumberFormatException e) {
            // Catch the exception if the input is not a valid integer
            System.out.println("Error: Invalid input. Please provide a valid decimal number.");
            return;  // Exit without throwing an exception
        }

        // Array for hexadecimal characters
        char ch[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        // If the number is 0, handle it explicitly
        if (num == 0) {
            System.out.println("Hexadecimal representation is: 0");
            return;
        }

        // String to store the hexadecimal representation
        String hexadecimal = "";
        System.out.println("Converting the Decimal Value " + num + " to Hex...");

        // Convert the decimal number to hexadecimal
        while (num != 0) {
            int rem = num % 16;
            hexadecimal = ch[rem] + hexadecimal;
            num = num / 16;
        }

        // Print the hexadecimal result
        System.out.println("Hexadecimal representation is: " + hexadecimal);
    }
}
