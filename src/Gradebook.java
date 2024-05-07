package src;

import java.io.IOException;

public class Gradebook {
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
