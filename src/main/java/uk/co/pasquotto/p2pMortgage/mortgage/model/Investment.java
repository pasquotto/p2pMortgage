/**
 * 
 */
package uk.co.pasquotto.p2pMortgage.mortgage.model;

/**
 * @author Rafael Costa
 *
 */
public class Investment {

	private Lender lender;
	private double ammount;
	
	public Investment(double ammount, Lender lender) {
		if(ammount < 0) throw new RuntimeException("Investment ammount can't be negative");
		this.ammount = ammount;
		this.lender = lender;
	}

	public double getAmmount() {
		return ammount;
	}

}
