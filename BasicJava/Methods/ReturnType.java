package BasicJava.Methods;
public class ReturnType {
    public static void main(String[] args) {
        int x = 3;
        int y = 5;

        int result = add(x, y);
        
        System.out.println(result);

        //or

        System.out.println(add(x, y));
    }

    static int add(int x, int y){
    
        return x+y;
    }
}
