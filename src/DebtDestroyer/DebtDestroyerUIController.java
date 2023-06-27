package DebtDestroyer;

import java.text.NumberFormat;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DebtDestroyerUIController {
	public Button addDebtBtn;
	public AnchorPane window;
	public Pane debtPane;
	public Label originalLabel;
	public Button calculatePayoff;
	public Button deleteDebtBtn;
	public TextField incomeEntry;
	public TextField monthlyExpenseEntry;
	public TextField originalAmountEntry;
	public TextField originalLoanProviderEntry;
	public TextField originalMinimumMonthlyPaymentEntry;
	public TextField originalInterestRateEntry;
	
	public double entryYPosition;
	public double labelYPosition;
	public double maximumMonthlyPayment;
	public int loanNumber = 1;
	
	public ArrayList<ArrayList<TextField>> debtEntries = new ArrayList<>();
	public ArrayList<Debt> debts = new ArrayList<>();
	
	public Pane snowballPane = new Pane();
	public Pane fiftyThirtyTwentyPane = new Pane();
	public Pane avalanchePane = new Pane();
	public Label snowballTitle = new Label("Snowball Method");
	public Label fiftyThirtyTwentyTitle = new Label("50/30/20 Method");
	public Label avalancheTitle = new Label("Avalanche Method");
	public Button calculateSnowball = new Button("See More Info");
	public Button calculateFiftyThirtyTwenty = new Button("See More Info");
	public Button calculateAvalanche = new Button("See More Info");
	private Label congrats = new Label("Congrats! You can afford to pay up to:");
	private Label congratsAmount = new Label();
	private boolean hasRun = false;
	
	public DebtDestroyerUIController () {
		this.debtEntries.add(new ArrayList<TextField>());
	}
	
	// Add a new debt field
	public void addDebtField () {
		// Sets delete debt button visible because there's more than one debt
		// Updates label Y position and entry Y position for first entry
		if (this.loanNumber == 1) {
			this.labelYPosition = this.originalLabel.getLayoutY();
			this.entryYPosition = this.originalAmountEntry.getLayoutY();
		
			deleteDebtBtn.setVisible(true);
		}
		
		// Updates loan number and label and entry y positions
		this.loanNumber += 1;
		this.labelYPosition += 90;
		this.entryYPosition += 90;
		
		this.debtEntries.add(new ArrayList<TextField>());
		
		// Changes the position of our buttons and sizes of our panes
		this.addDebtBtn.setLayoutY(this.addDebtBtn.getLayoutY() + 90);
		this.deleteDebtBtn.setLayoutY(this.deleteDebtBtn.getLayoutY() + 90);
		this.calculatePayoff.setLayoutY(this.calculatePayoff.getLayoutY() + 90);
		this.debtPane.setPrefHeight(this.debtPane.getPrefHeight() + 90);
		this.window.setPrefHeight(this.window.getPrefHeight() + 90);
		
		// If the program has run we need to update the Y Layout of our other elements
		if (this.hasRun) {
			this.congrats.setLayoutY(this.congrats.getLayoutY() + 90);
			this.congratsAmount.setLayoutY(this.congratsAmount.getLayoutY() + 90);
			this.snowballPane.setLayoutY(this.snowballPane.getLayoutY() + 90);
			this.updatePaneChildrenPos(this.snowballPane, true);
			this.fiftyThirtyTwentyPane.setLayoutY(this.fiftyThirtyTwentyPane.getLayoutY() + 90);
			this.updatePaneChildrenPos(this.fiftyThirtyTwentyPane, true);
			this.avalanchePane.setLayoutY(this.avalanchePane.getLayoutY() + 90);
			this.updatePaneChildrenPos(this.avalanchePane, true);
		}
		
		// Adds the total amount field
		TextField loanProviderField = new TextField();
		this.debtPane.getChildren().add(loanProviderField);
		loanProviderField.setStyle("-fx-border-color: skyBlue");
		loanProviderField.setLayoutY(entryYPosition);
		loanProviderField.setPrefHeight(44.0);
		loanProviderField.setPrefWidth(204.0);
		loanProviderField.setPromptText("Loan Provider");
		loanProviderField.setLayoutX(37.0);
		this.debtEntries.get(loanNumber - 1).add(loanProviderField);
				
		// Adds the total amount field
		TextField totalAmountField = new TextField();
		this.debtPane.getChildren().add(totalAmountField);
		totalAmountField.setStyle("-fx-border-color: skyBlue");
		totalAmountField.setLayoutY(entryYPosition);
		totalAmountField.setPrefHeight(44.0);
		totalAmountField.setPrefWidth(162.0);
		totalAmountField.setPromptText("Total Amount");
		totalAmountField.setLayoutX(251.0);
		this.debtEntries.get(loanNumber - 1).add(totalAmountField);
		
		// Adds the minimum monthly payment field
		TextField mmpField = new TextField();
		this.debtPane.getChildren().add(mmpField);
		mmpField.setStyle("-fx-border-color: skyBlue");
		mmpField.setLayoutY(entryYPosition);
		mmpField.setPrefHeight(44.0);
		mmpField.setPrefWidth(196.0);
		mmpField.setPromptText("Minimum Monthly Payment");
		mmpField.setLayoutX(420.0);
		this.debtEntries.get(loanNumber - 1).add(mmpField);
		
		// Adds the interest rate field
		TextField interestRateField = new TextField();
		this.debtPane.getChildren().add(interestRateField);
		interestRateField.setStyle("-fx-border-color: skyBlue");
		interestRateField.setLayoutY(entryYPosition);
		interestRateField.setPrefHeight(44.0);
		interestRateField.setPrefWidth(106.0);
		interestRateField.setPromptText("Interest Rate");
		interestRateField.setLayoutX(626.0);
		this.debtEntries.get(loanNumber - 1).add(interestRateField);
		
		// Adds the new debt label with styling
		Label newLabel = new Label("Debt #" + loanNumber);
		this.debtPane.getChildren().add(newLabel);
		newLabel.setFont(Font.font("System", FontWeight.BOLD, 19));
		newLabel.setLayoutY(labelYPosition);
		newLabel.setLayoutX(35.0);
	}
	
	// Delete most recently added debt field
	public void deleteDebtField() {
		// Removes the five most recent children from the debt pane (Debt Entry)
		this.debtPane.getChildren().remove(this.debtPane.getChildren().size() - 1);
		this.debtPane.getChildren().remove(this.debtPane.getChildren().size() - 1);
		this.debtPane.getChildren().remove(this.debtPane.getChildren().size() - 1);
		this.debtPane.getChildren().remove(this.debtPane.getChildren().size() - 1);
		this.debtPane.getChildren().remove(this.debtPane.getChildren().size() - 1);
		
		// Resetting positions of buttons and size of panes
		this.addDebtBtn.setLayoutY(this.addDebtBtn.getLayoutY() - 90);
		this.deleteDebtBtn.setLayoutY(this.deleteDebtBtn.getLayoutY() - 90);
		this.calculatePayoff.setLayoutY(this.calculatePayoff.getLayoutY() - 90);
		this.debtPane.setPrefHeight(this.debtPane.getPrefHeight() - 90);
		this.window.setPrefHeight(this.window.getPrefHeight() - 90);
		
		// If the program has run we need to update the Y Layout of our other elements
		if (this.hasRun) {
			this.congrats.setLayoutY(this.congrats.getLayoutY() - 90);
			this.congratsAmount.setLayoutY(this.congratsAmount.getLayoutY() - 90);
			this.snowballPane.setLayoutY(this.snowballPane.getLayoutY() - 90);
			this.updatePaneChildrenPos(this.snowballPane, false);
			this.fiftyThirtyTwentyPane.setLayoutY(this.fiftyThirtyTwentyPane.getLayoutY() - 90);
			this.updatePaneChildrenPos(this.fiftyThirtyTwentyPane, false);
			this.avalanchePane.setLayoutY(this.avalanchePane.getLayoutY() - 90);
			this.updatePaneChildrenPos(this.avalanchePane, false);
		}
		
		// Sets the loan number back one so our number is accurate next time we add a debt
		this.loanNumber -= 1;
		// Removes most recent Debt Entry in ArrayList
		this.debtEntries.remove(this.loanNumber);
		
		// Hides delete debt button if there's one loan, changes positions of label y
		// and entry y if there's more than one entry
		if (this.loanNumber == 1) {
			this.deleteDebtBtn.setVisible(false);
		} else {
			this.labelYPosition -= 90;
			this.entryYPosition -= 90;
		}
	}
	
	// Creates debts, and components to choose what method you would like to use to 
	public void calculateDebts() {
		try {
			// Adds original debt entries to our array
			this.createDebts();
			// Creates payment panels so users can choose a debt payoff option
			this.generatePaymentPanels();
			this.hasRun = true; // Error, this gets set to true and if there's an error in the code it breaks functionality.
		} catch (Exception e) {
			System.err.println("Error in creating info");
		}
	}
	
	private void generatePaymentPanels() {
		// Make sure we have data to work with before creating components
		if (this.debts.size() > 0) {
			this.window.setPrefHeight(this.window.getPrefHeight() + 150);
			
			// Update congrats message
			this.congrats.setFont(Font.font("System", FontWeight.BOLD, 32));
			this.congrats.setLayoutY(this.calculatePayoff.getLayoutY() + 40);
			this.congrats.setLayoutX(35.0);
			
			// Congrats value
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			this.congratsAmount.setText(formatter.format(this.maximumMonthlyPayment) + " extra per month");
			this.congratsAmount.setFont(Font.font("System", FontWeight.BOLD, 24));
			this.congratsAmount.setTextFill(Color.color(0, 1, 0));
			this.congratsAmount.setLayoutY(this.congrats.getLayoutY() + 35);
			this.congratsAmount.setLayoutX(35.0);
			
			// Add Panes
			this.snowballPane.setLayoutX(40.0);
			this.snowballPane.setLayoutY(this.congratsAmount.getLayoutY() + 50);
			this.snowballPane.setStyle("-fx-border-color: lightGray;");
			this.snowballPane.setPrefWidth(210.0);
			this.snowballPane.setPrefHeight(240.0);
			
			// Add the snowball title
			snowballTitle.setFont(Font.font("System", FontWeight.BOLD, 19));
			snowballTitle.setLayoutY(10);
			snowballTitle.setLayoutX(11);
			this.snowballPane.getChildren().add(snowballTitle);
			
			// Add the Snowball info section
			Label snowballInfo = new Label("This aggressive method, popularized by Dave Ramsey involves paying off the loan with the lowest amount of debt first. Then riding the momentum of killing a loan into the next loan until all your debt is paid off.");
			snowballInfo.setLayoutY(15);
			snowballInfo.setLayoutX(5);
			snowballInfo.setPrefHeight(200.0);
			snowballInfo.setPrefWidth(200.0);
			snowballInfo.setWrapText(true);
			this.snowballPane.getChildren().add(snowballInfo);
			
			// Add Snowball method button
			this.calculateSnowball.setOnAction(e -> {
				try {
					calculateSnowball();
				} catch (DebtGrowingFasterThanPaying e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			this.calculateSnowball.setStyle("-fx-background-color: skyBlue; -fx-background-radius: 100;");
			this.calculateSnowball.setTextFill(Color.WHITE);
			this.calculateSnowball.setLayoutY(200.0);
			this.calculateSnowball.setLayoutX(52);
			this.snowballPane.getChildren().add(this.calculateSnowball);
			
			// Add 50/30/20 Pane
			this.fiftyThirtyTwentyPane.setLayoutX(290.0);
			this.fiftyThirtyTwentyPane.setLayoutY(this.congratsAmount.getLayoutY() + 50);
			this.fiftyThirtyTwentyPane.setStyle("-fx-border-color: lightGray;");
			this.fiftyThirtyTwentyPane.setPrefWidth(210.0);
			this.fiftyThirtyTwentyPane.setPrefHeight(240.0);
			
			// Add the 50/30/20 title
			fiftyThirtyTwentyTitle.setFont(Font.font("System", FontWeight.BOLD, 19));
			fiftyThirtyTwentyTitle.setLayoutY(10);
			fiftyThirtyTwentyTitle.setLayoutX(12);
			this.fiftyThirtyTwentyPane.getChildren().add(fiftyThirtyTwentyTitle);
			
			// Add the 50/30/20 info section
			Label fiftyThirtyTwentyInfo = new Label("This passive budget was created by Senator Elizabeth Warren. Follow the budget and allocate 50% of your money to your needs, 30% to your wants and 20% to your debts to pay off your loans.");
			fiftyThirtyTwentyInfo.setLayoutY(15);
			fiftyThirtyTwentyInfo.setLayoutX(5);
			fiftyThirtyTwentyInfo.setPrefHeight(180.0);
			fiftyThirtyTwentyInfo.setPrefWidth(200.0);
			fiftyThirtyTwentyInfo.setWrapText(true);
			this.fiftyThirtyTwentyPane.getChildren().add(fiftyThirtyTwentyInfo);
			
			// Add 50/30/20 Method
			this.calculateFiftyThirtyTwenty.setOnAction(e -> calculateFiftyThirtyTwenty());
			this.calculateFiftyThirtyTwenty.setStyle("-fx-background-color: skyBlue; -fx-background-radius: 100;");
			this.calculateFiftyThirtyTwenty.setTextFill(Color.WHITE);
			this.calculateFiftyThirtyTwenty.setLayoutY(200.0);
			this.calculateFiftyThirtyTwenty.setLayoutX(52);
			this.fiftyThirtyTwentyPane.getChildren().add(this.calculateFiftyThirtyTwenty);
			
			// Add Avalanche pane
			this.avalanchePane.setLayoutX(535.0);
			this.avalanchePane.setLayoutY(this.congratsAmount.getLayoutY() + 50);
			this.avalanchePane.setStyle("-fx-border-color: lightGray;");
			this.avalanchePane.setPrefWidth(210.0);
			this.avalanchePane.setPrefHeight(240.0);
			
			// Add the avalanche title
			avalancheTitle.setFont(Font.font("System", FontWeight.BOLD, 19));
			avalancheTitle.setLayoutY(10);
			avalancheTitle.setLayoutX(7);
			this.avalanchePane.getChildren().add(avalancheTitle);
			
			// Add the Avalanche info section
			Label avalancheInfo = new Label("This is another aggressive method to pay off your debt as fast as possible. In the avalanche method, you pay the debt with the highest interest rate first. Then, after that debt is paid off, pay the next debt with a high interest rate.");
			avalancheInfo.setLayoutY(15);
			avalancheInfo.setLayoutX(5);
			avalancheInfo.setPrefHeight(200.0);
			avalancheInfo.setPrefWidth(200.0);
			avalancheInfo.setWrapText(true);
			this.avalanchePane.getChildren().add(avalancheInfo);
			
			// Add avalanche method
			this.calculateAvalanche.setOnAction(e -> {
				try {
					calculateAvalanche();
				} catch (DebtGrowingFasterThanPaying e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			this.calculateAvalanche.setStyle("-fx-background-color: skyBlue; -fx-background-radius: 100;");
			this.calculateAvalanche.setTextFill(Color.WHITE);
			this.calculateAvalanche.setLayoutY(200.0);
			this.calculateAvalanche.setLayoutX(52);
			this.avalanchePane.getChildren().add(this.calculateAvalanche);

			
			// If the functionality hasn't run yet we need to add the new elements extra to the window
			if (!hasRun) {
				// Add Panes to window
				this.window.getChildren().add(this.snowballPane);
				this.window.getChildren().add(this.fiftyThirtyTwentyPane);
				this.window.getChildren().add(this.avalanchePane);
				this.window.getChildren().add(this.congrats);
				this.window.getChildren().add(this.congratsAmount);
			}
		}
	}
	
	// Creates debts from entries given, calculates maximum monthly payments
	private void createDebts() {
		// TODO fix recalc
		
		this.debtEntries.get(0).clear();
		this.debtEntries.get(0).add(originalLoanProviderEntry);
		this.debtEntries.get(0).add(originalAmountEntry);
		this.debtEntries.get(0).add(originalMinimumMonthlyPaymentEntry);
		this.debtEntries.get(0).add(originalInterestRateEntry);

		// Gathers income info
		try {
			// Get starting maximum monthly payment
			this.maximumMonthlyPayment = (Double.valueOf(this.incomeEntry.getText()) / 12) - (Double.valueOf(this.monthlyExpenseEntry.getText()));
			System.out.println("maximum monthly payment thus far: " + this.maximumMonthlyPayment);
		} catch (Exception e) {
			// Display error message if entry's are invalid
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Invalid Entry");
			alert.setContentText("Please make sure income and minimum monthly payment values are numbers.");
			alert.showAndWait().ifPresent(response -> {
			    if (response == ButtonType.OK) {
			    	alert.close();
			    }
			});
		}
		// Creates debts
		for (int i = 0; i < this.debtEntries.size(); i++) {
			try {
				// Get debt name
				String debtName = this.debtEntries.get(i).get(0).getText();
				// Get amount as a double
				double amount =  Double.valueOf(this.debtEntries.get(i).get(1).getText());
				// Get Minimum Monthly Payment as a double
				double mmp =  Double.valueOf(this.debtEntries.get(i).get(2).getText());
				// Update maximum monthly payment for a debt
				this.maximumMonthlyPayment -= mmp;
				if (this.maximumMonthlyPayment <= 0) {
					throw new ExpensesGreaterthanIncome();
				}
				// Get Interest Rate as a double
				double interestRate =  Double.valueOf(this.debtEntries.get(i).get(3).getText());
				
				// Create new Debt, add it to debts array list
				this.debts.add(new Debt(debtName, amount, mmp, interestRate));
			} catch (ExpensesGreaterthanIncome e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Expenses Greater Than Income");
				alert.setContentText("Your expenses are greater than your income! Please consider finding a way to lower your monthly expenses.");
				alert.showAndWait().ifPresent(response -> {
				    if (response == ButtonType.OK) {
				    	alert.close();
				    }
				});
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Invalid Entry");
				alert.setContentText("Please make sure all entries for \"" + debtEntries.get(i).get(0).getText() + "\" are correct.\n\t-Loan Provider should be text.\n\t- Amount, Minimum Monthly Payment, and Interest Rate should all be numbers.");
				alert.showAndWait().ifPresent(response -> {
				    if (response == ButtonType.OK) {
				    	alert.close();
				    }
				});
			}
		}
	}
	
	public void calculateSnowball() throws DebtGrowingFasterThanPaying {
		Snowball snow = new Snowball(this.debts, this.maximumMonthlyPayment);
		String[][] snowprint = snow.getPayoff();
		System.out.println();
		snow.printMatrix(snowprint);
	}
	
	public void calculateFiftyThirtyTwenty() {
		System.out.println("Will run 50/30/20");
	}
	
	public void calculateAvalanche() throws DebtGrowingFasterThanPaying {
		Avalanch ave = new Avalanch(this.debts, this.maximumMonthlyPayment);
		String[][] aveprint = ave.getPayoff();
		System.out.println();
		ave.printMatrix(aveprint, 5);
	}
	
	// Lowers all the elements in the pane with the pane
	private void updatePaneChildrenPos(Pane pane, Boolean lowering) {
//		if (lowering) {
//			ObservableList<Node> children = pane.getChildren();
//			for (Node child : children) {
//				child.setLayoutY(child.getLayoutY() + 90);
//			}
//		} else {
//			ObservableList<Node> children = pane.getChildren();
//			for (Node child : children) {
//				child.setLayoutY(child.getLayoutY() - 90);
//			}
//		}
	}
}
