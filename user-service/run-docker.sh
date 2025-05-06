#!/bin/bash

# 提示用户输入必要的环境变量，且提供默认值
read -p "请输入数据库URL (默认值: jdbc:mysql://mysql:3306/user_db): " SPRING_DATASOURCE_URL
SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL:-"jdbc:mysql://mysql:3306/user_db"}

read -p "请输入数据库用户名 (默认值: myuser): " SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME:-"myuser"}

read -s -p "请输入数据库密码（密文形式）（默认值: ENC(YourDefaultEncryptedPassword)）: " SPRING_DATASOURCE_PASSWORD
echo
SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD:-"ENC(YourDefaultEncryptedPassword)"}

read -p "请输入JPA Hibernate DDL模式 (例如 update, 默认值: update): " SPRING_JPA_HIBERNATE_DDL_AUTO
SPRING_JPA_HIBERNATE_DDL_AUTO=${SPRING_JPA_HIBERNATE_DDL_AUTO:-"update"}

read -p "请输入是否启用Docker Compose (true/false, 默认值: false): " SPRING_DOCKER_COMPOSE_ENABLED
SPRING_DOCKER_COMPOSE_ENABLED=${SPRING_DOCKER_COMPOSE_ENABLED:-"false"}

# 新增 - 读取Spring激活配置
read -p "请输入Spring Profiles激活环境 (例如 dev, prod，默认值: dev): " SPRING_PROFILES_ACTIVE
SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE:-"dev"}

# 新增 - 读取并脱敏Jasypt加密密钥
read -s -p "请输入Jasypt加密器密码（用于解密ENC()的密码）: " JASYPT_ENCRYPTOR_PASSWORD
echo
# 注意这里不加默认值，强制要求输入
if [ -z "$JASYPT_ENCRYPTOR_PASSWORD" ]; then
  echo "Jasypt加密器密码不能为空！"
  exit 1
fi

# 输出默认值，方便用户确认（敏感信息用****隐藏）
echo "以下是您选择的配置："
echo "数据库URL: $SPRING_DATASOURCE_URL"
echo "数据库用户名: $SPRING_DATASOURCE_USERNAME"
echo "数据库密码: $SPRING_DATASOURCE_PASSWORD"
echo "JPA Hibernate DDL模式: $SPRING_JPA_HIBERNATE_DDL_AUTO"
echo "启用Docker Compose: $SPRING_DOCKER_COMPOSE_ENABLED"
echo "Spring Profiles激活环境: $SPRING_PROFILES_ACTIVE"
echo "Jasypt加密器密码: ******（已隐藏）"

# 运行Docker容器
docker run -d \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL="$SPRING_DATASOURCE_URL" \
  -e SPRING_DATASOURCE_USERNAME="$SPRING_DATASOURCE_USERNAME" \
  -e SPRING_DATASOURCE_PASSWORD="$SPRING_DATASOURCE_PASSWORD" \
  -e SPRING_JPA_HIBERNATE_DDL_AUTO="$SPRING_JPA_HIBERNATE_DDL_AUTO" \
  -e SPRING_DOCKER_COMPOSE_ENABLED="$SPRING_DOCKER_COMPOSE_ENABLED" \
  -e SPRING_PROFILES_ACTIVE="$SPRING_PROFILES_ACTIVE" \
  -e JASYPT_ENCRYPTOR_PASSWORD="$JASYPT_ENCRYPTOR_PASSWORD" \
  user-service:latest
