package BasicJava.Methods;
public class OverLoadedMethods {
    public static void main(String[] args){
        
        int a, b, c, d;
        a = 10;
        b = 20;
        c = 30;
        d = 40;
        

        System.out.println(add(a, d));
        System.out.println(add(b, d));
        System.out.println(add(c, d, a));
        System.out.println(add(a, b, c, d));
    } 

    static int add(int x, int y){
        System.out.println("This is #1");
        return x + y;
    }

    static int add(int x, int y, int z){
        System.out.println("This is #2");
        return x + y + z;
    }

    static int add(int x, int y, int z, int f){
        System.out.println("This is #3");
        return x + y + z + f;
    }
    
    
}
