package DebtDestroyer;

import java.util.ArrayList;

public class TestSnow {
    static String[][] SnowballPayoffAmt;
    public static void main(String[] args) throws DebtGrowingFasterThanPaying {
       Debt Citizens = new Debt("Citizens", 20190, 200, 1.2);
        Debt liberty = new Debt("Liberty Mutial", 12580, 50, 3.9);
        ArrayList<Debt> debList = new ArrayList<Debt>();
    debList.add(liberty);
    debList.add(Citizens);
    Snowball snow = new Snowball(debList,850);
    SnowballPayoffAmt= snow.getPayoff();
    snow.printMatrix(SnowballPayoffAmt,5);


    }  
}
