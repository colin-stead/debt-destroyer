package DebtDestroyer;

public class Debt {
    String debtName;
    double totalAmountOwed;
    double minimumMonthlyPayment;
    double APR;

    public Debt(String debtName, double totalAmountOwed, double minimumMonthlyPayment, double APR){
        setDebtName(debtName);
        setTotalAmountOwed(totalAmountOwed);
        setMinimumMonthlyPayment(minimumMonthlyPayment);
        setAPR(APR);
    }

    public String getDebtName(){
        return this.debtName;
    }

    public double getAPR() {
        return this.APR;
    }
    public double getMinimumMonthlyPayment() {
        return this.minimumMonthlyPayment;
    }

    public double getTotalAmountOwed() {
        return this.totalAmountOwed;
    }

    private void setDebtName(String name){
        this.debtName = name;
    }
    private void setAPR(double APR) {
        this.APR = APR;
    }

    private void setMinimumMonthlyPayment(double minimumMonthlyPayment) {
        this.minimumMonthlyPayment = minimumMonthlyPayment;
    }

    private void setTotalAmountOwed(double totalAmountOwed) {
        this.totalAmountOwed = totalAmountOwed;
    }

}

