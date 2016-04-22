/**
 * 
 */
package uk.co.pasquotto.p2pMortgage.mortgage.repository;

import org.springframework.data.repository.CrudRepository;

import uk.co.pasquotto.p2pMortgage.mortgage.model.MortgageEntity;

/**
 * @author Rafael Costa
 *
 */
public interface MortgageRepository extends CrudRepository<MortgageEntity, Integer> {

}
