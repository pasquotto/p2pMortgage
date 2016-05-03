/**
 * 
 */
package uk.co.pasquotto.p2pMortgage.mortgage.model;

/**
 * @author Rafael Costa
 *
 */
public class Investment {

	private Integer id;
	private Lender lender;
	private double ammount;

	public Investment() { }

	public Investment(double ammount, Lender lender) {
		if (ammount < 0)
			throw new RuntimeException("Investment ammount can't be negative");
		this.ammount = ammount;
		this.lender = lender;
	}

	public Lender getLender() {
		return lender;
	}

	public void setLender(Lender lender) {
		this.lender = lender;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	public double getAmmount() {
		return ammount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(ammount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lender == null) ? 0 : lender.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Investment other = (Investment) obj;
		if (Double.doubleToLongBits(ammount) != Double.doubleToLongBits(other.ammount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lender == null) {
			if (other.lender != null)
				return false;
		} else if (!lender.equals(other.lender))
			return false;
		return true;
	}

}
