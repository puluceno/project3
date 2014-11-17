/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.luc.comp433.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Thiago Vieira Puluceno
 */
@Entity
@Table(schema = "bookstore")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Customer.findAll", query = "SELECT u FROM Customer u"),
		@NamedQuery(name = "Customer.findById", query = "SELECT u FROM Customer u WHERE u.id = :id"),
		@NamedQuery(name = "Customer.findByLogin", query = "SELECT u FROM Customer u WHERE u.login = :login"),
		@NamedQuery(name = "Customer.findByName", query = "SELECT u FROM Customer u WHERE u.name = :name") })
public class Customer implements BaseEntity<Short> {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Short id;
	@Basic(optional = false)
	private String login;
	@Basic(optional = false)
	private String password;
	@Basic(optional = false)
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY)
	private List<Address> addressList = new ArrayList<Address>();
	@OneToMany(cascade = CascadeType.REFRESH, mappedBy = "customer", fetch = FetchType.LAZY)
	private List<Order> orderList = new ArrayList<Order>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY)
	private List<Payment> paymentList = new ArrayList<Payment>();

	public Customer() {
	}

	public Customer(Short id) {
		this.id = id;
	}

	public Customer(Short id, String login, String password, String name) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.name = name;
	}

	@Override
	public Short getId() {
		return id;
	}

	@Override
	public void setId(Short id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	@XmlTransient
	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddress(List<Address> addressList) {
		this.addressList = addressList;
	}

	@JsonIgnore
	@XmlTransient
	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	@JsonIgnore
	@XmlTransient
	public List<Payment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addressList == null) ? 0 : addressList.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((orderList == null) ? 0 : orderList.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((paymentList == null) ? 0 : paymentList.hashCode());
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
		Customer other = (Customer) obj;
		if (addressList == null) {
			if (other.addressList != null)
				return false;
		} else if (!addressList.equals(other.addressList))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orderList == null) {
			if (other.orderList != null)
				return false;
		} else if (!orderList.equals(other.orderList))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (paymentList == null) {
			if (other.paymentList != null)
				return false;
		} else if (!paymentList.equals(other.paymentList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "model.Customer[ id=" + id + " ]";
	}

}
