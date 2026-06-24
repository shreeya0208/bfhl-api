# Railway / Render deployment
# Build: ./mvnw clean package -DskipTests
# Start: java -jar target/bfhl-api-0.0.1-SNAPSHOT.jar

FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/bfhl-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
