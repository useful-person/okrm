#!/bin/bash
# start.sh

if [ $# -lt 1 ]; then
  echo "用法: $0 <jar文件名>"
  exit 1
fi

jar_file=$1

read -p "请输入环境（比如 prod/test/dev）：" profile
read -s -p "请输入解密密钥：" key
echo
read -s -p "请输入密码(带ENC)：" password
echo
echo "正在启动服务..."
java -jar $jar_file \
  --spring.profiles.active=$profile \
  --jasypt.encryptor.password=$key \
  --spring.datasource.password=$password
