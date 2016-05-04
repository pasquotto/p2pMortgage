package uk.co.pasquotto.p2pMortgage.mortgage.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author Rafael Costa
 *
 */
@Entity
public class MortgageEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private double principal;
	private double interest;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<InvestmentEntity> investments;
	
	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public MortgageEntity() { }

	public MortgageEntity(String name) {
		super();
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MortgageEntity [id=" + id + ", name=" + name + "]";
	}

	public List<InvestmentEntity> getInvestments() {
		return investments;
	}

	public void setInvestments(List<InvestmentEntity> investments) {
		this.investments = investments;
	}
	
	
	
}
