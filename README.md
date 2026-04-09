# Automated Greenhouse Management System (AGMS)

A microservices-based application designed to monitor and automate greenhouse operations.

---

## 🚀 Execution Order (CRITICAL)

To start the system correctly, you must follow this specific order:

### Phase 1: Infrastructure (Wait for each to fully start)

1. **Config-Server (Port 8888):** Provides centralized configuration.
2. **Eureka-Server (Port 8761):** Service Registry. Check `http://localhost:8761` to ensure it's up.
3. **API-Gateway (Port 8080):** Entry point for all requests.

### Phase 2: Domain Services

4. **Identity-Service:** Required for JWT authentication.
5. **Zone-Management-Service:** Core infrastructure management.
6. **Crop-Management-Service:** Biology and plant tracking.
7. **Sensor-Telemetry-Service:** Real-time monitoring logic.
8. **Automation-Control-Service:** Device triggering and logging.

---

## 🛠 Prerequisites

- Java 21+  
- MySQL 8.x  
- Maven  
- Git (for Config Server)

---

## ⚙️ Configuration Repository

The AGMS system uses an external centralized configuration repository:

🔗 https://github.com/WKBASFernando/agms-config-repo.git

### 📌 Description

This repository acts as the **central configuration source** for all microservices using the **Spring Cloud Config Server**. It contains environment-specific configuration files (e.g., `application.yml`, service-specific YAML files) that are dynamically loaded by each service at runtime.

### ✅ Key Features

- Centralized configuration management  
- Environment-based configs (dev, prod, etc.)  
- Easy updates without rebuilding services  
- Keeps sensitive and service-specific settings organized  

> ⚠️ Make sure this repository is accessible and properly linked in your **Config-Server** (`application.yml`) before starting the system.

---

## 📡 Gateway Routes

| Service | API Path |
| :--- | :--- |
| Identity | `/api/v1/auth/**` |
| Zones | `/api/v1/zones/**` |
| Crops | `/api/v1/crops/**` |
| Telemetry | `/api/v1/telemetry/**` |
| Automation | `/api/v1/automation/**` |
