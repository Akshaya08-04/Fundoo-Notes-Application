# Fundoo Notes Application


A robust and scalable Spring Boot backend application for the Fundoo Notes system, developed using a Use-Case (UC) driven approach and following a clean layered architecture.

# 🏗️ Architecture & Development

This project follows a structured GitFlow strategy:

main → Base / Documentation branch
develop → Integrated working backend
feature/UC-* → Individual use-case implementation

Each use case is:

Developed in a separate feature branch
Tested independently
Merged into develop
🔄 Backend Flow

Client
⬇️
Controller
⬇️
DTO
⬇️
Service
⬇️
Repository
⬇️
Database

# ✅ Part 1 — Backend Foundations (UC1 - UC11)
🔹 UC1: Project Setup
Spring Boot project initialized,
Proper package structure created,
Maven dependencies configured

🔹 UC2: Database Configuration,
MySQL integration using application.properties ,
Hibernate auto schema update enabled

🔹 UC3: User Entity & Repository,
Created User entity,
Implemented UserRepository using JPA

🔹 UC4: User Registration,
Registration API created,
DTO validation implemented

🔹 UC5: User Login & JWT,
Login API built,
JWT token generation added,
Password encrypted using BCrypt

🔹 UC6: JWT Validation,
Token parsing and validation logic implemented,
User identity extracted from token

🔹 UC7: Note Entity & Repository,
Created Note entity,
Fields: title, description, pinned, archived, trashed

🔹 UC8: Create Note API,
API to create notes,
Notes linked with user

🔹 UC9: Get Notes API,
Fetch all notes,
Fetch note by ID

🔹 UC10: Note Toggle APIs,
Pin / Unpin,
Archive / Unarchive,
Trash / Restore

🔹 UC11: Exception Handling & Logging,
Global Exception Handler implemented,
AOP Logging added for tracking API flow

# 🚀 Part 2 — Advanced Backend (UC12 - UC16)
🔹 UC12: Redis Caching,
Integrated Redis,
Used for token/session caching

🔹 UC13: RabbitMQ Messaging,
Implemented producer and consumer,
Enabled asynchronous notification flow

🔹 UC14: Spring Batch,
Configured batch processing,
prepared structure for bulk/Excel operations

🔹 UC15: Scheduler (Reminders),
Implemented background jobs using @Scheduled,
Designed reminder workflow

🔹 UC16: Spring Cloud Architecture
Designed microservices-ready architecture,
Defined:
API Gateway,
Service Discovery,
Config Server