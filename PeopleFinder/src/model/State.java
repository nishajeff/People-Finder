package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the STATES database table.
 * 
 */
@Entity
@Table(name="STATES")
@NamedQuery(name="State.findAll", query="SELECT s FROM State s")
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="STATE_ID")
	private long stateId;

	@Column(name="STATE_NAME")
	private String stateName;

	//bi-directional many-to-one association to CustomerBonus
	@OneToMany(mappedBy="state")
	private List<CustomerBonus> customerBonuses;

	public State() {
	}

	public long getStateId() {
		return this.stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public List<CustomerBonus> getCustomerBonuses() {
		return this.customerBonuses;
	}

	public void setCustomerBonuses(List<CustomerBonus> customerBonuses) {
		this.customerBonuses = customerBonuses;
	}

	public CustomerBonus addCustomerBonus(CustomerBonus customerBonus) {
		getCustomerBonuses().add(customerBonus);
		customerBonus.setState(this);

		return customerBonus;
	}

	public CustomerBonus removeCustomerBonus(CustomerBonus customerBonus) {
		getCustomerBonuses().remove(customerBonus);
		customerBonus.setState(null);

		return customerBonus;
	}

}