# Spring boot user authentication Application 

Spring boot application to kick start any Spring API.
This application uses **JWT** for authentication of the user and MongoDB as database. Use this application for 
pre-configured User Management and authentication System.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development purposes. 

### Prerequisites

You will need these installed to run this application.

```
Java 12
gradle 4.9
Mongo DB
Postman or any other REST Client
```

### Installing

Run MongoDB if you have not started yet. Open terminal and enter

```
    mongod
```

In another terminal, open project root directory and enter

```
    gradle compileJava
```

After gradle completes installing all the dependencies, start application

```
    gradle bootRun
```

Application should be started in port 8080.

## Authors

* **Anewj Maharjan** - [anewj](https://github.com/anewj)


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details