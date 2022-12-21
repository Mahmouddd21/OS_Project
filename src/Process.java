import java.io.*;
import java.util.*;

//TEST FOR GITHUB
public class Process extends Thread {
    private int processID;
    private int programCounter;
    int arrvTime;
    int burstTime;
    private ProcessState processState;
    Priority priority; //having 3 Qs; (Order from the highest priority to lowest) Q1 = systemProcess, Q2 = Interactive Process (Input/Output?)
    static String var;
    static String x;
    static QueueObj queueOfQueues = new QueueObj(3);

    public Process(int processID, int programCounter) throws IOException {
        this.processID = processID;
        this.programCounter = programCounter;
        // processState = false;
//        //MS1
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String input = br.readLine();
//        String [] choice = input.split(" ");
//        if (choice[0].equals("print") || choice[0].equals("readfile"))
//            processA(choice);
//        else if (choice[0].equals("assign") || choice[0].equals("writefile"))
//            processB(choice[1],choice[2]);//Still incomplete NOT SURE!

        //MS2
//        System.out.println("Number of process for system queue: ");//adjust this line later
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        int numOfSP = Integer.parseInt(br.readLine());
//        systemQueue = new QueueObj(numOfSP);
//
//        System.out.println("Number of process for interactive queue: ");


    }

    public int getArrvTime() {
        return arrvTime;
    }

    public void setArrvTime(int arrvTime) {
        this.arrvTime = arrvTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    enum ProcessState {
        NEW, READY, RUNNUNG, BLOCKED, FINISHED
    }

    enum Priority {
        HIGH, MED, LOW
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

    public ProcessState isProcessState() {
        return processState;
    }

    public void setProcessState(ProcessState processState) {
        this.processState = processState;
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

}
