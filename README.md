<h1>Booking dotCom, A Microservices Project</h1>

<p>
    This repository contains the source code for a <strong>Booking.com clone</strong> built using 
    <strong>Java</strong> and <strong>Spring Boot</strong>, following best practices of 
    <strong>Event-Driven Architecture</strong>, <strong>Clean Architecture</strong>, and 
    <strong>Hexagonal Architecture</strong>. The project leverages <strong>RabbitMQ</strong> 
    as the message broker for inter-service communication. Each microservice is backed by its 
    own <strong>MySQL database</strong>, ensuring clear separation of concerns and scalability.
</p>

## Table of Contents

- [Overview](#overview)
- [Architectural Highlights](#architectural-highlights)
- [Services](#services)
    - [Hotel Service](#hotel-service)
    - [Payment Service](#payment-service)
    - [Booking Service](#booking-service)
    - [Customer Service](#customer-service)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
  
## Overview

This project replicates the core functionality of Booking.com by enabling users to:
- Register a hotel.
- Search for a hotel.
- Book a room in hotel based on the availability.
- Process payments seamlessly.

The system is designed to be **modular**, **scalable**, and **fault-tolerant**, adhering to modern software engineering practices.

## Architectural Highlights

- **Event-Driven Architecture**: Services communicate asynchronously using RabbitMQ to decouple dependencies and improve scalability.
- **Clean Architecture**: Organized into layers - Presentation, Application, Domain, and Infrastructure.
- **Hexagonal Architecture**: Domain logic is independent of external services using the port-and-adapter pattern.
- **Database Per Service**: Each microservice has its own database, ensuring data isolation.

## Services


### Hotel Service
- **Responsibilities**: Manage hotel listings, rooms, and hotel categories.
- **Port**: 8001
- **Endpoints**: `GET /hotel`, `POST /hotel`.
- **Events**: Publishes `BookingRoomRequestedEvent`, `BookingRoomStatusUpdatedEvent`, `CustomerBookingStatusUpdatedEvent`, `PaymentRequestedEvent`.

### Payment Service
- **Responsibilities**: Handle payments and refunds.
- **Port**: 8003
- **Events**: Publishes `PaymentResponseEvent`.

### Booking Service
- **Responsibilities**: Manage bookings and reservations.
- **Port**:8004
- **Events**: Publishes `BookingRoomResponseEvent`.

### Customer Service
- **Responsibilities**: Manage customer accounts.
- **Port**: 8002
- **Endpoints**: `POST /customers`, `GET /{customerId}/reservation-order/{reservationOrderId}`.
- **Events**: Publishes `CustomerBookingStatusUpdatedEvent`.


## Tech Stack

- **Programming Language**: Java 17
- **Framework**: Spring Boot
- **Message Queue**: RabbitMQ
- **Database**: MySQL
- **Build Tool**: Maven
- **Containerization**: Docker & Docker Compose
- **Testing**: JUnit, Mockito

## Getting Started

### Prerequisites

1. Install Java 17.
2. Install Docker and Docker Compose.
3. Install RabbitMQ and ensure it is running.

### Installation
