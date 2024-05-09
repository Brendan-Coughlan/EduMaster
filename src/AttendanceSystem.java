import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** This class is used for keeping track and marking the attendance for a given date. */
public class AttendanceSystem {
    /**
     *The date of the attendance record
     * */
    private String date;
    /**
     * The  attendance record with the String being the ID of each studen
     * */
    private HashMap<String, Boolean> attendanceRecords = new HashMap<String, Boolean>();

    /**
     * The constructor for AttendanceSystem, initalizing all attendance to false
     * @param students the students that attendance is being taken for
     * */
    public AttendanceSystem(ArrayList<Student> students) throws IOException{
        this.date = java.time.LocalDate.now().toString();
        for(Student student : students) {
            attendanceRecords.put(student.getID(), false);
        }
    }

    /**
     * The constructor for AttendanceSystem, initalizing all attendance to false
     * @param students the students taking attendance for
     * @param date the date that attendance is being taken for
     * */
    public AttendanceSystem(ArrayList<Student> students, String date) throws IOException{
        this.date = date;
        for(Student student : students) {
            attendanceRecords.put(student.getID(), false);
        }
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Marks a given student ID as present
     * @param studentID that is being marked
     * @return if that particular ID could be marked
     */
    public boolean markAttendance(String studentID) {
        return attendanceRecords.replace(studentID, true);
    }

    /**
     * Writes the attendance records for the day to the CSV file for attedance (Note: Must be run before ending the program.)
     */
    public void finalizeAttendance() throws IOException{
        CSVFIleManager attendaneFile = new CSVFIleManager("Data/Attendance.csv");
        if(attendaneFile.readFromCSV(0) == null || attendaneFile.readFromCSV(0).size()-1 != attendanceRecords.size()) {
            ArrayList<String> keys = new ArrayList<String>();
            keys.add("Date");
            for (Map.Entry<String, Boolean> item : attendanceRecords.entrySet()) {
                keys.add(item.getKey());
            }
            attendaneFile.writeToCSV(keys, true);
        }
        attendaneFile.writeToCSV(date, false);
        attendaneFile.writeToCSV(attendanceRecords);
    } 
}