import java.util.Scanner;

public class ChristopherTineoAssignment1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n;
        Integer smallest = null;
        Integer largest = null;

        // Prompt the user to enter another number or -1 to stop
        System.out.println("Enter a number or -1 to stop");
        n = scan.nextInt();

        while (n != -1){
            // Update smallest if necessary
            if (smallest == null || n < smallest){
                smallest = n;
            }

            // Update largest if necessary
            if (largest == null || n > largest){
                largest = n;
            }

            // Prompt the user to enter another number or -1 to stop
            System.out.println("Enter a number or -1 to stop");
            n = scan.nextInt();

        }

        // Check if any positive numbers were entered
        if (largest == null && smallest == null) {
            System.out.println("No positive numbers were entered");
        } else {

            // Print the smallest and largest numbers
            System.out.println("Smallest is " + smallest);
            System.out.println("Largest is " + largest);
        }
    }
}
