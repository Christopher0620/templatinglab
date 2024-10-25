import java.util.Scanner;

public class ChristopherTineoAssignment2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Prompt the user to enter a string
        System.out.println("Enter a string: ");
        String input = scan.nextLine();

        // Initialize counters for 'a', 'e', and 'c'
        int countA = 0;
        int countE = 0;
        int countC = 0;

        // Loop through the string and count occurrences of 'a', 'e', and 'c'
        for (char ch : input.toCharArray()) {
            // Convert character to lowercase to make the count case-insensitive
            ch = Character.toLowerCase(ch);
            if (ch == 'a') {
                countA++;
            } else if (ch == 'e') {
                countE++;
            } else if (ch == 'c') {
                countC++;
            }
        }

        // Print the counts of 'a', 'e', and 'c' only if they are greater than zero
        if (countA > 0) {
            System.out.println(countA + " a");
        }
        if (countE > 0) {
            System.out.println(countE + " e");
        }
        if (countC > 0) {
            System.out.println(countC + " c");
        }
    }
}
