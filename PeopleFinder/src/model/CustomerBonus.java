package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CUSTOMER_BONUS database table.
 * 
 */
@Entity
@Table(name="CUSTOMER_BONUS")
@NamedQuery(name="CustomerBonus.findAll", query="SELECT c FROM CustomerBonus c")
public class CustomerBonus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUST_ID")
	private long custId;

	private String email;

	private String fname;

	private String fullname;

	private String lname;

	@Column(name="\"POSITION\"")
	private String position;

	private String street;

	private String title;

	private BigDecimal zip;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="CITY_ID")
	private City city;

	//bi-directional many-to-one association to CompanyBonus
	@ManyToOne
	@JoinColumn(name="COMPANY_ID")
	private CompanyBonus companyBonus;

	//bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumn(name="STATE_ID")
	private State state;

	public CustomerBonus() {
	}

	public long getCustId() {
		return this.custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getLname() {
		return this.lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getZip() {
		return this.zip;
	}

	public void setZip(BigDecimal zip) {
		this.zip = zip;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public CompanyBonus getCompanyBonus() {
		return this.companyBonus;
	}

	public void setCompanyBonus(CompanyBonus companyBonus) {
		this.companyBonus = companyBonus;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

}