package BasicJava.StaticKeyword;

public class Employee {

    int empId;
    double salary;
    static String ceo = "faisal";

    public void displayEmp(){
        System.out.println("Employee ID : " + empId + "\n" + "Salary : " + salary + "\n" + "CEO : "+ ceo + "\n");
    }
}
