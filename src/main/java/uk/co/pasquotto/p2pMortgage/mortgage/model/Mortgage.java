/**
 * 
 */
package uk.co.pasquotto.p2pMortgage.mortgage.model;

import java.util.List;

/**
 * @author Rafael Costa
 *
 */
public class Mortgage {

	private int id;
	private String name;
	private double principal;
	private double interest;
	private Portfolio portfolio;
	
	public Mortgage() {
		
	}
	
	public Mortgage(double principal, double interest) {
		// TODO Auto-generated constructor stub
		this.principal = principal;
		this.interest = interest;
	}

	public void addInvestment(Investment investment) {
		if(portfolio == null) {
			this.portfolio = new Portfolio();
		}
		if(investment.getAmmount() + this.portfolio.totalInvestment() > this.principal) {
			throw new RuntimeException("Investment amount greater than reminder principal");
		}
		this.portfolio.addInvestment(investment);
	}
	
}
