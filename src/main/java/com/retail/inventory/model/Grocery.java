package com.retail.inventory.model;

import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Represents a grocery product in the system.
 *
 * This class extends the Product class and is 
 * identified as a "Grocery" type in the SINGLE_TABLE inheritance strategy 
 * using a discriminatory value.
 *
 * @author KrishnaMohapatra
 * @version 1.0
 * @since 2025-03-28
 */

@Entity
@DiscriminatorValue("Grocery")
public class Grocery extends Product{

	public Grocery(String id, String name, String category, BigDecimal price, int stockQuantity) {
		super(id, name, category, price, stockQuantity);
	}

}
