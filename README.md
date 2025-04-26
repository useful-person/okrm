# OKRM

## Maven操作

### 清理 & 构建

```shell
mvn clean install
```

等价于

```shell
mvn clean
mvn validate
mvn compile
mvn test
mvn package
mvn verify
mvn install
```

### 单元测试

```shell
mvn test
```

### 跳过单元测试

```shell
mvn clean install -DskipTests
```

### 运行 user-service

```bash
cd user-service
java -jar myapp.jar --spring.profiles.active=prod

mvn spring-boot:run
mvn spring-boot:run -Dspring-boot.run.profiles=prod

```

如果是用 IDE（比如 IntelliJ IDEA），也可以在 Run/Debug Configurations → VM Options 里加：

```
-Dspring.profiles.active=test
```

user-service 就可以使用 common-utils 里的工具类了。

### `mvn clean install -Dversion=0.0.1-SNAPSHOT` 为什么没有覆盖 `pom.xml` 里的 `version`  

这是因为 Maven `-Dversion` 只是一个临时的 System Property，它不会修改 `pom.xml` 里的 `version`。  

## 为什么 `-Dversion` 不会覆盖 `pom.xml` 里的 `version`  

1. Maven 运行时 `-Dversion` 只是一个临时参数，不会修改 `pom.xml`  
2. Maven 不会自动更新 `pom.xml`，因为 `version` 是一个 POM 元数据  
3. Maven 只会使用 `-Dversion` 影响某些插件（如 `versions:set`），但不会影响 `install`  

## 如何正确修改 `version`  

### 方法 1：手动修改 `pom.xml`  

```xml
<version>0.0.1-SNAPSHOT</version>
```  

然后运行：  

```bash
mvn clean install
```  

### 使用 `versions:set` 插件  

如果想自动修改 `pom.xml` 的 `version`，需要使用 `maven-versions-plugin`：  

```bash
mvn versions:set -DnewVersion=0.0.1-SNAPSHOT
```  

然后提交修改：  

```bash
mvn versions:commit
```  

这会真正修改 `pom.xml` 里的 `version`，然后再运行：  

```bash
mvn clean install
```  

### 临时指定版本（不修改 `pom.xml`）  

如果只是想在一次构建中使用临时版本，而不修改 `pom.xml`：  

```bash
mvn clean install -Drevision=0.0.1-SNAPSHOT
```  

这个方法适用于 `pom.xml` 使用 `revision` 变量：  

```xml
<version>${revision}</version>
```  

这样 `-Drevision=0.0.1-SNAPSHOT` 就能生效。  

### 总结  

| 方法 | 是否修改 `pom.xml` | 是否影响 `install` |  
|---------|----------------|------------------|  
| `-Dversion=xxx` | ❌ 否 | ❌ 否 |  
| `versions:set` | ✅ 是 | ✅ 是 |  
| 直接改 `pom.xml` | ✅ 是 | ✅ 是 |  
| `-Drevision=xxx`（如果 `pom.xml` 使用 `revision` 变量） | ❌否 | ✅是 |  

如果想真正修改 `version`，推荐 `mvn versions:set -DnewVersion=xxx`。

cd user-service
mvn spring-boot:run

启用docker

```sh
# 是否启用docker
spring.docker.compose.enabled=true
```


## 使用说明

安装IDE，修改IDE编码格式为UTF-8

安装mysql 8.x

运行redis

安装docker

docker pull redis

docker run --name okrm-redis -d redis

或者直接下载redis

make

make test

make install

make test

./src/redis-server

新建mysql用户okrm并给它赋予权限

安装lombok

maven构建

maven构建

```shell
mvn clean install package -Dmaven.test.skip=true -Pprod
```


## 介绍

okrm-server基于spring全家桶开发。

## 软件架构

```mermaid
graph TD;
	useful-person.com-->okrm-server;
	okrm-server-->sms;
	okrm-server-->mail;
	okrm-server-->cache;
	okrm-server-->mysql;
	okrm-server-->oss;
	sms-->redis;
	mail-->redis;
	cache-->redis;
```






## 安装教程

### 数据库密码加密

```shell
# 加密
mvn jasypt:encrypt-value -Djasypt.encryptor.password="密钥（仅限ascii格式）" -Djasypt.plugin.value="密码明文"
# 可以直接在配置文件中配置DEC(123456)，使用DEC(密码明文)，执行下面的命令后，会将配置文件中的DEC(密码明文)替换成ENC(加密后的密码)
mvn jasypt:encrypt -Djasypt.plugin.path="file:useful-person.okrm-server/src/main/resources/application.yml" -Djasypt.encryptor.password="密钥"
# 解密
mvn jasypt:decrypt-value -Djasypt.encryptor.password="密钥" -Djasypt.plugin.value="密文"
```
也可以直接使用jar包进行加解密

```shell
java -cp jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="root" password=security algorithm=PBEWithMD5AndDES

java -cp jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI input="i00VogiiZ1FpZR9McY7XNw==" password=security algorithm=PBEWithMD5AndD
```


### MySQL

使用root用户使用utf8mb4字符集创建okrm库，创建用户名okrm密码okrm_password授权dba、数据库okrm，

使用okrm-core/src/main/resources/sql中的sql初始化持续登录的表和用户连接信息的表

### Redis

在项目根目录使用docker-compose启动redis

```shell
docker-compose up -d
docker-compose stop
docker-compose restart
docker-compose rm
```

### 服务器目录结构

```
# okrm-server
/home/okrm
/home/okrm/goaloneService.sh
/home/okrm/servicespace
/home/okrm/okrm-server
# useful-person.com
/home/okrm/www/useful-person.com
```

### nginx配置

```
location / {
    root /home/okrm/www/useful-person.com;
    index index.html index.htm;
}
location /api/ {
    proxy_pass http://127.0.0.1:8081/;
    # 获取客户端真实ip $host 变量，Host 为变量名
    proxy_set_header   Host             $host;
    proxy_set_header   X-Real-IP        $remote_addr;
    proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;
}
```

### 证书配置
nginx配置目录`/root/nginx/conf`
将证书上传到目录`/root/nginx/conf/cert`
```shell
scp ~/Downloads/6079910_useful-person.com_nginx/6079910_useful-person.com.* root@121.40.244.200:/root/nginx/conf/cert/
```
修改对应的conf文件，将指定正确的证书地址
```shell
```
重启nginx
```shell
/root/nginx/nginx -s reload
```
访问浏览，刷新证书

