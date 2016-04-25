/**
 * 
 */
package uk.co.pasquotto.p2pMortgage.mortgage.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import uk.co.pasquotto.p2pMortgage.mortgage.model.MortgageEntity;

/**
 * @author Rafael Costa
 *
 */
@Repository
public interface MortgageRepository extends CrudRepository<MortgageEntity, Integer> {

	public MortgageEntity findById(Integer id);
}
