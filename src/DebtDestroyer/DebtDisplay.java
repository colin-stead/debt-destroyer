package DebtDestroyer;

import javafx.beans.property.SimpleStringProperty;

public class DebtDisplay {
	private final SimpleStringProperty year;
	private final SimpleStringProperty amount;
	private final SimpleStringProperty spend;
	private final SimpleStringProperty left;

	public DebtDisplay (String[] info) {
		this.year   = new SimpleStringProperty(info[0]);
		this.amount  = new SimpleStringProperty(info[1]);
		this.spend = new SimpleStringProperty(info[2]);
		this.left   = new SimpleStringProperty(info[3]);
	}
	
	public String getYear () {
		return this.year.get();
	}
	
	public String getSpend () {
		return this.spend.get();
	}
	
	public String getAmount () {
		return this.amount.get();
	}
	
	public String getLeft () {
		return this.left.get();
	}
}
