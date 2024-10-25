import java.util.*;
import java.io.*;

class Dog {
    private String _name;
    private String _breed;
    private int _age;
    private String _color;

    public Dog() {
        _name = "";
        _breed = "";
        _age = 0;
        _color = "";
    }

    public Dog(String s1, String s2, int x) {
        _name = s1;
        _breed = s2;
        _age = x;
    }

    public Dog(String s1, String s2, int x, String s3) {
        _name = s1;
        _breed = s2;
        _age = x;
        _color = s3;
    }

    public Dog(Dog d) {
        _name = d._name;
        _breed = d._breed;
        _age = d._age;
        _color = d._color;
    }

    public void setName(String n) {
        _name = n;
    }

    public void setBreed(String b) {
        _breed = b;
    }

    public void setAge(int x) {
        _age = x;
    }

    public String getName() {
        return _name;
    }

    public String getBreed() {
        return _breed;
    }

    public int getAge() {
        return _age;
    }

}

class Daycare {
    private ArrayList<Dog> _dogs;
    private static Daycare _uniqueInstance;
    private int _next;

    private int find(String n) {
        System.out.println("In find");
        // non-exhaustive search of name
        boolean found = false;
        int i = 0;
        while (i < _dogs.size() && !found) {
            if (_dogs.get(i).getName().equals(n))
                found = true;
            else
                i++;
        }
        if (found == false)
            return -1;
        else
            return i;
    }

    public Daycare() {
        System.out.println("In default Daycare constructor");
        _dogs = new ArrayList<Dog>();
        _next = 0;
    }

    private Daycare(int size) {
        System.out.println("in n-arg Daycare");
        _dogs = new ArrayList<Dog>();
        _next = 0;
    }

    public static Daycare getInstance() {
        System.out.println("in getInstance()");
        if (_uniqueInstance == null)
            _uniqueInstance = new Daycare();
        return _uniqueInstance;
    }

    public static Daycare getInstance(int size) {
        System.out.println("in getInstance(size)");
        if (_uniqueInstance == null)
            _uniqueInstance = new Daycare(size);
        return _uniqueInstance;
    }

    public void addDog(Dog d) {
        System.out.println("in addDog");

        _dogs.add(d);
    }

    public boolean removeDog(String n) {
        System.out.println("in removeDog");
        int idx = find(n);
        if (idx == -1)
            return false;
        else {
            _dogs.remove(idx);
            return true;
        }
    }

    public boolean modifyAge(String n, int a) {
        System.out.println("in modifyAge");
        int idx = find(n);
        if (idx == -1)
            return false;
        else {
            _dogs.get(idx).setAge(a);
            return true;
        }
    }

    public int findAge(String name) {
        System.out.println("in findAge");
        int idx = find(name);
        if (idx == -1)
            return -1;
        else
            return _dogs.get(idx).getAge();
    }

    public String getName(int idx) {
        System.out.println("in getName");
        if (idx >= 0 && idx < _dogs.size())
            return _dogs.get(idx).getName();
        else
            return "";
    }

    public int getAge(int idx) {
        System.out.println("in getAge");
        if (idx >= 0 && idx < _dogs.size())
            return _dogs.get(idx).getAge();
        else
            return -1;
    }

    public int size() {
        System.out.println("in size");
        return _dogs.size();
    }

    // iterator methods
    public void start(){
        _next = 0;
    }
    public boolean hasNext(){
        if (_next > _dogs.size())
            return false;
        else
            return true;
    }
    public Dog next(){
        // create a copy that is returned
        Dog d = new Dog(_dogs.get(_next));
        _next++;
        return d;
        // return _dogs.get(_next++);
    }
    public void print() {
        System.out.println("in size");
        for (int i = 0; i < _dogs.size(); i++) {
            System.out.println(_dogs.get(i).getName() + " "
                    + _dogs.get(i).getBreed() + " " + _dogs.get(i).getAge());

        }
    }
}

public class Main3 {
    public static void main(String[] args) throws IOException {
        //Testing default constructor
        Daycare d = Daycare.getInstance();

        // Testing addDog on an empty container
        d.addDog(new Dog("fido", "german shepard", 6, "brown"));

        d.addDog(new Dog("chaser", "poodle", 7));

        d.start();
        while(d.hasNext()){
            System.out.println(d.next().getName());
        }
        d.start();
        d.next().setAge(8);
        d.print();


    }
}
