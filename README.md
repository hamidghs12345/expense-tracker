# Expense Tracker

A simple Spring Boot application to track personal expenses.

## Features

* Add, edit, and delete expenses
* Categorize expenses
* View summary of expenses
* REST API for integration

## Technologies Used

* Java 17
* Spring Boot
* Maven
* Docker (optional)

## Prerequisites

* Java 17 installed
* Maven installed (or use the included `mvnw`)
* Docker installed (optional)

## Running Locally

If you want to run the application **locally with Docker** without Compose:

1. Clone the repository:

```bash
git clone https://github.com/hamidghs12345/expense-tracker.git
cd expense-tracker
```

2. Build the Docker image:

```bash
docker build -t expense-tracker .
```

3. Run the Docker container:

```bash
docker run -p 8080:8080 expense-tracker
```

Now the application will be accessible at: `http://localhost:8080`

### Notes

* Make sure your **MySQL database** is running locally or update `application.yml` to match your database credentials.
* If you don’t have a database, you can create one manually:

```sql
CREATE DATABASE ex_tracker CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

* The Docker container only contains your **Spring Boot application**. The database is expected to be external (running on localhost).
