// Name: Christopher Tineo
// Assignment: Homework 2
// This program translates between Morse code and alphabetic characters using a menu-driven interface.

import java.util.*;
import java.io.*;

public class MorseTranslator {
    private ArrayList<String> _morse;
    private ArrayList<String> _letter;
    private static MorseTranslator _uniqueInstance;

    // Default constructor
    private MorseTranslator() {
        _morse = new ArrayList<>();
        _letter = new ArrayList<>();
    }

    // n-arg constructor
    private MorseTranslator(ArrayList<String> morse, ArrayList<String> letter) {
        this._morse = morse;
        this._letter = letter;
    }

    // Singleton getInstance
    public static MorseTranslator getInstance() {
        if (_uniqueInstance == null) {
            _uniqueInstance = new MorseTranslator();
        }
        return _uniqueInstance;
    }

    // The add method
    public void addMorsecode(String morseCode, String alphabet) {
        _morse.add(morseCode);
        _letter.add(alphabet);
    }

    // The remove method
    public void removeMorseCode(String morseCode) {
        for (int i = 0; i < _morse.size(); i++) {
            if (_morse.get(i).equals(morseCode)) {
                _morse.remove(i);
                _letter.remove(i);
                break;
            }
        }
    }

    // Modify method
    public void modifyMorseCode(String oldMorseCode, String newMorseCode, String newAlphabet) {
        for (int i = 0; i < _morse.size(); i++) {
            if (_morse.get(i).equals(oldMorseCode)) {
                _morse.set(i, newMorseCode);
                _letter.set(i, newAlphabet);
                break;
            }
        }
    }

    // Size method
    public int size(){return _morse.size();}
    // Find method for Morse to alphabet
    private String findMorseToAlphabet(String morseCode) {
        for (int i = 0; i < _morse.size(); i++) {
            if (_morse.get(i).equals(morseCode)) {
                return _letter.get(i);
            }
        }
        return "";
    }

    // Find method for alphabet to Morse
    private String findAlphabetToMorse(String alphabet) {
        for (int i = 0; i < _letter.size(); i++) {
            if (_letter.get(i).equals(alphabet)) {
                return _morse.get(i);
            }
        }
        return "";
    }

    // Get method
    public String getMorsecode(int idx) {
        if (idx >= 0 && idx < _morse.size()) {
            return _morse.get(idx);
        }
        return null;
    }

    // Loads the data from the file
    public void populate(String filename) {
        try {
            Scanner infile = new Scanner(new File(filename));
            while (infile.hasNextLine()) {
                String line = infile.nextLine();
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    _letter.add(parts[0]);
                    _morse.add(parts[1]);
                }
            }
            infile.close();
        } catch (Exception e) {
            System.out.println("Problem reading in file");
            System.exit(-1);
        }
    }

    // Converts Morse to alphabetic characters
    public String morseToAlphabet(String morseCode) {
        String result = "";
        String morseChar = "";
        for (int i = 0; i < morseCode.length(); i++) {
            if (morseCode.charAt(i) == ' ') {
                result += findMorseToAlphabet(morseChar);
                morseChar = "";
            } else {
                morseChar += morseCode.charAt(i);
            }
        }
        result += findMorseToAlphabet(morseChar);
        return result;
    }

    // Converts alphabetic characters to Morse
    public String alphabetToMorse(String alphabet) {
        String result = "";
        for (int i = 0; i < alphabet.length(); i++) {
            char c = alphabet.charAt(i);
            result += findAlphabetToMorse(String.valueOf(c)) + " ";
        }
        return result;
    }

    // Menu-driven interface will allow user to input morse code or letter
    public void menu() {
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. Convert Morse to Alphabet");
            System.out.println("2. Convert Alphabet to Morse");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = scan.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter Morse code: ");
                String morseCode = scan.nextLine();
                System.out.println("Alphabet: " + morseToAlphabet(morseCode));
            } else if (choice.equals("2")) {
                System.out.print("Enter alphabetic characters: ");
                String alphabet = scan.nextLine();
                if (!alphabet.equals(alphabet.toUpperCase())) {
                    System.out.println("Error: All characters must be capitalized.");
                } else if (alphabet.contains(" ")) {
                    System.out.println("Error: No spaces allowed in between characters.");
                } else {
                    System.out.println("Morse code: " + alphabetToMorse(alphabet));
                }
            } else if (choice.equals("3")) {
                System.out.println("Exiting...");
                exit = true;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        MorseTranslator translator = MorseTranslator.getInstance();
        translator.populate("morse.txt");
        translator.menu();
    }
}
