import java.util.*;

interface Ingredient {
    String getName();
    double getQuantity();
}

class SolidIngredient implements Ingredient{
    private String _name;
    private double _quantity;
    public SolidIngredient(String name, double quantity) {
        _name = name;
        _quantity = quantity;
    }
    public String getName(){
        return _name;
    }
    public double getQuantity() { //Override getQuantity
        return _quantity;
    }
}
class LiquidIngredient implements Ingredient{
    private String _name;
    private double _quantity;
    public LiquidIngredient(String name, double quantity){
        _name = name;
        _quantity = quantity;
    }
    public String getName(){
        return _name;
    }
    public double getQuantity() {
        return _quantity;
    }
}

class Recipe<T extends Ingredient> {
    private String _name;
    private String _instructions;
    private ArrayList<T> _ingredients;

    public Recipe(String name, String instructions){
        _name = name;
        _instructions = instructions;
        _ingredients = new ArrayList<>();
    }
    public void addIngredient(T t) {
        _ingredients.add(t);
    }
    public void print() {
        System.out.println("Recipe: " + _name);
        System.out.println("Instructions: " + _instructions);
        System.out.println("Ingredients:");
        for (T t : _ingredients) {
            System.out.println("- " + t.getName() + ": " + t.getQuantity());
        }
    }
}

public class templatinglab {
    public static int menu(Scanner scan){
        System.out.println("\nRecipe Menu: ");
        System.out.println("1. Add Ingredient ");
        System.out.println("2. List Ingredients ");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        int choice = 0;
        try {
            choice = Integer.valueOf(scan.nextLine());
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid data entered " + e.getMessage());
            System.exit(-1);
        }
        return choice;
    }

    public static void main(String[] args) {
        Recipe<Ingredient> recipe;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter recipe name: ");
        String name = scan.nextLine();
        System.out.print("Enter instructions: ");
        String instructions = scan.nextLine();
        recipe = new Recipe<Ingredient>(name, instructions);
        int choice = menu(scan);
        while (choice != 3){
            switch (choice) {
                case 1 -> addIngredient(recipe, scan);
                case 2 -> recipe.print();
                default -> System.out.println("Invalid choice. Try again.");
            }
            choice = menu(scan);
        }
    }
    public static void addIngredient(Recipe<Ingredient> recipe, Scanner scan) {
        System.out.println("Is this a solid (s) or liquid (l)");
        char type = scan.nextLine().charAt(0);
        System.out.print("Enter ingredient name");
        String name = scan.nextLine();
        System.out.print("Enter the quantity");
        double quantity = Double.parseDouble(scan.nextLine());
        Ingredient ingredient;
        if (type == 's')
            ingredient = new SolidIngredient(name, quantity);
        else
            ingredient = new LiquidIngredient(name, quantity);

        recipe.addIngredient(ingredient);
    }
}
