package com.retail.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Inventory Management System Application
 * 
 * This is the main entry point of the Inventory Management System application.
 * It bootstraps the Spring Boot application, enabling component scanning 
 * and JPA repository support for managing product inventory.
 * 
 * @author KrishnaMohapatra
 * @version 1.0
 * @since 2025-03-28
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.retail.inventory")
@EnableJpaRepositories("com.retail.inventory.repository")
public class InventoryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementSystemApplication.class, args);
	}

}
