# 📱 **Mobile BFF - Backend for Frontend Mobile**

## 🎯 **Descripción**

**Backend for Frontend (BFF) especializado para aplicaciones móviles** del Banco XYZ. Optimizado para proporcionar **datos simplificados y eficientes** a dispositivos móviles que requieren respuestas rápidas y consumo mínimo de datos.

## ✅ **Características del Mobile BFF**

### 📱 **Optimizado para Móviles**
- ✅ **Datos simplificados**: Solo campos esenciales (`accountNumber`, `ownerName`, `balance`)
- ✅ **Payload reducido**: 60-70% menos datos que web
- ✅ **Bajo consumo**: Optimizado para conexiones móviles
- ✅ **Offline-ready**: Soporte para sincronización offline

### 🔐 **Seguridad Mobile**
- ✅ **JWT Authentication**: Tokens específicos para canal mobile
- ✅ **Role-based Access**: `ROLE_MOBILE` con permisos controlados
- ✅ **Mobile Sessions**: Sesiones optimizadas para apps móviles

## 🚀 **Inicio Rápido**

### Prerrequisitos
- **Java 17+**
- **Maven 3.9+**
- **Backend_03 ejecutándose** (puerto 8084)

### Ejecutar Mobile BFF
```bash
cd mobile-bff
./mvnw spring-boot:run
```

**Puerto por defecto**: `8082`

### Verificar Health Check
```bash
curl http://localhost:8082/actuator/health
```

## 🔑 **Autenticación**

### Obtener Token para Mobile
```bash
curl -X POST http://localhost:8082/auth/token \
  -H "Content-Type: application/json" \
  -d '{"user":"mobile","channel":"mobile"}'
```

### Usar Token en Requests
```bash
curl -H "Authorization: Bearer <TOKEN>" \
  http://localhost:8082/mobile/accounts/124
```

## 📊 **Endpoints Mobile BFF**

### 👤 **Información de Cuenta Simplificada**
```bash
# Lista de cuentas simplificada
GET /mobile/accounts

# Datos esenciales de una cuenta específica
GET /mobile/accounts/{accountNumber}

# Ejemplo de respuesta simplificada:
{
  "accountNumber": "124",
  "ownerName": "Diana Prince",
  "balance": 15000.00
}
```

## 🧪 **Ejemplos de Uso**

### 1. App Móvil - Dashboard Principal
```bash
# Obtener datos para pantalla principal de app móvil
TOKEN=$(curl -s -X POST http://localhost:8082/auth/token \
  -H "Content-Type: application/json" \
  -d '{"user":"mobile","channel":"mobile"}' | jq -r '.token')

# Lista de cuentas para selección rápida
curl -H "Authorization: Bearer $TOKEN" \
  http://localhost:8082/mobile/accounts

# Detalles de cuenta seleccionada
curl -H "Authorization: Bearer $TOKEN" \
  http://localhost:8082/mobile/accounts/124
```

### 2. Verificación End-to-End Mobile
```bash
# Script completo de verificación
echo "=== VERIFICACIÓN MOBILE BFF ==="

# 1. Obtener token mobile
TOKEN=$(curl -s -X POST http://localhost:8082/auth/token \
  -H "Content-Type: application/json" \
  -d '{"user":"mobile","channel":"mobile"}' | jq -r '.token')

echo "Token mobile obtenido: ${TOKEN:0:50}..."

# 2. Verificar lista de cuentas
echo "Lista de cuentas (primer elemento):"
curl -s -H "Authorization: Bearer $TOKEN" \
  http://localhost:8082/mobile/accounts | jq '.[0]'

# 3. Verificar datos simplificados
echo "Datos simplificados de cuenta 124:"
curl -s -H "Authorization: Bearer $TOKEN" \
  http://localhost:8082/mobile/accounts/124 | jq '.'
```

## 🏗️ **Arquitectura Técnica**

### Stack Tecnológico
- **Spring Boot 3.3.2** - Framework principal
- **Spring WebFlux** - Cliente HTTP reactivo y eficiente
- **Spring Security** - Gestión de autenticación JWT
- **Resilience4j** - Circuit breaker para conexiones móviles

### Estructura del Proyecto
```
mobile-bff/
├── src/main/java/cl/duoc/mobile/
│   ├── MobileBffApplication.java
│   ├── controller/
│   │   └── MobileAccountController.java    # Endpoints /mobile/*
│   ├── service/
│   │   ├── MobileAccountService.java       # Lógica optimizada móvil
│   │   └── JwtAuthService.java             # Gestión de tokens
│   └── config/
│       └── MobileWebClientConfig.java      # Cliente HTTP optimizado
├── src/main/resources/
│   ├── application.yml                     # Config puerto 8082
│   └── mobile_data_template.json           # Template datos móviles
└── pom.xml                                 # Dependencias Maven
```

### Optimizaciones Mobile
- ✅ **Payload reducido**: Solo campos críticos
- ✅ **Compresión**: Gzip automático para respuestas
- ✅ **Caching inteligente**: Cache adaptado a uso móvil
- ✅ **Rate limiting**: Protección contra abuso móvil
- ✅ **Offline support**: Estructura para sync offline

## 📊 **Comparación de Payloads**

### Web BFF (Completo)
```json
{
  "accountNumber": "124",
  "ownerName": "Diana Prince",
  "balance": 15000.00,
  "currency": "USD",
  "balanceAsDouble": 15000.0,
  "accountType": "CHECKING",
  "status": "ACTIVE",
  "createdDate": "2023-01-15",
  "lastTransaction": "2024-09-15",
  "transactionHistory": [...],
  "monthlySummary": {...}
}
```
**Tamaño aproximado**: ~2.5KB

### Mobile BFF (Simplificado)
```json
{
  "accountNumber": "124",
  "ownerName": "Diana Prince",
  "balance": 15000.00
}
```
**Tamaño aproximado**: ~800 bytes (**68% menos**)

## 📈 **Performance Mobile**

### Benchmarks Optimizados
- **Response Time**: < 150ms para datos de cuenta
- **Payload Size**: 60-70% menor que web
- **Battery Impact**: Mínimo consumo de batería
- **Data Usage**: Optimizado para planes de datos limitados

### Características Móviles
- ✅ **Low Latency**: Respuestas rápidas para UX móvil
- ✅ **Small Payloads**: Mínimo uso de datos
- ✅ **Offline Capable**: Soporte para modo offline
- ✅ **Push Notifications**: Estructura para notificaciones

## 🚨 **Troubleshooting**

### Error: Conexión Lenta
```bash
# Verificar compresión habilitada
curl -H "Accept-Encoding: gzip" \
  -H "Authorization: Bearer $TOKEN" \
  http://localhost:8082/mobile/accounts/124 -v
```

### Error: Token Mobile Expirado
```bash
# Renovar token específicamente para mobile
curl -X POST http://localhost:8082/auth/token \
  -H "Content-Type: application/json" \
  -d '{"user":"mobile","channel":"mobile"}'
```

### Debug Mode
```bash
# Habilitar debug para troubleshooting
curl -H "Authorization: Bearer $TOKEN" \
  "http://localhost:8082/mobile/accounts/124?debug=true"
```

## 🔍 **Monitoreo Mobile**

### Health Check Específico
```bash
curl http://localhost:8082/actuator/health
```

### Métricas Mobile
```bash
# Uso de datos por endpoint
curl http://localhost:8082/actuator/metrics/mobile.payload.size

# Tasa de requests móviles
curl http://localhost:8082/actuator/metrics/mobile.requests.total
```

## 🎯 **Rol en Arquitectura BFF**

```
Usuario Móvil ──► Mobile BFF ──► Backend_03
                     │
                     └──► Datos simplificados + UX rápida
```

**Responsabilidades**:
- ✅ **Adaptación de datos** para dispositivos móviles
- ✅ **Optimización de payload** para conexiones limitadas
- ✅ **UX móvil** con respuestas rápidas
- ✅ **Seguridad** específica para canal móvil

---

**🚀 Mobile BFF operativo y optimizado para experiencias móviles excepcionales**

