/**
 * Teacher is the subclass of Person
 * */
public class Teacher extends Person{

    /**
     * The constructor for Teacher class
     * @param id the unique 9-digit ID for this person
     * @param role the role of this person
     * @param firstName the first name for this person
     * @param lastName the last name for this person
     * */
    public Teacher(String id, Role role, String password, String firstName, String lastName) {
        super(id, role, password, firstName, lastName);
    }
}
