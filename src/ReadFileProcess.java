import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFileProcess  extends Process{
    public ReadFileProcess(int processID, int priority, int programCounter) {
        super(processID, priority, programCounter);
    }
    public ReadFileProcess(int processID, int priority, int programCounter, String s) {
        super(processID, priority, programCounter);
        readfile(s);
    }
    public static void readfile(String s) {
        try {
            File myObj = new File(s+".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
