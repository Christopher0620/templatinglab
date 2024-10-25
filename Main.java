import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String word;
        int ctr = 0;
        System.out.println("Enter a word");
        word = scan.next();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o' || word.charAt(i) == 'u'){
                ctr++;
            }
        }
        System.out.println("Number of vowels = " + ctr);
    }
}