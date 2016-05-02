/**
 * 
 */
package uk.co.pasquotto.p2pMortgage.mortgage.model;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public Mortgage() {
		this.portfolio = new Portfolio();
	}
	
	public Mortgage(String name, double principal, double interest) {
		this();
		this.name = name;
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

	@Override
	public String toString() {
		return "Mortgage [id=" + id + ", name=" + name + ", principal=" + principal + ", interest=" + interest
				+ ", portfolio=" + portfolio + "]";
	}

	@JsonIgnore
	public List<Investment> getInvestments() {
		
		return Collections.unmodifiableList(this.portfolio.getInvestments());
	}
	
}
