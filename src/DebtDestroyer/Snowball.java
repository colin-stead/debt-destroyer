package DebtDestroyer;

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
ArrayList<Debt> debtList = new ArrayList<Debt>();
//Accepts arraylist of users debts income and expenses or we can do another object of of the user with thier expenses and income.
Snowball(ArrayList<Debt> debtlist){
this.debtList= debtlist;
}
//TODO this is a place holder. going testing abilty to get name from this debt list from snowball class.
public void getDebtName(int location){
    System.out.println(debtList.get(location).debtName);
}

//TODO create method to get all of the total amounts that are owed.
public void getOwedAmounts(){
    for(int i=0; i<debtList.size();i++){
        System.out.println("Amount owed on " + debtList.get(i).getDebtName() + " is " + debtList.get(i).getTotalAmountOwed());
    }
}


//TODO sort all of the total amounts owed from lowes to greates.

//TODO calc payoff timframe based on paying min on non-lowest loan and paying max afforadable on lowest owed loan.
}




