package src;

import java.util.ArrayList;

public class Student extends Person{
    private int gpa;

    public Student(String id, Role role, String password, String firstName, String lastName) {
        super(id, role, password, firstName, lastName);
        gpa = 0;
    }

    public void setGPA(int gpa) {
        this.gpa = gpa;
    }

    public int getGPA() {
        return gpa;
    }

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
