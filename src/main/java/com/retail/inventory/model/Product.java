package com.retail.inventory.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

/**
 * Represents a product entity.
 *
 * This class is mapped to a database table using the SINGLE_TABLE 
 * inheritance strategy, allowing different product types to be 
 * stored in a single table with a discriminatory column.
 *
 * Each product has attributes such as ID, name, category, price, 
 * and stock quantity.
 *
 * @author KrishnaMohapatra
 * @version 1.0
 * @since 2025-03-28
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
public class Product {
	@Id
	@Column
	private String id;
	@Column
	private String name;
	@Column
	private String category;
	@Column
	private BigDecimal price;
	@Column(name = "stock_quantity")
	@JsonProperty("stockQuantity")
	private int stockQuantity;
	
	public Product() {}
	
	public Product(String id, String name, String category, BigDecimal price, int stockQuantity) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

}
