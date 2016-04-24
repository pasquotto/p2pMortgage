/**
 * 
 */
package uk.co.pasquotto.p2pMortgage.mortgage.controller;

import javax.ws.rs.GET;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.co.pasquotto.p2pMortgage.mortgage.model.MortgageEntity;

/**
 * @author Rafael Costa
 *
 */
@RestController
@RequestMapping("/mortgage")
public class MortgageController {

	@GET
	@RequestMapping("/{id}")
	public MortgageEntity getMortgage(@PathVariable("id") int id) {
		return new MortgageEntity("mortgage id: " + id);
	}
	
}
