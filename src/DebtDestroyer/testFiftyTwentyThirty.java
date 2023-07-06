package DebtDestroyer;

import java.util.ArrayList;

public class testFiftyTwentyThirty {
    public static void main(String[] args) throws DebtGrowingFasterThanPaying {
        Debt USAA = new Debt("USAA", 000.00, 500, 1.5);
        Debt NFCU = new Debt("NFCU", 5000.00, 25, .7);
        Debt WellsFargo = new Debt("Wells Fargo", 9000.00, 100, 1.5);
        ArrayList<Debt> debtList = new ArrayList<Debt>();
        debtList.add(USAA);
        debtList.add(NFCU);
        debtList.add(WellsFargo);
        System.out.println("test 1");
        FiftyThirtyTwenty ftt = new FiftyThirtyTwenty(debtList, 30000.00, .1);
        String[][] payoffDetails = ftt.getPayoff();
        ftt.printMatrix(payoffDetails);
        System.out.println("test 2");
        Debt Regions = new Debt("Regions", 2000.00, 100, 1.5);
        debtList.add(Regions);
        ftt = new FiftyThirtyTwenty(debtList, 40000, .2);
        payoffDetails = ftt.getPayoff();
        ftt.printMatrix(payoffDetails);


    }
}


