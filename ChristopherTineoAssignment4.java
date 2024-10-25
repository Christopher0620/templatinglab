import java.util.*;

public class ChristopherTineoAssignment4 {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the user
        Scanner scan = new Scanner(System.in);

        // Create an ArrayList to store the numbers entered by the user
        ArrayList<Integer> numbers = new ArrayList<>();

        // Define the sentinel value to stop the input loop
        int sentinel = -1;
        int n;

        // Prompt the user to enter a positive number or the sentinel value to stop
        System.out.println("Enter a positive number or -1 to stop:");
        n = scan.nextInt();

        // Loop to read numbers until the sentinel value is entered
        while (n != sentinel) {
            // Check if the entered number is positive
            if (n > 0) {
                // Add the positive number to the list
                numbers.add(n);
            } else {
                // Inform the user to enter a positive number
                System.out.println("Please enter a positive number.");
            }
            // Prompt the user to enter another number or the sentinel value to stop
            System.out.println("Enter a positive number or -1 to stop:");
            n = scan.nextInt();
        }

        // Sort the numbers in ascending order
        Collections.sort(numbers);

        // Print the sorted numbers, space delimited
        System.out.println("Sorted numbers:");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }
}