package com.retail.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.retail.inventory.model.Product;
import com.retail.inventory.services.ProductServicesImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing product-related operations.
 * This controller provides end points to create, retrieve, search, and sort product data.
 * 
 * It interacts with ProductServicesImpl class to perform business logic.
 *
 * Available End points:
 * 
 * POST /products/ : Add a new product.
 * GET /products/ : Retrieve all products.
 * GET /products/{idOrName} : Retrieve products by ID or name.
 * GET /products/search?name=xyz&category=abc : Search products by name and/or category.
 * GET /products/sorted?sortBy=price|name|category : Retrieve products sorted by a given attribute.
 * 
 * @author KrishnaMohapatra
 * @version 1.0
 * @since 2025-03-28
 * 
 */


@RestController
@RequestMapping("/products")
public class ProductManagementController {

	@Autowired
	private ProductServicesImpl productServicesImpl;

	/**
	 * Creating a mapping that saves a new product 
	 *
	 * @param product : The product details received in the request body.
	 * @return The saved product.
	 */
	@PostMapping("/")
	public Product addProduct(@RequestBody Product product) {
		return productServicesImpl.addProduct(product);
	}

	/**
	 * Creating a mapping that retrieves all available products.
	 *
	 * @return List of all products.
	 */
	@GetMapping("/")
	public List<Product> getAllProducts() {
		List<Product> products = productServicesImpl.getAllproducts();
		return products;
	}

	/**
	 * Creating a mapping to fetch a product by either ID or name.
	 *
	 * @param idOrName The product ID or name.
	 * @return List of matching products.
	 */
	@GetMapping("/{idOrName}")
	public List<Product> getProductsByIdOrName(@PathVariable String idOrName) {
		return productServicesImpl.getProductsByIdOrName(idOrName);
	}

	/**
	 * Creating a mapping to retrieve products based on name and/or category.
	 *
	 * @param name The name of the product (optional).
	 * @param category The category of the product (optional).
	 * @return List of matching products, or all products if no parameters are provided.
	 */
	@GetMapping("/search")
	public List<Product> searchProducts(@RequestParam(required = false) String name,
			@RequestParam(required = false) String category) {
		if (name != null && category != null) {
			return productServicesImpl.getProductsByNameAndCategory(name, category);
		} else if (name != null) {
			return productServicesImpl.getProductsByName(name);
		} else if (category != null) {
			return productServicesImpl.getProductsByCategory(category);
		} else {
			return productServicesImpl.getAllproducts();
		}
	}	

	/**
	 * Retrieves products sorted by price, name, or category.
	 *
	 * @param sortBy The attribute to sort by (default: "name").
	 *        Accepts "price", "name", or "category".
	 * @return Sorted list of products.
	 */
	@GetMapping("/sorted")
	public List<Product> getSortedProducts(@RequestParam(required = false, defaultValue = "name") String sortBy) {
		return productServicesImpl.getSortedProducts(sortBy);
	}

}
