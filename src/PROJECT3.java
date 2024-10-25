//use it as a starting point but it will not be identical
import java.util.*;
import java.io.*;

// Abstract class representing a generic Animal
abstract class Animal {
    private String _name;  // Name of the animal
    private String _breed; // Breed of the animal
    private int _age;      // Age of the animal

    // Default constructor for Animal
    public Animal() {
        System.out.println("in Animal default");
    }

    // Parameterized constructor for Animal
    public Animal(String s1, String s2, int x) {
        System.out.println("in Animal n-arg");
        _name = s1;
        _breed = s2;
        _age = x;
    }

    // Setter methods for name, breed, and age
    public void setName(String n) {_name = n;}
    public void setBreed(String b) {_breed = b;}
    public void setAge(int x) {_age = x;}

    // Getter methods for name, breed, and age
    public String getName() {return _name;}
    public String getBreed() {return _breed;}
    public int getAge() {return _age;}

    // Abstract method to check equality between two Animal objects
    public abstract boolean equals(Animal a);

//    // Prints the details of the animal (commented out)
//    public void print(){
//        System.out.println(_name + " " + _breed + " " + _age);
//    }
}

// Subclass Dog that extends Animal
class Dog extends Animal {
    private boolean _guard;  // Indicates if the dog is a guard dog

    // Default constructor for Dog
    public Dog() {
        System.out.println("in Dog default");
    }

    // Parameterized constructor for Dog
    public Dog(String s1, String s2, int x, boolean g) {
        super(s1, s2, x);  // Calls the superclass (Animal) constructor
        System.out.println("in Dog n-arg");
        _guard = g;
    }

    // Setter and getter for the _guard attribute
    public void setGuard(boolean g) {_guard = g;}
    public boolean getGuard() {return _guard;}

    // Overrides the abstract equals method to compare two Dog objects
    public boolean equals(Animal a) {
        Dog d = (Dog) a;  // Downcasts Animal to Dog
        // Compares name, breed, age, and guard attributes
        if (getName().equals(d.getName())
                && getBreed().equals(d.getBreed())
                && getAge() == d.getAge()
                && _guard == d._guard)
            return true;
        else
            return false;
    }

//    // Prints details specific to Dog (commented out)
//    public void print(){
//        super.print();
//        System.out.println(_guard);
//    }
}

// Subclass Cat (commented out)
//class Cat extends Animal {
//    private boolean _indoor; // Indicates if the cat is indoor or outdoor
//
//    // Default constructor for Cat
//    public Cat() {System.out.println("in Cat default");}
//
//    // Parameterized constructor for Cat
//    public Cat(String s1, String s2, int x, boolean g){
//        super(s1, s2, x);  // Calls the superclass constructor
//        System.out.println("in Cat n-arg");
//        _indoor = g;
//    }
//
//    // Setter and getter for _indoor attribute
//    public void setIndoor(boolean g) {_indoor = g;}
//    public boolean getIndoor(){return _indoor;}
//
//    // Prints details specific to Cat (commented out)
//    public void print(){
//        super.print();
//        System.out.println(_indoor);
//    }
//}

public class PROJECT3 {
    public static void main(String[] args) throws IOException {
        // ArrayList to store Animal objects
        ArrayList<Animal> animals = new ArrayList<>();

        // Adding instances of Cat and Dog to the list (commented out)
//        animals.add(new Cat("John", "calico", 2, false));
//        animals.add(new Cat("fido", "german shepard", 6, true));
//        animals.add(new Cat("spot", "dalmation", 4, false));

        // Iterating through the list and printing Animal details
        for (int i = 0; i < animals.size(); i++) {
            System.out.print(animals.get(i).getName() + " ");
            System.out.print(animals.get(i).getBreed() + " ");
            System.out.print(animals.get(i).getAge() + " ");

            // Check if the Animal is a Dog and print additional Dog details
            if (animals.get(i) instanceof Dog)
                System.out.println(((Dog) animals.get(i)).getGuard());
//            else  // Check if the Animal is a Cat and print additional Cat details (commented out)
//                System.out.println(((Cat) animals.get(i)).getIndoor());
        }

        // Creating two Dog objects for comparison
        Dog d1 = new Dog("fido", "german shepard", 6, true);
        Dog d2 = new Dog("fido", "german shepard", 6, true);

        // Checking if the two Dog objects are equal based on attributes
        if (d1.equals(d2))
            System.out.println("same");
        else
            System.out.println("different");
    }
}
