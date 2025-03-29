# Inventory Management System
This is a Spring Boot-based project for managing an inventory system. It provides APIs for managing products including adding, retrieving and sorting products.

## Import the Project from GitHub
To import this project into your local machine, follow these steps:

## Clone the repository:
Open your terminal and run the following command to clone the repository from GitHub:
git clone https://github.com/km90000/InventoryManagementSystem.git

## Set Up the Project
Prerequisites:
- Java 17 or above is required.
- Maven should be installed on your machine
Import into an IDE

## Build the Project:
Navigate to the project directory in the terminal:
cd InventoryManagementSystem

Run the following Maven command to build the project and resolve dependencies, run the following command in the terminal:
mvn clean install

Using Maven in your IDE:
Build the project using the IDE's built-in Maven tool. Look for the Maven panel and click on Install or Clean & Build.

## Run the Project
To run the project locally, locate the InventoryManagementSystemApplication.java file from your IDE, Right-click the class and choose Run.

To check the h2 in memory database, navigate to the following:
http://localhost:8080/h2-console/

## Test the Project:
To test the use cases use the following endpoints in Postman:

1. Add new products to the system:
   POST: http://localhost:8080/products/
   
   Sample data:
   {
    "id": 1001,
    "name": "TV",
    "category": "Electronics",
    "price": 1001,
    "stockQuantity": 100
    }

3. Retrieve all products:
   GET: http://localhost:8080/products/

4. Retrieve a product by name or ID:
   GET: http://localhost:8080/products/{id}
   GET: http://localhost:8080/products/{name}

5. List all products sorted by price, name, or category:
   GET: http://localhost:8080/products/sorted?sortBy={price}
   GET: http://localhost:8080/products/sorted?sortBy={category}
   GET: http://localhost:8080/products/sorted?sortBy={name}
   GET: http://localhost:8080/products/sorted (default by name)

6. Categorize products into Electronics, Grocery, and Clothing:
   GET: http://localhost:8080/products/search?category={category}


