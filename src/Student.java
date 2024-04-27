package src;
public class Student extends Person{
    private double gpa;

    public Student(String id, Role role, String password, String firstName, String lastName) {
        super(id, role, password, firstName, lastName);
        gpa = 0;
    }

    public void setGPA(double gpa) {
        this.gpa = gpa;
    }

    public double getGPA() {
        return gpa;
    }
}
