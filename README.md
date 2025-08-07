# 🚗 Gallerist Service - Spring Boot

This project is a car gallery management REST API built with Spring Boot. Users can add, update, and delete operations. It also includes JWT-based authentication and role-based access control (admin/user/gallerist).

---

## 📌 Features

- ✅ User registration and login (JWT-based)
- ✅ Role-based access: Admin & Customer & Gallerist
- ✅ CRUD operations
- ✅ RESTful architecture

---

## 🧰 Technologies Used

- Java 17
- Spring Boot
- Spring Security & JWT
- Spring Data JPA (Hibernate)
- MySQL
- Lombok
- Maven

---

## ⚙️ Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/car-gallery-service.git
   cd car-gallery-service

2. Build the project and install dependencies:
  mvn clean install

3. Create your own application.properties file in src/main/resources:
  spring.datasource.url=jdbc:mysql://localhost:3306/car_gallery
  spring.datasource.username=root
  spring.datasource.password=yourpassword
  jwt.secret=your-jwt-secret

4.Run the application:
  mvn spring-boot:run
  


🔐 Authentication (JWT)
After login, you'll receive a JWT token. To access secured endpoints, send it in the request header like this:
Authorization: Bearer <your-token>





