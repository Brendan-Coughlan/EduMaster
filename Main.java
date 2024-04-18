import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
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

        AttendanceSystem attendance = new AttendanceSystem("5/23/24");
        attendance.markAttendance("4");
        System.out.println(attendance.viewAttendance("0"));
        attendance.finalize();
    }
}