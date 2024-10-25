class Test{
    private int _x;
    public Test(){}
    public Test(int a) {_x = a;}
    public void setX(int a) {_x = a;}
    public int getX(){return _x;}
}
class Test2{
    private int _y;
    public Test2(){}
    public Test2(int a) {_y = a;}
    public void sety(int a) {_y = a;}
    public int gety(){return _y;}
}
class Stuff<T>{
    private T _thing;
    public Stuff() {_thing = null;}
    public Stuff(T t) {_thing = t;}
    public T getThing() {return _thing;}
    public void setThing(T t){_thing = t;}
    public void print () {
        if (_thing != null)
            System.out.println("Type: " + _thing.getClass().getName() + ", value: " + _thing);
        else
            System.out.println("null member variable");
    }
}
public class TestMain {
    public static void main(String[] args) {
        Stuff<Test> s = new Stuff<Test>();
        s.print();
        s.setThing(new Test(5));
        s.print();
        System.out.println(s.getThing().getX());

    }
}
