import java.util.Scanner;

// Class to handle input operations
public class InputHelper {
    // Method to get integer input from user with error handling
    protected static int getInput(String prompt, Scanner scanner) {
        int input = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println(prompt);
                input = Integer.parseInt(scanner.nextLine());
                validInput = true; // Set to true if parsing succeeds
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        return input;
    }
}

// Class to generate