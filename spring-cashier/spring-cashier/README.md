# 1.0 Code Changes  & Discussion 

# Explain (with code snippets from sample code) how the Web UI is able to "remember" the selected Store. 

Capture 1

In the Command class, there is a stores field that represents the selected store. When a user selects a store from the dropdown menu, the value of this field is set in the postAction method of the SpringCashierController class.

Capture 2

In the Get Order block of this method, the selected store is retrieved from the Command object, and is stored in the session with the name "store".

Capture

In this method, the "store" attribute is retrieved from the session using the getAttribute method. If the attribute exists, it is then used to set the value of the Command object's stores field

# Explain (with code snippets from your code) how you implemented storing the Order in MySQL.

To implement storing the Order in MySQL, I used Spring Data JPA and Hibernate. I first created an CashierOrder class with the necesarry fields to map it to a database.

Capture 4

Next, I created a CashierOrderRepository interface for basic functionalities, and injected it into the CashierOrderService Class. I ran into issues here, because I used the classname "Order", which is a reserved keyword in SQL.

Capture 5 & 6

# Explain (with code snippets from your code) how you support generating a different "random" order with each "Place Order" request (instead of the starter code's "hard coded" order)

To generate a random order with each "Place Order" request, I editted the GetNewOrder() method in the CashierOrder class. The order elements are chosen randomly and the price is determined via a hashmap.

Capture 7 & 8

# Explain (with code snippets from your code) how you added support for Spring Security / Login Page

To add support for Spring Security and Login Page, I first added the spring-boot-starter-security dependency to the pom.xml.

capture 10

The username is "user" and the password is "password". The password is hashed using the BCryptPasswordEncoder, which is a bean in the configuration class.

capture 11

To make it work behind a load balancer without sticky sessions, I tried to use Spring's HttpSession object to remember which store an order is from.

capture 12

# Did you make any System Architecture Changes to support the requirements?

Remapped port 80 to 8081 due to gitPod constraints.

# 2.0 Successful Deployment 

Dockerfile:

dockerfile

Docker-Compose:

dc

Application.properties:

ap

Screenshots for Successful Deployment:

13,14,15


# 3.0 Test Results / Demonstration

demo pics

