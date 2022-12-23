public class Sem {
    public static int s;

    public Sem() {
        s = 1;
    }

    public void SemWait(Process p) throws Exception {
        while (s == 0) {
            p.setProcessState(Process.ProcessState.BLOCKED);
            p.sleep(0, 1);
        }
        if (s == 1) {
            s = 0;
            System.out.println("Process ID: (" + p.getId() + ") is ready");
            p.setProcessState(Process.ProcessState.READY);
        }

    }

    public void SemSignal() {
        s = 1;
    }
}

