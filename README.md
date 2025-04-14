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
mvn spring-boot:run
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
