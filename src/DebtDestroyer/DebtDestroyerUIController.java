package DebtDestroyer;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DebtDestroyerUIController {
	public Button addDebtBtn;
	public Pane debtPane;
	public AnchorPane window;
	public Label originalLabel;
	public TextField originalAmountEntry;
	public Button calculatePayoff;
	public Button deleteDebtBtn;
	
	public double entryYPosition;
	public double labelYPosition;
	public int loanNumber = 1;
	
	public DebtDestroyerUIController () { }
	
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
		
		// Changes the position of our buttons and sizes of our panes
		this.addDebtBtn.setLayoutY(this.addDebtBtn.getLayoutY() + 90);
		this.deleteDebtBtn.setLayoutY(this.deleteDebtBtn.getLayoutY() + 90);
		this.calculatePayoff.setLayoutY(this.calculatePayoff.getLayoutY() + 90);
		this.debtPane.setPrefHeight(this.debtPane.getPrefHeight() + 90);
		this.window.setPrefHeight(this.window.getPrefHeight() + 90);
		
		// Adds the total amount field
		TextField totalAmountField = new TextField();
		this.debtPane.getChildren().add(totalAmountField);
		totalAmountField.setStyle("-fx-border-color: skyBlue");
		totalAmountField.setLayoutY(entryYPosition);
		totalAmountField.setPrefHeight(44.0);
		totalAmountField.setPrefWidth(220.0);
		totalAmountField.setPromptText("Total Amount");
		totalAmountField.setLayoutX(35.0);
		
		// Adds the interest rate field
		TextField interestRateField = new TextField();
		this.debtPane.getChildren().add(interestRateField);
		interestRateField.setStyle("-fx-border-color: skyBlue");
		interestRateField.setLayoutY(entryYPosition);
		interestRateField.setPrefHeight(44.0);
		interestRateField.setPrefWidth(220.0);
		interestRateField.setPromptText("Interest Rate");
		interestRateField.setLayoutX(273.0);
		
		// Adds the minimum monthly payment field
		TextField mmpField = new TextField();
		this.debtPane.getChildren().add(mmpField);
		mmpField.setStyle("-fx-border-color: skyBlue");
		mmpField.setLayoutY(entryYPosition);
		mmpField.setPrefHeight(44.0);
		mmpField.setPrefWidth(220.0);
		mmpField.setPromptText("Minimum Monthly Payment");
		mmpField.setLayoutX(512.0);
		
		// Adds the new debt label with styling
		Label newLabel = new Label("Debt #" + loanNumber);
		this.debtPane.getChildren().add(newLabel);
		newLabel.setFont(Font.font("System", FontWeight.BOLD, 19));
		newLabel.setLayoutY(labelYPosition);
		newLabel.setLayoutX(35.0);
	}
	
	// Delete most recently added debt field
	public void deleteDebtField() {
		// Removes the four most recent children from the debt pane (Debt Entry)
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
		
		// Sets the loan number back one so our number is accurate next time we add a debt
		this.loanNumber -= 1;
		
		// Hides delete debt button if there's one loan, changes positions of label y
		// and entry y if there's more than one entry
		if (this.loanNumber == 1) {
			this.deleteDebtBtn.setVisible(false);
		} else {
			this.labelYPosition -= 90;
			this.entryYPosition -= 90;
		}
	}
}
