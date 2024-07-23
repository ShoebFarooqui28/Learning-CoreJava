package BasicJava.Polymorphism;

public class Main {
    public static void main(String[] args) {

        Car car = new Car();
        Bicycle bicycle = new Bicycle();
        Boat boat = new Boat();

        Vehicle[] racers = new Vehicle[3];

        racers[0] = car;
        racers[1] = bicycle;
        racers[2] = boat;
        

        for(Vehicle x : racers){
            x.go();
        }

    }
}
