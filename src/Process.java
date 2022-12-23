import java.io.*;
import java.util.*;
//TEST FOR GITHUB
public class Process extends Thread{
    private int processID;
    private static int programCounter;
    int processType;
    int arrvTime;
    int burstTime;
    private ProcessState processState;
    Priority priority; //having 3 Qs; (Order from the highest priority to lowest) Q1 = systemProcess, Q2 = Interactive Process (Input/Output?)
    static String var;
    static String x;

    public Process(int processID, int processType) throws IOException {
        this.processID = processID;
        programCounter++;
        this.processType = processType;
        Thread t = new Thread(this);
        t.start();
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

    enum ProcessState{
        NEW, READY, RUNNUNG, BLOCKED, FINISHED
    }

    enum Priority{
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

    public void processA() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String [] choice = input.split(" ");
        if (choice[0].equals("print"))
            OperatingSystem.print(choice, var, x);
        else if (choice[0].equals("readfile"))
            OperatingSystem.readfile(choice);
        //else System.out.println("Command not defined!");
    }

    public void processB() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String [] choice = input.split(" ");
        if (choice[0].equals("writefile"))
            OperatingSystem.writefile(choice[1],choice[2]);
        else if (choice[0].equals("assign"))
            OperatingSystem.assign(choice[1],choice[2]);
    }

    public void run(){
        if (processType == 1) {
            try {
                processA();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (processType == 2){
            try {
                processB();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws IOException {
        Process p = new Process(0,1);
        Process p1 = new Process(1,2);
        p.start();
        p1.start();
//        long startTime = System.currentTimeMillis();
//        System.out.println(startTime);
    }

}
