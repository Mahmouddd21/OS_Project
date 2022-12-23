public class SemWrite extends Sem {
    public static int s;

    public SemWrite(){
        super();
    }
    public void SemWriteWait(Process p) throws Exception{
        super.SemWait(p);

    }
    public void SemWriteSignal(){
        super.SemSignal();
        System.out.println("Write resource is now Free");
    }
}
