/**
 * 
 */
package uk.co.pasquotto.p2pMortgage.mortgage.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import uk.co.pasquotto.p2pMortgage.mortgage.service.MortgageNotFoundException;

/**
 * @author Rafael Costa
 *
 */
@ControllerAdvice
public class NotFoundExceptionHanddler extends ResponseEntityExceptionHandler {

	/**
	 * 
	 */
	public NotFoundExceptionHanddler() {
	}

	@ResponseStatus(value = NOT_FOUND)
	@ExceptionHandler({ MortgageNotFoundException.class })
	public ResponseEntity<Object> badRequest(HttpServletRequest req, Exception exception) {
		ErrorResource error = new ErrorResource("InvalidRequest", exception.getMessage());
		HttpHeaders headers = new HttpHeaders();
		
		return new ResponseEntity<Object>(error, headers, NOT_FOUND);
	}
}
