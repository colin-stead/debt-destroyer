package DebtDestroyer;

public class DebtGrowingFasterThanPaying extends Exception {

    public DebtGrowingFasterThanPaying(String debt, double payRate) {
        System.err.println("Loan " + debt + " that you are paying off at a current rate of " + payRate
                + " is growing at a higher rate than you are paying it off\nRecomend a loan consolidation program, lower intrest rate or paying more towards your debt monthly");
    System.exit(0);
            }

    public DebtGrowingFasterThanPaying(double twentyPercentIncome, double APR) {
        System.err.println("The loan is growing faster than you can pay it off you must find a lower APR than "
        + APR + " or choose another method as the payments will exceed your 20% income: " + twentyPercentIncome);
        System.exit(0);
    }

}
