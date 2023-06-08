package DebtDestroyer;
import java.lang.Math;
import java.util.ArrayList;


public class FiftyThirtyTwenty {

    ArrayList<Debt> debts = new ArrayList<Debt>();

    int timeToPayOff = 0;
    Double twentyPercentIncome = 0.0;
    Double totalMonthlyDebtPayments = 0.0;
    Double totalAmountOwedAcrossAllDebt = 0.0;
    Double APR = .08;

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
        System.out.println("Min time to payoff: " + calculateMinTimeToPayOffWithTwentyPercentIncome());

    }

    public int updateAPR(double newAPR){
        this.APR = newAPR;
        return calculateMinTimeToPayOffWithTwentyPercentIncome();
    }

    public int calculateMinTimeToPayOffWithTwentyPercentIncome(){
        double monthlyAPR = this.APR/12;
        double exactTimeToPayOff = -((Math.log(1-(totalAmountOwedAcrossAllDebt*monthlyAPR)/twentyPercentIncome))/Math.log(1+monthlyAPR));
        int  minMonthsTimeToPayoff = (int) Math.ceil(exactTimeToPayOff);
        return minMonthsTimeToPayoff;
    }



}
