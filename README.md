# fundtransfer
Fund Tansfer

# HOW TO USE THE APP
The application has 4 main features
- CLIENTS
- ACCOUNTS
- TRANSACTIONS
- TRANSFERS

To do anything we need first to create a client, each client can have unlimited number of accounts.
Each account belongs to one client
1 - Create a client 
2 - Ccreate an account
3 - Make a Doposit on the client account
4 - You can now make any transactions 

* The application has been developed using Spring Boot and PostgreSQL, you can us any
* other database of your choice by changing the driver in the Pom and properties files

Many other features can be added or improve

You can use this link after starting the app to see the end poinds definitions
http://localhost:8080/swagger-ui/index.html

For test purpose the setting **spring.jpa.hibernate.ddl-auto** is set to **update**
in a real environnment we can use liquibase or flyway and set that setting to **none**
