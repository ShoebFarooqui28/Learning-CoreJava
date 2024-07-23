package BasicJava.OverLoaded_Constructor;
public class Main {
    public static void main(String[] args) {
        
        //Pizza pizza = new Pizza("Thin Crust", "Alfredo", "Mozorella", "Salami"); 
        Pizza pizza2 = new Pizza("Thin Crust", "Alfredo"); 


        System.out.println("Here are the ingredients of your pizza");

        System.out.println(pizza2.bread);
        System.out.println(pizza2.sauce);
        System.out.println(pizza2.cheese);
        System.out.println(pizza2.topping);


    }
}