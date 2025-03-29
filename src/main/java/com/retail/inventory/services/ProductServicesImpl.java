package com.retail.inventory.services;

import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.inventory.model.Product;
import com.retail.inventory.repository.ProductRepository;

/**
 * Implementation of Product Services.
 * 
 * This service provides various operations for managing products, 
 * including adding, retrieving, searching, and sorting products by different criteria.
 * 
 * @author KrishnaMohapatra
 * @version 1.0
 * @since 2025-03-28
 * 
 */

@Service
public class ProductServicesImpl {

	@Autowired
	private ProductRepository productRepository;

	/**
	 * Adds a new product to the repository.
	 * @param product to be added.
	 * @return The saved product.
	 */
	public Product addProduct(Product product) {
		return productRepository.saveProduct(product);
	}

	/**
	 * Retrieves all products from the repository.
	 * @return a list of products.
	 */
	public List<Product> getAllproducts(){
		return productRepository.getAllProducts();
	}

	/**
	 * Retrieves a product by its unique ID.
	 * @param id: unique id of the product.
	 * @return The product with the specified ID.
	 */
	public Product getProductById(String id) {
		return productRepository.getProductById(id);
	}

	/**
	 * Retrieves products by their name.
	 * @param name : name of the product
	 * @return A list of products matching the given name.
	 */
	public List<Product> getProductsByName(String name) {
		return productRepository.getProductsByName(name);
	}

	/**
	 * Retrieves products by either ID or name.
	 * @param idOrName : The ID (if numeric) or name of the product.
	 * @return A list of products matching the given ID or name.
	 */
	public List<Product> getProductsByIdOrName(String idOrName) {
		return productRepository.getProductsByIdOrName(idOrName);
	}

	/**
	 * Retrieves products by their category.
	 * @param category of the product
	 * @return A list of products in the given category.
	 */
	public List<Product> getProductsByCategory(String category) {
		return productRepository.getProductsByCategory(category);
	}

	/**
	 * Retrieves products by both name and category.
	 * @param name of the product.
	 * @param category of the product.
	 * @return A list of products that match both name and category.
	 */
	public List<Product> getProductsByNameAndCategory(String name, String category) {
		return productRepository.getProductsByNameAndCategory(name, category);
	}

	/**
	 * Retrieves a list of products sorted by the specified criteria.
	 * Sorting can be done by price, category, or name.
	 * 
	 * @param sortBy : The sorting criteria ("price", "category", "name").
	 * @return A sorted list of products.
	 */
	public List<Product> getSortedProducts(String sortBy) {
		List<Product> products = productRepository.getAllProducts();

		switch (sortBy.toLowerCase()) {
		case "price":
			products.sort(Comparator.comparing(Product::getPrice, Comparator.naturalOrder()));
			break;
		case "category":
			products.sort(Comparator.comparing(Product::getCategory, Comparator.nullsLast(Comparator.naturalOrder())));
			break;
		case "name":
		default:
			products.sort(Comparator.comparing(Product::getName, Comparator.nullsLast(Comparator.naturalOrder())));
			break;
		}
		return products;
	}

}
