# SmartShop Event Commerce

Plataforma e-commerce basada en arquitectura de microservicios y eventos en tiempo real utilizando Spring Boot, Kafka, Angular, Docker y API Gateway.

---

# Descripción General

SmartShop Event Commerce es una plataforma distribuida diseñada siguiendo principios de arquitectura event-driven (dirigida por eventos).

El sistema simula un flujo real enterprise donde múltiples microservicios se comunican de forma asíncrona mediante Kafka mientras el frontend recibe actualizaciones en tiempo real utilizando Server-Sent Events (SSE).

Este proyecto demuestra:

- Arquitectura de microservicios
- Comunicación asíncrona
- Event-Driven Architecture
- Actualizaciones realtime
- API Gateway
- Dockerización completa
- Procesamiento distribuido
- Integración frontend/backend moderna

---

# Arquitectura

```text
Angular Frontend
        ↓
API Gateway
        ↓
Microservicios
 ├── Order Service
 ├── Inventory Service
 ├── Payment Service
 └── Notification Service
        ↓
Kafka Event Streaming
        ↓
PostgreSQL
```

---

# Funcionalidades

## Actualizaciones en Tiempo Real

Las órdenes se actualizan automáticamente en el dashboard Angular sin necesidad de refrescar la página.

## Arquitectura Event-Driven

Los servicios se comunican mediante eventos Kafka.

Flujo completo:

1. Creación de orden
2. Reserva de stock
3. Procesamiento de pago
4. Notificación realtime
5. Actualización automática del frontend

## API Gateway

Centralización de rutas y manejo de CORS utilizando Spring Cloud Gateway.

## Infraestructura Dockerizada

Toda la plataforma se ejecuta mediante Docker Compose.

## Arquitectura Escalable

Cada servicio es independiente y puede desplegarse de manera aislada.

---

# Tecnologías Utilizadas

## Backend

- Java 21
- Spring Boot
- Spring Cloud Gateway
- Spring WebFlux
- Spring Data JPA
- Spring Kafka
- Server-Sent Events (SSE)
- Maven

## Frontend

- Angular
- TypeScript
- RxJS
- Standalone Components

## Infraestructura

- Docker
- Docker Compose
- Apache Kafka
- Zookeeper
- PostgreSQL

---

# Microservicios

## Order Service

Responsable de:

- Crear órdenes
- Persistir órdenes en PostgreSQL
- Publicar eventos order-created

## Inventory Service

Responsable de:

- Consumir eventos order-created
- Reservar stock
- Publicar eventos stock-reserved

## Payment Service

Responsable de:

- Consumir eventos stock-reserved
- Procesar pagos
- Publicar eventos payment-completed

## Notification Service

Responsable de:

- Consumir eventos Kafka
- Enviar actualizaciones realtime mediante SSE

## API Gateway

Responsable de:

- Centralizar rutas
- Gestionar CORS
- Punto de entrada único para frontend

---

# Comunicación en Tiempo Real

El frontend se conecta mediante Server-Sent Events (SSE):

```typescript
const eventSource = new EventSource(
  'http://localhost:8080/realtime/events'
);
```

Esto permite actualizar el dashboard en tiempo real sin polling.

---

# Ejecución del Proyecto

## Clonar repositorio

```bash
git clone https://github.com/raknar9/smartshop-event-commerce.git
```

## Ejecutar Docker Compose

```bash
docker compose up --build
```

---

# Servicios y Puertos

| Servicio | Puerto |
|---|---|
| Frontend | 80 |
| API Gateway | 8080 |
| Order Service | 8081 |
| Inventory Service | 8082 |
| Notification Service | 8083 |
| Payment Service | 8084 |
| PostgreSQL | 5432 |
| Kafka | 9092 |

---

# Conceptos Aplicados

Este proyecto refuerza conocimientos sobre:

- Arquitectura distribuida
- Event-Driven Architecture
- Kafka Event Streaming
- Comunicación realtime con SSE
- API Gateway
- Docker Networking
- Microservicios
- Spring Cloud
- Procesamiento asíncrono
- Sincronización realtime frontend/backend

---

# Mejoras Futuras

- JWT Authentication
- Spring Security
- Kubernetes
- CI/CD
- Redis Cache
- Prometheus + Grafana
- Eureka Service Discovery
- AWS Deployment
- Circuit Breakers
- WebSockets

---

# Autor

Miguel Aguilera

Desarrollador Backend Java especializado en Spring Boot, Microservicios y Arquitectura Cloud.