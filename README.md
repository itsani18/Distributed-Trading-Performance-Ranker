# Distributed-Trading-Performance-Ranker

A distributed benchmarking infrastructure for evaluating algorithmic trading systems under concurrent workloads.

The platform enables isolated deployment, execution, benchmarking, and performance analysis of trading systems using containerized sandbox environments and low-latency inter-service communication.

---

# Architecture Diagram

```text
                                   ┌──────────────────────┐
                                   │       Client         │
                                   └──────────┬───────────┘
                                              │
                                        HTTP / REST
                                              │
                                              ▼
                           ┌──────────────────────────────┐
                           │     Submission Service       │
                           │------------------------------│
                           │ • Accept submissions         │
                           │ • Validate metadata          │
                           │ • Trigger deployments        │
                           └──────────┬───────────────────┘
                                      │
                               gRPC + Protobuf
                                      │
                                      ▼
                           ┌──────────────────────────────┐
                           │       Sandbox Service        │
                           │------------------------------│
                           │ • Build Docker images        │
                           │ • Run isolated containers    │
                           │ • Manage execution runtime   │
                           └──────────┬───────────────────┘
                                      │
                                      │ Docker
                                      ▼
                    ┌───────────────────────────────────────┐
                    │  Containerized Trading Environment    │
                    │---------------------------------------│
                    │ • Isolated execution                  │
                    │ • Concurrent workloads                │
                    │ • Runtime sandboxing                  │
                    └──────────┬────────────────────────────┘
                               │
                               │ gRPC
                               ▼
                    ┌───────────────────────────────────────┐
                    │    Load Generator Service             │
                    │---------------------------------------│
                    │ • Generate benchmark traffic          │
                    │ • Simulate trading load               │
                    │ • Measure latency                     │
                    └──────────┬────────────────────────────┘
                               │
                               │ Kafka Event Streams
                               ▼
                    ┌───────────────────────────────────────┐
                    │         Kafka Event Bus               │
                    └──────────┬────────────────────────────┘
                               │
              ┌────────────────┴────────────────┐
              ▼                                 ▼

    ┌──────────────────────┐       ┌──────────────────────┐
    │  Validation Service  │       │    Metrics Service   │
    │----------------------│       │----------------------│
    │ • Validate results   │       │ • Aggregate metrics  │
    │ • Detect anomalies   │       │ • Generate rankings  │
    └──────────┬───────────┘       └──────────┬───────────┘
               │                              │
               └──────────────┬───────────────┘
                              ▼
                 ┌──────────────────────────┐
                 │     MySQL / Redis        │
                 │--------------------------│
                 │ • Metrics storage        │
                 │ • Rankings               │
                 │ • Benchmark results      │
                 └──────────────────────────┘
```

---

# Core Features

- Distributed microservice architecture
- Containerized sandbox execution using Docker
- Low-latency inter-service communication using gRPC
- Protocol Buffer based service contracts
- Benchmark-driven performance evaluation
- Concurrent execution workflow orchestration
- Scalable deployment pipeline
- Isolated runtime environments for trading systems
- Kafka-based asynchronous event streaming
- Real-time ranking and metrics aggregation

---

# Microservices

## Submission Service

Handles user submissions and coordinates deployment requests.

### Responsibilities

- Accept trading system submissions
- Validate request metadata
- Communicate with Sandbox Service via gRPC
- Manage deployment workflow
- Forward benchmark requests

---

## Sandbox Service

Responsible for isolated execution and deployment orchestration.

### Responsibilities

- Build Docker images dynamically
- Run isolated containers
- Allocate execution environments
- Coordinate benchmark execution
- Monitor deployment status
- Handle runtime isolation

---

## Load Generator Service 

Simulates concurrent benchmark traffic and trading workloads.

### Responsibilities

- Generate concurrent requests
- Simulate trading workloads
- Measure throughput and latency
- Stress test deployed systems

---

## Validation Service 

Validates execution correctness and benchmark behavior.

### Responsibilities

- Validate execution results
- Detect anomalies and failures
- Verify benchmark integrity
- Ensure runtime correctness

---

## Metrics Service (Planned)

Aggregates benchmark metrics and leaderboard rankings.

### Responsibilities

- Aggregate performance metrics
- Compute rankings
- Store benchmark statistics
- Generate leaderboard data

---

# Technologies Used

## Backend / Systems

- Java
- Spring Boot
- gRPC
- Protocol Buffers
- Kafka
- REST APIs

---

## Infrastructure

- Docker
- AWS
- Maven

---

## Databases

- MySQL


---

# Systems Concepts

- Distributed Systems
- Multithreading
- Concurrency
- TCP/IP Communication
- Inter-Service Communication
- Containerized Execution
- Benchmarking Infrastructure
- Runtime Isolation
- Event-Driven Architecture

---

# Communication Flow

1. Client submits trading system
2. Submission Service validates request
3. gRPC request sent to Sandbox Service
4. Sandbox Service creates isolated Docker container
5. Load Generator simulates benchmark traffic
6. Kafka streams benchmark events
7. Validation Service verifies execution
8. Metrics Service computes rankings
9. Leaderboard results are generated

---

# Future Improvements

- Kubernetes deployment
- Distributed worker orchestration
- Advanced ranking algorithms
- Real-time monitoring dashboards
- Auto-scaling benchmark infrastructure
- High-frequency trading simulation engine
- Latency heatmap visualization
