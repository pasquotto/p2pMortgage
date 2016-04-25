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

	private Integer id;
	private String name;
	private double principal;
	private double interest;
	private Portfolio portfolio;
	
	public Mortgage() {
		this.portfolio = new Portfolio();
	}
	
	public Mortgage(String name, double principal, double interest) {
		this.name = name;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
	
}
