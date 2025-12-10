# Smart Travel Platform – Microservices Assignment 2

## Index No : ICT/21/809
## Name : Athapattu D.S.

## 1. Overview

Smart Travel Platform is a distributed microservices system designed to simulate an end-to-end travel booking flow.  
The system allows a user to book a flight and hotel, process a payment, and trigger notifications.

This project has been implemented using **Spring Boot microservices**, following the assignment rules:

- NO deprecated technologies (no RestTemplate)
- Inter-service communication using **WebClient** and **Feign Client**
- In-memory H2 databases
- Individual services running on separate ports

---

## 2. Microservices Architecture

### Services Implemented

| Service | Purpose | Port |
|---|---|---|
| **User Service** | Provide user information | 8081 |
| **Flight Service** | Flight availability and pricing | 8082 |
| **Hotel Service** | Hotel availability and pricing | 8083 |
| **Booking Service** | Main orchestration logic | 8084 |
| **Payment Service** | Payment processing | 8085 |
| **Notification Service** | Sends notifications | 8086 |

---

## 3. Communication Flow

### Booking Flow Steps

1. User sends a booking request to Booking Service
2. Booking Service:
   - Validates user (WebClient → User Service)
   - Checks flight availability (Feign → Flight Service)
   - Checks hotel availability (Feign → Hotel Service)
   - Calculates total cost
   - Saves booking as `PENDING`
   - Calls Payment Service (WebClient)
3. Payment Service:
   - Processes payment
   - Calls Booking Service to update status to `CONFIRMED`
   - Calls Notification Service
4. Notification Service:
   - Logs/prints notification to console

---

## 4. Technology Stack

- **Spring Boot 3.5.8**
- **Spring Cloud OpenFeign**
- **Spring WebFlux (WebClient)**
- **Spring Data JPA**
- **H2 In-memory Databases**
- **Lombok**
- **Maven**

---

## 5. Communication Rules Compliance

| Requirement | Implemented |
|---|:---:|
| No deprecated RestTemplate | ✔️ |
| WebClient used for REST communication | ✔️ |
| Feign Client used for at least two service interactions | ✔️ |
| Persistent storage via JPA + H2 | ✔️ |
| Notification triggered | ✔️ |

### WebClient Calls
- Booking → User
- Booking → Payment
- Booking → Notification

### Feign Calls
- Booking → Flight
- Booking → Hotel

---

## 6. Main Endpoints

### Booking Service

| Method | Endpoint | Description |
|---|---|---|
| POST | `/bookings` | Create booking |
| GET | `/bookings/{id}` | Get booking by ID |
| GET | `/bookings` | Get all bookings |
| PUT | `/bookings/{id}/status` | Update booking status |

Example booking request:

```json
{
  "userId": 1,
  "flightId": 200,
  "hotelId": 55,
  "travelDate": "2025-01-10"
}
```

---

### Payment Service

| Method | Endpoint | Description |
|---|---|---|
| POST | `/payments` | Process payment |

---

### Notification Service

| Method | Endpoint | Description |
|---|---|---|
| POST | `/notifications` | Trigger notification |

---

## 7. H2 Databases

Each service uses an **in-memory H2 database**.

Access via browser:

```
http://localhost:<PORT>/h2-console
JDBC URL: jdbc:h2:mem:<dbname>
```

---

## 8. How to Run

Run each service in order:

```
mvn spring-boot:run
```

or using IntelliJ.

Recommended order:

1. User Service
2. Flight Service
3. Hotel Service
4. Booking Service
5. Payment Service
6. Notification Service

---

## 9. Testing With Postman

Recommended flow:

1. **POST /bookings**
2. Booking triggers full flow:
   - User validation
   - Flight and hotel checks
   - Payment processing
   - Status update
   - Notification

3. **GET /bookings**
   - Confirm booking status = `CONFIRMED`

A Postman Collection is provided.


## 10. Conclusion

This project successfully demonstrates a microservices architecture with communication between services using **Feign Client** and **WebClient**, following all assignment rules.
