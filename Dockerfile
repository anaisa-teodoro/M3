# --- ESTÁGIO 1: O "BUILDER" ---
FROM maven:3.8-openjdk-17 AS builder
WORKDIR /app

# 1. Copia SÓ os arquivos de definição do Maven
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# 2. Baixa todas as dependências do projeto
RUN ./mvnw dependency:go-offline

# 3. Copia o código-fonte do seu projeto
COPY src ./src

# 4. Compila o projeto e gera o .jar
RUN ./mvnw package -DskipTests

# --- ESTÁGIO 2: A IMAGEM "FINAL" ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
EXPOSE 8080

# Copia o .jar que foi gerado no Estágio 1
COPY --from=builder /app/target/*.jar app.jar

# Comando para iniciar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]