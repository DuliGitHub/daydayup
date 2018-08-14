package ahead;

public class Employee implements Comparable<Employee>{
    private Double salary;
    private String name;

    public Employee(Double salary, String name) {
        this.salary = salary;
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public void raiseSalary(double byPercent){
        double raise = salary * byPercent/100;
        salary += raise;
    }

    public int compareTo(Employee o) {
        return Double.compare(salary,o.salary);
    }
}
