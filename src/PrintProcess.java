public class PrintProcess extends Process{
    public PrintProcess(int processID, int priority, int programCounter, String s[], String var, String x) {
        super(processID, priority, programCounter);
        print(s, var, x);
    }
    public static void print(String s[], String var, String x){
        if (s[1].equals(var)){
            System.out.println(x);
        }else{
            System.out.println();
            for(int i = 1; i < s.length; i++)
                System.out.print(s[i] + " ");
            System.out.println();
        }
    }
}
