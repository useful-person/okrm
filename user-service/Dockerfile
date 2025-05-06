# 使用 JDK 作为基础镜像（假设 Spring Boot 需要 Java 运行环境）
FROM openjdk:25-ea-jdk-slim-bullseye

# 设置工作目录
WORKDIR /app

# 复制项目构建好的JAR包
COPY target/user-service-0.0.1-SNAPSHOT.jar app.jar

# 暴露应用端口（假设Spring Boot默认是11000）
EXPOSE 11000

# 运行 Spring Boot 应用，Spring Boot自己读取外部传进来的环境变量
ENTRYPOINT ["java", "-jar", "app.jar"]
