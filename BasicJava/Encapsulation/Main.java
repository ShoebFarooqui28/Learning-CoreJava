package BasicJava.Encapsulation;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Toyota", "Civic",2023);

        //System.out.println(car.make);   //error because the variable is private

        System.out.println(car.getMake());
        System.out.println(car.getModel());
        System.out.println(car.getYear());


        car.setMake("hello");
        System.out.println(car.getMake());        
    }


}