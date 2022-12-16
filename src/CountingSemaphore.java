public class CountingSemaphore extends Sem{
    private int count;
    private QueueObj q;

    CountingSemaphore(int count, QueueObj q){
        this.count = count;
        this.q=q;
    }
    public void SemWait(CountingSemaphore c, Process p) throws InterruptedException {
        c.count--;
        if (c.count < 0) {
            q.enqueue(p);
            p.wait();
        }
    }
    public void SemSignal(CountingSemaphore c) {
        c.count++;
        if (c.count <= 0) {
            /*else
            addToReadyList(c.q.dequeue());*/ //add to ready queue
        }
    }


}
