import java.util.ArrayList;

public class AddressTranslator {

    public static void main(String[] args) {
        ArrayList<String> pagetables = new ArrayList<String>();
        pagetables.add("0,8192");

        pagetables.add("1,0");
        pagetables.add("2,4096");
        pagetables.add("3,1024");
        pagetables.add("4,12268");
        int phyAdd = getPhysicalAddress(20, 4096, pagetables);
        System.out.println("Physical Address: " + phyAdd);
    }

    private static int getPhysicalAddress(int logical, int pSize, ArrayList<String> pagetables) {
        int phyAddr = -1;
        int pageNum = logical/pSize;
        int offset = logical%pSize;
        System.out.println("Page number: " + pageNum);
        System.out.println("Offset: " + offset);
        for (String e : pagetables){
            int pageIndex = Integer.parseInt(e.split(",")[0]);
            int startPhyAddr = Integer.parseInt(e.split(",")[1]);
            if (pageNum == pageIndex)
                phyAddr = startPhyAddr + offset;
        }
        if (phyAddr == -1)
            return 0;
        return phyAddr;
    }


}
