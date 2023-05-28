package DebtDestroyer;

import java.util.ArrayList;

public class TestSnow {
    public static void main(String[] args) {
        Debt liberty = new Debt("Liberty Mutial", 15000, 25, 2.5);
        Debt American = new Debt("American Express", 10000, 100, 5);
    ArrayList<Debt> debList = new ArrayList<Debt>();
    debList.add(liberty);
    debList.add(American);
    Snowball snow = new Snowball(debList);
    snow.getOwedAmounts();


    }  
}
