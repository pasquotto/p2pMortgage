package uk.co.pasquotto.p2pMortgage.mortgage.model;

import org.junit.Test;

public class InvestmentTest {

	@Test(expected=RuntimeException.class)
	public void test() {
		new Investment(-1D, new Lender("lender1"));
	}

}
