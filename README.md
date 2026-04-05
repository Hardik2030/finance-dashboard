# 💰 Finance Dashboard Backend

A Spring Boot backend application for managing financial records with role-based access control (RBAC) and dashboard analytics.

---

## 🚀 Features

* 🔐 Role-Based Access Control (ADMIN, ANALYST, VIEWER)
* 📊 Dashboard APIs (Income, Expense, Balance, Category-wise, Recent Activity)
* 🧾 CRUD operations for financial records
* 🔍 Filtering (type, category, date range)
* 📦 DTO-based architecture
* ✅ Input validation
* ⚠️ Global exception handling
* 📄 Swagger API documentation

---

## 🧱 Tech Stack

* Java 17+
* Spring Boot
* Spring Security (HTTP Basic Auth)
* Spring Data JPA
* Hibernate
* MySQL / H2
* Swagger (Springdoc OpenAPI)
* Lombok

---

## 🔐 Roles & Permissions

| Role    | Permissions                            |
| ------- | -------------------------------------- |
| ADMIN   | Full access (CRUD + Users + Dashboard) |
| ANALYST | View records + dashboard               |
| VIEWER  | View records only                      |

---

## 🔑 Authentication

This project uses **HTTP Basic Authentication** for simplicity.

### Default Users:

| Username | Password   | Role    |
| -------- | ---------- | ------- |
| admin    | admin123   | ADMIN   |
| analyst  | analyst123 | ANALYST |
| viewer   | viewer123  | VIEWER  |

---

## 📊 API Endpoints

### 🔹 Financial Records

* `GET /records` → Get all records
* `POST /records?userId=1` → Create record (ADMIN only)
* `PUT /records/{id}` → Update record (ADMIN only)
* `DELETE /records/{id}` → Delete record (ADMIN only)

---

### 🔹 Filtering

* `GET /records/filter?type=INCOME`
* `GET /records/filter?category=Food`
* `GET /records/filter?startDate=2026-04-01&endDate=2026-04-05`

---

### 🔹 Dashboard

* `GET /records/dashboard/summary`
* `GET /records/dashboard/category`
* `GET /records/dashboard/recent`

---

### 🔹 Users

* `POST /users` → Create user (ADMIN only)

---

## 🧪 Sample Request

### Create Financial Record

```json
{
  "amount": 5000,
  "recordType": "INCOME",
  "category": "Salary",
  "date": "2026-04-01",
  "description": "Monthly salary"
}
```

---

## 📄 API Documentation

Swagger UI available at:

```
http://localhost:8080/swagger-ui/index.html
```

---

## ⚙️ How to Run

```bash
mvn clean install
mvn spring-boot:run
```

---

## 🧠 Technical Decisions & Trade-offs

* **HTTP Basic Auth** used instead of JWT for simplicity
* **DTO pattern** used to avoid exposing entities
* **Filtering implemented in service layer** for flexibility
* **Category as String** to allow dynamic user-defined values
* **Aggregation done in service layer** for simplicity over performance

---

## 📌 Assumptions

* Categories are user-defined and not restricted
* Basic authentication is sufficient for demonstration
* Data volume is small, so in-memory filtering is acceptable

---

## 🚀 Future Improvements

* JWT-based authentication
* Pagination & sorting
* Database-level filtering and aggregation
* Export reports (CSV/PDF)

---

## 👨‍💻 Author

Hardik Pandey

---
