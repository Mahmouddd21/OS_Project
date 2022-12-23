public class SemPrint extends Sem{
    public static int s;

    public SemPrint(){
        super();
    }
    public void SemPrintWait(Process p) throws Exception{
        super.SemWait(p);

    }
    public void SemPrintSignal(){
        super.SemSignal();
        System.out.println("Print resource is now Free");
    }
    }
