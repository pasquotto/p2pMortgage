package uk.co.pasquotto.p2pMortgage.mortgage.service;

import uk.co.pasquotto.p2pMortgage.mortgage.model.Mortgage;

public interface MortgageService {

	public Mortgage createMortgage(Mortgage mortgage);

	public Mortgage getMortgageById(int id);

	
}
