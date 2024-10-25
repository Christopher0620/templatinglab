import java.util.Scanner;

public class ChristopherTineoAssignment3 {

    public static void main(String[] args) {
        // Create a Scanner object to read input from the user
        Scanner scan = new Scanner(System.in);

        // Prompt the user to enter a string
        System.out.println("Enter a string: ");
        String input = scan.nextLine();

        // Check if the input string is a palindrome
        if (isPalindrome(input) == 1) {
            System.out.println(input + " is a palindrome");
        } else {
            System.out.println(input + " is not a palindrome");
        }
    }

    // Method to check if a string is a palindrome
    public static int isPalindrome(String str) {
        // Initialize pointers for the start and end of the string
        int left = 0;
        int right = str.length() - 1;

        // Check if the string is a palindrome
        while (left < right) {
            // Move left pointer to the next letter
            while (left < right && !Character.isLetter(str.charAt(left))) {
                left++;
            }
            // Move right pointer to the previous letter
            while (left < right && !Character.isLetter(str.charAt(right))) {
                right--;
            }
            // Compare characters
            if (Character.toLowerCase(str.charAt(left)) != Character.toLowerCase(str.charAt(right))) {
                return 0; // Not a palindrome
            }
            left++;
            right--;
        }

        return 1; // Is a palindrome
    }
}
