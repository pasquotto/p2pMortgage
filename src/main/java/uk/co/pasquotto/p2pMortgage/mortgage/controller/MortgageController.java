/**
 * 
 */
package uk.co.pasquotto.p2pMortgage.mortgage.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
		
	@GET
	@RequestMapping("/{id}")
	public Mortgage getMortgage(@PathVariable("id") int id) {
		Mortgage mortgage = mortgageService.getMortgageById(id);
		
		return mortgage;
	}
	
	@POST
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping
	public Mortgage createMortgage(@RequestBody Mortgage mortgage) {
		Mortgage newMortgage = this.mortgageService.createMortgage(mortgage);
		return newMortgage;
	}
	
}
