/**
 * Admin is the subclass of Person
 * */
public class Admin extends Person{

    /**
     * The constructor for Admin class
     * @param id the unique 9-digit ID for this person
     * @param role the role of this person
     * @param firstName the first name for this person
     * @param lastName the last name for this person
     * */
    public Admin(String id, Role role, String password, String firstName, String lastName) {
        super(id, role, password, firstName, lastName);
    }
}
