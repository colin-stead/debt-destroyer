package DebtDestroyer;

import java.util.ArrayList;

public class testFiftyTwentyThirty {
    public static void main(String[] args) throws DebtGrowingFasterThanPaying {
        Debt Citizens = new Debt("USAA", 3000.00, 500, 1.5);
        Debt liberty = new Debt("NFCU", 5000.00, 25, .7);
        Debt American = new Debt("Wells Fargo", 9000.00, 100, 1.5);
        ArrayList<Debt> debList = new ArrayList<Debt>();
        debList.add(liberty);
        debList.add(American);
        debList.add(Citizens);
        FiftyThirtyTwenty ftt = new FiftyThirtyTwenty(debList, 3000, .1);
        ftt.updateAPR(.2);


    }
}


