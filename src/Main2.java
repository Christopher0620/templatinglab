import java.util.*;
import java.io.*;

public class Main2 {
    public static void populate(ArrayList<String> words){
        try {
            Scanner infile = new Scanner(new File("data.txt"));
            String word;
            while(infile.hasNextLine()) {
                word = infile.nextLine();
                words.add(word);
            }
        }
        catch(Exception e){
            System.out.println("Problem reading in file");
            System.exit(-1);
        }
    }
    public static String checkLetter(char letter, String PuzzleWord, String GuessWord) {
        String NewWord = "";
        for( int i=0; i<PuzzleWord.length(); i++){
            if(letter == PuzzleWord.charAt(i)){
                NewWord = NewWord + letter;
            }
            else {
                NewWord = NewWord + GuessWord.charAt(i);
            }
        }
        System.out.println(NewWord);
        return NewWord;
    }
    public static void playgame(String pw, String pg){
        Scanner scan = new Scanner(System.in);
        boolean guessed = false;
        int ctr = 0;
        String guess;
        String newWord;
        while (ctr < 7 && !guessed){
            for (int i = 0; i < pg.length(); i++)
                System.out.print(pg.charAt(i) + " ");
            System.out.println();
            System.out.println("Enter a letter or guess the word");
            guess = scan.next();
            if (guess.length() == 1){
               pg = checkLetter(guess.charAt(0), pw, pg);
               if (pg.equals(pw))
                   guessed = true;
            }
            else{
                if (guess.equals(pw))
                    guessed = true;
            }
            ctr++;
        }
        if (guessed)
            System.out.println("CONGRATS!");
        else
            System.out.println("NOTHING");
    }

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        populate(words);

        Random rand = new Random();
        int r = rand.nextInt(words.size());
        String puzzleword = words.get(r);
        //System.out.println(puzzleword);
        String puzzleGuess = "";
        for (int i = 0; i < puzzleword.length(); i++)
            puzzleGuess += '_';
        playgame(puzzleword, puzzleGuess);

    }
}
