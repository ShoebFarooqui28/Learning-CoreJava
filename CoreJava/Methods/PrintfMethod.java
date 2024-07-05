package CoreJava.Methods;
public class PrintfMethod {
    public static void main(String[] args) {
        
        boolean myBoolean = false;
        char myChar = '@';
        String myString = "Hello";
        int myInt = 3;
        double myDouble = 3.14;

        System.out.printf("My name is %s\n", myString);
        System.out.printf("My name is %d\n", myInt);
        System.out.printf("My name is %c\n", myChar);
        System.out.printf("My name is %b\n", myBoolean);
        System.out.printf("My name is %.2f\n", myDouble);


    }
}
