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
public class Avalanch {
  // ArrayList<Debt> debtList = new ArrayList<Debt>();
    String[][] debtCopy;
    String[][] outputArray;// 0=year,1=amount,2=amountPaid,3=amountRemaining
    String[][] payOffData; //0=Lender, 1=year,2=startingAmt,3=yrlyAmtPaid,4=amtRema
    String[] yearStart;
    double[] paidPY;
    ArrayList<String> loanTracker = new ArrayList<String>();
    ArrayList<Debt> debts = new ArrayList<Debt>();
    double amountPay, totalInterest, previousDebtamount, income = 0.0;
    int prevDebtgreater = 0;
    Month months;

    // Accepts arraylist of users debts income and expenses or we can do another
    // object of of the user with thier expenses and income.
        Avalanch(ArrayList<Debt> debtlist, double amountPayable) throws DebtGrowingFasterThanPaying {
        this.debts = debtlist;
        this.amountPay = amountPayable; // ingest what the person can afford to pay minus all of the min monthly
                                        // payments except the lowest loan that is being paid off.
        paidPY = new double[debtlist.size()];
      //calculatePayoff();
    }
          Avalanch(ArrayList<Debt> debtlist) throws DebtGrowingFasterThanPaying {
        this.debts = debtlist;
        paidPY = new double[debtlist.size()];
      //calculatePayoff();
    }

    private void copyDebt(ArrayList<Debt> debtList) {
        yearStart = new String[debtList.size()];
        this.debtCopy = new String[debtList.size()][4];
        for (int i = 0; i < debtList.size(); i++) {
            debtCopy[i][0] = debtList.get(i).debtName; // 0 = lender name
            debtCopy[i][1] = String.valueOf(debtList.get(i).totalAmountOwed); // 1 = total amount owed
            debtCopy[i][2] = String.valueOf(debtList.get(i).minimumMonthlyPayment); // 2 = minumim montly payment amount
            debtCopy[i][3] = String.valueOf((debtList.get(i).APR / 100)); // 3 = Annual Precentage rate.
            yearStart[i] = String.valueOf(debtList.get(i).totalAmountOwed); // set starting loan value.
        }
        //TODO remove this. This is for debug
        System.out.println("Printing a copy of the User inputed sorted debt");
        printMatrix(debtCopy, 4);
        System.out.println("\n");

    }
    private void createDebtList(){
    sortDebtList();
    copyDebt(this.debts);
    }

    public String[][] getDebtList(){
        createDebtList();
        return debtCopy;
    }

    private void calcPayAmount(){
         remMinMonthly();
    }
    public double getAmtPay(){
        calcPayAmount();
        return amountPay;
    }

    // Method used to print the String array to see the lender information
    public void printMatrix(String[][] stringArray,int columns) {
        // copyDebt();
        for (int i = 0; i < stringArray.length; i++) {
            for (int j = 0; j < columns; j++) {
                if (j == 4) {
                    System.out.print(stringArray[i][j] + " ");
                } else
                    System.out.print(stringArray[i][j] + ", ");
            }
            System.out.println();
        }
    }

    // TODO sort all of the APR's owed from lowest to greatest.
    private void sortDebtList() {
        Debt temp; // Save swap element.
        // BubbleSort the loans on the from least APR to greatest.
        for (int i = 0; i < this.debts.size(); i++) {
            for (int j = 1; j < (this.debts.size() - i); j++) {
                if (debts.get(j - 1).APR < debts.get(j).APR) {
                    temp = debts.get(j - 1);
                    debts.set(j - 1, debts.get(j));
                    debts.set(j, temp);
                }
            }
        }

    }
        private void remMinMonthly(){
        for(int i=0;i<debtCopy.length; i++){
            amountPay = (amountPay - Double.valueOf(debtCopy[i][2]));
        }
        System.out.println("New monthly amount payable after min montly subtracted is: " + amountPay);
    }

    
    public String[][] getPayoff(){
        return payOffData;
    }

//     // TODO calc payoff timeframe based on paying max APR to min APR
//     // max afforadable on lowest owed loan.
//     // To update totalPayoffValue update this.debtCopy[loan][1] = String.valueOf();
//     // for each month a minimum payment will need to be applied to the loan so that
//     // there is not a late fee applied.
//    private void calculatePayoff() throws DebtGrowingFasterThanPaying {
//     createDebtList();
//     calcPayAmount();
//         // set inital total amount owed value loan are sorted to the lowest loan is in
//         // the first location.
//         boolean paidInFull = false;
//         int lowest = 0; // lowest loan is in the first[0] location
//         int month = 0;
//         double temp = 0.0;
//         while (paidInFull == false) {
//             if (amountPay > Double.valueOf(debtCopy[lowest][1])) {
//                 temp = amountPay - Double.valueOf(debtCopy[lowest][1]);
//                 paidPY[lowest] = (paidPY[lowest] + Double.valueOf(debtCopy[lowest][1]));
//                 this.debtCopy[lowest][1] = String.valueOf(0.0);
//                 System.out.println("adding " + debtCopy[lowest][0] + " min payment of " + debtCopy[lowest][2] + " to amount payable initial value is: " + amountPay);
                
//                 amountPay += Double.valueOf(debtCopy[lowest][2]);
//                 System.out.println("new value is: " + amountPay);
// //                int years = (int)month%12;
//                // double year = (month+1) / 12.0;
//                 if((int)month/12<1){
//                     loanTracker.add(debtCopy[lowest][0] + "," + ((month+1)-(((int)month/12)*12)) + " Month(s)" + "," + yearStart[lowest] + "," + paidPY[lowest] + ","
//                         + debtCopy[lowest][1]);
//                 }else{
//                 loanTracker.add(debtCopy[lowest][0] + "," + (int)month/12 +" Year(s) "+ ((month+1)-(((int)month/12)*12)) + " Month(s)" + "," + yearStart[lowest] + "," + paidPY[lowest] + ","
//                         + debtCopy[lowest][1]);}
//                 prevDebtgreater = 0;
//                 if (lowest + 1 < debtCopy.length) {
//                     lowest++;
//                 }
               
//             } else {
                
//                // System.out.println("Amount paid: " +(amountPay + Double.valueOf(debtCopy[lowest][2])) + " on " + debtCopy[lowest][0] + " loan");
//                 this.debtCopy[lowest][1] = String.valueOf(Double.valueOf(debtCopy[lowest][1]) - (amountPay + Double.valueOf(debtCopy[lowest][2])));
//                 paidPY[lowest] = (paidPY[lowest] + (amountPay + Double.valueOf(debtCopy[lowest][2])));

//             }
//             // Pay the min values payments on all other loans so there are no extra finance
//             // charges.
//             if (lowest + 1 < debtCopy.length) {// if this is false then we are working on the last loan and we dont need
//                                                // to do this.
//                 for (int i = lowest+1; i < debtCopy.length; i++) {
//                     // If there is a positive varance left over from the previous loan pay off apply
//                     // it to the first loan minimum pay off.
//                     if (temp > 0.0) {
//                         debtCopy[i][1] = String
//                                 .valueOf(Double.valueOf(debtCopy[i][1]) - (Double.valueOf(debtCopy[i][2]) + temp));
//                         paidPY[i] += (paidPY[i] + (Double.valueOf(debtCopy[i][2]) + temp));
//                         temp = 0.0;
//                     } else {
//                         debtCopy[i][1] = String
//                                 .valueOf(Double.valueOf(debtCopy[i][1]) - Double.valueOf(debtCopy[i][2]));
//                         paidPY[i] = (paidPY[i] + Double.valueOf(debtCopy[i][2]));
//                    //     System.out.println(debtCopy[i][0] + " minumum payment of " + debtCopy[i][2] + " paid total amount paid is now " + paidPY[i] );
//                     }
//                 }
//             }
//             addAPR(lowest);
//             this.previousDebtamount = Double.valueOf(debtCopy[lowest][1]);
//             month++;

//             for (int i = lowest; i < debtCopy.length; i++) {
//                 // on the year mark add the loan values to the string array
//                 if (month % 12 == 0) {
//                     double year = (double) month / 12.0;
//                 if(((int)month/12 > 1))
//                     loanTracker.add(debtCopy[i][0] + "," + (int)month/12 +" Years "+ "," + yearStart[i] + "," + paidPY[i] + ","
//                         + debtCopy[i][1]);
//                 else{
//                    loanTracker.add(debtCopy[i][0] + "," + (int)month/12 +" Year "+ "," + yearStart[i] + "," + paidPY[i] + ","
//                         + debtCopy[i][1]); 
//                 }
//                     yearStart[i] = debtCopy[i][1];// The new year starting value becomes last years ending value.
//                     //reset amount paid per year to 0
//                     System.out.println(debtCopy[i][0] + "," + year + "," + yearStart[i] + "," + paidPY[i] + "," + debtCopy[i][1]);
//                     paidPY[i]=0;
//                 }
//                 if (Double.valueOf(debtCopy[i][1]) == 0.0) {
//                     paidInFull = true;
//                 } else
//                     paidInFull = false;
//             }

//             if (prevDebtgreater > 5) {
//                 throw new DebtGrowingFasterThanPaying(debtCopy[lowest][0], amountPay);
//             }

//         }
//          createMultiString(loanTracker);

//     }

//    private void addAPR(int lowest) {
//         double currentInterest = 0.0;
//         // that will check that all loans are paid off.
//         for (int i = lowest; i < debtCopy.length; i++) { // for loop to cycle through all of the users loans. i may
//                                                          // become lowests loan and we can propaly increnemt that var
//                                                          // once its paid off so we dont have to keep checking a known
//                                                          // paid off loan.
//             if (Double.valueOf(debtCopy[i][1]) != 0) {
//                 currentInterest = (Double.valueOf(debtCopy[i][1]) * Double.valueOf(debtCopy[i][3]));
//                 this.totalInterest = this.totalInterest + currentInterest;
//                 debtCopy[i][1] = String.valueOf(currentInterest + Double.valueOf(debtCopy[i][1]));
//             }
//         }
//         if (Double.valueOf(debtCopy[lowest][1]) > previousDebtamount) {
//             prevDebtgreater++;
//         } else {
//             prevDebtgreater = 0;
//         }

//     }

    
//     //TODO Create Multidimentional array to return to main.
//     private void createMultiString(ArrayList<String> debtInformation){
//         payOffData = new String[debtInformation.size()][5]; //Create multi-dim array to hold all of the debt information
//         //Cycle through debt information and save it into a multi dimentional array
//         for(int i=0; i<debtInformation.size(); i++){
//             String[] temp = debtInformation.get(i).split(",");
//             payOffData[i][0] = temp[0];//Lender
//             payOffData[i][1] = temp[1];//Year
//             payOffData[i][2] = temp[2];//Year Starting Balance
//             payOffData[i][3] = temp[3];//Yearly Amount Paid
//             payOffData[i][4] = temp[4];//Amount Remaining
//         }
        
//     }
    // TODO Add method to check/catch checking that the expenses plus all of the
    // minimum monthly payment are not greater than than the monthly income.

}
