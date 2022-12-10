import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFileProcess  extends Process{
    public WriteFileProcess(int processID, int priority, int programCounter, String s1, String s2) {
        super(processID, priority, programCounter);
        writefile(s1, s2);
    }

    public static void writefile(String s, String s1) {
        try {
            File myObj = new File(s+".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter(s+".txt");
            myWriter.write(s1);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
