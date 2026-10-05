# HRMS — Enterprise Human Resource Management System

A backend-focused **Human Resource Management System (HRMS)** built with **Java and Quarkus**, designed to demonstrate real-world enterprise backend development, role-based security, business logic, database relationships, file management, email notifications, scheduled jobs, and containerized deployment.

The system supports multiple actors — **Super Admin, HR Manager, Manager, and Employee** — with different permissions over the same business data.

> **Project Status:** 🚧 In Development

---

## 📌 Overview

The goal of this project is to build a realistic enterprise HR platform rather than a simple CRUD application.

The system manages employees, departments, leave requests, documents, and payslips while enforcing role-based and ownership-based access.

For example:

```text
Employee
   │
   ├── Applies for Leave
   │
   ▼
Leave Request
   │
   ├── Manager → Approves / Rejects team member's leave
   │
   └── HR Manager → Can manage organization-wide leave
```

The same resource can therefore be accessed by different users with different permissions.

---

## 👥 User Roles

### SUPER_ADMIN

System-level administrator.

* Manage companies
* Manage system-wide configuration
* Future multi-tenancy / SaaS support

### HR_MANAGER

Main HR administrator.

* Manage employees
* Manage departments
* Manage leave requests
* Manage payroll
* Manage employee documents
* View organization-wide information

### MANAGER

Team-level manager.

* View direct reports
* View team leave requests
* Approve or reject leave for direct reports
* View team organization structure

### EMPLOYEE

Regular employee.

* View own profile
* Apply for leave
* View leave history and balance
* Upload personal documents
* View payslips
* Download authorized documents

---

# 🚀 Features

## 🔐 Authentication & Authorization

* JWT-based authentication
* Secure login
* Role-based authorization
* Ownership-based authorization
* Password change
* Protected REST endpoints
* Different permissions for each user role

Example:

```text
EMPLOYEE
   ↓
Can access own leave

MANAGER
   ↓
Can access direct reports' leave

HR_MANAGER
   ↓
Can manage organization-wide leave
```

---

## 👨‍💼 Employee Management

Manage complete employee information including:

* Personal information
* Department
* Designation
* Manager
* Employment type
* Joining date
* Employment status

Supported statuses:

```text
ACTIVE
RESIGNED
TERMINATED
```

The employee entity uses a self-referential relationship where an employee can have another employee as their manager.

Example:

```text
CEO
 ├── HR Manager
 │    ├── Employee A
 │    └── Employee B
 │
 └── Engineering Manager
      ├── Developer A
      └── Developer B
```

---

## 🏢 Department & Organization Management

* Create departments
* Assign department heads
* Assign employees to departments
* Assign managers
* View organizational hierarchy
* Generate team/org structures
* Support employee reassignment

---

## 🏖️ Leave Management

Employees can submit leave requests using supported leave types:

```text
ANNUAL
SICK
UNPAID
```

The system manages:

* Leave applications
* Leave balances
* Leave approval
* Leave rejection
* Rejection reasons
* Leave history
* Team leave visibility
* HR-wide leave management

Example workflow:

```text
Employee
   │
   │ Apply Leave
   ▼
PENDING
   │
   ├───────────────┐
   ▼               ▼
APPROVED         REJECTED
   │               │
   ▼               ▼
Balance         Reason
Updated         Stored
```

The leave module is also used to demonstrate service-layer business rules and ownership checks.

---

## 📁 Document Management

Employees can upload documents such as:

* Contracts
* CVs
* Certificates
* Other HR documents

Features:

* Multipart file upload
* File type validation
* File size validation
* Secure file download
* Authorization checks
* Document metadata stored in database
* Storage abstraction for local storage / S3-compatible storage

The actual file is separated from its database metadata so that clients never receive direct filesystem paths.

---

## 💰 Payroll & Payslips

The payroll module provides a simplified monthly payslip system.

A payslip contains:

```text
Gross Salary
- Deductions
----------------
Net Salary
```

Features:

* Monthly payslip generation
* Payslip persistence
* PDF generation
* Secure payslip download
* Email payslip as attachment
* Monthly scheduled generation

> This project does not attempt to implement a complete real-world payroll/tax engine. The purpose is to demonstrate backend processing, PDF generation, scheduling, and email integration.

---

## 📧 Email Notifications

The system will send notifications for important events such as:

* Employee account activation
* Leave approval
* Leave rejection
* Payslip generation
* Contract expiry warnings

HTML email templates will be used for structured emails.

Development/testing email delivery will use a local or sandbox SMTP service rather than sending real emails.

---

## ⏰ Scheduled Jobs

The project includes scheduled background jobs for recurring HR operations.

### Leave Balance Reset

Automatically processes annual leave balances at the beginning of a new year.

### Contract Expiry Notification

Runs periodically and identifies employees whose contracts are approaching expiration.

Example:

```text
Daily Scheduler
      ↓
Find contracts expiring in 7 days
      ↓
Notify HR
```

---

# 🧠 Backend Concepts Demonstrated

This project is intentionally designed to cover backend concepts that are commonly used in enterprise Java applications.

### JPA / Hibernate

* `@ManyToOne`
* `@OneToMany`
* `@OneToOne`
* Self-referential relationships
* Bidirectional relationships
* Cascade strategies
* Lazy loading
* N+1 query problem
* Entity graphs
* Transactions

The employee-manager relationship is particularly useful for learning self-referential JPA mappings and organization trees.

### Security

* JWT authentication
* Role-based authorization
* Ownership checks
* Service-layer authorization
* Protected resources

### API Design

* RESTful endpoints
* DTOs
* Request/response separation
* Validation
* Pagination
* Filtering
* Global exception handling
* OpenAPI documentation

### Database

* PostgreSQL
* Database constraints
* Relationships
* Indexes
* Transactions
* Flyway migrations

### Enterprise Features

* Email notifications
* File storage
* PDF generation
* Scheduled jobs
* Auditability
* Dockerized infrastructure

---

# 🏗️ Architecture

The application follows a layered backend architecture:

```text
                 ┌─────────────────────┐
                 │       Client        │
                 │  Web / Postman etc. │
                 └──────────┬──────────┘
                            │
                            ▼
                 ┌─────────────────────┐
                 │    REST Resources   │
                 │    / Controllers    │
                 └──────────┬──────────┘
                            │
                            ▼
                 ┌─────────────────────┐
                 │   Security Layer    │
                 │   JWT + Roles       │
                 └──────────┬──────────┘
                            │
                            ▼
                 ┌─────────────────────┐
                 │    Service Layer    │
                 │   Business Logic    │
                 └──────────┬──────────┘
                            │
                            ▼
                 ┌─────────────────────┐
                 │ Repository / Panache│
                 └──────────┬──────────┘
                            │
                            ▼
                 ┌─────────────────────┐
                 │     PostgreSQL      │
                 └─────────────────────┘
```

Additional infrastructure:

```text
Application
    │
    ├── PostgreSQL
    ├── Email / SMTP
    ├── File Storage
    └── PDF Generation
```

---

# 📂 Project Structure

```text
src/
└── main/
    ├── java/
    │   └── com/saud/hrms/
    │       │
    │       ├── controller/
    │       │   ├── AuthController
    │       │   ├── EmployeeController
    │       │   ├── DepartmentController
    │       │   ├── LeaveController
    │       │   ├── DocumentController
    │       │   └── PayrollController
    │       │
    │       ├── service/
    │       │   ├── AuthService
    │       │   ├── EmployeeService
    │       │   ├── DepartmentService
    │       │   ├── LeaveService
    │       │   ├── DocumentService
    │       │   └── PayrollService
    │       │
    │       ├── repository/
    │       │
    │       ├── entity/
    │       │   ├── User
    │       │   ├── Employee
    │       │   ├── Department
    │       │   ├── Leave
    │       │   ├── LeaveBalance
    │       │   ├── Document
    │       │   └── Payslip
    │       │
    │       ├── dto/
    │       │   ├── request/
    │       │   └── response/
    │       │
    │       ├── mapper/
    │       │
    │       ├── security/
    │       │
    │       ├── exception/
    │       │
    │       ├── scheduler/
    │       │
    │       ├── email/
    │       │
    │       └── storage/
    │
    └── resources/
        ├── application.properties
        ├── db/
        │   └── migration/
        └── templates/
            └── email/
```

The package separation follows the planned controller/service/repository/entity/DTO/security/scheduler/email/storage structure.

---

# 🌐 REST API

## Authentication

| Method | Endpoint                    | Description                      |
| ------ | --------------------------- | -------------------------------- |
| POST   | `/api/auth/login`           | Authenticate user and return JWT |
| POST   | `/api/auth/refresh`         | Refresh authentication token     |
| POST   | `/api/auth/change-password` | Change current password          |

## Employees

| Method | Endpoint                     | Description              |
| ------ | ---------------------------- | ------------------------ |
| GET    | `/api/employees`             | List employees           |
| GET    | `/api/employees/{id}`        | Get employee             |
| POST   | `/api/employees`             | Create employee          |
| PUT    | `/api/employees/{id}`        | Update employee          |
| PATCH  | `/api/employees/{id}/status` | Change employment status |
| GET    | `/api/employees/{id}/team`   | Get employee's team      |

Filtering and pagination will be supported, for example:

```text
GET /api/employees?department=engineering&status=ACTIVE&page=0
```

---

## Leave

| Method | Endpoint                   | Description                  |
| ------ | -------------------------- | ---------------------------- |
| POST   | `/api/leaves`              | Apply for leave              |
| PATCH  | `/api/leaves/{id}/approve` | Approve leave                |
| PATCH  | `/api/leaves/{id}/reject`  | Reject leave                 |
| GET    | `/api/leaves/my`           | Current user's leave history |
| GET    | `/api/leaves/team`         | Manager's team leave         |
| GET    | `/api/leaves/pending`      | Pending organization leaves  |

---

## Documents

| Method | Endpoint                        | Description       |
| ------ | ------------------------------- | ----------------- |
| POST   | `/api/employees/{id}/documents` | Upload document   |
| GET    | `/api/employees/{id}/documents` | List documents    |
| GET    | `/api/documents/{id}/download`  | Download document |
| DELETE | `/api/documents/{id}`           | Delete document   |

---

## Payroll

| Method | Endpoint                     | Description               |
| ------ | ---------------------------- | ------------------------- |
| POST   | `/api/payroll/generate`      | Generate monthly payslips |
| GET    | `/api/payroll/my`            | View own payslips         |
| GET    | `/api/payroll/{id}/download` | Download payslip          |

---

## Departments

| Method | Endpoint                          | Description           |
| ------ | --------------------------------- | --------------------- |
| GET    | `/api/departments`                | List departments      |
| POST   | `/api/departments`                | Create department     |
| GET    | `/api/departments/{id}/org-chart` | Get organization tree |

---

# 🛠️ Technology Stack

### Backend

* Java 21
* Quarkus 3
* RESTEasy Reactive / Quarkus REST
* Hibernate ORM with Panache
* Hibernate Validator
* SmallRye JWT
* SmallRye OpenAPI

### Database

* PostgreSQL
* Flyway
* H2 / test database for testing

### Additional Services

* Quarkus Mailer
* SMTP testing service
* Local file storage / S3-compatible storage
* PDF generation

### Development & DevOps

* Maven
* Git
* Docker
* Docker Compose
* OpenAPI / Swagger UI

---

# 🗺️ Development Roadmap

## Phase 1 — Foundation

* [x] Create Quarkus project
* [ ] Configure PostgreSQL
* [ ] Configure Flyway
* [ ] Create database schema
* [ ] Create User entity
* [ ] Create Employee entity
* [ ] Create Department entity
* [ ] Employee CRUD
* [ ] DTOs
* [ ] Validation
* [ ] Pagination and filtering

## Phase 2 — Authentication & Security

* [ ] Login
* [ ] JWT generation
* [ ] JWT validation
* [ ] Role-based authorization
* [ ] Password change
* [ ] Protected endpoints
* [ ] Ownership checks

Roles:

```text
SUPER_ADMIN
HR_MANAGER
MANAGER
EMPLOYEE
```

## Phase 3 — Organization & Leave

* [ ] Department management
* [ ] Employee-manager relationship
* [ ] Organization tree
* [ ] Leave types
* [ ] Leave balance
* [ ] Leave application
* [ ] Leave approval
* [ ] Leave rejection
* [ ] Manager ownership checks
* [ ] Transaction handling

## Phase 4 — Email & Documents

* [ ] Email service
* [ ] HTML email templates
* [ ] Leave notification emails
* [ ] Employee account emails
* [ ] Multipart file upload
* [ ] MIME validation
* [ ] File size validation
* [ ] Secure file download
* [ ] Storage abstraction

## Phase 5 — Payroll & Automation

* [ ] Payslip entity
* [ ] Monthly payslip generation
* [ ] PDF generation
* [ ] PDF email attachment
* [ ] Monthly scheduler
* [ ] Contract expiry scheduler
* [ ] Leave balance scheduler

## Phase 6 — Production Readiness

* [ ] Global exception handling
* [ ] Audit logging
* [ ] Integration tests
* [ ] Dockerfile
* [ ] Docker Compose
* [ ] API documentation
* [ ] Database indexes
* [ ] Logging improvements
* [ ] README documentation

---

# 🔄 Example Business Flow

One complete HRMS workflow:

```text
1. HR creates employee
          ↓
2. Employee account is created
          ↓
3. Employee activates account
          ↓
4. Employee logs in
          ↓
5. Employee submits annual leave
          ↓
6. System validates leave balance
          ↓
7. Manager receives notification
          ↓
8. Manager approves leave
          ↓
9. Leave balance is updated
          ↓
10. Employee receives email
          ↓
11. Action is recorded
```

This demonstrates authentication, authorization, validation, database transactions, business rules, relationships, email notifications, and persistence in a single workflow.

---

# 🎯 Learning Objectives

This project is being developed to gain practical experience with:

* Enterprise Java backend architecture
* Quarkus
* Hibernate / JPA
* Panache
* PostgreSQL
* Database migrations
* REST API design
* JWT authentication
* Role-based authorization
* Ownership-based authorization
* DTO and mapper patterns
* Bean Validation
* JPA relationship design
* Lazy loading and N+1 queries
* Transactions
* File upload and streaming
* Email integration
* Scheduled background jobs
* PDF generation
* Docker
* Integration testing

The project specifically focuses on the areas where enterprise applications become more complex than basic CRUD, such as JPA relationships, ownership checks, file handling, email, scheduling, and DTO/mapping patterns.

---

# 🧪 Testing

The project will include:

### Unit Tests

Testing individual business components such as:

* Leave balance calculation
* Leave approval rules
* Employee validation
* Authorization rules

### Integration Tests

Testing complete flows such as:

```text
Request
  ↓
REST Resource
  ↓
Service
  ↓
Repository
  ↓
PostgreSQL
```

### API Testing

REST APIs can be tested using:

* Postman
* Swagger UI
* Integration tests

---

# 🐳 Running with Docker

The final project will provide Docker support for the application and infrastructure services.

Planned architecture:

```text
┌──────────────────────────┐
│       HRMS API           │
│       Quarkus            │
└────────────┬─────────────┘
             │
      ┌──────┴──────┐
      ▼             ▼
 PostgreSQL       Mail Service
```

The exact Docker setup and environment variables will be documented as the project reaches the deployment phase.

---

# 🔒 Security Considerations

The application is designed around the principle that authentication alone is not enough.

For example:

```text
EMPLOYEE
❌ Cannot view another employee's private documents

MANAGER
❌ Cannot approve leave for employees outside their team

EMPLOYEE
❌ Cannot modify their own role

HR_MANAGER
✅ Can manage organization-wide HR operations
```

Authorization is therefore handled at both the endpoint and business/service layers where appropriate.

---

# 📚 Project Documentation

Additional documentation will be added as the project develops:

```text
/docs
├── architecture.md
├── database-design.md
├── api.md
├── security.md
└── development-notes.md
```

---

# 🚧 Current Status

This project is actively being developed.

The implementation is intentionally being built incrementally so that each module is understood and tested before moving to the next one.

Current focus:

```text
Project Setup
     ↓
PostgreSQL
     ↓
Flyway
     ↓
Entities
     ↓
Employee CRUD
     ↓
Authentication
     ↓
Authorization
```

---

# 👨‍💻 Author

**Saud Ahmed**

Software Design Engineer | Java Backend Developer

Focused on:

```text
Java
Spring Boot
Quarkus
REST APIs
Hibernate / JPA
PostgreSQL
Microservices
Docker
Backend Architecture
```

---

## ⭐ Project Goal

The goal of this project is not simply to build an HR application.

It is to demonstrate how a backend engineer designs and implements a system where:

* multiple users interact with shared data,
* each role has different permissions,
* business rules are enforced in the service layer,
* database relationships represent real organizational structures,
* sensitive resources are protected,
* background processes automate recurring work,
* and the application can be packaged and deployed using containers.

> **Built to learn, experiment, and demonstrate enterprise Java backend development.**
