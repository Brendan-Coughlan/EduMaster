package src;
import java.util.ArrayList;
/*
 * This class
 */


public abstract class Person {
    public static enum Role {Student, Teacher, Admin};
    private Role role = Role.Student;
    private String id;
    private String password;
    private String firstName;
    private String lastName;

    public Person(String id, Role role, String password, String firstName, String lastName) {
        this.id = id;
        this.role = role;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if(oldPassword.equals(password)) {
            password = newPassword;
            return true;
        }
        return false;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void seLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getID() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public boolean checkPassword(String password) {
        return password.equals(this.password);
    }

    protected String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public ArrayList<String> getList() {
        return new ArrayList<String>() {
            {
                add(id);
                add(role.toString());
                add(password);
                add(firstName);
                add(lastName);
                add("N/A");
            }
        };
    }
}
