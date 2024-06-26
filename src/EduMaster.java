import java.io.IOException;
import java.util.ArrayList;

/**
 * The main program and features for EduMaster
 */
public class EduMaster {
    /**
    * All the people in the data
    */
    private ArrayList<Person> persons = new ArrayList<Person>();
    /**
    * All the students in the data
    */
    private ArrayList<Student> students = new ArrayList<Student>();
    /**
    * All the teachers in the data
    */
    private ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    /**
     * All the admins in the data
     */
    private ArrayList<Admin> admins = new ArrayList<Admin>();
    /**
     * Instance of attendance system
     */
    private AttendanceSystem attendanceSystem;
    /**
     * Instance of gradebook system
     */
    private Gradebook gradebook = new Gradebook();
    /**
     * Instance of GUI
     */
    private GUI gui;
    /**
     * The current user
     */
    private Person currentUser;
    /**
     * Should the program end
     */
    private boolean exitProgram = false;

    /**
     * The constructor for EduMaster
     */
    public EduMaster() throws IOException {
        populateLists();
        gui = new GUI();
    }

    /**
     * Starts the program
     */
    public void startProgram() throws IOException {
        String id = "";
        String password = "";

        while (true) {
            try {
                id = gui.getTextField(0);
                password = gui.getTextField(1);

                if (gui.getButtonPressed()) {
                    if (gui.getAction() == 5) {
                        exitProgram = true;
                        break;
                    }
                    if (getPersonByID(id).checkPassword(password)) {
                        currentUser = getPersonByID(id);
                        gui.createActionsFrame(currentUser.getRole());
                        gui.setButtonPressed(false);
                        break;
                    } else {
                        gui.showIncorrectLabel("Invalid ID or Password");
                    }
                    gui.setButtonPressed(false);
                }
            } catch (Exception e) {
                if (gui.getButtonPressed()) {
                    if (gui.getAction() == 5) {
                        exitProgram = true;
                        return;
                    }
                    gui.showIncorrectLabel("Invalid ID or password");
                    gui.setButtonPressed(false);
                }
            }
        }

        while (!exitProgram) {
            System.out.print("");
            if (gui.getButtonPressed()) {
                gui.setButtonPressed(false);
                switch (gui.getAction()) {
                    case 1:
                        gui.createChangePasswordFrame();
                        changePassword();
                        break;
                    case 2:
                        gui.createAttendanceFrame(students);
                        takeAttendance();
                        break;
                    case 3:
                        gui.createNewUserFrame();
                        createProfile();
                        break;
                    case 4:
                        gui.createNewGradeFrame();
                        addGrade();
                        break;
                    case 5:
                        exitProgram = true;
                        break;
                    case 6:
                        gui.createProfileFrame(currentUser);
                        viewProfile();
                        break;
                }
            }
        }
        calculateGPA();
        gui.close();
        finalizeLists();
    }

    /**
     * Calculates the GPA for all the students in the data
     */
    private void calculateGPA() throws IOException {
        for (Student student : students) {
            CSVFIleManager gradebookFile = new CSVFIleManager("Data/Gradebook-" + student.getID() + ".csv");
            int currentGPA = 0;
            int i = 1;
            while (gradebookFile.readFromCSV(i) != null) {
                currentGPA += Integer.parseInt(gradebookFile.readFromCSV(i).get(2));
                i++;
            }

            if (i == 1) {
                currentGPA /= i;
            } else {
                currentGPA /= (i - 1);
            }
            student.setGPA(currentGPA);
        }
    }

    /**
     * Populates each ArrayList with all the users from the CSV file
     */
    private void populateLists() throws IOException {
        CSVFIleManager studentsFile = new CSVFIleManager("Data/People.csv");
        int i = 1;
        Person newPerson = null;
        while (studentsFile.readFromCSV(i) != null && !studentsFile.readFromCSV(i).isEmpty()) {
            ArrayList<String> line = studentsFile.readFromCSV(i);
            switch (line.get(1)) {
                case "Student":
                    newPerson = new Student(line.get(0), Person.Role.valueOf(line.get(1)), line.get(2), line.get(3),
                            line.get(4));
                    ((Student) newPerson).setGPA(Integer.parseInt(line.get(5)));
                    students.add((Student) newPerson);
                    break;
                case "Teacher":
                    newPerson = new Teacher(line.get(0), Person.Role.valueOf(line.get(1)), line.get(2), line.get(3),
                            line.get(4));
                    teachers.add((Teacher) newPerson);
                    break;
                case "Admin":
                    newPerson = new Admin(line.get(0), Person.Role.valueOf(line.get(1)), line.get(2), line.get(3),
                            line.get(4));
                    admins.add((Admin) newPerson);
                    break;
            }
            persons.add(newPerson);
            i++;
        }
    }

    /**
     * Rewrites the CSV file with the updated information
     */
    private void finalizeLists() throws IOException {
        CSVFIleManager peopleFile = new CSVFIleManager("Data/People.csv");
        ArrayList<String> headers = new ArrayList<String>() {
            {
                add("id");
                add("role");
                add("password");
                add("firstName");
                add("lastName");
                add("gpa");
            }
        };
        peopleFile.writeToCSV(headers, true);
        for (Person person : persons) {
            peopleFile.writeToCSV(person.getList(), false);
        }
    }

    /**
     * Searches through the list to find a person by their ID
     * @param id the id of the person being searched
     * @return the person who has that ID or null if no one has that ID
     */
    private Person getPersonByID(String id) {
        for (Person person : persons) {
            if (person.getID().equals(id))
                return person;
        }
        return null;
    }

    /**
     * Takes information from the attendance frame and writes it to the CSV file
     */
    private void takeAttendance() throws IOException {
        while (!gui.getButtonPressed()) {
            System.out.print("");
        }
        if (gui.getAction() == 7) {
            gui.setButtonPressed(false);
            gui.createActionsFrame(currentUser.getRole());
            return;
        }
        ArrayList<Boolean> marks = gui.getAttendanceMarks();
        if(gui.getTextField(7).equals("")) {
            attendanceSystem = new AttendanceSystem(students);
        }
        else {
            attendanceSystem = new AttendanceSystem(students, gui.getTextField(7));
        }
        for (int i = 0; i < students.size(); i++) {
            if (marks.get(i)) {
                attendanceSystem.markAttendance(students.get(i).getID());
            }
        }
        if (!gui.getTextField(7).equals("")) {
            attendanceSystem.setDate(gui.getTextField(7));
        }
        attendanceSystem.finalizeAttendance();
        gui.setButtonPressed(false);
        gui.createActionsFrame(currentUser.getRole());
    }

    /**
     * Takes information from the create profile frame and creates a new object with those values
     */
    private void createProfile() {
        while (!gui.getButtonPressed()) {
            System.out.print("");
        }
        if (gui.getAction() == 7) {
            gui.setButtonPressed(false);
            gui.createActionsFrame(currentUser.getRole());
            return;
        }

        int id = 0;
        for (Person person : persons) {
            if (id < Integer.parseInt(person.getID())) {
                id = Integer.parseInt(person.getID());
            }
        }
        String firstName = gui.getTextField(3);
        String lastName = gui.getTextField(4);
        Person newPerson = null;
        switch (gui.getSelectedRole()) {
            case Student:
                newPerson = new Student(String.format("%09d", id + 1), gui.getSelectedRole(),
                        firstName.charAt(0) + lastName,
                        firstName, lastName);
                students.add((Student) newPerson);
                break;
            case Teacher:
                newPerson = new Teacher(String.format("%09d", id + 1), gui.getSelectedRole(),
                        firstName.charAt(0) + lastName,
                        firstName, lastName);
                teachers.add((Teacher) newPerson);
                break;
            case Admin:
                newPerson = new Admin(String.format("%09d", id + 1), gui.getSelectedRole(),
                        firstName.charAt(0) + lastName,
                        firstName, lastName);
                admins.add((Admin) newPerson);
                break;
        }
        persons.add(newPerson);
        gui.setButtonPressed(false);
        gui.createActionsFrame(currentUser.getRole());
    }

    /**
     * Takes information from the change password frame and changes the user's password to the new password
     */
    private void changePassword() {
        gui.setTextField(1, "");
        while (!gui.getButtonPressed()) {
            System.out.print("");
        }
        if (gui.getAction() == 7) {
            gui.setButtonPressed(false);
            gui.createActionsFrame(currentUser.getRole());
            return;
        }
        if (currentUser.checkPassword(gui.getTextField(1))) {
            currentUser.changePassword(gui.getTextField(1), gui.getTextField(2));
            gui.setButtonPressed(false);
            gui.createActionsFrame(currentUser.getRole());
        } else {
            gui.showIncorrectLabel("Incorrect passowrd");
            gui.setButtonPressed(false);
            changePassword();
        }
    }

    /**
     * Adds grade to the gradebook of a student
     */
    private void addGrade() throws IOException {
        gui.setTextField(0, "");
        while (!gui.getButtonPressed()) {
            System.out.print("");
        }
        if (gui.getAction() == 7) {
            gui.setButtonPressed(false);
            gui.createActionsFrame(currentUser.getRole());
            return;
        }
        if (getPersonByID(gui.getTextField(0)) != null) {
            if (getPersonByID(gui.getTextField(0)).getRole() == Person.Role.Student) {
                if (!gui.getTextField(6).equals("")) {
                    if (gui.getTextField(7).equals("")) {
                        gradebook.recordGrade(gui.getTextField(0), gui.getTextField(6),
                                Integer.parseInt(gui.getTextField(5)));
                    } else {
                        gradebook.recordGrade(gui.getTextField(0), gui.getTextField(6), gui.getTextField(7),
                                Integer.parseInt(gui.getTextField(5)));
                    }
                    gui.setButtonPressed(false);
                    gui.createActionsFrame(currentUser.getRole());
                } else {
                    gui.showIncorrectLabel("Need a Title");
                    gui.setButtonPressed(false);
                    addGrade();
                }
            } else {
                gui.showIncorrectLabel("Not a Student");
                gui.setButtonPressed(false);
                addGrade();
            }
        } else {
            gui.showIncorrectLabel("Invalid ID");
            gui.setButtonPressed(false);
            addGrade();
        }
    }

    /**
     * Takes information from the create profile frame and creates a new object with those values
     */
    private void viewProfile() {
        while (!gui.getButtonPressed()) {
            System.out.print("");
        }
        if (gui.getAction() == 7) {
            gui.setButtonPressed(false);
            gui.createActionsFrame(currentUser.getRole());
            return;
        }
    }
}