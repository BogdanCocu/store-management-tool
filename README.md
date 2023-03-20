# store-management-tool
This is a simple Java / Maven / Spring Boot application used to implement different services on store products. Uses an in-memory database (H2) to store data and Spring Boot Starter Data JPA.
Project created with https://start.spring.io/.

# About
This application provides a simple store-management solution using REST services.
Can be run locally on http://localhost:8080. Current endpoints:
* /product/addProduct - add a product
* /product/changePrice/{appId} - change price of a product
* /product/getProducts - retrieve all products
* /product/getProductByAppId/{appId} - find product by appId
* /product/searchProducts/{key} - search products on productName and description

Currently, covers ProductService.java class with tests. Handles exceptions on adding products  and price change, displays logs using slf4j-api.

# How to install
* Clone this repository
* Make sure you have Java and Maven installed and configured on your computer
* Set up Run Configuration Application, add main class

On successful run, console displays the following:
```
2023-03-20T20:38:52.373+02:00  INFO 12996 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-03-20T20:38:52.387+02:00  INFO 12996 --- [           main] r.i.s.StoreManagementToolApplication     : Started StoreManagementToolApplication in 6.467 seconds (process running for 7.219)
```