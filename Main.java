import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        // Student x = new Student(35, "John", "Doe");
        // ArrayList<String> a = new ArrayList<String>();
        // a.add("1234");
        // a.add("Jane");
        // a.add("Campbell");
        // a.add("3.4");
        // writeToCSV("Students.csv", a);
        // a = readFromCSV("Students.csv", 0);
        // for (String item : a) {
        //     System.out.println(item);
        // }
        int test = 240000;
        System.out.println(String.format("%09d", test));
    }

    public static void writeToCSV(String fileName, ArrayList<String> content) throws IOException {
        if (content.size() == 0)
            return;

        FileWriter file = new FileWriter(fileName, true);
        BufferedWriter writer = new BufferedWriter(file);
        writer.write("\n");
        for (int i = 0; i < content.size(); i++) {
            if (content.size() - 1 != i)
                writer.write(content.get(i) + ",");
            else
                writer.write(content.get(i));
        }
        writer.close();
    }

    public static ArrayList<String> readFromCSV(String fileName, int line) throws IOException {
        FileReader file = new FileReader(fileName);
        BufferedReader reader = new BufferedReader(file);
        ArrayList<String> content = new ArrayList<String>();
        for (int i = 0; i <= line; i++) {
            reader.readLine();
        }
        String item = reader.readLine();
        String currentItem = "";
        char currentChar;
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