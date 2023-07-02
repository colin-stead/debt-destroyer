package DebtDestroyer;

import java.util.ArrayList;

public class TestAvalanch {
        static String[][] avalanchPayoffAmt;
    public static void main(String[] args) throws DebtGrowingFasterThanPaying {
        Debt Citizens = new Debt("Citizens", 20190, 200, 1.5);
        Debt liberty = new Debt("Liberty Mutial", 12580, 50, 1.2);
        Debt American = new Debt("American Express", 6600, 100, 2.5);
        ArrayList<Debt> debList = new ArrayList<Debt>();
    debList.add(liberty);
    debList.add(American);
    debList.add(Citizens);
    Avalanch ava = new Avalanch(debList,850);
    // avalanchPayoffAmt= ava.getPayoff();
    System.out.println("Lender,Year,StartAmt,YrlyAmtPaid,AmtRem");
    CalcPayoff avaPayoff = new CalcPayoff(ava.getDebtList(), ava.getAmtPay());
    ava.printMatrix(avaPayoff.getPayoffInfo(),5);
    Snowball snow = new Snowball(debList, 850);
   System.out.println("\n\nPrinting out Snowball calculations:\n\nLender,Year,StartAmt,YrlyAmtPaid,AmtRem");
   CalcPayoff snowPayoff = new CalcPayoff(snow.getDebtList(), snow.getAmtPay());
    snow.printMatrix(snowPayoff.getPayoffInfo(),5);

//ava.printMatrix(ava.getPayoff(), 5);



    }  
}
