public class Process {
    private int processID;
    private int programCounter;
    private boolean processState;
    private int priority;

    public boolean isProcessState() {
        return processState;
    }

    public void setProcessState(boolean processState) {
        this.processState = processState;
    }

    public Process(int processID, int priority, int programCounter){
        this.processID = processID;
        this.priority = priority;
        this.programCounter = programCounter;
        processState = false;
    }
}
