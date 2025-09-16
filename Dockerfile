FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . .
RUN ./mvnw -q -DskipTests package
CMD ["java","-jar","target/bff-springboot-1.0.0.jar"]
