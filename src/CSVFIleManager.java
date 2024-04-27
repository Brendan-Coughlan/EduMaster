package src;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The purpose of this class is to convert and write any given data into CSV format.
 */

public class CSVFIleManager {
    private final String fileName;

    public CSVFIleManager(String fileName) throws IOException{
        this.fileName = fileName;
        createCSVFile();
    }

    /**
     * Creates a new file if one doesn't exist at the given oath.
     * @return if a new file was created
     */
    public boolean createCSVFile() throws IOException{
		File f = new File(fileName); 
		return f.createNewFile();
    }

    /**
     * Writes a String value to the given CSV file.
     * @param content the string being written
     * @param endLine if the content being written should end on a new line or continue on current line
     */
     public void writeToCSV(String content, boolean endLine) throws IOException {
         FileWriter file = new FileWriter(fileName, true);
         BufferedWriter writer = new BufferedWriter(file);
         if(endLine)
             writer.write(content + "\n");
         else
             writer.write(content + ",");
         writer.close();
     }

    /**
     * Writes a ArrayList of any type to the given CSV file on a single line.
     * @param content the ArrayList being written
     * @param rewrite if the content being written should overwrite the old content
     */
    public void writeToCSV(ArrayList<?> content, boolean rewrite) throws IOException {
        //Ends function if there is no content to add
        if (content.isEmpty())
            return;

        FileWriter file = new FileWriter(fileName, !rewrite);
        BufferedWriter writer = new BufferedWriter(file);
        //Loop through the content
        for (int i = 0; i < content.size(); i++) {
            //If not last index in content, then add comma after content
            if (content.size() - 1 != i)
                writer.write(content.get(i).toString() + ",");
            //Else just add content
            else
                writer.write(content.get(i).toString());
        }
        //End on new line amd close
        writer.write("\n");
        writer.close();
    }

    /**
     * Writes a HashMap<String, Boolean> to the given CSV file on a single line.
     * @param content the ArrayList being written
     * @param rewrite if the content being written should overwrite the old content
     */
    public void writeToCSV(HashMap<String, Boolean> content) throws IOException {
        //Ends function if there is no content to add
        if (content.isEmpty())
            return;

        FileWriter file = new FileWriter(fileName, true);
        BufferedWriter writer = new BufferedWriter(file);
        //Loop through the content
        int count = 0;
        for (Map.Entry<String, Boolean> item : content.entrySet()) {
            //If not last index in content, then add comma after content
            if (content.size() - 1 != count)
                writer.write(item.getValue().toString() + ",");
            //Else just add content
            else
                writer.write(item.getValue().toString());
            count++;
        }
        //End on new line
        writer.write("\n");
        writer.close();
    }


    /**
     * Reads a CSV file and returns an ArrayList of a given line.
     * @param line of the CSV file to return
     * @return an ArrayList<String> with the content on the given line
     */
    public ArrayList<String> readFromCSV(int line) throws IOException {
        FileReader file = new FileReader(fileName);
        BufferedReader reader = new BufferedReader(file);
        ArrayList<String> content = new ArrayList<String>();
        for (int i = 0; i < line; i++) {
            reader.readLine();
        }
        String item = reader.readLine();
        String currentItem = "";
        char currentChar;
        if(item == null) {
            reader.close();
            return null;
        }

        for (int i = 0; i < item.length(); i++) {
            currentChar = item.charAt(i);
            if (i == item.length() - 1) {
                currentItem += currentChar;
                content.add(currentItem);
            } else if (currentChar == ',') {
                content.add(currentItem);
                currentItem = "";
            } else {
                currentItem += currentChar;
            }
        }
        reader.close();
        return content;
    }
}
