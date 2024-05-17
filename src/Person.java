import java.util.ArrayList;

/**
 * Person is the base class for all the users
 * */
public abstract class Person {
    /**
     * Roles that people can be
     * */
    public static enum Role {Student, Teacher, Admin};
    /**
     * The role of this person
     * */
    private Role role;
    /**
     * The unique 9-digit id for this person
     * */
    private String id;
    /**
     * The password for this person
     * */
    private String password;
    /**
     * The first name for this person
     * */
    private String firstName;
    /**
     * The last name for this person
     * */
    private String lastName;

    /**
     * The constructor for Person class
     * @param id the unique 9-digit ID for this person
     * @param role the role of this person
     * @param firstName the first name for this person
     * @param lastName the last name for this person
     * */
    public Person(String id, Role role, String password, String firstName, String lastName) {
        this.id = id;
        this.role = role;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Setter function for ID
     * @param id the new ID of this person
     * */
    public void setID(String id) {
        this.id = id;
    }

    /**
     * Setter function for role
     * @param role the new role of this person
     * */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Changes password only if the old password matches the current password
     * @param oldPassword is the old/current password
     * @param newPassword is the new password
     * @return if the password has been changed
     * */
    public boolean changePassword(String oldPassword, String newPassword) {
        if(oldPassword.equals(password)) {
            password = newPassword;
            return true;
        }
        return false;
    }

    /**
     * Setter function for first name
     * @param firstName the new first name
     * */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Setter function for last name
     * @param lastName the new last name
     * */
    public void seLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getID() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    /**
     * Checks if the password is correct
     * @param password the password input
     * */
    public boolean checkPassword(String password) {
        return password.equals(this.password);
    }

    /**
     * Getter function for password
     * @return the password
     * */
    protected String getPassword() {
        return password;
    }

    /**
     * Getter function for first name
     * @return the first name
     * */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter function for last name
     * @return the last name
     * */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter function for the full name
     * @return the first name and last name
     * */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Gives ArrayList to represent this student
     * @return the list for this person
     * */
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
