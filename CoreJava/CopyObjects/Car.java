package CoreJava.CopyObjects;

public class Car {
    private String make;

    Car(String make){
        this.setMake(make);

    }

    public String getMake(){
        return make;
    }
    public void setMake(String make){
        this.make = make;
    }

    public void copy(Car car1){
        this.setMake(car1.getMake());
    }
}
