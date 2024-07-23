package BasicJava.DynamicPolymorphism;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Animal animal;

        System.out.println("What animal do you want?");
        System.out.println("(1 : Dog) or (2 : Cat)");
        int choice = sc.nextInt();

        if (choice == 1) {
            animal = new Dog();
            animal.speak();
        }
        else if(choice == 2){
            animal = new Cat();
            animal.speak();
        }
        else{
            System.out.println("Please choose between '1' or '2' ");
        }

        sc.close();
    }
}