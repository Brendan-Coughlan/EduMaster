package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EduMaster {
    private ArrayList<Person> persons = new ArrayList<Person>();
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    private ArrayList<Admin> admins = new ArrayList<Admin>();
    private AttendanceSystem attendanceSystem;
    private GUI gui;

    public EduMaster() throws IOException {
        populateLists();
        gui = new GUI();
    }

    public void startProgram() throws IOException {
        Scanner scnr = new Scanner(System.in);
        boolean exitProgram = false;
        String id = "";
        String password = "";

        while (true) {
            try {
                id = gui.getIDInput();
                password = gui.getPasswordInput();

                if (gui.getEnterButtonPressed()) {
                    if (getPersonByID(id).checkPassword(password)) {
                        System.out.println("You are logged in");
                        gui.createActionsFrame();
                        break;
                    } else {
                        System.out.println("Invalid ID or password");
                    }
                    gui.setEnterButtonPressed(false);
                }
            } catch (Exception e) {
                if (gui.getEnterButtonPressed()) {
                    System.out.println("Invalid ID or password");
                    gui.setEnterButtonPressed(false);
                }
            }
        }

        while (!exitProgram) {
            String input = scnr.nextLine().toUpperCase();
            switch (input) {
                case "CHANGE PASSWORD":
                    System.out.print("Enter old password: ");
                    String oldPassword = scnr.nextLine();
                    if (getPersonByID(id).checkPassword(oldPassword)) {
                        System.out.print("Enter new password: ");
                        password = scnr.nextLine();
                        getPersonByID(id).changePassword(oldPassword, password);
                        System.out.println("Password successfully changed.");
                    } else {
                        System.out.println("Incorrect passowrd. Cannot change password.");
                    }
                    break;
                case "CREATE":
                    if (getPersonByID(id).getRole() == Person.Role.Student
                            || getPersonByID(id).getRole() == Person.Role.Teacher) {
                        System.out.println("Unauthorized action");
                        break;
                    }
                    createProfile(scnr);
                    break;
                case "LOOKUP":
                    if (getPersonByID(id).getRole() == Person.Role.Student) {
                        System.out.println("Unauthorized action");
                        break;
                    }
                    System.out.println("Enter ID for lookup:");
                    String searchID = scnr.nextLine();
                    for (Person person : persons) {

                        if (person.getID().equals(searchID)) {
                            System.out.print("Full Name: " + person.getFullName());
                        }
                    }
                    break;
                case "PRINT ALL":
                    if (getPersonByID(id).getRole() == Person.Role.Student
                            || getPersonByID(id).getRole() == Person.Role.Teacher) {
                        System.out.println("Unauthorized action");
                        break;
                    }
                    if (persons.isEmpty()) {
                        System.out.println("None");
                        break;
                    }
                    for (Person person : persons) {
                        System.out.println(person.getFullName());
                    }
                    break;
                case "PRINT STUDENTS":
                    if (getPersonByID(id).getRole() == Person.Role.Student) {
                        System.out.println("Unauthorized action");
                        break;
                    }
                    if (students.isEmpty()) {
                        System.out.println("None");
                        break;
                    }
                    for (Student student : students) {
                        System.out.println(student.getFullName());
                    }
                    break;
                case "PRINT TEACHERS":
                    if (getPersonByID(id).getRole() == Person.Role.Student
                            || getPersonByID(id).getRole() == Person.Role.Teacher) {
                        System.out.println("Unauthorized action");
                        break;
                    }
                    if (teachers.isEmpty()) {
                        System.out.println("None");
                        break;
                    }
                    for (Teacher teacher : teachers) {
                        System.out.println(teacher.getFullName());
                    }
                    break;
                case "PRINT ADMINS":
                    if (getPersonByID(id).getRole() == Person.Role.Student
                            || getPersonByID(id).getRole() == Person.Role.Teacher) {
                        System.out.println("Unauthorized action");
                        break;
                    }
                    if (admins.isEmpty()) {
                        System.out.println("None");
                        break;
                    }
                    for (Admin admin : admins) {
                        System.out.println(admin.getFullName());
                    }
                    break;
                case "ATTENDANCE":
                    if (getPersonByID(id).getRole() == Person.Role.Student) {
                        System.out.println("Unauthorized action");
                        break;
                    }
                    attendanceSystem = new AttendanceSystem(students);
                    System.out.println("Enter Y/N if the student is present or absent, respectively.");
                    for (Student student : students) {
                        System.out.print(student.getFullName() + ": ");
                        String mark = scnr.nextLine().toUpperCase();
                        if (mark.equals("Y")) {
                            attendanceSystem.markAttendance(student.getID());
                        }
                    }
                    System.out.println("That is the end of the student list.");
                    break;
                case "?":
                    switch (getPersonByID(id).getRole()) {
                        case Student:
                            System.out.println("CHANGE PASSWORD - Change passwod\nEXIT - Exits the program");
                            break;
                        case Teacher:
                            System.out.println(
                                    "ATTENDANCE - Mark attendance\nCHANGE PASSWORD - Change passwod\nPRINT STUDENTS - Prints all student profiles in the system\nEXIT - Exits the program");
                            break;
                        case Admin:
                            System.out.println(
                                    "CREATE - Prompts user to add a new profile to the system\nATTENDANCE - Mark attendance\nCHANGE PASSWORD - Change passwod\nPRINT ALL - Prints all profiles in the system\nPRINT STUDENTS - Prints all student profiles in the system\nPRINT TEACHERS - Prints all teacher profiles in the system\nPRINT ADMINS - Prints all admin profiles in the system\nEXIT - Exits the program");
                            break;
                    }
                    break;
                case "EXIT":
                    System.out.println("Exiting program...");
                    exitProgram = true;
                    break;
                default:
                    System.out.println("Invalid action. Enter ? to get a list of actions you can perform.");
                    break;
            }
        }
        gui.close();
        if (attendanceSystem != null)
            attendanceSystem.finalizeAttendance();
        finalizeLists();
        scnr.close();
    }

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

    /*
     * Rewrites all the data on People CSV file with the new updated information
     * 
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
            }
        };
        peopleFile.writeToCSV(headers, true);
        for (Person person : persons) {
            peopleFile.writeToCSV(person.getList(), false);
        }
    }

    private Person getPersonByID(String id) {
        for (Person person : persons) {
            if (person.getID().equals(id))
                return person;
        }
        return null;
    }

    private void createProfile(Scanner scnr) {
        int id = 0;
        for (Person person : persons) {
            if (id < Integer.parseInt(person.getID())) {
                id = Integer.parseInt(person.getID());
            }
        }

        String role = "";
        System.out.print("Enter first name: ");
        String firstName = scnr.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scnr.nextLine();
        while (true) {
            System.out.print("Enter role: ");
            role = scnr.nextLine();
            try {
                Person newPerson = null;
                switch (role) {
                    case "Student":
                        newPerson = new Student(String.format("%09d", id + 1), Person.Role.valueOf(role),
                                firstName.charAt(0) + lastName,
                                firstName, lastName);
                        students.add((Student) newPerson);
                        break;
                    case "Teacher":
                        newPerson = new Teacher(String.format("%09d", id + 1), Person.Role.valueOf(role),
                                firstName.charAt(0) + lastName,
                                firstName, lastName);
                        teachers.add((Teacher) newPerson);
                        break;
                    case "Admin":
                        newPerson = new Admin(String.format("%09d", id + 1), Person.Role.valueOf(role),
                                firstName.charAt(0) + lastName,
                                firstName, lastName);
                        admins.add((Admin) newPerson);
                        break;
                }
                persons.add(newPerson);
                break;
            } catch (Exception e) {
                System.out.println("Invalid role");
            }
        }
        System.out.println("Profile created");
    }
}