/**
 * 
 */
package uk.co.pasquotto.p2pMortgage;
import static org.dozer.loader.api.FieldsMappingOptions.*;
import static org.dozer.loader.api.TypeMappingOptions.*;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOption;
import org.dozer.loader.api.TypeMappingOption;
import org.dozer.loader.api.TypeMappingOptions;

import uk.co.pasquotto.p2pMortgage.mortgage.model.Investment;
import uk.co.pasquotto.p2pMortgage.mortgage.model.InvestmentEntity;
import uk.co.pasquotto.p2pMortgage.mortgage.model.Mortgage;
import uk.co.pasquotto.p2pMortgage.mortgage.model.MortgageEntity;

/**
 * @author development
 *
 */
public class MortgageMappingBuilder extends BeanMappingBuilder {

	/* (non-Javadoc)
	 * @see org.dozer.loader.api.BeanMappingBuilder#configure()
	 */
	@Override
	protected void configure() {
		
		mapping(Mortgage.class, 
				MortgageEntity.class)
		.fields("portfolio", 
				"investments", 
				customConverter(PortfolioCustomConverter.class));
		
		mapping(Investment.class, 
				InvestmentEntity.class)
		.fields("lender.name", 
				"lender");
		
		/*mapping(Bean.class, Bean.class,
                TypeMappingOptions.oneWay(),
                mapId("A"),
                mapNull(true)
        )
                .exclude("excluded")
                .fields("src", "dest",
                        copyByReference(),
                        collectionStrategy(true, 
                            RelationshipType.NON_CUMULATIVE),
                        hintA(String.class),
                        hintB(Integer.class),
                        FieldsMappingOptions.oneWay(),
                        useMapId("A"),
                        customConverterId("id")
                )
                .fields("src", "dest",
                    customConverter("org.dozer.CustomConverter")
                );
		 */
	}

}
