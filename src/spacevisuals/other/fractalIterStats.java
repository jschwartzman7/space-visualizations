package spacevisuals.other;
import spacevisuals.fractalanimations.*;
import spacevisuals.spaces.*;
import java.io.File;  // Import the File class
import java.io.FileWriter;  // Import the FileWriter class
import java.io.IOException;

public class fractalIterStats {

    public static void writeToNewFile(String fileName, String text){
        try {
            new File("/Users/jonahschwartzman/Downloads/computerScience/Personal/githubProfile/space-visualizations/"+fileName+".txt");
            FileWriter myWriter = new FileWriter(fileName+".txt");
            myWriter.write(text);
            myWriter.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void writeToExistingFile(String fileName, String text){
        try {
            FileWriter myWriter = new FileWriter(fileName+".txt", true);
            myWriter.write(text);
            myWriter.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        writeToExistingFile("sssfs", "1, 2, 3, 4, 3, 2, 3, 4, 5, 4, 33");
    }
    
}
