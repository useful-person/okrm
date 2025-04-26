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

### 如何为不同的密码使用不同的密钥：

可以为不同的敏感信息（如数据库密码、Redis 密码、MQ 密码）使用不同的密钥进行加密和解密。这可以通过 Jasypt 的配置来实现，为每个密码配置不同的密钥。


1. **配置多个加密器**：你可以在 Spring Boot 配置文件中设置多个 Jasypt 加密器，每个加密器使用不同的密钥。
   
2. **使用不同的配置文件或环境变量**：可以通过环境变量或者命令行参数来指定不同的密钥，确保每个敏感信息使用不同的加密密钥进行解密。

### 步骤 1：为不同的密码设置不同的加密密钥

首先，你需要为每个密码分别生成加密密钥，并进行加密。

例如，使用 Jasypt 加密工具为不同的密码生成加密后的值。

- **数据库密码加密**：
   ```bash
   mvn jasypt:encrypt -Djasypt.encryptor.password=dbSecretKey -Djasypt.plugin.value="databasePassword"
   ```

- **Redis 密码加密**：
   ```bash
   mvn jasypt:encrypt -Djasypt.encryptor.password=redisSecretKey -Djasypt.plugin.value="redisPassword"
   ```

- **MQ 密码加密**：
   ```bash
   mvn jasypt:encrypt -Djasypt.encryptor.password=mqSecretKey -Djasypt.plugin.value="mqPassword"
   ```

这样，你就得到了每个密码的加密值，类似于：

- `ENC(WDYgHWcc1tNPwrX5vHV/bLhMOjokAensnK9nUnUKup8UGsL41zjjbfTpBFHaaqBJ)`（数据库密码）
- `ENC(WDYgHWcc1tNPwrX5vHV/bLhMOjokAensnK9nUnUKup8UGsL41zjjbfTpBFHaaqBJ)`（Redis 密码）
- `ENC(WDYgHWcc1tNPwrX5vHV/bLhMOjokAensnK9nUnUKup8UGsL41zjjbfTpBFHaaqBJ)`（MQ 密码）

### 步骤 2：在配置文件中使用不同的密钥进行解密

在你的 Spring Boot 配置文件中，你可以为每个密码设置不同的解密密钥。这样，Spring Boot 会根据你指定的密钥分别解密各个密码。

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: myuser
    password: ENC(WDYgHWcc1tNPwrX5vHV/bLhMOjokAensnK9nUnUKup8UGsL41zjjbfTpBFHaaqBJ) # 数据库密码加密

  redis:
    host: localhost
    port: 6379
    password: ENC(WDYgHWcc1tNPwrX5vHV/bLhMOjokAensnK9nUnUKup8UGsL41zjjbfTpBFHaaqBJ) # Redis 密码加密

  mq:
    host: localhost
    port: 5672
    username: mquser
    password: ENC(WDYgHWcc1tNPwrX5vHV/bLhMOjokAensnK9nUnUKup8UGsL41zjjbfTpBFHaaqBJ) # MQ 密码加密
```

### 步骤 3：在 `application.yml` 中配置多个密钥解密器

要使用不同的密钥解密这些密码，可以通过 `@Value` 注解或者 `Spring Boot` 的 `jasypt` 配置来实现。例如，你可以为每个加密的配置使用不同的解密密钥：

```yaml
jasypt:
  encryptor:
    password:
      db: dbSecretKey         # 数据库密钥
      redis: redisSecretKey   # Redis 密钥
      mq: mqSecretKey         # MQ 密钥
```

如果你想要在同一个应用中使用不同的密钥，你可以手动配置多个解密器：

```java
@Configuration
public class JasyptConfig {

    @Bean
    public StandardPBEStringEncryptor dbEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("dbSecretKey"); // 数据库密码的密钥
        return encryptor;
    }

    @Bean
    public StandardPBEStringEncryptor redisEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("redisSecretKey"); // Redis 密码的密钥
        return encryptor;
    }

    @Bean
    public StandardPBEStringEncryptor mqEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("mqSecretKey"); // MQ 密码的密钥
        return encryptor;
    }
}
```

### 步骤 4：在应用启动时指定密钥

如果你在应用启动时使用不同的密钥来解密，你可以通过环境变量或命令行参数来指定每个密钥：

```bash
java -Djasypt.encryptor.password.db=dbSecretKey \
     -Djasypt.encryptor.password.redis=redisSecretKey \
     -Djasypt.encryptor.password.mq=mqSecretKey \
     -jar your-app.jar
```

这样，你的应用将使用不同的密钥来解密不同的密码。

### 总结：

1. **为每个敏感信息使用不同的密钥加密**：你可以使用 Jasypt 对数据库密码、Redis 密码、MQ 密码分别加密，并将它们放到配置文件中，使用 `ENC(...)` 格式存储。
   
2. **为每个密码使用不同的密钥解密**：通过配置文件或启动时的参数来指定不同的密钥解密不同的密码。

3. **Jasypt 配置**：你可以为每个敏感信息创建不同的解密器，并在 Spring Boot 配置文件中进行配置。
