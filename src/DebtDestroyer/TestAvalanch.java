package DebtDestroyer;

import java.util.ArrayList;

public class TestAvalanch {
        static String[][] avalanchPayoffAmt;
    public static void main(String[] args) throws DebtGrowingFasterThanPaying {
        Debt Citizens = new Debt("Citizens", 7000, 100, 1.5);
        Debt liberty = new Debt("Liberty Mutial", 11000, 25, .7);
        Debt American = new Debt("American Express", 10000, 100, 2.5);
        ArrayList<Debt> debList = new ArrayList<Debt>();
    debList.add(liberty);
    debList.add(American);
    debList.add(Citizens);
    Avalanch ava = new Avalanch(debList,400);
    avalanchPayoffAmt= ava.getPayoff();
    ava.printMatrix(avalanchPayoffAmt,5);


    }  
}
