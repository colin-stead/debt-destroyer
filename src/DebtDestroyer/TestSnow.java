package DebtDestroyer;

import java.util.ArrayList;

public class TestSnow {
    public static void main(String[] args) throws DebtGrowingFasterThanPaying {
       // Debt Citizens = new Debt("Citizens", 50000, 500, 1.5);
        Debt liberty = new Debt("Liberty Mutial", 5000, 25, .7);
        Debt American = new Debt("American Express", 10000, 100, 1.5);
        ArrayList<Debt> debList = new ArrayList<Debt>();
    debList.add(liberty);
    debList.add(American);
   // debList.add(Citizens);
    Snowball snow = new Snowball(debList,1000);
    //snow.getOwedAmounts();
    snow.printMatrix();


    }  
}
