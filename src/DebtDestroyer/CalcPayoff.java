package DebtDestroyer;

import java.util.ArrayList;

public class CalcPayoff {
double[] paidPY;
double previousDebtamount= 0.0;
String[][] debtCopy;
String[][] payOffData;
String[] yearStart;
double amountPay,totalInterest;
int prevDebtgreater = 0;
ArrayList<String> loanTracker = new ArrayList<String>();

CalcPayoff(String[][] debtCopy,double amountPay) throws DebtGrowingFasterThanPaying{
this.debtCopy = debtCopy;
 paidPY = new double[debtCopy.length];
 this.amountPay = amountPay;
 calculatePayoff();

}


     private void calculatePayoff() throws DebtGrowingFasterThanPaying {
        // set inital total amount owed value loan are sorted to the lowest loan is in
        // the first location.
        yearStart(debtCopy);
        boolean paidInFull = false;
        int lowest = 0; // lowest loan is in the first[0] location
        int month = 0;
        double temp = 0.0;
        while (paidInFull == false) {
            if (amountPay > Double.valueOf(debtCopy[lowest][1])) {
                temp = amountPay - Double.valueOf(debtCopy[lowest][1]);
                paidPY[lowest] = (paidPY[lowest] + Double.valueOf(debtCopy[lowest][1]));
                this.debtCopy[lowest][1] = String.valueOf(0.0);
             //   System.out.println("adding " + debtCopy[lowest][0] + " min payment of " + debtCopy[lowest][2] + " to amount payable initial value is: " + amountPay);
                
                amountPay += Double.valueOf(debtCopy[lowest][2]);
                System.out.println("new value is: " + amountPay);
//                int years = (int)month%12;
               // double year = (month+1) / 12.0;
                if((int)month/12<1){
                    if((int)(month+1)>1)
                    loanTracker.add(debtCopy[lowest][0] + "," + ((month+1)-(((int)month/12)*12)) + " Months" + "," + yearStart[lowest] + "," + paidPY[lowest] + ","
                        + debtCopy[lowest][1]);
                        else
                        loanTracker.add(debtCopy[lowest][0] + "," + ((month+1)-(((int)month/12)*12)) + " Month" + "," + yearStart[lowest] + "," + paidPY[lowest] + ","
                        + debtCopy[lowest][1]);

                }else{
                    //Loan payoff is greater than a year
                    if((int)month/12>1 && (month+1)%12>1){
                        loanTracker.add(debtCopy[lowest][0] + "," + (int)month/12 +" Years "+ ((month+1)-(((int)month/12)*12)) + " Months" + "," + yearStart[lowest] + "," + paidPY[lowest] + ","
                        + debtCopy[lowest][1]);
                    }else if((int)month/12>1 && (month+1)%12==1){
                       loanTracker.add(debtCopy[lowest][0] + "," + (int)month/12 +" Years "+ ((month+1)-(((int)month/12)*12)) + " Month" + "," + yearStart[lowest] + "," + paidPY[lowest] + ","
                        + debtCopy[lowest][1]); 
                    }else if((int)month/12==1 && (month+1)%12==1){
                loanTracker.add(debtCopy[lowest][0] + "," + (int)month/12 +" Year "+ ((month+1)-(((int)month/12)*12)) + " Month" + "," + yearStart[lowest] + "," + paidPY[lowest] + ","
                        + debtCopy[lowest][1]);}
                    else{
                        loanTracker.add(debtCopy[lowest][0] + "," + (int)month/12 +" Year "+ ((month+1)-(((int)month/12)*12)) + " Months" + "," + yearStart[lowest] + "," + paidPY[lowest] + ","
                        + debtCopy[lowest][1]);
                    }
                    }
                prevDebtgreater = 0;
                if (lowest + 1 < debtCopy.length) {
                    lowest++;
                }
               
            } else {
                // System.out.println("Amount paid: " +(amountPay + Double.valueOf(debtCopy[lowest][2])) + " on " + debtCopy[lowest][0] + " loan");
                this.debtCopy[lowest][1] = String.valueOf(Double.valueOf(debtCopy[lowest][1]) - (amountPay + Double.valueOf(debtCopy[lowest][2])));
                paidPY[lowest] = (paidPY[lowest] + (amountPay + Double.valueOf(debtCopy[lowest][2])));

            }
            // Pay the min values payments on all other loans so there are no extra finance
            // charges.
            if (lowest + 1 < debtCopy.length) {// if this is false then we are working on the last loan and we dont need
                                               // to do this.
                for (int i = lowest+1; i < debtCopy.length; i++) {
                    // If there is a positive varance left over from the previous loan pay off apply
                    // it to the first loan minimum pay off.
                    if (temp > 0.0) {
                        debtCopy[i][1] = String
                                .valueOf(Double.valueOf(debtCopy[i][1]) - (Double.valueOf(debtCopy[i][2]) + temp));
                        paidPY[i] += (paidPY[i] + (Double.valueOf(debtCopy[i][2]) + temp));
                        temp = 0.0;
                    } else {
                        debtCopy[i][1] = String
                                .valueOf(Double.valueOf(debtCopy[i][1]) - Double.valueOf(debtCopy[i][2]));
                        paidPY[i] = (paidPY[i] + Double.valueOf(debtCopy[i][2]));
                        // System.out.println(debtCopy[i][0] + " minumum payment of " + debtCopy[i][2] + " paid total amount paid is now " + paidPY[i] );
                    }
                }
            }
            addAPR(lowest);
            this.previousDebtamount = Double.valueOf(debtCopy[lowest][1]);
            month++;

            for (int i = lowest; i < debtCopy.length; i++) {
                // on the year mark add the loan values to the string array
                if (month % 12 == 0) {
                    double year = (double) month / 12.0;
                if(((int)(month+1)/12 > 1))
                    loanTracker.add(debtCopy[i][0] + "," + (int)(month+1)/12 +" Years "+ "," + yearStart[i] + "," + paidPY[i] + ","
                        + debtCopy[i][1]);
                else{
                   loanTracker.add(debtCopy[i][0] + "," + (int)(month+1)/12 +" Year "+ "," + yearStart[i] + "," + paidPY[i] + ","
                        + debtCopy[i][1]); 
                }
                    yearStart[i] = debtCopy[i][1];// The new year starting value becomes last years ending value.
                    //reset amount paid per year to 0
                   // System.out.println(debtCopy[i][0] + "," + year + "," + yearStart[i] + "," + paidPY[i] + "," + debtCopy[i][1]);
                    paidPY[i]=0;
                }
                if (Double.valueOf(debtCopy[i][1]) == 0.0) {
                    paidInFull = true;
                } else
                    paidInFull = false;
            }

            if (prevDebtgreater > 5) {
                throw new DebtGrowingFasterThanPaying(debtCopy[lowest][0], amountPay);
            }

        }
        createMultiString(loanTracker);

    }

   private void addAPR(int lowest) {
        double currentInterest = 0.0;
        // that will check that all loans are paid off.
        for (int i = lowest; i < debtCopy.length; i++) { // for loop to cycle through all of the users loans. i may
                                                         // become lowests loan and we can propaly increnemt that var
                                                         // once its paid off so we dont have to keep checking a known
                                                         // paid off loan.
            if (Double.valueOf(debtCopy[i][1]) != 0) {
                currentInterest = (Double.valueOf(debtCopy[i][1]) * Double.valueOf(debtCopy[i][3]));
                this.totalInterest = this.totalInterest + currentInterest;
                debtCopy[i][1] = String.valueOf(currentInterest + Double.valueOf(debtCopy[i][1]));
            }
        }
        if (Double.valueOf(debtCopy[lowest][1]) > previousDebtamount) {
            prevDebtgreater++;
        } else {
            prevDebtgreater = 0;
        }

    }

    public String[][] getPayoffInfo(){
        return payOffData;
    }

    
    //TODO Create Multidimentional array to return to main.
    private void createMultiString(ArrayList<String> debtInformation){
        payOffData = new String[debtInformation.size()][5]; //Create multi-dim array to hold all of the debt information
        //Cycle through debt information and save it into a multi dimentional array
        for(int i=0; i<debtInformation.size(); i++){
            String[] temp = debtInformation.get(i).split(",");
            payOffData[i][0] = temp[0];//Lender
            payOffData[i][1] = temp[1];//Year
            payOffData[i][2] = temp[2];//Year Starting Balance
            payOffData[i][3] = temp[3];//Yearly Amount Paid
            payOffData[i][4] = temp[4];//Amount Remaining
        }
            
        
    }
    //conduct initial load of yearstarting value array
    private void yearStart(String[][]debtCopy){
        yearStart = new String[debtCopy.length];
        for(int i=0; i<debtCopy.length; i++){
           yearStart[i] = String.valueOf(debtCopy[i][1]); // set starting loan value.
        }
    }  
}
