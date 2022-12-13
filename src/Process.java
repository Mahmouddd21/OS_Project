import java.io.*;
import java.util.*;
//TEST FOR GITHUB
public class Process {
    private int processID;
    private int programCounter;
    private boolean processState; //CHANGE INTO ENUM {READY, NEW, FINISHED, BLOCKED}
    private int priority; //having 3 Qs seems inefficient fa better to have one PQ
    static String var;
    static String x;
    enum TTY{
        //Terminal or TTY: terminal to which the process is connected. han-save feeh which schedulaing type we are calling
        ROUND_ROBIN, MLQS, FCFS
    }

    public int getProcessID() {
        return processID;
    }

    public void setProcessID(int processID) {
        this.processID = processID;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void  Scheduler_FCFS(){

    }

    public void Scheduler_RR(){

    }

    public void Scheduler_MLQS(){

    }

    public Process(int processID, int priority, int programCounter) throws IOException {
        this.processID = processID;
        this.priority = priority;
        this.programCounter = programCounter;
       // processState = false;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String [] choice = input.split(" ");
        if (choice[0].equals("print") || choice[0].equals("readfile"))
            processA(choice);
        else if (choice[0].equals("assign") || choice[0].equals("writefile"))
            processB(choice[1],choice[2]);//Still incomplete NOT SURE!
    }

    public boolean isProcessState() {
        return processState;
    }

    public void setProcessState(boolean processState) {
        this.processState = processState;
    }

    public void processA(String[] s){
        if (s[0].equals("print"))
            print(s, var, x);
        else if (s[0].equals("readfile"))
            readfile(s);
        else System.out.println("Command not defined!");
    }

    public void processB(String s1, String s2){
        if (s1.equals("writefile"))
            writefile(s1,s2);
        else if (s2.equals("assign"))
            assign(s1,s2);
    }

    public static void assign(String s1, String s2){
        var = s1;
        x = s2;
        // we need to check the data type w assign it as ordered
        System.out.println(x + " Has been Assigned to " + var);
    }

    public static void writefile(String s, String s1) {
        try {
            File myObj = new File(s+".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter(s+".txt");
            myWriter.write(s1);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void print(String s[], String var, String x){
        if (s[1].equals(var)){
            System.out.println(x);
        }else{
            System.out.println();
            for(int i = 1; i < s.length; i++)
                System.out.print(s[i] + " ");
            System.out.println();
        }
    }

    public void CreateProcess(){
        //we will hava a process queue w it will show the current process w order which process will
        //go first (the ordering will be executed depending on type of scheduling algorithm
        //NOTE: SEARCH HOW TO MAKE A PROCESS INTO A NEW PROCESS

    }

    public static void readfile(String s[]) {
        try {
            File myObj = new File(s[1]+".txt");
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
