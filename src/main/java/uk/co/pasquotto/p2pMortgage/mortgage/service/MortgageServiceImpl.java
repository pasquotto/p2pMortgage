/**
 * 
 */
package uk.co.pasquotto.p2pMortgage.mortgage.service;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import uk.co.pasquotto.p2pMortgage.mortgage.model.Mortgage;
import uk.co.pasquotto.p2pMortgage.mortgage.model.MortgageEntity;
import uk.co.pasquotto.p2pMortgage.mortgage.repository.MortgageRepository;

/**
 * @author Rafael Costa
 *
 */
@Service
public class MortgageServiceImpl implements MortgageService {

	@Inject
	private MortgageRepository repository;
	
	@Inject
	private Mapper mapper;
	
	/* (non-Javadoc)
	 * @see uk.co.pasquotto.p2pMortgage.mortgage.service.MortgageService#createMortgage(uk.co.pasquotto.p2pMortgage.mortgage.model.MortgageEntity)
	 */
	@Override
	public Mortgage createMortgage(Mortgage mortgage) {
		MortgageEntity mortgageEntity = this.mapper.map(mortgage, MortgageEntity.class);
		MortgageEntity createdMortgageEntity = repository.save(mortgageEntity);
		Mortgage createdMortgage = this.mapper.map(createdMortgageEntity, Mortgage.class);
		return createdMortgage;
	}

	@Override
	public Mortgage getMortgageById(int id) {
		MortgageEntity mortgageEntity = repository.findById(id);
		Mortgage mortgage = this.mapper.map(mortgageEntity, Mortgage.class);
		return mortgage;
	}

}
