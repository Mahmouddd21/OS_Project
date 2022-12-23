import java.io.*;
import java.util.*;
//TEST FOR GITHUB
public class Process extends Thread{

    private int processID;
    private static int programCounter;
    int processType;
    int arrvTime;
    int burstTime;
    boolean isRR;
    private ProcessState processState;
    Priority priority;
    static String var;
    static String x;

    public Process(int processID, int processType, boolean isRR) throws IOException {
        processState = ProcessState.NEW;
        this.processID = processID;
        programCounter++;
        this.processType = processType;
        this.isRR = isRR;
        if (isRR){
        Thread t = new Thread(this);
        t.start();
        }
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

    public boolean isRR() {
        return isRR;
    }

    public void setRR(boolean RR) {
        isRR = RR;
    }

    public void processA() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String [] choice = input.split(" ");
        System.out.println("entered process A method");
        if (choice[0].equals("print")) {
            System.out.println("entered print method");
            SemPrint p =new SemPrint();
            p.SemPrintWait(this);
            OperatingSystem.print(choice, var, x);
            p.SemPrintSignal();
        }
        else if (choice[0].equals("readfile")) {
            System.out.println("entered readfile method");
            SemRead r = new SemRead();
            r.SemReadWait(this);
            OperatingSystem.readfile(choice);
            r.SemReadSignal();
        }
    }

    public void processB() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String [] choice = input.split(" ");
        System.out.println("entered process B method");

        if (choice[0].equals("writefile")) {
            System.out.println("entered writefile method");
            SemWrite w = new SemWrite();
            w.SemWriteWait(this);
            OperatingSystem.writefile(choice[1], choice[2]);
            w.SemWriteSignal();
        }
        else if (choice[0].equals("assign")){
            System.out.println("entered assign method");
            SemAssign a = new SemAssign();
            a.SemAssignWait(this);
            OperatingSystem.assign(choice[1],choice[2]);
            a.SemAssignSignal();
        }
    }

    public void run(){
        if (processType == 1) {
            try {
                processA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (processType == 2){
            try {
                processB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        Process p = new Process(0,1);
//        Process p1 = new Process(1,2);
//        p1.run();
//        p.run();
//        long startTime = System.currentTimeMillis();
//        System.out.println(startTime);
    }

}
