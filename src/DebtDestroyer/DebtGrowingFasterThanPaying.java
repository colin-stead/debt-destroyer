package DebtDestroyer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DebtGrowingFasterThanPaying extends Exception {
    JFrame frame;

    public DebtGrowingFasterThanPaying(String debt, double payRate) {
        JOptionPane.showMessageDialog(frame, "Loan " + debt + " that you are paying off at a current rate of " + payRate
                + " per month is growing at a higher rate than you are paying it off\nRecomend a loan consolidation program, lower intrest rate or paying more towards your debt monthly","Debt Cannot be Paid off Error", JOptionPane.ERROR_MESSAGE);
        // System.err.println("Loan " + debt + " that you are paying off at a current rate of " + payRate
        //         + " is growing at a higher rate than you are paying it off\nRecomend a loan consolidation program, lower intrest rate or paying more towards your debt monthly");
        //System.exit(0);

            }

    public DebtGrowingFasterThanPaying(double twentyPercentIncome, double APR) {
        JOptionPane.showMessageDialog(frame, "The loan is growing faster than you can pay it off you must find a lower APR than "
        + APR + " or choose another method as the payments will exceed your 20% income: " + twentyPercentIncome,"Debt Cannot be Paid off Error", JOptionPane.ERROR_MESSAGE);
        // System.err.println("The loan is growing faster than you can pay it off you must find a lower APR than "
        // + APR + " or choose another method as the payments will exceed your 20% income: " + twentyPercentIncome);
        System.exit(0);
    }

    public String[][] debtGrowingError(String[][] debtCopy,String debt, double payRate){
        String[][] naString = new String[debtCopy.length][5];
        for(int i=0; i<debtCopy.length; i++){
            naString[i][0]=debtCopy[i][0];
            for(int j=1; j<5; j++){
                naString[i][j]= "N/A";
            }
        }
        return naString;

    }

}
