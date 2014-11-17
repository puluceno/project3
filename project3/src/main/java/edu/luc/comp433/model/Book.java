/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.luc.comp433.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
		@NamedQuery(name = Book.LIST_ALL_BOOKS, query = "SELECT b FROM Book b"),
		@NamedQuery(name = Book.FIND_BY_ID, query = "SELECT b FROM Book b WHERE b.id = :id"),
		@NamedQuery(name = Book.FIND_BY_IDS, query = "SELECT b FROM Book b WHERE b.id in :idsList"),
		@NamedQuery(name = Book.FIND_BY_TITLE, query = "SELECT b FROM Book b WHERE LOWER(b.title) like :title"),
		@NamedQuery(name = Book.FIND_BY_AUTHOR, query = "SELECT b FROM Book b WHERE LOWER(b.author) like :author"),
		@NamedQuery(name = Book.FIND_BY_PRICE, query = "SELECT b FROM Book b WHERE b.price >= :minPrice and b.price <= :maxPrice") })
public class Book implements BaseEntity<Short> {

	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_ID = "Book.findById";
	public static final String FIND_BY_IDS = "Book.findByIds";
	public static final String FIND_BY_AUTHOR = "Book.findByAuthor";
	public static final String FIND_BY_TITLE = "Book.findByTitle";
	public static final String FIND_BY_PRICE = "Book.findByPrice";
	public static final String LIST_ALL_BOOKS = "LIST_ALL_BOOKS";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Short id;
	@Basic(optional = false)
	private String title;
	@Basic(optional = false)
	private String author;
	@Max(value = 9999)
	@Min(value = 0)
	@Basic(optional = false)
	private BigDecimal price;
	@JoinTable(schema = "bookstore", name = "Order_has_Book", joinColumns = { @JoinColumn(name = "book", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "order_", referencedColumnName = "id") })
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Order> orderList = new ArrayList<Order>();

	public Book() {
	}

	public Book(Short id) {
		this.id = id;
	}

	public Book(Short id, String title, String author, BigDecimal price) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
	}

	@Override
	public Short getId() {
		return id;
	}

	@Override
	public void setId(Short id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@JsonIgnore
	@XmlTransient
	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((orderList == null) ? 0 : orderList.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "model.Book[ id=" + id + " ]";
	}

}
