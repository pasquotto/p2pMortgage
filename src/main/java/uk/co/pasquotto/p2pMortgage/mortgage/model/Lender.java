/**
 * 
 */
package uk.co.pasquotto.p2pMortgage.mortgage.model;

/**
 * @author development
 *
 */
public class Lender {

	private String name;
	
	public Lender() { }
	
	public Lender(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
