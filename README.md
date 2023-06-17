

# Spring Boot application for User Management

> RestControllers

> Dao Layer as JPA

> H2 As Database

---

## Table of Contents


- [Installation](#installation)
- [Features](#features)
- [Usage](#usage)
- [Documentation](#documentation)
- [Tests](#tests)


---

## Example

```javascript
// code away!

package com.sevilla.usermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsermanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsermanagerApplication.class, args);
	}

}


```

---

## Installation

- Just Clone this repo
- Run mvn clean install

### Clone

- Clone this repo to your local machine using: `https://github.com/sevillacesar/usermanager`

### Setup

- Run mvn spring-boot:run
- Check Swagger using the following link:
http://localhost:8080/swagger-ui/index.html
---

## Features

- Signup new users
- Validate email format and exists
- Validate password format
- JWT Token
- List users
- Find user by id
- Find user by email
- Update user
- Delete user

## Usage

- You can use Postman or Swagger to test the software.

## Documentation

- This Readme
- Swagger
- Script database.sql
- Diagrama de solucion.docx

## Tests

- Signup
- List users
- Find by email
- Update
- Delete
---

