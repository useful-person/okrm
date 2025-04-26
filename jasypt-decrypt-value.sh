read -p "请输入spring.profiles.active：" profile
read -s -p "请输入密钥：" key
echo
read -s -p "请输入密码：" password

echo "正在解密中..."
echo "解密密码为："

echo
mvn jasypt:decrypt-value \
	-Djasypt.encryptor.password=$key \
	-Djasypt.plugin.value=$password \
	-Dspring.profiles.active=$profile \
	-Dmaven.test.skip=true \
  | sed -n '/using default value: base64/,/\[INFO\] ------------------------------------------------------------------------/p' \
  | sed -n '3p'
