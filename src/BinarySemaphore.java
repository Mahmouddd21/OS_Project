import java.util.concurrent.Semaphore;

public class BinarySemaphore {
    private int proccesses; //takes either 0 or 1
    private QueueObj q; //queue of proccesses

    public BinarySemaphore(int n, QueueObj q) {
        proccesses = n;
        this.q = q;
    }

    public void SemPrintWait(BinarySemaphore b, Process p) throws InterruptedException {
        if (b.proccesses == 1)
            b.proccesses = 0;
        else {
            while (b.proccesses == 0) {
                System.out.println(p.getProcessID() + " is on wait ");
            }
        }
        b.q.enqueue(p);
    }

    public void SemPrintSignal(BinarySemaphore b) {
        if (b.q.isEmpty())
            b.proccesses = 1;
        //else
        //    print(b.q.dequeue); //add to ready queue
    }

    public void SemAssignWait(BinarySemaphore b, Process p) throws InterruptedException {
        if (b.proccesses == 1)
            b.proccesses = 0;
        else {
            while (b.proccesses == 0) {
                System.out.println(p.getProcessID() + " is on wait ");
            }
        }
        b.q.enqueue(p);
    }

    public void SemAssignSignal(BinarySemaphore b) {
        if (b.q.isEmpty())
            b.proccesses = 1;
        /*else
            Assign((b.q.dequeue);)*/ //add to ready queue
    }

    public void SemWriteWait(BinarySemaphore b, Process p) throws InterruptedException {
        if (b.proccesses == 1)
            b.proccesses = 0;
        else {
            while (b.proccesses == 0) {
                System.out.println(p.getProcessID() + " is on wait ");
            }
        }
        b.q.enqueue(p);
    }

    public void SemWriteSignal(BinarySemaphore b) {
        if (b.q.isEmpty())
            b.proccesses = 1;
        /*else
            Write(b.q.dequeue);*/ //add to ready queue
    }

    public void SemReadWait(BinarySemaphore b, Process p) throws InterruptedException {
        if (b.proccesses == 1)
            b.proccesses = 0;
        else {
            while (b.proccesses == 0) {
                System.out.println(p.getProcessID() + " is on wait ");
            }
        }
        b.q.enqueue(p);
    }

    public void SemReadSignal(BinarySemaphore b) {
        if (b.q.isEmpty())
            b.proccesses = 1;
        /*else
            Read(b.q.dequeue);*/ //add to ready queue
    }

}
