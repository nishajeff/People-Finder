package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the COMPANY_BONUS database table.
 * 
 */
@Entity
@Table(name="COMPANY_BONUS")
@NamedQuery(name="CompanyBonus.findAll", query="SELECT c FROM CompanyBonus c")
public class CompanyBonus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COMPANY_ID")
	private long companyId;

	@Column(name="COMPANY_NAME")
	private String companyName;

	//bi-directional many-to-one association to CustomerBonus
	@OneToMany(mappedBy="companyBonus")
	private List<CustomerBonus> customerBonuses;

	public CompanyBonus() {
	}

	public long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<CustomerBonus> getCustomerBonuses() {
		return this.customerBonuses;
	}

	public void setCustomerBonuses(List<CustomerBonus> customerBonuses) {
		this.customerBonuses = customerBonuses;
	}

	public CustomerBonus addCustomerBonus(CustomerBonus customerBonus) {
		getCustomerBonuses().add(customerBonus);
		customerBonus.setCompanyBonus(this);

		return customerBonus;
	}

	public CustomerBonus removeCustomerBonus(CustomerBonus customerBonus) {
		getCustomerBonuses().remove(customerBonus);
		customerBonus.setCompanyBonus(null);

		return customerBonus;
	}

}