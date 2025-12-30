# Banking Sample

A RESTful banking application built with Spring Boot 4.0.1 that provides basic banking operations including account management and transactions.

## Tech Stack

- Java 25
- Spring Boot 4.0.1
- Spring Data JPA
- Spring Validation
- H2 In-Memory Database
- Lombok
- Maven

## Features

- **Account Management**
  - Create new bank accounts
  - Check account balance
  - View account details with transaction history

- **Transactions**
  - Transfer money between accounts
  - Deposit funds
  - Withdraw funds

## Getting Started

### Prerequisites

- Java 25+
- Maven 3.9+

### Running the Application

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

### Running Tests

```bash
./mvnw test
```

### Building

```bash
./mvnw clean package
```

## API Endpoints

### Account Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/accounts` | Get account balance by sort code and account number |
| PUT | `/api/v1/accounts` | Create a new account |

### Transaction Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/transactions` | Transfer money between accounts |
| POST | `/api/v1/withdraw` | Withdraw funds from an account |
| POST | `/api/v1/deposit` | Deposit funds to an account |

## API Usage Examples

### Create Account

```bash
curl -X PUT http://localhost:8080/api/v1/accounts \
  -H "Content-Type: application/json" \
  -d '{
    "bankName": "Sample Bank",
    "ownerName": "John Doe"
  }'
```

### Check Account Balance

```bash
curl -X POST http://localhost:8080/api/v1/accounts \
  -H "Content-Type: application/json" \
  -d '{
    "sortCode": "12-34-56",
    "accountNumber": "12345678"
  }'
```

### Deposit

```bash
curl -X POST http://localhost:8080/api/v1/deposit \
  -H "Content-Type: application/json" \
  -d '{
    "targetAccountNo": "12345678",
    "amount": 100.00
  }'
```

### Withdraw

```bash
curl -X POST http://localhost:8080/api/v1/withdraw \
  -H "Content-Type: application/json" \
  -d '{
    "sortCode": "12-34-56",
    "accountNumber": "12345678",
    "amount": 50.00
  }'
```

### Transfer

```bash
curl -X POST http://localhost:8080/api/v1/transactions \
  -H "Content-Type: application/json" \
  -d '{
    "sourceSortCode": "12-34-56",
    "sourceAccountNumber": "12345678",
    "targetSortCode": "65-43-21",
    "targetAccountNumber": "87654321",
    "amount": 25.00
  }'
```

## H2 Console

Access the H2 database console at: `http://localhost:8080/h2-console`

- JDBC URL: `jdbc:h2:mem:online_bank`
- Username: `sa`
- Password: (empty)

## Project Structure

```
src/main/java/id/my/hendisantika/bankingsample/
├── BankingSampleApplication.java    # Main application class
├── constants/
│   ├── ACTION.java                  # Transaction action types
│   └── constants.java               # Application constants
├── controller/
│   ├── AccountRestController.java   # Account REST endpoints
│   └── TransactionRestController.java # Transaction REST endpoints
├── model/
│   ├── Account.java                 # Account entity
│   └── Transaction.java             # Transaction entity
├── repository/
│   ├── AccountRepository.java       # Account data access
│   └── TransactionRepository.java   # Transaction data access
├── service/
│   ├── AccountService.java          # Account business logic
│   └── TransactionService.java      # Transaction business logic
└── util/
    ├── AccountInput.java            # Account search input DTO
    ├── CodeGenerator.java           # Sort code/account number generator
    ├── CreateAccountInput.java      # Create account input DTO
    ├── DepositInput.java            # Deposit input DTO
    ├── InputValidator.java          # Input validation utilities
    ├── TransactionInput.java        # Transaction input DTO
    └── WithdrawInput.java           # Withdraw input DTO
```

## License

This project is open source.
