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
    private Person currentUser;
    private boolean exitProgram = false;

    public EduMaster() throws IOException {
        populateLists();
        gui = new GUI();
    }

    public void startProgram() throws IOException {
        String id = "";
        String password = "";

        while (true) {
            try {
                id = gui.getTextField(0);
                password = gui.getTextField(1);

                if (gui.getButtonPressed()) {
                    if (gui.getAction() == 4) {
                        exitProgram = true;
                        break;
                    }
                    if (getPersonByID(id).checkPassword(password)) {
                        currentUser = getPersonByID(id);
                        System.out.println("You are logged in");
                        gui.createActionsFrame();
                        gui.setButtonPressed(false);
                        break;
                    } else {
                        gui.showIncorrectLabel("Invalid ID or password");
                    }
                    gui.setButtonPressed(false);
                }
            } catch (Exception e) {
                if (gui.getButtonPressed()) {
                    if (gui.getAction() == 4) {
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
                        exitProgram = true;
                        break;
                }
            }
        }

        // while (!exitProgram) {
        // String input = scnr.nextLine().toUpperCase();
        // switch (input) {
        // case "CHANGE PASSWORD":

        // break;
        // case "CREATE":
        // if (getPersonByID(id).getRole() == Person.Role.Student
        // || getPersonByID(id).getRole() == Person.Role.Teacher) {
        // System.out.println("Unauthorized action");
        // break;
        // }
        // createProfile(scnr);
        // break;
        // case "LOOKUP":
        // if (getPersonByID(id).getRole() == Person.Role.Student) {
        // System.out.println("Unauthorized action");
        // break;
        // }
        // System.out.println("Enter ID for lookup:");
        // String searchID = scnr.nextLine();
        // for (Person person : persons) {

        // if (person.getID().equals(searchID)) {
        // System.out.print("Full Name: " + person.getFullName());
        // }
        // }
        // break;
        // case "PRINT ALL":
        // if (getPersonByID(id).getRole() == Person.Role.Student
        // || getPersonByID(id).getRole() == Person.Role.Teacher) {
        // System.out.println("Unauthorized action");
        // break;
        // }
        // if (persons.isEmpty()) {
        // System.out.println("None");
        // break;
        // }
        // for (Person person : persons) {
        // System.out.println(person.getFullName());
        // }
        // break;
        // case "PRINT STUDENTS":
        // if (getPersonByID(id).getRole() == Person.Role.Student) {
        // System.out.println("Unauthorized action");
        // break;
        // }
        // if (students.isEmpty()) {
        // System.out.println("None");
        // break;
        // }
        // for (Student student : students) {
        // System.out.println(student.getFullName());
        // }
        // break;
        // case "PRINT TEACHERS":
        // if (getPersonByID(id).getRole() == Person.Role.Student
        // || getPersonByID(id).getRole() == Person.Role.Teacher) {
        // System.out.println("Unauthorized action");
        // break;
        // }
        // if (teachers.isEmpty()) {
        // System.out.println("None");
        // break;
        // }
        // for (Teacher teacher : teachers) {
        // System.out.println(teacher.getFullName());
        // }
        // break;
        // case "PRINT ADMINS":
        // if (getPersonByID(id).getRole() == Person.Role.Student
        // || getPersonByID(id).getRole() == Person.Role.Teacher) {
        // System.out.println("Unauthorized action");
        // break;
        // }
        // if (admins.isEmpty()) {
        // System.out.println("None");
        // break;
        // }
        // for (Admin admin : admins) {
        // System.out.println(admin.getFullName());
        // }
        // break;
        // case "ATTENDANCE":
        // if (getPersonByID(id).getRole() == Person.Role.Student) {
        // System.out.println("Unauthorized action");
        // break;
        // }
        // attendanceSystem = new AttendanceSystem(students);
        // System.out.println("Enter Y/N if the student is present or absent,
        // respectively.");
        // for (Student student : students) {
        // System.out.print(student.getFullName() + ": ");
        // String mark = scnr.nextLine().toUpperCase();
        // if (mark.equals("Y")) {
        // attendanceSystem.markAttendance(student.getID());
        // }
        // }
        // System.out.println("That is the end of the student list.");
        // break;
        // case "?":
        // switch (getPersonByID(id).getRole()) {
        // case Student:
        // System.out.println("CHANGE PASSWORD - Change passwod\nEXIT - Exits the
        // program");
        // break;
        // case Teacher:
        // System.out.println(
        // "ATTENDANCE - Mark attendance\nCHANGE PASSWORD - Change passwod\nPRINT
        // STUDENTS - Prints all student profiles in the system\nEXIT - Exits the
        // program");
        // break;
        // case Admin:
        // System.out.println(
        // "CREATE - Prompts user to add a new profile to the system\nATTENDANCE - Mark
        // attendance\nCHANGE PASSWORD - Change passwod\nPRINT ALL - Prints all profiles
        // in the system\nPRINT STUDENTS - Prints all student profiles in the
        // system\nPRINT TEACHERS - Prints all teacher profiles in the system\nPRINT
        // ADMINS - Prints all admin profiles in the system\nEXIT - Exits the program");
        // break;
        // }
        // break;
        // case "EXIT":
        // System.out.println("Exiting program...");
        // exitProgram = true;
        // break;
        // default:
        // System.out.println("Invalid action. Enter ? to get a list of actions you can
        // perform.");
        // break;
        // }
        // }
        System.out.println("Here");
        gui.close();
        if (attendanceSystem != null)
            attendanceSystem.finalizeAttendance();
        finalizeLists();
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

    private void takeAttendance() throws IOException {
        attendanceSystem = new AttendanceSystem(students);
        while (!gui.getButtonPressed()) {
            System.out.print("");
        }
        if (gui.getAction() == 4) {
            exitProgram = true;
            return;
        }
        ArrayList<Boolean> marks = gui.getAttendanceMarks();
        for (int i = 0; i < students.size(); i++) {
            if (marks.get(i)) {
                attendanceSystem.markAttendance(students.get(i).getID());
            }
        }
        gui.setButtonPressed(false);
        gui.createActionsFrame();
    }

    private void createProfile() {
        while (!gui.getButtonPressed()) {
            System.out.print("");
        }
        if (gui.getAction() == 4) {
            exitProgram = true;
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
        gui.createActionsFrame();
    }

    public void changePassword() {
        gui.setTextField(1, "");
        while (!gui.getButtonPressed()) {
            System.out.print("");
        }
        if (gui.getAction() == 4) {
            exitProgram = true;
            return;
        }
        if (currentUser.checkPassword(gui.getTextField(1))) {
            currentUser.changePassword(gui.getTextField(1), gui.getTextField(2));
            gui.setButtonPressed(false);
            gui.createActionsFrame();
        } else {
            gui.showIncorrectLabel("Incorrect passowrd");
            gui.setButtonPressed(false);
            changePassword();
        }
    }
}