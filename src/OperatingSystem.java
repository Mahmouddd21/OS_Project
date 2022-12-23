import javax.management.QueryEval;
import java.io.*;
import java.util.*;

enum EventsOS {
    USER_INPUT,
    DISK_CONTROLLER,
    REQ_MORE_HEAP,
    DIVISON_BY_ZERO,
    ACCESS_PRIVILIGED_MEMORY
}

public class OperatingSystem {

    static Queue<Integer> Q1 = new LinkedList<>();
    static Queue<Integer> Q2 = new LinkedList<>();
    static Queue<Integer> Q3 = new LinkedList<>();
    static Queue<Integer> Q1arrvTime = new LinkedList<>();
    static Queue<Integer> Q2arrvTime = new LinkedList<>();
    static Queue<Integer> Q3arrvTime = new LinkedList<>();
    static Queue<Integer> Q1runTime = new LinkedList<>();
    static Queue<Integer> Q2runTime = new LinkedList<>();
    static Queue<Integer> Q3runTime = new LinkedList<>();
    static Queue<Integer> RRqueue = new LinkedList<>();
    static Queue<Integer> RRarrvTimeQ = new LinkedList<>();
    static Queue<Integer> RRrunTimeQ = new LinkedList<>();
    static ArrayList<Process> processes = new ArrayList<>();
    public static String memory[] = new String[6];
    static String var;
    static String x;
    public static int heapIndex = 0;
    public static int heapEnd = 2;
    public static String privateMemory[] = new String[4];
    public static int privateMemoryIndex = 0;
    private static int processProgramCounter = 0;

    public static void genrateRandomEvent() throws Exception {
        int random = (int) (Math.random() * 5);
        EventsOS[] events = new EventsOS[]{EventsOS.USER_INPUT, EventsOS.DISK_CONTROLLER, EventsOS.REQ_MORE_HEAP,
                EventsOS.DIVISON_BY_ZERO, EventsOS.ACCESS_PRIVILIGED_MEMORY};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        switch (events[random]) {
            case USER_INPUT:
                //keyboard user input
                System.out.println("Enter value to store in memory");
                input = br.readLine();
                try {
                    //action
                    if (heapIndex != heapEnd) {
                        memory[heapIndex++] = input;
                        System.out.println(Arrays.toString(memory));
                    } else {
                        System.out.println("Heap is full... Expanding Heap");
                        heapEnd++;
                    }
                } catch (Exception e) {
                    System.out.println("Memory is full... Cannot Expand Heap!");
                    System.out.println("Storing in Private Memory...");
                    privateMemory[privateMemoryIndex++] = input;
                    System.out.println(Arrays.toString(memory));

                }
                break;
            case DISK_CONTROLLER:
                //Disk controller
                int busyOrIdle = (int) (Math.random() * 2);
                String data = Integer.toBinaryString((int) (Math.random() * 10000));
                if (busyOrIdle == 1) {
                    System.out.println("Disk Busy Reading Data...");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    System.out.println("Disk Reading Data...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                //event
                System.out.println(Arrays.toString(memory));
                //action
                System.out.println(data);
                System.out.println("Data Read Successfully!\n" + "Length of Data is: " + data.length() + " Bits");
                break;
            case REQ_MORE_HEAP:
                System.out.println("Checking if memory is free...");
                if (heapEnd < heapIndex)
                    System.out.println("Free memory is available");
                else {
                    System.out.println(Arrays.toString(memory));
                    System.out.println("!Error!\n!Memory is Full! Expanding Heap");
                    heapEnd++;
                }
                break;
            case DIVISON_BY_ZERO:
                //Division by Zero
                try {
                    //event
                    System.out.println("Running\n");
                    int tmp = 2 / 0;
                } catch (Exception e) {
                    //action
                    System.out.println("!Error!\n!Attempting to Divide by zero!");
                    return;
                }
                break;
            case ACCESS_PRIVILIGED_MEMORY:
                //Accessing privileged memory
                try {
                    //event
                    System.out.println("Trying to Access privileged memory");
                    System.out.println("Access Heap instead");
                    input = br.readLine();
                    if (heapIndex != heapEnd) {
                        memory[heapIndex++] = input;
                        System.out.println(Arrays.toString(memory));
                    } else {
                        System.out.println("Heap is full... Expanding Heap");
                        heapEnd++;
                    }
                } catch (Exception e) {
                    //action
                    throw new Exception("!Error!\n!Attempting to access privileged memory!");
                }
            default:
                break;
        }
    }
//    public static void chooseProcess() throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String input = br.readLine();
//        String s[] = input.split(" ");
//        switch (s[0].toLowerCase()) {
//            case "print":
//                PrintProcess print = new PrintProcess(1, 4, processProgramCounter++,s, var, x);
//                print.setProcessState(true);
//                break;
//            case "assign":
//                AssignProcess assign = new AssignProcess(2, 3, processProgramCounter++);
//                var = s[1];
//                x = s[2];
//                System.out.println(x + " Has been Assigned to " + var);
//                assign.setProcessState(true);
//                break;
//            case "writefile":
//                String content = getContentHelper(s);
//                WriteFileProcess writefile = new WriteFileProcess(3, 1, processProgramCounter++,s[1],content);
//                writefile.setProcessState(true);
//                break;
//            case "readfile":
//                ReadFileProcess readfile = new ReadFileProcess(4, 2, processProgramCounter++,s[1]);
//                readfile.setProcessState(true);
//                break;
//            default:
//                System.out.println("Invalid Command");
//                break;
//        }
//    }


    public static String getContentHelper(String[] s) {
        String res = "";
        for (int i = 2; i < s.length; i++)
            res += s[i] + " ";
        return res;
    }

    //DO NOT DELETE THIS IS TO BE USED LATER
    //MAYBE
//    static void findWaitingTime(String proc[], int n, int bt[], int wt[], int at[]) {
//        int service_time[] = new int[n];
//        service_time[0] = 0;
//        wt[0] = 0;
//
//        for (int i = 1; i < n; i++) {
//            service_time[i] = service_time[i - 1] + bt[i - 1];
//
//            wt[i] = service_time[i] - at[i];
//            if (wt[i] < 0)
//                wt[i] = 0;
//        }
//    }
//
//    static void findTurnAroundTime(String proc[], int n, int bt[], int wt[], int tat[]) {
//        for (int i = 0; i < n; i++)
//            tat[i] = bt[i] + wt[i];
//    }
//
//    float findavgTime(String proc[], int n, int bt[], int at[]) {
//        int wt[] = new int[n], tat[] = new int[n];
//
//        findWaitingTime(proc, n, bt, wt, at);
//        findTurnAroundTime(proc, n, bt, wt, tat);
//
//        System.out.print("Processes " + " Arrival Time " + " Burst Time " + " Waiting Time " + " Turn Around Time \n");
//        int total_wt = 0, total_tat = 0;
//        for (int i = 0; i < n; i++) {
//            total_wt = total_wt + wt[i];
//            total_tat = total_tat + tat[i];
//            System.out.println(proc[i] + "\t\t" + at[i] + "\t\t" + bt[i] + "\t\t" + wt[i] + "\t\t " + tat[i]);
//        }
//
//        System.out.println("Average waiting time = " + (float) total_wt / n);
//        System.out.println("Average turn around time = " + (float) total_tat / n);
//        return (float) total_wt / n;
//    }


    public static void Scheduler_FCFS(Queue<Integer> proc) {
        while (!proc.isEmpty()) {
            for (int i = 0; i < processes.size(); i++) {
                if (proc.peek() == processes.get(i).getProcessID()) {
//                    processes.get(i).run();
                    Thread t = new Thread(processes.get(i));
                    t.run();
                    processes.get(i).setProcessState(Process.ProcessState.RUNNUNG);
                    System.out.println("Process ID: " + processes.get(i).getProcessID() + " is running");
                }
            }
            System.out.println(proc.toArray().toString());
            proc.remove();
        }
    }

    public static void Scheduler_RR() throws InterruptedException {
        if (RRqueue.isEmpty()) {
            System.out.println("Queue is empty");
            return;
        } else {
            while (!RRqueue.isEmpty()) {
                long start = System.currentTimeMillis();
                long end = start + 2;
                for (int i = 0; i < processes.size(); i++) {
                    if (RRqueue.peek() == processes.get(i).getProcessID()) {
                        if (!processes.get(i).isAlive()) {
                            processes.get(i).setProcessState(Process.ProcessState.FINISHED);
                            System.out.println("Process ID: " + processes.get(i).getProcessID() + " is finished");
                            RRqueue.remove();
                        }
                    } else {
                        while (System.currentTimeMillis() < end) {
                            processes.get(i).run();
//                            Thread t = new Thread(processes.get(i));
//                            t.run();
                            processes.get(i).setProcessState(Process.ProcessState.RUNNUNG);
                            System.out.println("Process ID: " + processes.get(i).getProcessID() + " is running");
                        }
                        processes.get(i).wait((RRqueue.size() * 2) - 2);
                        processes.get(i).setProcessState(Process.ProcessState.BLOCKED);
                        RRqueue.offer(RRqueue.remove());
                    }
                }
            }
        }
    }

    public static void Scheduler_MLQS() {

        while (!Q1.isEmpty()){
            Scheduler_FCFS(Q1);
            System.out.println("Queue 1 is finished");
        }

        //        while (!Q1.isEmpty()) {
//            Scheduler_FCFS(Q1);
//            System.out.println("Queue 1 is finished");
//            if (Q1.isEmpty()) flagQ1 = true;
//            if (flagQ1){
//                while (!Q2.isEmpty()) {
//                    Scheduler_FCFS(Q2);
//                    System.out.println("Queue 2 is finished");
//                    if (Q2.isEmpty()) flagQ2 = true;
//                    if (flagQ2){
//                        while (!Q3.isEmpty()) {
//                            Scheduler_FCFS(Q3);
//                            System.out.println("Queue 3 is finished");
//                            if (Q3.isEmpty()) flagQ3 = true;
//                            if (flagQ3) return;
//                        }
//                    }
//                }
//            }
//        }
        while (!Q2.isEmpty()) {
            Scheduler_FCFS(Q2);
            System.out.println("Queue 2 is finished");
        }
        while (!Q3.isEmpty()) {
            Scheduler_FCFS(Q3);
            System.out.println("Queue 3 is finished");
        }
    }

    public static void createProcess(Process p, Process.Priority priority) {
        processes.add(p);

        if (!p.isRR) {
            switch (priority) {
                case HIGH:
                    Q1.offer(p.getProcessID());
                    System.out.println(p.getProcessID() + " has been added to the HIGH prio queue");
                    p.setProcessState(Process.ProcessState.READY);
                    break;
                case MED:
                    Q2.offer(p.getProcessID());
                    System.out.println(p.getProcessID() + " has been added to the MED prio queue");
                    p.setProcessState(Process.ProcessState.READY);
                    break;
                case LOW:
                    Q3.offer(p.getProcessID());
                    System.out.println(p.getProcessID() + " has been added to the LOW prio queue");
                    p.setProcessState(Process.ProcessState.READY);
                    break;
                default:
                    System.out.println("specify the priority for the process");
            }
        } else {
            RRqueue.offer(p.getProcessID());
            p.setProcessState(Process.ProcessState.READY);
        }
    }


    public static void assign(String s1, String s2) {
        var = s1;
        x = s2;
        System.out.println(x + " Has been Assigned to " + var);
    }


    public static void writefile(String s, String s1) {
        try {
            File myObj = new File(s + ".txt");
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
            FileWriter myWriter = new FileWriter(s + ".txt");
            myWriter.write(s1);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void print(String s[], String var, String x) {
        if (s[1].equals(var)) {
            System.out.println(x);
        } else {
            System.out.println();
            for (int i = 1; i < s.length; i++)
                System.out.print(s[i] + " ");
            System.out.println();
        }
    }

    public static void readfile(String s[]) {
        try {
            File myObj = new File(s[1] + ".txt");
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

    public static void main(String[] args) throws IOException, InterruptedException {


        //for MLQS
        Process p1 = new Process(0,2,false);
        Process tmp = new Process(10,2,false);
        Process p2 = new Process(1,2,false);
//        Process p3 = new Process(2,1);
//        Process p4 = new Process(3, 2);
////
        createProcess(tmp, Process.Priority.HIGH);
        createProcess(p2, Process.Priority.MED);
        createProcess(p1, Process.Priority.LOW);
//        createProcess(p4, Process.Priority.HIGH,true);
//        createProcess(p3, Process.Priority.HIGH,true);
//
       Scheduler_MLQS();

        //for RR
//        Process p5 = new Process(4,1, true);
//        Process p6 = new Process(5,2, true);
////        Process p7 = new Process(6,1);
////        Process p8 = new Process(7,2);
////
//        createProcess(p5,null);
//        createProcess(p6,null);
//////        createProcess(p7,null,false);
//////        createProcess(p8,null,false);
////
//        Scheduler_RR();
    }
}