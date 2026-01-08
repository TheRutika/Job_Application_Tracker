# 🚀 Job Application Tracker – Spring Boot Backend

A **Job Application Tracker** backend application built using **Spring Boot** that helps users manage job applications securely with authentication, role-based authorization, and clean REST APIs.

This project demonstrates **real-world backend development practices** such as JWT authentication, centralized exception handling, validation, and API documentation using Swagger.

---

## 📌 Key Highlights

- Production-style backend architecture  
- Secure authentication using JWT  
- Role-Based Access Control (USER / ADMIN)  
- Clean REST API design  
- Centralized exception handling  
- Environment-based configuration (no hardcoded secrets)  

---

## 🛠️ Tech Stack

| Category | Technology |
|-------|-----------|
| Language | Java 23 |
| Framework | Spring Boot 3 |
| Security | Spring Security + JWT |
| Database | MySQL |
| ORM | Spring Data JPA, Hibernate |
| API Docs | Swagger / OpenAPI |
| Build Tool | Maven |

---

## ✨ Features

- 🔐 JWT-based authentication
- 👤 Role-based authorization (USER / ADMIN)
- 📄 Job application management (CRUD)
- 🕒 Status history tracking
- 🛡 Global exception handling
- ✅ Request validation
- 📘 Swagger API documentation
- 🔒 Secure configuration using environment variables

---

## 📂 Project Structure

src/main/java/com/rutika/job_application_tracker
├── controller # REST controllers
├── service # Business logic
├── repository # JPA repositories
├── entity # Database entities
├── dto # Request / Response DTOs
├── security # JWT & security config
├── exception # Custom exceptions & handler
└── config # Application configuration

yaml
Copy code

---

## 🔐 Authentication Flow (JWT)

1. User registers using `/auth/signup`
2. User logs in using `/auth/login`
3. Backend returns a **JWT token**
4. Client sends token with every request:
Authorization: Bearer <JWT_TOKEN>

yaml
Copy code
5. Access is granted based on user role

---

## 📘 API Documentation (Swagger)

After running the application, open:

http://localhost:8080/swagger-ui/index.html

yaml
Copy code

Swagger allows you to:
- View all APIs
- Test endpoints
- Authorize using JWT token

---

## ⚙️ Environment Variables

### 📄 `.env.example` (committed)

```env
DB_URL=jdbc:mysql://localhost:3306/Job_Application_Db
DB_USER=root
DB_PASS=your_password_here

JWT_SECRET=change_this_to_a_long_random_secret
🚫 .env (local only)
Create a .env file locally with real values.
This file is ignored using .gitignore.

▶️ How to Run the Project
1️⃣ Clone the repository
bash
Copy code
git clone https://github.com/TheRutika/Job_Application_Tracker.git
cd Job_Application_Tracker
2️⃣ Set environment variables
Using terminal:

bash
Copy code
export DB_URL=jdbc:mysql://localhost:3306/Job_Application_Db
export DB_USER=root
export DB_PASS=your_password
export JWT_SECRET=your_secret_key
Or using IDE Run Configuration.

3️⃣ Run the application
bash
Copy code
mvn spring-boot:run
🧪 Sample API Usage
🔹 User Signup
bash
Copy code
POST /auth/signup
json
Copy code
{
  "email": "user@gmail.com",
  "password": "password123",
  "role": "USER"
}
🔹 User Login
bash
Copy code
POST /auth/login
Response:

json
Copy code
{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}
🛡 Global Error Handling
All exceptions are handled centrally using @RestControllerAdvice.

Example error response:

json
Copy code
{
  "message": "User not found",
  "status": 404,
  "timestamp": "2026-01-08T10:30:45"
}
🧠 Learning Outcomes
Implemented stateless authentication using JWT

Designed secure REST APIs

Used DTOs for clean API contracts

Applied centralized exception handling

Configured environment-based secrets

Integrated Swagger for API documentation

Followed clean backend architecture principles

🚀 Future Enhancements
Frontend integration (React)

Pagination & filtering

Refresh token support

Docker & containerization

CI/CD pipeline

👩‍💻 Author
Rutika Patil
Backend Developer | Java | Spring Boot

🔗 GitHub: https://github.com/TheRutika
