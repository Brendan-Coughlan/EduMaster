import java.util.ArrayList;

/**
 * Student is the subclass of Person
 * */
public class Student extends Person{
    /**
     * The grade point average for the student
     * */
    private int gpa;

    /**
     * Admin is the subclass of Person
     * */
    public Student(String id, Role role, String password, String firstName, String lastName) {
        super(id, role, password, firstName, lastName);
        gpa = 0;
    }

    /**
     * Setter function for GPA
     * @param gpa the new gpa
     * */
    public void setGPA(int gpa) {
        this.gpa = gpa;
    }

    /**
     * Getter function for GPA
     * @return the gpa for this student
     * */
    public int getGPA() {
        return gpa;
    }

    /**
     * Gives ArrayList to represent this student
     * @return the list for this student
     * */
    public ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(super.getID());
        list.add(super.getRole().toString());
        list.add(super.getPassword());
        list.add(super.getFirstName());
        list.add(super.getLastName());
        list.add(Integer.toString(gpa));
        return list;
    }
}
