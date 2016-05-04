/**
 * 
 */
package uk.co.pasquotto.p2pMortgage.mortgage.model;

import org.junit.Test;

/**
 * @author Rafael Costa
 *
 */
public class MortgageTest {

	@Test(expected=RuntimeException.class)
	public void testInvestmentGreaterThanMortgagePrincipal() {
		Mortgage mortgage = new Mortgage("Mortgage", 100000.00D, 3.9D);
		mortgage.addInvestment(new Investment(80000D, new Lender("lender1")));
		mortgage.addInvestment(new Investment(20000D, new Lender("lender2")));
		mortgage.addInvestment(new Investment(10000D, new Lender("lender3")));
	}
	
	

}
