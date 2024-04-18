import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AttendanceSystem {
    private final String date;
    private HashMap<String, Boolean> attendanceRecords = new HashMap<String, Boolean>();

    public AttendanceSystem(String date) {
        populateAttendane();
        this.date = date;
    }

    public boolean markAttendance(String studentID) {
        return attendanceRecords.replace(studentID, true);
    }

    public boolean viewAttendance(String studentID) {
        return attendanceRecords.get(studentID);
    }

    public void populateAttendane() {
        for(int i = 0; i < 5; i++) {
            attendanceRecords.put(Integer.toString(i), false);
        }
    }

    public void finalize() throws IOException{  
        CSVFIleManager attendaneFile = new CSVFIleManager("Attendance.csv");
        if(attendaneFile.readFromCSV(0) == null) {
            ArrayList<String> keys = new ArrayList<String>();
            keys.add("Date");
            for (Map.Entry<String, Boolean> item : attendanceRecords.entrySet()) {
                keys.add(item.getKey());
            }
            attendaneFile.writeToCSV(keys);
        }
        attendaneFile.writeToCSV(date, false);
        attendaneFile.writeToCSV(attendanceRecords, true);
    } 
}