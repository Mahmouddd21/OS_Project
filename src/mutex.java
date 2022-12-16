public class mutex extends Sem{
    private int proccesses;
    private QueueObj q;
    private int ownerID;

    public mutex(int proccesses,QueueObj q,int ownerID){
        this.proccesses= proccesses;
        this.q=q;
        this.ownerID=ownerID;
    }
    void semWait(mutex m, Process p) throws InterruptedException {
        if (m.proccesses == 1) {
            m.ownerID = p.getProcessID();
            m.proccesses = 0;
        } else {
           q.enqueue(p);
           p.wait();
        }
    }
    void semSignal(mutex m, Process p) {
        //check if this process is the owner
        if (m.ownerID == p.getProcessID()){
            if (m.q.isEmpty())
                m.proccesses = 1;
            else
               // addToReadyList(c.q.dequeue());
                m.ownerID = p.getProcessID();
        }
    }


}
