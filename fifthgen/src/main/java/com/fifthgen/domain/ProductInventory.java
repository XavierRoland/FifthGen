package com.fifthgen.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Roland
 *
 */

@Entity
@Table(name = "product_inventory")
public class ProductInventory {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "price_updated")
	private int priceUpdated;

	@Column(name = "quantity_added")
	private int quantityAdded;

	@Column(name = "created_date")
	private Date createdDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getPriceUpdated() {
		return priceUpdated;
	}

	public void setPriceUpdated(int priceUpdated) {
		this.priceUpdated = priceUpdated;
	}

	public int getQuantityAdded() {
		return quantityAdded;
	}

	public void setQuantityAdded(int quantityAdded) {
		this.quantityAdded = quantityAdded;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
