# 使用 JDK 作为基础镜像（假设 Spring Boot 需要 Java 运行环境）
FROM openjdk:24-ea-17-jdk-slim-bullseye

# 设置工作目录
WORKDIR /app

# 复制项目代码（假设已经构建了 JAR 文件）
COPY target/user-service-0.0.1-SNAPSHOT.jar app.jar

# 设置环境变量
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/user_db
ENV SPRING_DATASOURCE_USERNAME=myuser
ENV SPRING_DATASOURCE_PASSWORD=secret
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_DOCKER_COMPOSE_ENABLED=false

# 暴露端口
EXPOSE 8080

# 运行 Spring Boot 应用
ENTRYPOINT ["java", "-jar", "app.jar"]
