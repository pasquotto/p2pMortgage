package uk.co.pasquotto.p2pMortgage.mortgage.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	public List<Investment> getInvestments() {
		lazyInitializeInvestments();
		return this.investments;
	}

	public List<Investment> getInvestmentsFromLender(final Lender lender) {
		 return this.investments.stream()
			.filter(
					(inv) -> {
						return inv.getLender().getName().equals(lender.getName()); 
					}).collect(Collectors.toList());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((investments == null) ? 0 : investments.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Portfolio other = (Portfolio) obj;
		if (investments == null) {
			if (other.investments != null)
				return false;
		} else if (!investments.equals(other.investments))
			return false;
		return true;
	}
	
	

}
