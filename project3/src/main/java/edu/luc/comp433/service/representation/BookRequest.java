/**
 * 
 */
package edu.luc.comp433.service.representation;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
@XmlRootElement
public class BookRequest {

	private Short id;
	private String title;
	private String author;
	private BigDecimal price;

	public BookRequest() {
	}

	public BookRequest(Short id) {
		this.id = id;
	}

	public BookRequest(Short id, String title, String author,
			BigDecimal price) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public Short getId() {
		return id;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		BookRequest other = (BookRequest) obj;
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
		return "request.Book[ id=" + id + " ]";
	}

}
