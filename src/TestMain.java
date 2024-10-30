import java.util.*;
class Test {
    private int _x;
    public Test() {}
    public Test(int a) { _x = a; }
    public void setX(int a) { _x = a; }
    public int getX() { return _x; }
    public boolean equals(Object t){
        System.out.println("in test equals");
        if(this == t)return true;
        if (t == null) return false;
        if (t instanceof Test)
            return _x == ((Test)t)._x;
        return false;
    }
}
class Test2 {
    private int _y;
    public Test2() {}
    public Test2(int a) { _y = a; }
    public void sety(int a) { _y = a; }
    public int gety() { return _y; }
}
class Stuff<T> {
    private ArrayList<T> _thing;
    public Stuff() { _thing = new ArrayList<T>(); }
    public Stuff(int size) { _thing = new ArrayList<T>(size); }
    public void addItem(T t) {
        _thing.add(t);
    }
    public T removeItem(int idx) {
        if (idx < _thing.size() && idx >= 0) {
            T item = _thing.remove(idx);
            return item;
        }
        return null;
    }
    public boolean findItem(T t) {
        boolean found = false;
        int i = 0;
        while (found != true && i < _thing.size()) {
            if (_thing.get(i).equals(t))
                found = true;
            i++;
        }
        return found;
    }
    //public T getThing() {return _thing;}
    //public void setThing(T item) {_thing = item;}
    public void print() {
        if (_thing != null)
            System.out.println("Type: " + _thing.getClass().getName() + ", value: " + _thing);
        else
            System.out.println("null member variable");
    }
}
public class TestMain {
    public static void main(String[] args) {
        Stuff<Test> s = new Stuff<Test>();
        Stuff<Test> s2 = new Stuff<Test>(5);
        s.addItem(new Test(3));
        Test t = new Test(3);
        System.out.println(s.findItem(t));
    }
}
