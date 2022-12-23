public class SemAssign extends Sem {
    public static int s;

    public SemAssign() {
        super();
    }

    public void SemAssignWait(Process p) throws Exception {
        super.SemWait(p);

    }

    public void SemAssignSignal() {
        super.SemSignal();
        System.out.println("Assign resource is now Free");
    }
}
