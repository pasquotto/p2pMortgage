package uk.co.pasquotto.p2pMortgage.mortgage.model;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {

	public List<Investment> investments;

	public double totalInvestment() {
		lazyInitializeInvestments();
		return investments.stream().mapToDouble(investment -> {
				return investment.getAmmount();
			}).sum();
	}

	private void lazyInitializeInvestments() {
		if(this.investments == null) this.investments = new ArrayList<>();
	}

	public void addInvestment(Investment investment) {
		lazyInitializeInvestments();
		this.investments.add(investment);
	}

}
