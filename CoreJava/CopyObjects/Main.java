package CoreJava.CopyObjects;

public class Main {
    public static void main(String[] args) {
        System.out.println();

        Car car1 = new Car("Toyata");
        Car car2 = new Car("BMW");

        car2.copy(car1);

        System.out.println(car1);
        System.out.println(car2);

        System.out.println();

        System.out.println(car1.getMake());
        
        System.out.println();

        System.out.println(car2.getMake());
    }
}
