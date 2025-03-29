package com.retail.inventory.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.retail.inventory.model.Product;

/**
 * Repository class for managing product data operations.
 * 
 * This class interacts with the database using JDBC and provides various methods 
 * to retrieve, search, and save product information.
 * 
 * @author KrishnaMohapatra
 * @version 1.0
 * @since 2025-03-28
 * 
 */

@Repository
public class ProductRepository {
	private final JdbcTemplate jdbc;

	/**
	 * Constructs a ProductRepository with a {@link JdbcTemplate} for database access.
	 * 
	 * @param jdbc : The JdbcTemplate instance for executing SQL queries.
	 */
	public ProductRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	/**
	 * Retrieves all products from the database.
	 * @return A list of all products.
	 */
	public List<Product> getAllProducts() {
		String sql = "SELECT * FROM product";
		return jdbc.query(sql, this::mapRowToProduct);
	}

	/**
	 * Retrieves a product by its unique ID.
	 *
	 * @param id The unique product ID.
	 * @return The product matching the given ID.
	 */
	public Product getProductById(String id) {
		String sql = "SELECT * FROM product WHERE id = ?";
		return jdbc.queryForObject(sql, this::mapRowToProduct, id);
	}

	/**
	 * Retrieves products by name, allowing partial matches using LIKE.
	 *
	 * @param name The name (or partial name) of the product.
	 * @return A list of products matching the given name.
	 */
	public List<Product> getProductsByName(String name) {
		String sql = "SELECT * FROM product WHERE LOWER(name) LIKE LOWER(?)";
		return jdbc.query(sql, this::mapRowToProduct, "%" + name + "%");
	}

	/**
	 * Retrieves products by either ID or name using method overloading.
	 *
	 * If the input is numeric, it searches by ID; otherwise, it searches by name.
	 *
	 * @param idOrName The ID or name of the product.
	 * @return A list of products matching the given ID or name.
	 */
	public List<Product> getProductsByIdOrName(String idOrName) {
		if (idOrName.matches("\\d+")) { // If input is numeric, search by ID
			return List.of(getProductById(idOrName));
		} else { // Otherwise, search by Name
			return getProductsByName(idOrName);
		}
	}

	/**
	 * Retrieves products by both name and category using method overloading.
	 *
	 * @param name The name of the product.
	 * @param category The category of the product.
	 * @return A list of products that match both name and category.
	 */
	public List<Product> getProductsByNameAndCategory(String name, String category) {
		String sql = "SELECT * FROM product WHERE LOWER(name) LIKE LOWER(?) AND LOWER(category) = LOWER(?)";
		return jdbc.query(sql, this::mapRowToProduct, "%" + name + "%", category);
	}

	/**
	 * Retrieves products by their category.
	 *
	 * @param category The category of the product.
	 * @return A list of products in the given category.
	 */
	public List<Product> getProductsByCategory(String category) {
		String sql = "SELECT * FROM product WHERE LOWER(category) = LOWER(?)";
		return jdbc.query(sql, this::mapRowToProduct, category);
	}

	/**
	 * Saves a new product to the database.
	 *
	 * @param product The product to be added.
	 * @return The saved product.
	 */
	public Product saveProduct(Product product) {
		String sql = "INSERT INTO product (id, name, category, price, stock_Quantity) VALUES (?, ?, ?, ?, ?)";
		jdbc.update(sql, product.getId(), product.getName(), product.getCategory(), product.getPrice(), product.getStockQuantity());
		return product;
	}

	private Product mapRowToProduct(ResultSet rs, int rowNum) throws SQLException {
		return new Product(
				rs.getString("id"),
				rs.getString("name"),
				rs.getString("category"),
				rs.getBigDecimal("price"),
				rs.getInt("stock_Quantity")
				);
	}

}
