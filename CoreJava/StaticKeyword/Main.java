package CoreJava.StaticKeyword;

public class Main {
    @SuppressWarnings("unused")
    public static void main(String[] args) {

        Employee hasan = new Employee();
        hasan.empId = 9919;
        hasan.salary = 55000;
        
        Employee shoeb = new Employee();
        shoeb.empId = 7860;
        shoeb.salary = 45000;

        shoeb.displayEmp();
        hasan.displayEmp();
    }
}
