package edu.luc.comp433.service.representation;

import javax.xml.bind.annotation.XmlRootElement;

import edu.luc.comp433.model.enumerator.OrderStatus;

/**
 *
 * @author Bruno Correa <brunogmc at gmail>
 */
@XmlRootElement
public class OrderRepresentation {

	private Short id;
	private OrderStatus status;
	private CustomerRepresentation customerRepresentation;
	private PaymentRepresentation paymentRepresentation;
	private AddressRepresentation addressRepresentation;

	public OrderRepresentation() {
	}

	public OrderRepresentation(Short id) {
		this.id = id;
	}

	public OrderRepresentation(Short id, OrderStatus status) {
		this.id = id;
		this.status = status;
	}

	public OrderRepresentation(CustomerRepresentation customerRepresentation, AddressRepresentation addressRepresentation,
			PaymentRepresentation paymentRepresentation) {
		this.customerRepresentation = customerRepresentation;
		this.addressRepresentation = addressRepresentation;
		this.paymentRepresentation = paymentRepresentation;
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public CustomerRepresentation getCustomer() {
		return customerRepresentation;
	}

	public void setCustomer(CustomerRepresentation customerRepresentation) {
		this.customerRepresentation = customerRepresentation;
	}

	public PaymentRepresentation getPayment() {
		return paymentRepresentation;
	}

	public void setPayment(PaymentRepresentation paymentRepresentation) {
		this.paymentRepresentation = paymentRepresentation;
	}

	public AddressRepresentation getAddress() {
		return addressRepresentation;
	}

	public void setAddress(AddressRepresentation addressRepresentation) {
		this.addressRepresentation = addressRepresentation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((paymentRepresentation == null) ? 0 : paymentRepresentation.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		OrderRepresentation other = (OrderRepresentation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (paymentRepresentation == null) {
			if (other.paymentRepresentation != null)
				return false;
		} else if (!paymentRepresentation.equals(other.paymentRepresentation))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
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
		return "representation.Order[ id=" + id + " ]";
	}

}
