# Flight Search Application

Flight Search Application for travix homework, based on a simplified microservice architecture, docker containers.

### Architecture overview
![System Design](/documentation-resources/travix-homework-system-design.png)


### How To Run
Simply execute

> docker-compose up

and go to

> localhost:8080/swagger-ui.html

I pushed builded containers to my own docker hub account to prevent mvn build times etc.

### Screen Shots

### Tech Stack
* API : Java8, SpringBoot
* Mysql
* Swagger

### Adding A New Flight Supplier

![System Design](/documentation-resources/travix-homewprk-uml-diagram.PNG)

- I've applied "Adapter Pattern" and "Factory Method Pattern" to handle the differences of both input and output data. When a new supplier  will be added, below steps can be follwed.

>- Add a new value to the FlightSupplierTypes.
>- Add an adapter class for the new supplier.
>- Add an supplier class(adaptee) for get the flight data.
>- Add a convenient switch case to the FlightSupplierFactory .

- This design is support both single responsibility and open/close principles. There is only one reason for changing a class in this design. Also, it fits the opened improvements and closed the changes while adding new supplier.

### Discuss Topics
- Using redis? Because the flight data are dynamic, a cache mechanism cannot be used. For the future if a fronted is written, the cache mechanism can be used for airport codes/names. For example, the frontend can pull the airport codes/names from the cache. 

- Crazyair Api using JPQL? I thought that if there are two cabin class options for one flight, they will be two different flights in the response. So, I've preferred using JPQL to provide this data. 

### TODO
- Improve logging. Logging API, NoSql, Streaming etc.

- Add paging to reduce traffic size.

- Add rate limiter to protect system.

- Solve single point of failures.

- Absolutely increase test coverage.

- UI design.

- Adding load balancer, nginx etc.

- Design CI piplenes, code quality,sonar etc.

- Design CD pipelines, docker containers, kubernetes, Cloud deployment etc.
