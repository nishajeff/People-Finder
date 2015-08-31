package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CITIES database table.
 * 
 */
@Entity
@Table(name="CITIES")
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CITY_ID")
	private long cityId;

	@Column(name="CITY_NAME")
	private String cityName;

	//bi-directional many-to-one association to CustomerBonus
	@OneToMany(mappedBy="city")
	private List<CustomerBonus> customerBonuses;

	public City() {
	}

	public long getCityId() {
		return this.cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<CustomerBonus> getCustomerBonuses() {
		return this.customerBonuses;
	}

	public void setCustomerBonuses(List<CustomerBonus> customerBonuses) {
		this.customerBonuses = customerBonuses;
	}

	public CustomerBonus addCustomerBonus(CustomerBonus customerBonus) {
		getCustomerBonuses().add(customerBonus);
		customerBonus.setCity(this);

		return customerBonus;
	}

	public CustomerBonus removeCustomerBonus(CustomerBonus customerBonus) {
		getCustomerBonuses().remove(customerBonus);
		customerBonus.setCity(null);

		return customerBonus;
	}

}