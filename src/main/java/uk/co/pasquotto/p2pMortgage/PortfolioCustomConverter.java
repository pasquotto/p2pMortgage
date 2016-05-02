/**
 * 
 */
package uk.co.pasquotto.p2pMortgage;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

import uk.co.pasquotto.p2pMortgage.mortgage.model.Investment;
import uk.co.pasquotto.p2pMortgage.mortgage.model.InvestmentEntity;
import uk.co.pasquotto.p2pMortgage.mortgage.model.Portfolio;

/**
 * @author Rafael Costa
 *
 */
public class PortfolioCustomConverter 
			extends DozerConverter<Portfolio, List> 
			implements MapperAware {

	private Mapper mapper;
	
	public PortfolioCustomConverter() {
		super(Portfolio.class, List.class);
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<?> convertTo(Portfolio source, List destination) {
		List<InvestmentEntity> investmentDestination = new ArrayList<>();
		List<Investment> investments = source.getInvestments();
		for (Investment investment : investments) {
			InvestmentEntity entity = mapper.map(investment, InvestmentEntity.class);
			investmentDestination.add(entity);
		}
		return investmentDestination;
	}

	@Override
	public Portfolio convertFrom(List source, Portfolio destination) {
		Portfolio portfolio = new Portfolio();
		
		for (Object object : source) {
			Investment investment = mapper.map(object, Investment.class);
			portfolio.addInvestment(investment);
		}
		
		return portfolio;
	}

}
