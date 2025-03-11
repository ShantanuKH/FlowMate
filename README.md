# 🚀 FlowMate - Java Spring Boot Backend

FlowMate is a robust backend application developed using **Java Spring Boot**, designed to provide a scalable and secure RESTful API for managing various backend functionalities. This project incorporates **Spring Boot, Spring Data JPA, MySQL, Spring Security, JWT Authentication, caching, and logging** to enhance efficiency, security, and performance.

## 🌟 Features

### 1. 🔗 RESTful API Development
- Built using **Spring Boot** to ensure modularity and ease of development.
- Provides CRUD (Create, Read, Update, Delete) operations for managing application data.
- Well-structured API endpoints following RESTful conventions.

### 2. 🗄️ Spring Data JPA & MySQL Database
- Uses **Spring Data JPA** for seamless interaction with the database.
- MySQL is used as the relational database for structured and persistent storage.
- Implements repository pattern to manage database operations efficiently.

### 3. 🔐 Authentication & Security
- Implements **Spring Security** for authentication and authorization.
- Uses **JWT (JSON Web Tokens)** for secure user authentication.
- Password encryption using **BCrypt** to ensure data security.
- Protects API endpoints with role-based access control (RBAC).

### 4. 🛠️ API Testing with Postman
- Fully tested API using **Postman** with well-defined request/response structures.
- Supports input validation and error handling for better API usability.

### 5. 🚀 Performance Enhancements
- **⚡ Caching:** Uses **Spring Cache** to improve response times for frequently requested data.
- **📜 Logging:** Implements structured logging with **SLF4J and Logback** for better debugging and monitoring.
- **❌ Exception Handling:** Centralized exception handling using `@ControllerAdvice` for consistent error responses.

## 🛠️ Technologies Used

- **☕ Java 17**  
- **🌱 Spring Boot 3.x**  
- **🗄️ Spring Data JPA**  
- **🐬 MySQL**  
- **🔐 Spring Security & JWT**  
- **🧪 Postman (for API testing)**  
- **✂️ Lombok (for reducing boilerplate code)**  
- **📖 Swagger (for API documentation)**  
- **🐳 Docker (for containerization - optional)**  

## 📥 Installation and Setup

### 🔧 Prerequisites
Ensure you have the following installed on your system:
- **☕ Java 17 or later**
- **🐘 Maven**
- **🐬 MySQL Database**
- **📮 Postman** (for testing APIs)

### ⚙️ Steps to Run the Project
1. **📂 Clone the Repository**
   ```bash
   git clone https://github.com/your-username/FlowMate.git
   cd FlowMate
   ```

2. **🔧 Configure Database**
   - Update the `application.properties` file with your MySQL database credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/flowmate_db
     spring.datasource.username=root
     spring.datasource.password=yourpassword
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **🏗️ Build and Run the Application**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **🌐 Access API Endpoints**
   - Default port: `http://localhost:8080`
   - API Documentation (if Swagger is enabled): `http://localhost:8080/swagger-ui/index.html`

## 🔥 API Endpoints

| 🛠 Method | 🌍 Endpoint | 📌 Description |
|--------|---------|-------------|
| `POST` | `/api/auth/register` | 📝 User Registration |
| `POST` | `/api/auth/login` | 🔑 User Login (returns JWT) |
| `GET` | `/api/users` | 👥 Fetch All Users (Admin Only) |
| `GET` | `/api/users/{id}` | 📋 Fetch User by ID |
| `PUT` | `/api/users/{id}` | 🔄 Update User Details |
| `DELETE` | `/api/users/{id}` | ❌ Delete User (Admin Only) |

## 🔐 Authentication Flow
1. **📝 User registers** via `/api/auth/register`.
2. **🔑 User logs in** via `/api/auth/login`, receiving a **JWT Token**.
3. The token is used in the **Authorization Header** (Bearer Token) for secured API requests.
4. Role-based access ensures **only authorized users** can access specific endpoints


## Contact Here


   ### Shantanu Khadse
  
  GitHub: [ShantanuKH](https://github.com/ShantanuKH)
  
  Email: shantanukhadse784@gmail.com  
  
  LinkedIn: [shantanukhadse](https://www.linkedin.com/in/shantanu-khadse-a62585230/)
  
