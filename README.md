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

Currently, covers ProductService.java class with tests. Handles exceptions on adding products  and price change, displays logs using slf4j-api. Tested with Postman.

# How to install
* Clone this repository
* Make sure you have Java and Maven installed and configured on your computer
* Set up Run Configuration with Maven, add spring-boot:run on Run command

# Security 
Added Spring Security with configuration blocking requests for unauthenticated users (401 Unauthorized).
Added two in-memory users:
* name: user | password: user | role: USER (**Basic YWRtaW46YWRtaW4=**)
* name: admin | password: admin | role: ADMIN (**Basic dXNlcjp1c2Vy**)

To authorize requests you need to add the Authorization header to the request, with the value from above (Basic ...)*. 
These represent the Base64 encoding of the user credentials: *user:user* and *admin:admin*.
The following endpoints require ADMIN Role, otherwise they will return 403 Forbidden:
* /product/addProduct 
* /product/changePrice/{appId}
