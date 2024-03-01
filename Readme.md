# DataWareHouse Simulator

In this project I created a Datawarehouse Microservice, in which it's connected to mysql server, in order to save fx Deals in database table.
I used Spring Boot to implement my microservices, Additionally I used these Libraries within SpringBoot:
* Spring Cloud / Eureka Server
  Used to register microservices to allow loadbalancing feature.
* Spring Data Validation
  Used to validate FxDeals data to prevent broken data. 
* Spring Data JPA
  Used to establish database connection and execute queries through hibernate implementation
