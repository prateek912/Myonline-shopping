package com.spring.backend.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Address implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private User user;
	@NotBlank(message="Please enter your address")
	@Column(name="address_line_one")
	private String addresslineOne;
	@Column(name="address_line_two")
	private String addresslineTwo;
	@NotBlank(message="Please enter your City Name")
	private String city;
	@NotBlank(message="Please enter your State Name")
	private String state;
	@NotBlank(message="Please enter your Country Name")
	private String country;
	@Column(name="postal_code")
	@NotBlank(message="Please enter your local Postal Code")
	private String postalCode;
	private boolean shipping;
	private boolean billing;
	
	public int getId() {
		return id;
	}
	public String getAddresslineOne() {
		return addresslineOne;
	}
	public void setAddresslineOne(String addresslineOne) {
		this.addresslineOne = addresslineOne;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public boolean isShipping() {
		return shipping;
	}
	public void setShipping(boolean shipping) {
		this.shipping = shipping;
	}
	public boolean isBilling() {
		return billing;
	}
	public void setBilling(boolean billing) {
		this.billing = billing;
	}
	public String getAddresslineTwo() {
		return addresslineTwo;
	}
	public void setAddresslineTwo(String addresslineTwo) {
		this.addresslineTwo = addresslineTwo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", user=" + user + ", addresslineOne=" + addresslineOne + ", addresslineTwo="
				+ addresslineTwo + ", city=" + city + ", state=" + state + ", country=" + country + ", postalCode="
				+ postalCode + ", shipping=" + shipping + ", billing=" + billing + "]";
	}
	
}
