import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CSVFIleManager {
    private final String fileName;

    public CSVFIleManager(String fileName) throws IOException{
        this.fileName = fileName;
        createCSVFile();
    }

    public boolean createCSVFile() throws IOException{
		File f = new File(fileName); 
		return f.createNewFile();
    }

    // public void writeToCSV(ArrayList<String> content) throws IOException {
    //     //Ends function if there is no content to add
    //     if (content.size() == 0)
    //         return;

    //     FileWriter file = new FileWriter(fileName, true);
    //     BufferedWriter writer = new BufferedWriter(file);
    //     //Start on new line
    //     writer.write("\n");
    //     //Loop through the content
    //     for (int i = 0; i < content.size(); i++) {
    //         //If not last index in content, then add comma after content
    //         if (content.size() - 1 != i)
    //             writer.write(content.get(i) + ",");
    //         //Else just add content
    //         else
    //             writer.write(content.get(i));
    //     }
    //     writer.close();
    // }

    public void writeToCSV(String content, boolean skipLine) throws IOException {
        FileWriter file = new FileWriter(fileName, true);
        BufferedWriter writer = new BufferedWriter(file);
        writer.write(content + ",");
        //End on new line
        if(skipLine)
            writer.write("\n");
        writer.close();
    }

    public void writeToCSV(ArrayList<?> content) throws IOException {
        //Ends function if there is no content to add
        if (content.size() == 0)
            return;

        FileWriter file = new FileWriter(fileName, true);
        BufferedWriter writer = new BufferedWriter(file);
        //Loop through the content
        for (int i = 0; i < content.size(); i++) {
            //If not last index in content, then add comma after content
            if (content.size() - 1 != i)
                writer.write(content.get(i) + ",");
            //Else just add content
            else
                writer.write(content.get(i).toString());
        }
        //End on new line
        writer.write("\n");
        writer.close();
    }

    public void writeToCSV(HashMap<String, Boolean> content, boolean skipLine) throws IOException {
        //Ends function if there is no content to add
        if (content.size() == 0)
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
        if(skipLine)
            writer.write("\n");
        writer.close();
    }

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
