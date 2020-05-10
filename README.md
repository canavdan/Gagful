# Gagful

Gagful is a very simple and clean-design system implemented with Spring Boot.A simple website like 9gag

![Spring Boot 2.0](https://img.shields.io/badge/Spring%20Boot-2.0-brightgreen.svg)
![Mysql 5.6](https://img.shields.io/badge/Mysql-5.6-blue.svg)
![JDK 1.8](https://img.shields.io/badge/JDK-1.8-brightgreen.svg)
![Maven](https://img.shields.io/badge/Maven-3.5.0-yellowgreen.svg)

## Analysis of the project
[![Build Status](https://travis-ci.com/canavdan/Gagful.svg?branch=master)](https://travis-ci.com/canavdan/Gagful)
[![docker build](https://img.shields.io/docker/cloud/build/canavdan/gagful-api)](https://cloud.docker.com/u/canavdan/repository/docker/canavdan/gagful-api)

Gagful is powered by many powerful frameworks and third-party projects:

-  Spring Boot and many of Spring family (e.g. Spring MVC, Spring JPA, Spring Security and etc)
-  MySQL
- [HikariCP](https://github.com/brettwooldridge/HikariCP) - A solid high-performance JDBC connection pool
- [Redis](http://redis.io/) - A very powerful in-memory data cache server.

## Getting Started

## Development

**How to import the project into Intellij IDEA and run from the IDE?**

```
git clone https://github.com/canavdan/Gagful.git 
cd Gagful
```

Before development, please install the following service software:

- [MySQL](https://www.mysql.com)


1. Clone the project
2. Download all dependencies
3. Open the project in Intellij IDEA.
4. Choose local profile.
5. Run as Java application.
6. Preview: http://localhost:8080/login, the default admin account is: `admin`, password: `admin`
7. Then you can access other endpoints with JWT Token.

## Development with Docker

```
git clone https://github.com/canavdan/Gagful.git 
cd Gagful


docker-compose -f docker-compose up -d
```

Preview: http://localhost:8080/login, the default admin account is: `admin`, password: `admin`
Then you can access other endpoints with JWT Token.


## TODO
- [ ] Implement frontend.
- [ ] Change some endpoint to Webflux
- [ ] Frontend building tools, e.g. webpack
- [ ] Write unit test
- [ ] Changing architecture as Microservice
- [ ] Implement ELK
- [ ] Implement SonarQube
