package DebtDestroyer;

import java.time.Month;
import java.util.ArrayList;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * Package: DeptDestroyer                                            *
 * Class: Snowball      											 *
 * Author: DebtDestroyers											 *
 * Date: 27 May 2023											 	 *
 * Course CMSC 495 6382												 *
 * Purpose: Define the snowball payoff debt method. Snowball payoff  *
 * method is structured to payoff the lowest debt amount first and   *
 * shifts focus to the next lowest amount once that has been paid off*
 * and so on and so forth until all debt has been paid off.          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class Snowball {
    // ArrayList<Debt> debtList = new ArrayList<Debt>();
    String[][] debtCopy;
    ArrayList<Debt> debts = new ArrayList<Debt>();
    Double lowestLoanInitialAmt = 0.0;
    Month months;

    // Accepts arraylist of users debts income and expenses or we can do another
    // object of of the user with thier expenses and income.
    Snowball(ArrayList<Debt> debtlist) {
        this.debts = debtlist;
        sortDebtList();
        copyDebt(this.debts);
        addAPR();

    }

    // TODO this is a place holder. going testing abilty to get name from this debt
    // list from snowball class.
    public void getDebtName(int location) {
        // System.out.println(debtList.get(location).debtName);
    }

    // TODO create method to get all of the total amounts that are owed.
    // public void getOwedAmounts(){
    // for(int i=0; i<debtList.size();i++){
    // System.out.println("Amount owed on " + debtList.get(i).getDebtName() + " is "
    // + debtList.get(i).getTotalAmountOwed());
    // }
    // }
    // Copy the debt object into a multi String array so that we can minupulate the
    // data with out messing with the integrity of the data since its pass by
    // referernece.
    private void copyDebt(ArrayList<Debt> debtList) {
        this.debtCopy = new String[debtList.size()][4];
        for (int i = 0; i < debtList.size(); i++) {
            debtCopy[i][0] = debtList.get(i).debtName; // 0 = lender name
            debtCopy[i][1] = String.valueOf(debtList.get(i).totalAmountOwed); // 1 = total amount owed
            debtCopy[i][2] = String.valueOf(debtList.get(i).minimumMonthlyPayment); // 2 = minumim montly payment amount
            debtCopy[i][3] = String.valueOf((debtList.get(i).APR/100)); // 3 = Annual Precentage rate.
        }

    }

    public void printMatrix() {
        // copyDebt();
        for (int i = 0; i < debtCopy.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 3) {
                    System.out.print(debtCopy[i][j] + " ");
                } else
                    System.out.print(debtCopy[i][j] + ", ");
            }
            System.out.println();
        }
    }

    // TODO sort all of the total amounts owed from lowes to greates.
    private void sortDebtList() {
        Debt temp; // Save swap element.
        // BubbleSort the loans on the from least total amount owed to greast amount
        // owed.
        for (int i = 0; i < this.debts.size(); i++) {
            for (int j = 1; j < (this.debts.size() - i); j++) {
                if (debts.get(j - 1).totalAmountOwed > debts.get(j).totalAmountOwed) {
                    temp = debts.get(j - 1);
                    debts.set(j - 1, debts.get(j));
                    debts.set(j, temp);
                }
            }
        }

    }

    // TODO calc payoff timeframe based on paying min on non-lowest loan and paying
    // max afforadable on lowest owed loan.
    // To update totalPayoffValue update this.debtCopy[loan][1] = String.valueOf();
    // for each month a minimum payment will need to be applied to the loan so that
    // there is not a late fee applied.
    private void calculatePayoff() {
        this.lowestLoanInitialAmt = Double.valueOf(this.debtCopy[0][1]); // set inital total amount owed value loan are
                                                                          // sorted to the lowest loan is in the firts
                                                                          // location.
        

    }
    private void addAPR(){
        for(int times = 0; times < 5; times++){
        for(int i=0; i<debtCopy.length; i++){
            if(Double.valueOf(debtCopy[i][1]) !=0){
                debtCopy[i][1] = String.valueOf((Double.valueOf(debtCopy[i][1]) * Double.valueOf(debtCopy[i][3])) + Double.valueOf(debtCopy[i][1]));
                System.out.println(debtCopy[i][1]);
            }
        }
    }
    }

    // TODO Add method to check/catch checking that the expenses plus all of the
    // minimum monthly payment are not greater than than the monthly income.

}
