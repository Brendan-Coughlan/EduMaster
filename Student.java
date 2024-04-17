public class Student extends Person{
    private double gpa;

    public Student(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
        gpa = 0;
    }

    public void setGPA(double gpa) {
        this.gpa = gpa;
    }

    public double getGPA() {
        return gpa;
    }
}
