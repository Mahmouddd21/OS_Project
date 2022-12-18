import java.io.*;
import java.util.*;

//TEST FOR GITHUB
public class Process extends Thread{
    private int processID;
    private int programCounter;
    private ProcessState processState;
    private int priority; //having 3 Qs; (Order from the highest priority to lowest) Q1 = systemProcess, Q2 = Interactive Process (Input/Output?)
    static String var;
    static String x;
    static QueueObj batchQueue; //priority lowest
    static QueueObj queueOfQueues = new QueueObj(3);


    public Process(int processID, int priority, int programCounter) throws IOException {
        this.processID = processID;
        this.programCounter = programCounter;
        this.processState = ProcessState.NEW;
        //MS1
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        //MS2
        System.out.println("Number of process for system queue: ");//adjust this line later
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numOfSP = Integer.parseInt(br.readLine());


        System.out.println("Number of process for interactive queue: ");

    }

    public void processA(String[] s) {
        if (s[0].equals("print"))
            OperatingSystem.print(s, var, x);
        else if (s[0].equals("readfile"))
            OperatingSystem.readfile(s);
        else System.out.println("Command not defined!");
    }

    public void processB(String s1, String s2) {
        if (s1.equals("writefile"))
            OperatingSystem.writefile(s1, s2);
        else if (s2.equals("assign"))
            OperatingSystem.assign(s1, s2);
    }

    public static void main(String[] args) throws IOException {
        Process p = new Process(1, 3, 4);

    }

    enum TTY {
        //Terminal or TTY: terminal to which the process is connected. han-save feeh which scheduling type we are calling
        ROUND_ROBIN, //uses Q1 and Q2
        MLQS,
        FCFS



        /*

        while(input != "execute")
            we will enqueue all the processes into the queues corresponding to their type
            and then once we get execute as an input we will implement the queueing workflow above.

         */

    }

    enum ProcessState {
        NEW, READY, RUNNING, BLOCKED, FINISHED //this will also be used on the queues
        //for example
    }

    enum Priority{
        HIGH, MED, LOW
    }

    public ProcessState getProcessState() {
        return processState;
    }

    public void setProcessState(ProcessState processState) {
        this.processState = processState;
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

//    public int getPriority() {
//        return priority;
//    }
//
//    public void setPriority(int priority) {
//        this.priority = priority;
//    }




}
