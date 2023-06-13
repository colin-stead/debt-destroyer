package DebtDestroyer;
import java.lang.Math;
import java.util.ArrayList;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Package: DeptDestroyer                                            *
 * Class: Snowball      											 *
 * Author: DebtDestroyers											 *
 * Date: 27 May 2023											 	 *
 * Course CMSC 495 6382												 *
 * Purpose: Define the 50/30/20 payoff debt method. 50/30/20 payoff  *
 * method is intended to be a more conservative payoff method than   *
 * others. The idea is to divide your net income into three          *
 * categories, spending 50% on needs, 30% on wants, and 20% on debt  *
 * or savings. Our approach is for the user to take out a debt       *
 * consolidation loan that fits there 20% goal and gets the user     *
 * debt free.                                                        *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

public class FiftyThirtyTwenty {

    ArrayList<Debt> debts = new ArrayList<Debt>();

    int timeToPayOff = 0;
    Double twentyPercentIncome = 0.0;
    Double totalMonthlyDebtPayments = 0.0;
    Double totalAmountOwedAcrossAllDebt = 0.0;
    Double APR = .08;
    String[][] payOffData;


    FiftyThirtyTwenty(ArrayList<Debt> debtlist, double income, double APR) throws DebtGrowingFasterThanPaying {
        this.debts = debtlist;
        this.APR = APR;
        twentyPercentIncome = income * .20;
        for (int i =0; i < this.debts.size(); i++){
            this.totalMonthlyDebtPayments = this.totalMonthlyDebtPayments + this.debts.get(i).minimumMonthlyPayment;
            this.totalAmountOwedAcrossAllDebt = this.totalAmountOwedAcrossAllDebt + this.debts.get(i).totalAmountOwed;
        }
        timeToPayOff = calculateMinTimeToPayOffWithTwentyPercentIncome();
        if (timeToPayOff == 0){
            throw new DebtGrowingFasterThanPaying(twentyPercentIncome, this.APR);
        }
        payOffData = calculatePayoff();
        //System.out.println("Min time to payoff: " + calculateMinTimeToPayOffWithTwentyPercentIncome());
    }

    public void updateAPR(double newAPR){
        this.APR = newAPR;
        calculateMinTimeToPayOffWithTwentyPercentIncome();
        calculatePayoff();
    }

    private int calculateMinTimeToPayOffWithTwentyPercentIncome(){
        double monthlyAPR = this.APR/12;
        double exactTimeToPayOff = -((Math.log(1-(totalAmountOwedAcrossAllDebt*monthlyAPR)/twentyPercentIncome))/Math.log(1+monthlyAPR));
        int  minMonthsTimeToPayoff = (int) Math.ceil(exactTimeToPayOff);
        return minMonthsTimeToPayoff;
    }

    private String[][] calculatePayoff(){
        Double yearsToPayOff = this.timeToPayOff/12.0;
        int x = (int) Math.ceil(yearsToPayOff);
        System.out.println("Years to payoff: " + yearsToPayOff);
        String[][] payOffData = new String[x][5];

        double startingBalance = totalAmountOwedAcrossAllDebt;
        double yearlyAmountPaid = 0;
        double yearlyAmountRemaining = 0;
        double monthlyIntereest = 0;
        double monthlyPaidToBalance =0;
        double currentAmountRemaining = startingBalance;

        // outer loop goes through each year until to pay off to get yearly payoff information
        // inner loop (j) goes through each month of the year to determine the amount paid to balance and interest to get
        // the yearly information.
        for (int i = 0; i <= yearsToPayOff; i++){
            int year = i + 1;
            startingBalance = currentAmountRemaining;
            for (int month = 0; month < 12; month++) {
                monthlyIntereest = currentAmountRemaining * (APR / 12);
                monthlyPaidToBalance = twentyPercentIncome - monthlyIntereest;
                if (monthlyPaidToBalance > currentAmountRemaining) {
                    monthlyPaidToBalance = currentAmountRemaining;
                }
                currentAmountRemaining = currentAmountRemaining - monthlyPaidToBalance;
                yearlyAmountPaid = yearlyAmountPaid + monthlyPaidToBalance + monthlyIntereest;
                if (currentAmountRemaining == 0){
                    month = 11; // exit loop
                }
               // System.out.println("Year: " + year + " Month: " + month + 1 + " Monthly Paid to balance: " + monthlyPaidToBalance +
               //         " Monthly Interest: " + monthlyIntereest + " monthly Balance Remaining: " + currentAmountRemaining);
            }
            payOffData[i][0] = "50/30/20";
            payOffData[i][1] = String.valueOf(year);
            payOffData[i][2] = String.valueOf(startingBalance);
            payOffData[i][3] = String.valueOf(yearlyAmountPaid);
            payOffData[i][4] = String.valueOf(currentAmountRemaining);
        }
        printMatrix(payOffData);
        return payOffData;
    }

    public String[][] getPayoff(){
        return payOffData;
    }

    private void printMatrix(String[][] payOffData) {
       for (int i = 0; i < payOffData.length; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 3) {
                    System.out.print(payOffData[i][j] + " ");
                } else
                    System.out.print(payOffData[i][j] + ", ");
            }
            System.out.println();
        }
    }



}
