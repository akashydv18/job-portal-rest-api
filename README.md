# 🏢 Job Portal REST API

A production-ready **Job Portal Backend** built with **Spring Boot**, featuring JWT Authentication, Role-Based Access Control, Global Exception Handling, Pagination, and Swagger UI Documentation.

---

## 🚀 Tech Stack

| Technology | Usage |
|---|---|
| Java 17 | Programming Language |
| Spring Boot 3.2.5 | Backend Framework |
| Spring Security | Authentication & Authorization |
| JWT (JSON Web Token) | Stateless Authentication |
| Spring Data JPA | Database ORM |
| Hibernate | ORM Implementation |
| MySQL | Relational Database |
| SpringDoc OpenAPI | Swagger UI Documentation |
| Maven | Build Tool |
| Postman | API Testing |

---

## ✨ Features

- ✅ **JWT Authentication** — Stateless login with token-based security
- ✅ **Role-Based Access Control** — ADMIN, COMPANY, JOB_SEEKER roles
- ✅ **Global Exception Handling** — Clean error responses with proper HTTP status codes
- ✅ **Pagination & Sorting** — Efficient data retrieval
- ✅ **RESTful APIs** — Clean 4-layer architecture
- ✅ **Password Encryption** — BCrypt password hashing
- ✅ **Location-based Job Search** — Filter jobs by location
- ✅ **Swagger UI** — Interactive API documentation with JWT support

---

## 🏗️ Project Architecture

```
com.jobportal
│
├── config/             → Swagger Configuration (SwaggerConfig)
├── controller/         → API endpoints (AuthController, JobController)
├── service/            → Business logic (AuthService, JobService)
├── repository/         → Database operations (UserRepository, JobRepository)
├── entity/             → Database tables (User, Job, Role)
├── dto/                → Data Transfer Objects (RegisterRequest, LoginRequest, ErrorResponse)
├── security/           → JWT & Security (JwtUtil, JwtFilter, SecurityConfig)
└── exception/          → Exception handling (GlobalExceptionHandler, ResourceNotFoundException)
```

---

## 👥 Roles & Permissions

| Role | Permissions |
|---|---|
| `ADMIN` | Full access — manage all users and jobs |
| `COMPANY` | Post, update, delete own jobs |
| `JOB_SEEKER` | View and search jobs |

---

## 📡 API Endpoints

### 🔐 Auth APIs (Public)

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login and get JWT token |

### 💼 Job APIs (Protected)

| Method | Endpoint | Role Required | Description |
|---|---|---|---|
| POST | `/api/jobs/post` | COMPANY | Post a new job |
| GET | `/api/jobs/all` | Any authenticated user | Get all jobs (paginated) |
| GET | `/api/jobs/{id}` | Any authenticated user | Get job by ID |
| GET | `/api/jobs/search?location=Noida` | Any authenticated user | Search jobs by location |

---

## 🔒 Authentication Flow

```
1. User registers → POST /api/auth/register
2. User logs in  → POST /api/auth/login → JWT Token received
3. User sends token in header → Authorization: Bearer <token>
4. JwtFilter validates token → extracts email + role
5. SecurityConfig checks role → allow or block request
```

---

## 📘 Swagger UI

Interactive API documentation available at:

```
http://localhost:9090/swagger-ui/index.html
```

### How to use Swagger UI with JWT:

```
Step 1 → Open Swagger UI in browser
Step 2 → Go to Authentication → POST /api/auth/login
Step 3 → Click "Try it out" → Enter email & password → Execute
Step 4 → Copy JWT token from response
Step 5 → Click "Authorize 🔒" button on top right
Step 6 → Paste token (without Bearer) → Click Authorize
Step 7 → Now test all protected APIs directly from browser!
```

---

## ⚙️ Setup & Installation

### Prerequisites
- Java 17+
- MySQL
- Maven
- Postman (for testing)

### Step 1 — Clone the Repository
```bash
git clone https://github.com/akashydv18/job-portal-rest-api.git
cd job-portal-rest-api
```

### Step 2 — Create MySQL Database
```sql
CREATE DATABASE job_portal_db;
```

### Step 3 — Configure application.properties
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/job_portal_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.open-in-view=false

jwt.secret=mySecretKey123456789mySecretKey123456789
jwt.expiration=86400000

# Swagger
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/v3/api-docs
```

### Step 4 — Run the Application
```bash
mvn spring-boot:run
```

Server starts at: `http://localhost:9090`

Swagger UI at: `http://localhost:9090/swagger-ui/index.html`

---

## 📬 API Usage Examples

### Register a User
```json
POST /api/auth/register
{
  "name": "Aakash Yadav",
  "email": "aakash@gmail.com",
  "password": "123456",
  "role": "JOB_SEEKER"
}
```

### Login
```json
POST /api/auth/login
{
  "email": "aakash@gmail.com",
  "password": "123456"
}

Response:
"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYWthc2..."
```

### Post a Job (COMPANY token required)
```json
POST /api/jobs/post
Authorization: Bearer <company_token>

{
  "title": "Java Backend Developer",
  "description": "Spring Boot developer needed",
  "location": "Noida",
  "salary": "5-8 LPA"
}
```

### Get All Jobs (Paginated)
```
GET /api/jobs/all?page=0&size=10
Authorization: Bearer <token>
```

### Search Jobs by Location
```
GET /api/jobs/search?location=Noida&page=0&size=5
Authorization: Bearer <token>
```

---

## ❌ Exception Handling

Global exception handling with proper HTTP status codes:

| Exception | HTTP Status | When |
|---|---|---|
| `ResourceNotFoundException` | 404 Not Found | User or Job not found |
| `RuntimeException` | 400 Bad Request | Invalid password, duplicate email |
| `Exception` | 500 Internal Server Error | Unexpected errors |

### Error Response Format
```json
{
  "status": 404,
  "message": "User not found with email: wrong@gmail.com",
  "timestamp": "2026-04-30T18:00:00"
}
```

---

## 🗄️ Database Schema

### Users Table
| Column | Type | Description |
|---|---|---|
| id | BIGINT (PK) | Auto-generated |
| name | VARCHAR | User's full name |
| email | VARCHAR (Unique) | Login email |
| password | VARCHAR | BCrypt encrypted |
| role | ENUM | ADMIN / COMPANY / JOB_SEEKER |

### Jobs Table
| Column | Type | Description |
|---|---|---|
| id | BIGINT (PK) | Auto-generated |
| title | VARCHAR | Job title |
| description | TEXT | Job description |
| location | VARCHAR | Job location |
| salary | VARCHAR | Salary range |
| company_id | BIGINT (FK) | Reference to Users table |

---

## 🔮 Future Improvements

- ⬜ **Job Application System** — Seekers can apply, Companies can manage applications
- ⬜ **Refresh Token** — Better authentication experience
- ⬜ **Email Notifications** — Notify on application status via Spring Mail
- ⬜ **Docker** — Containerize the application
- ⬜ **AWS Deployment** — Deploy on EC2 with RDS MySQL

---

## 👨‍💻 Author

**Aakash Kumar Yadav**
- GitHub: [@akashydv18](https://github.com/akashydv18)
- LinkedIn: [linkedin.com/in/aakash-yadav-988447281](https://linkedin.com/in/aakash-yadav-988447281)
- Email: aakashrajyaduvanshi7@gmail.com
- LeetCode: [akashydv18](https://leetcode.com/akashydv18)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

⭐ **If you found this project helpful, please give it a star!**
