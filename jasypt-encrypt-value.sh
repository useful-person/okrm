read -p "请输入spring.profiles.active：" profile
read -t 300 -s -p "请输入密钥：" key
echo
read -s -p "请输入密码：" password
echo
mvn jasypt:encrypt-value \
	-Djasypt.encryptor.password=$key \
	-Djasypt.plugin.value=$password \
	-Dspring.profiles.active=$profile \
	-Dmaven.test.skip=true \
	| grep ENC
