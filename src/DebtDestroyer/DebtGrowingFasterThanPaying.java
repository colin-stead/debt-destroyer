package DebtDestroyer;

public class DebtGrowingFasterThanPaying extends Exception {

    public DebtGrowingFasterThanPaying(String debt, double payRate) {
        System.err.println("Loan " + debt + " that you are paying off at a current rate of " + payRate
                + " is growing at a higher rate that you are paying it off\nRecomend a loan consolidation program, lower intrest rate or paying more towards your debt monthly");
    System.exit(0);
            }

}
