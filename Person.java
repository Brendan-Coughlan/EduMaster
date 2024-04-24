import java.util.ArrayList;

public abstract class Person {
    private String id;
    private String firstName;
    private String lastName;

    public Person(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setID(String id) {
        this.id = id;
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
                add(firstName);
                add(lastName);
            }
        };
    }
}
