# Microservices

###Spring Boot, Spring Cloud, Docker and Kubernetes ###

A learning project to create RESTful services and microservices with spring boot. Planning to add
few microservices to support few more functionalities and to support real time 
currency exchange rates.

__Tech stack:__
* Spring boot
* Spring cloud
* Spring Data JPA
* Docker
* Kubernetes

__Spring Cloud Services__
* Eureka
* Spring cloud API gateway
* Feign client
* Sleuth, zipkin and rabbitMQ
* Resilience4j
* Circuit breaker
* Rate limiter

__Description__
<br>
There are two microservices *CurrencyExchangeService* and *CurrencyConversionService* which are designed to store international currency exchange rates 
in a database. It can be exposed as an API such that the downstream services can access and retrieve the current exchange rates.  

__Currency Exchange Service__  
http://localhost:8000/currency-exchange/USD/to/INR   
This API retrieves the data from the database and exposes it to the consumer.
* Registers itself with Eureka server
* Can be accessed through spring cloud API gateway
* Manual SQL statements in *data.sql* file will insert or create data in the dB on app start.
* Docker image  
<code>docker pull bmanojprabhakar/microservices_currency_exchange_service:0.0.3</code>  

__Currency Conversion Service__  
http://localhost:8100/currency-conversion/USD/to/INR/10  
It consumes the service exposed by *CurrencyExchangeService* and does a manipulation based on the quantity passed in URL params which is returned as response.  
* Registers itself with Eureka server
* Can be accessed through spring cloud API gateway
* Utilizes Feign client to interact with producer
* Docker image  
<code>docker pull bmanojprabhakar/currency_conversion_service:0.0.2</code>

__Naming Server__
Port number - 8761
* Eureka naming server for service discovery
* Disabled self registering

__ApiGateway__
Port number - 8765
* Added configuration to re-route the path
* Added logging filter

__Docker__
* Code source contains docker-compose.yml file
* Docker image has been created with maven plugin, hence no DockerFile is written.

__Kubernetes__  
* Validated with GKE on Google Cloud Platform.
* Utilized inbuilt log tracing and service discovery stack driver APIs

__Setup__
* It requires zipkin and RabbitMQ to be installed on local machines or to expose a docker image on local environment
of respective tools which can be obtained from -   
<code>docker pull openzipkin/zipkin</code><br>
<code>docker pull rabbitmq</code>
* JDK 1.8.0 or above, Maven 
* Next, all the project files can be imported as maven projects and executed

----------
