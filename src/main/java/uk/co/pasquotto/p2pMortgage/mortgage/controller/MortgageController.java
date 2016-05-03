/**
 * 
 */
package uk.co.pasquotto.p2pMortgage.mortgage.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.*;


import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import uk.co.pasquotto.p2pMortgage.mortgage.model.Investment;
import uk.co.pasquotto.p2pMortgage.mortgage.model.Mortgage;
import uk.co.pasquotto.p2pMortgage.mortgage.service.MortgageService;

/**
 * @author Rafael Costa
 *
 */
@RestController
@RequestMapping("/mortgage")
public class MortgageController {

	@Inject
	private MortgageService mortgageService;
		
	@RequestMapping("/{id}")
	public Mortgage getMortgage(@PathVariable("id") int id) {
		Mortgage mortgage = mortgageService.getMortgageById(id);
		
		return mortgage;
	}
	
	@ResponseStatus(CREATED)
	@RequestMapping(method=POST)
	public Mortgage createMortgage(@RequestBody Mortgage mortgage) {
		Mortgage newMortgage = this.mortgageService.createMortgage(mortgage);
		return newMortgage;
	}
	
	@RequestMapping(path="/{id}", method=DELETE)
	public void deleteMortgage(@PathVariable("id") int id) {
		mortgageService.deleteMortgageById(id);
	}
	
	@RequestMapping(path="/{id}/portfolio", method=POST)
	public void investIntoMortgage(@PathVariable("id") int id, @RequestBody Investment investment) {
		Mortgage mortgage = mortgageService.getMortgageById(id);
		mortgage.addInvestment(investment);
		mortgageService.saveMortgage(mortgage);
	}
	
	@RequestMapping("/error")
	public Exception handleError() {
		return null;
	}
}
