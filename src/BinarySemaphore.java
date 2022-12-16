import java.util.concurrent.Semaphore;

public class BinarySemaphore extends Sem{
    private int proccesses; //takes either 0 or 1
    private QueueObj q; //queue of proccesses

    public BinarySemaphore(int n, QueueObj q) {
        proccesses = n;
        this.q = q;
    }

    public void SemWait(BinarySemaphore b, Process p) throws InterruptedException {
        if (b.proccesses == 1)
            b.proccesses = 0;
        else
            q.enqueue(p);
        p.wait();
    }

    public void SemSignal(BinarySemaphore b) {
        if (b.q.isEmpty())
            b.proccesses=1;
        /*else
            addToReadyList(b.q.dequeue());*/ //add to ready queue
    }
}
