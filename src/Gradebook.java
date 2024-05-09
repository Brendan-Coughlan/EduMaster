import java.io.IOException;

/**
 * Records grade records for a student
 * */
public class Gradebook {
    /**
     * Records the title and grade to a CSV file for that student
     * @param id the unique 9-digit ID for this student
     * @param title the title of what is being graded
     * @param grade the grade that the student got
     * */
    public void recordGrade(String id, String title, int grade) throws IOException{
        CSVFIleManager gradebook = new CSVFIleManager("Data/Gradebook-" + id + ".csv");
        if(gradebook.readFromCSV(0) == null || gradebook.readFromCSV(0).isEmpty()) {
            gradebook.writeToCSV("title", false);
            gradebook.writeToCSV("date", false);
            gradebook.writeToCSV("grade", true);
        }
        gradebook.writeToCSV(title, false);
        gradebook.writeToCSV("N/A", false);
        gradebook.writeToCSV(Integer.toString(grade), true);
    }

    /**
     * Records the title, date, and grade to a CSV file for that student
     * @param id the unique 9-digit ID for this student
     * @param title the title of what is being graded
     * @param date the date that the grade is being added for
     * @param grade the grade that the student got
     * */
    public void recordGrade(String id, String title, String date, int grade) throws IOException{
        CSVFIleManager gradebook = new CSVFIleManager("Data/Gradebook-" + id + ".csv");
        if(gradebook.readFromCSV(0) == null || gradebook.readFromCSV(0).isEmpty()) {
            gradebook.writeToCSV("title", false);
            gradebook.writeToCSV("date", false);
            gradebook.writeToCSV("grade", true);
        }
        gradebook.writeToCSV(title, false);
        gradebook.writeToCSV(date, false);
        gradebook.writeToCSV(Integer.toString(grade), true);
    }
}
