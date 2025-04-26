#!/bin/bash

# 读取 spring.profiles.active
read -p "请输入spring.profiles.active：" profile

# 读取密钥
read -t 300 -s -p "请输入密钥：" key
echo

# 判断profile，决定使用哪个yml文件
if [ -z "$profile" ]; then
    yml_file="application.yml"
else
    yml_file="application-$profile.yml"
fi

# 打印提示信息
echo "正在使用配置文件: $yml_file"

# 执行mvn命令
mvn clean install \
  -Djasypt.encryptor.password=$key \
  -Djasypt.plugin.path="file:user-service/src/main/resources/$yml_file" \
  -Dspring.profiles.active=$profile
