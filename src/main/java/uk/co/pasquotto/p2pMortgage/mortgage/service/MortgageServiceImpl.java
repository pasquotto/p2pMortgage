/**
 * 
 */
package uk.co.pasquotto.p2pMortgage.mortgage.service;

import org.springframework.stereotype.Service;

import uk.co.pasquotto.p2pMortgage.mortgage.model.MortgageEntity;
import uk.co.pasquotto.p2pMortgage.mortgage.repository.MortgageRepository;

/**
 * @author Rafael Costa
 *
 */
@Service
public class MortgageServiceImpl implements MortgageService {

	private MortgageRepository repository;
	
	/* (non-Javadoc)
	 * @see uk.co.pasquotto.p2pMortgage.mortgage.service.MortgageService#createMortgage(uk.co.pasquotto.p2pMortgage.mortgage.model.MortgageEntity)
	 */
	@Override
	public MortgageEntity createMortgage(MortgageEntity mortgage) {
		return repository.save(mortgage);
	}

}
