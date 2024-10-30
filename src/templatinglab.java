import java.util.*;
interface Ingredient {
    public String getName();
    public int getQuantity();
}

class SolidIngredient implements Ingredient{
    private String _name;
    private int _quantity;
    public int getQuantity() {
        return -1;
    }
    public String getName(){
        return "s";
    }
}
class LiquidIngredient implements Ingredient{
    private String _name;
    private int _quantity;
    public LiquidIngredient(String name, int quantity){
        _name = name;
        _quantity = quantity;
    }
    public int getQuantity() {
        return -1;
    }
    public String getName(){
        return "s";
    }
}
class Recipe<T extends Ingredient>{
    private String _name;
    private String _instructions;
    private ArrayList<T> setIngredients;
    public Recipe(String name, String instructions){
        _instructions = instructions;
        _name = name;
    }
    public void addIngredient(T ingredients){
    }
}

public class templatinglab {
    public static void main(String[] args) {

    }
}
