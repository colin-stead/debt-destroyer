package DebtDestroyer;

import java.util.ArrayList;

public class TestAvalanch {
        static String[][] avalanchPayoffAmt;
    public static void main(String[] args) throws DebtGrowingFasterThanPaying {
        Debt Citizens = new Debt("Citizens", 27590, 200, 1.2);
        Debt liberty = new Debt("Liberty Mutial", 3580, 50, 3.9);
 //       Debt American = new Debt("American Express", 10000, 100, 2.5);
        ArrayList<Debt> debList = new ArrayList<Debt>();
    debList.add(liberty);
 //   debList.add(American);
    debList.add(Citizens);
    Avalanch ava = new Avalanch(debList,800);
    avalanchPayoffAmt= ava.getPayoff();
    System.out.println("Lender,Year,StartAmt,YrlyAmtPaid,AmtRem");
    ava.printMatrix(avalanchPayoffAmt,5);


    }  
}
