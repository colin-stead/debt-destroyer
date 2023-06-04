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
    double lowestLoanInitialAmt, amountPay, totalInterest = 0.0;
    Month months;

    // Accepts arraylist of users debts income and expenses or we can do another
    // object of of the user with thier expenses and income.
    Snowball(ArrayList<Debt> debtlist) {
        this.debts = debtlist;
        amountPay = 475.00;
        sortDebtList();
        copyDebt(this.debts);
        calculatePayoff();
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
            debtCopy[i][3] = String.valueOf((debtList.get(i).APR / 100)); // 3 = Annual Precentage rate.
        }

    }

    // Method used to print the String array to see the lender information
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
        // set inital total amount owed value loan are sorted to the lowest loan is in
        // the first location.
        this.lowestLoanInitialAmt = Double.valueOf(this.debtCopy[0][1]);
        boolean paidInFull = false;
        int lowest = 0; // lowest loan is in the first[0] location
        int month =1;
        double temp =0.0;
        while (paidInFull == false) {
            if (amountPay > Double.valueOf(debtCopy[lowest][1])) {
                 temp = amountPay - Double.valueOf(debtCopy[lowest][1]);
                this.debtCopy[lowest][1] = String.valueOf(0.0);
                System.out.println("Month " + month  + "\nLender - "  + debtCopy[lowest][0] + " is paid off\nAmount left to pay to the next loan is: " + temp);
                this.amountPay+= Double.valueOf(debtCopy[lowest][2]);
                System.out.println("New Monthly amount to pay: " + amountPay);
                if(lowest+1<debtCopy.length){
                lowest++;
                System.out.println("lowest now = " + lowest + " New Loan being paid off is " + debtCopy[lowest][0]);
                }
            } else {
                this.debtCopy[lowest][1] = String.valueOf(Double.valueOf(debtCopy[lowest][1]) - amountPay);
                System.out.println("Month " + month  + "\nAmount after monthly pay off: " + this.debtCopy[lowest][1]);
            }
            //Pay the min values payments on all other loans so there are no extra finance charges.
            if(lowest+1 <debtCopy.length){//if this is false then we are working on the last loan and we dont need to do this.
            for(int i=lowest+1; i<debtCopy.length; i++){
                //If there is a positive varance left over from the previous loan pay off apply it to the first loan minimum pay off.
                if(temp>0.0){
                    debtCopy[i][1] = String.valueOf(Double.valueOf(debtCopy[i][1])-(Double.valueOf(debtCopy[i][2]) + temp));
                    temp = 0.0;
                }
                else{
                    debtCopy[i][1] = String.valueOf(Double.valueOf(debtCopy[i][1])-Double.valueOf(debtCopy[i][2]));
                }
            }
        }
            addAPR(lowest);
            month++;

            for (int i = lowest; i < debtCopy.length; i++) {
                if (Double.valueOf(debtCopy[i][1]) == 0.0) {
                    paidInFull = true;
                } else
                    paidInFull = false;
            }
            // if(month>150){
            //     paidInFull = true;
            // }

        }
        System.out.println("Total interest paid through life of loan pay off was: " + totalInterest);

    }

    private void addAPR(int lowest) {
        double currentInterest = 0.0;
        // while(Double.valueOf(debtCopy[lowest][1])>0.0){//make this a boolean value
        // that will check that all loans are paid off.
        for (int i = lowest; i < debtCopy.length; i++) { // for loop to cycle through all of the users loans. i may
                                                         // become lowests loan and we can propaly increnemt that var
                                                         // once its paid off so we dont have to keep checking a known
                                                         // paid off loan.
            if (Double.valueOf(debtCopy[i][1]) != 0) {
                currentInterest = (Double.valueOf(debtCopy[i][1]) * Double.valueOf(debtCopy[i][3]));
                this.totalInterest = this.totalInterest + currentInterest;
                debtCopy[i][1] = String.valueOf(currentInterest + Double.valueOf(debtCopy[i][1]));
                System.out.println("Amount after interst charges: " + debtCopy[i][1]);
            }
        }
        System.out.println();
        
    }

    // TODO Add method to check/catch checking that the expenses plus all of the
    // minimum monthly payment are not greater than than the monthly income.

}
