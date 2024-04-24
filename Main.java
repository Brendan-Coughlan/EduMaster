import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Student> students = new ArrayList<Student>();

    public static void main(String[] args) throws IOException {
        // Student x = new Student(35, "John", "Doe");
        // ArrayList<Boolean> a = new ArrayList<Boolean>();
        // a.add(true);
        // a.add(false);
        // a.add(false);
        // a.add(true);

        // CSVFIleManager studentsFile = new CSVFIleManager("Students.csv");
        // CSVFIleManager attendaneFile = new CSVFIleManager("Attendance.csv");
        // attendaneFile.writeToCSV(a);

        // AttendanceSystem attendance = new AttendanceSystem("5/23/24");
        // attendance.markAttendance("1234");
        // System.out.println(attendance.viewAttendance("1234"));
        // attendance.finalize();
        populateStudents();
        Scanner scnr = new Scanner(System.in);
        boolean exitProgram = false;
        while(!exitProgram) {
            String input = scnr.nextLine().toUpperCase();
            switch(input) {
                case "CREATE":
                    System.out.print("Enter ficrst name: ");
                    String firstName = scnr.nextLine();
                    System.out.print("Enter last name: ");
                    String lastName = scnr.nextLine();
                    Student newStudent = new Student("000000001", firstName, lastName);
                    students.add(newStudent);
                    System.out.println("Profile created");
                    break;
                case "PRINT":
                    for (Student student : students) {
                        System.out.println(student.getFullName());
                    }
                    break;
                case "EXIT":
                    System.out.println("Exiting program...");
                    exitProgram = true;
                    break;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
        finalizeStudents();
        scnr.close();
    }

    private static void populateStudents() throws IOException {
        CSVFIleManager studentsFile = new CSVFIleManager("Data/Students.csv");
        int i = 1;
        while(studentsFile.readFromCSV(i) != null && !studentsFile.readFromCSV(i).isEmpty()) {
            ArrayList<String> line = studentsFile.readFromCSV(i);
            students.add(new Student(line.get(0), line.get(1), line.get(2)));
            i++;
        }
    }

    private static void finalizeStudents() throws IOException {
        CSVFIleManager studentsFile = new CSVFIleManager("Data/Students.csv");
        ArrayList<String> headers = new ArrayList<String>() {
            {
                add("id");
                add("firstName");
                add("lastName");
            }
        };
        studentsFile.writeToCSV(headers, true);
        for (Student student : students) {
            studentsFile.writeToCSV(student.getList(), false);
        }
    }
}