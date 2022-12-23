public class SemRead extends Sem {
    public static int s;

    public SemRead() {
        super();
    }

    public void SemReadWait(Process p) throws Exception {
        super.SemWait(p);

    }

    public void SemReadSignal() {
        super.SemSignal();
        System.out.println("Read resource is now Free");
    }
}
