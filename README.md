# Exp2_S5 - Backend for Frontend (BFF) - Spring Boot (Java)

## Description
This project is a **sample** implementation of the Backend for Frontend (BFF) pattern for three channels:
- Web BFF (`/web/*`) — returns full account information.
- Mobile BFF (`/mobile/*`) — returns lightweight responses (reduced payload).
- ATM BFF (`/atm/*`) — exposes only critical operations (balance, transactions).

It uses a small sample dataset (`src/main/resources/bank_legacy_data_sample.json`) that simulates the `bank_legacy_data` referenced in the assignment.

## Security
A minimal JWT-based authentication is provided:
- Obtain a token: `POST /auth/token` with JSON `{ "user":"alice", "channel":"web" }`
- The `channel` must be one of `web`, `mobile`, `atm` and maps to roles `ROLE_WEB`, `ROLE_MOBILE`, `ROLE_ATM`.
- Then call endpoints with header `Authorization: Bearer <token>`.

**Note:** This demo uses a hard-coded signing key for simplicity. Do NOT use this in production.

## Run locally
Requirements: Java 17+, Maven.

From project root:

```bash
# build
mvn -q -DskipTests package

# run
java -jar target/bff-springboot-1.0.0.jar
```

By default the server runs on port `8080`.

## HTTPS (optional)
To enable HTTPS for demonstration, create a Java keystore and add to `application.yml`:
```yaml
server:
  ssl:
    enabled: true
    key-store: classpath/keystore.p12
    key-store-password: changeit
    key-store-type: PKCS12
```
Then place `keystore.p12` into `src/main/resources`. See official Spring Boot docs for creating a self-signed keystore.

## Project structure
- `src/main/java/cl/duoc/bff` — main application and controllers
- `src/main/resources/bank_legacy_data_sample.json` — sample data
- Security: simple JWT utils and filter (demo-only)

## Evidence of execution
If you run the application you can:
1. `curl -s -X POST http://localhost:8080/auth/token -H "Content-Type: application/json" -d '{"user":"alice","channel":"web"}'`
2. Use returned token to call: `curl -H "Authorization: Bearer <token>" http://localhost:8080/web/accounts`

## Notes to instructor / student
- This is a compact, single-repository implementation of three BFFs for demonstration and evaluation purposes.
- For a production-like submission you may split each BFF into independent microservices repositories, add HTTPS with a proper certificate, and connect to the real `bank_legacy_data` dataset.

