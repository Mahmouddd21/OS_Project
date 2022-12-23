import java.io.*;
import java.util.*;

//TEST FOR GITHUB
public class Process extends Thread {
    private SemRead r = new SemRead();
    private SemAssign a = new SemAssign();
    private SemPrint p = new SemPrint();
    private SemWrite w = new SemWrite();
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

    public Process(int processID, int processType) throws IOException {
        processState = ProcessState.NEW;
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

    public boolean isRR() {
        return isRR;
    }

    public void setRR(boolean RR) {
        isRR = RR;
    }

    public void processA() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] choice = input.split(" ");
        if (choice[0].equals("print")) {
            p.SemPrintWait(this);
            OperatingSystem.print(choice, var, x);
            p.SemPrintSignal();
        } else if (choice[0].equals("readfile")) {
            r.SemReadWait(this);
            OperatingSystem.readfile(choice);
            r.SemReadSignal();
        }
    }

    public void processB() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] choice = input.split(" ");
        if (choice[0].equals("writefile")) {
            w.SemWriteWait(this);
            OperatingSystem.writefile(choice[1], choice[2]);
            w.SemWriteSignal();
        } else if (choice[0].equals("assign")) {
            a.SemAssignWait(this);
            OperatingSystem.assign(choice[1], choice[2]);
            a.SemAssignSignal();
        }
    }

    public void run() {
        if (processType == 1) {
            try {
                processA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (processType == 2) {
            try {
                processB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
