/**
 * 
 */
package uk.co.pasquotto.p2pMortgage;
import static org.dozer.loader.api.FieldsMappingOptions.*;
import static org.dozer.loader.api.TypeMappingOptions.*;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOption;
import org.dozer.loader.api.TypeMappingOptions;

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
		this.
		
		
		
		mapping(Mortgage.class, 
				MortgageEntity.class, 
				TypeMappingOptions.oneWay())
		.fields("portfolio", 
				"investments", customConverter(PortfolioCustomConverter.class));
		
		mapping(MortgageEntity.class,
				Mortgage.class,
				TypeMappingOptions.oneWay())
		.fields("investments", 
				"portfolio", customConverter(PortfolioCustomConverter.class));
		
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
