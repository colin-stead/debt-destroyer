package DebtDestroyer;

import java.util.ArrayList;

public class TestSnow {
    public static void main(String[] args) {
        Debt liberty = new Debt("Liberty Mutial", 15000, 25, 2.5);
        Debt American = new Debt("American Express", 10000, 100, 5);
        Debt Citizens = new Debt("Citizens", 50000, 500, 1.5);
        ArrayList<Debt> debList = new ArrayList<Debt>();
    debList.add(liberty);
    debList.add(American);
    debList.add(Citizens);
    Snowball snow = new Snowball(debList);
    //snow.getOwedAmounts();
    snow.printMatrix();


    }  
}
