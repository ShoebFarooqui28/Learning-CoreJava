package OOPs.ExceptionHandling;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Enter a number to divide : ");
            int x = sc.nextInt();

            System.out.println("Enter a number to divide by : ");
            int y = sc.nextInt();


            int z = (x/y);

            System.out.println("Result : "+z);
        }
        catch(ArithmeticException e){
            System.out.println("You cant divide by 0 idiot!");
        } 
        catch(InputMismatchException z){
            System.out.println("Please enter a number!");
        }
        catch(Exception y){
            System.out.println("Something went wrong!");
        }
        finally{
            sc.close();
        }
    }
}
 