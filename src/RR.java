import java.util.LinkedList;
import java.util.Queue;

public class RR {

    static Queue<Integer> RRqueue = new LinkedList<>();
    static Queue<Integer> RRarrvTimeQ = new LinkedList<>();
    static Queue<Integer> RRrunTimeQ = new LinkedList<>();

    public static void roundRobin(String pid[], int num, int b[], int a[], int n) {
        int res = 0;
        int resc = 0;

        int qt = 2;//Quantum time = 2
        int numOfP = RRqueue.size();//Number of processes

        Queue<Integer> arrvTMP = new LinkedList<>(RRarrvTimeQ);//a copy of the arrvTime queue
        Queue<Integer>runTMP = new LinkedList<>(RRrunTimeQ);// a copy of runTime queue

        int res_x[] = new int[num];
        int res_y[] = new int[num];

        for (int i = 0; i < num; i++) {
            res_y[i] = b[i];
            res_x[i] = a[i];
        }

        int t = 0;//time ??? forgot what it was, need to debug
        int w[] = new int[num];//Waiting time
        int ttime[] = new int[num];//turn around time

        while (true) {
            boolean flag = true;// a flag to know whether th scheduling is done
            //need to convert the for loops int a while loop to figure out how to deal with the queues
            for (int i = 0; i < num; i++) {
                if (res_x[i] <= t) {
                    if (res_x[i] <= n && res_y[i] > 0) {
                        flag = false;
                        if (res_y[i] > n) {

                            t = t + n;
                            res_y[i] = res_y[i] - n;
                            res_x[i] = res_x[i] + n;
                        }

                        else {
                            t = t + res_y[i];
                            ttime[i] = t - a[i];

                            w[i] = t - b[i] - a[i];
                            res_y[i] = 0;
                        }

                    }

                    else if (res_x[i] > n) {
                        for (int j = 0; j < num; j++) {
                            if (res_x[j] < res_x[i] && res_y[j] > 0) {
                                flag = false;
                                if (res_y[j] > n) {
                                    t = t + n;
                                    res_y[j] = res_y[j] - n;
                                    res_x[j] = res_x[j] + n;
                                } else {
                                    t = t + res_y[j];
                                    ttime[j] = t - a[j];
                                    w[j] = t - b[j] - a[j];
                                    res_y[j] = 0;
                                }

                            }
                        }

                        if (res_y[i] > 0) {
                            flag = false;

                            if (res_y[i] > n) {
                                t = t + n;
                                res_y[i] = res_y[i] - n;
                                res_x[i] = res_x[i] + n;
                            }

                            else {
                                t = t + res_y[i];
                                ttime[i] = t - a[i];
                                w[i] = t - b[i] - a[i];
                                res_y[i] = 0;
                            }
                        }
                    }
                } else if (res_x[i] > t) {
                    t++;
                    i--;
                }
            }

            if (flag)
                break;
        }

        System.out.println("Processes " + " Arrival Time " + " Burst Time " + " Waiting Time " + " Turn Around Time");
        for (int i = 0; i < num; i++) {
            System.out.println(pid[i] + "          " + a[i] + "             " + b[i] + "           " + w[i] + "             " + ttime[i]);

            res = res + w[i];
            resc = resc + ttime[i];
        }

        System.out.println("Average waiting time = " + (float) res / numOfP);
        System.out.println("Average turn around time = " + (float) resc / numOfP);
    }

    public static void main(String[] args) {
        String pid [] = {"0","1","2","3"};
        int numOfProcesses = 4;
        int burstTime [] ={2,4,6,3};
        int arrvTime [] = {0,2,3,5};
        int qt = 2;
        roundRobin(pid,numOfProcesses,burstTime,arrvTime,qt);
    }
}