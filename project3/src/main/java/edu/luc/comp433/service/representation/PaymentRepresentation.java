/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.luc.comp433.service.representation;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

import edu.luc.comp433.model.enumerator.PaymentType;

/**
 *
 * @author Bruno Correa <brunogmc at gmail>
 */
@XmlRootElement
public class PaymentRepresentation {

	private Short id;
	private PaymentType type;
	private BigDecimal amount;
	private CustomerRepresentation customerRepresentation;
	private String cardNumber;
	private String cardHolderName;
	private int expirationMonth;
	private int expirationYear;
	private int securityCode;

	public PaymentRepresentation() {
	}

	public PaymentRepresentation(Short id) {
		this.id = id;
	}

	public PaymentRepresentation(Short id, PaymentType type, BigDecimal amount) {
		this.id = id;
		this.type = type;
		this.amount = amount;
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@JsonIgnore
	public CustomerRepresentation getCustomer() {
		return customerRepresentation;
	}

	public void setCustomer(CustomerRepresentation customerRepresentation) {
		this.customerRepresentation = customerRepresentation;
	}

	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber
	 *            the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return the cardHolderName
	 */
	public String getCardHolderName() {
		return cardHolderName;
	}

	/**
	 * @param cardHolderName
	 *            the cardHolderName to set
	 */
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	/**
	 * @return the expirationMonth
	 */
	public int getExpirationMonth() {
		return expirationMonth;
	}

	/**
	 * @param expirationMonth
	 *            the expirationMonth to set
	 */
	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	/**
	 * @return the expirationYear
	 */
	public int getExpirationYear() {
		return expirationYear;
	}

	/**
	 * @param expirationYear
	 *            the expirationYear to set
	 */
	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}

	/**
	 * @return the securityCode
	 */
	public int getSecurityCode() {
		return securityCode;
	}

	/**
	 * @param securityCode
	 *            the securityCode to set
	 */
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result
				+ ((customerRepresentation == null) ? 0 : customerRepresentation.hashCode());
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
		PaymentRepresentation other = (PaymentRepresentation) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (customerRepresentation == null) {
			if (other.customerRepresentation != null)
				return false;
		} else if (!customerRepresentation.equals(other.customerRepresentation))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "representation.Payment[ id=" + id + " ]";
	}

}
