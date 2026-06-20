# Nacos 完整入门教程
[Nacos](https://nacos.io/docs/ebook/kbyo6n/?spm=5238cd80.2ef5001f.0.0.3f613b7cgxXmoE) 是阿里开源的**服务发现、配置管理、动态 DNS** 三合一微服务组件，是 Spring Cloud Alibaba 生态核心组件，支持单机/集群部署，兼容 Spring Boot、Spring Cloud、Dubbo 等主流框架。

## 一、核心概念
- **服务注册/发现**：微服务启动时向 Nacos 注册自身信息，调用方通过 Nacos 获取服务地址，实现服务间通信。
- **配置中心**：集中管理应用配置，支持配置动态推送、版本管理、灰度发布、一键回滚。
- **命名空间（Namespace）**：用于环境隔离（如 dev、test、prod），不同命名空间数据完全隔离。
- **分组（Group）**：同一命名空间内对服务/配置分组，默认 DEFAULT_GROUP。
- **数据ID（Data ID）**：配置唯一标识，通常对应配置文件名（如 application.yml）。

## 二、安装部署（3种方式）
### 方式1：Docker 部署（推荐，快速无依赖）
```bash
# 拉取最新稳定版镜像（3.1.1）
docker pull nacos/nacos-server:3.1.1

# 单机模式启动（开发环境）
docker run -d \
  --name nacos-standalone \
  -p 8848:8848 \
  -p 9848:9848 \
  -p 9849:9849 \
  -e MODE=standalone \
  -e JVM_XMS=512m \
  -e JVM_XMX=512m \
  nacos/nacos-server:3.1.1

# 验证启动
docker ps  # 查看容器状态为 Up
# 访问控制台：http://localhost:8848/nacos
# 默认账号/密码：nacos/nacos（3.0+需初始化密码）
```

### 方式2：二进制包部署（本地开发）
1. **下载**：[Nacos 官方下载页](https://github.com/alibaba/nacos/releases)，下载 `nacos-server-3.1.1.zip`（Windows）或 `.tar.gz`（Linux/Mac）。
2. **解压**：
   ```bash
   # Linux/Mac
   tar -zxvf nacos-server-3.1.1.tar.gz
   cd nacos/bin
   
   # Windows
   解压到无中文路径目录，进入 bin 文件夹
   ```
3. **启动（单机模式）**：
   ```bash
   # Linux/Mac
   sh startup.sh -m standalone
   
   # Windows
   startup.cmd -m standalone
   ```
4. **3.0+ 鉴权配置**：启动时需输入3项配置（可提前写入 `conf/application.properties`）：
   ```properties
   nacos.core.auth.plugin.nacos.token.secret.key=Base64加密密钥
   nacos.core.auth.server.identity.key=serverIdentity
   nacos.core.auth.server.identity.value=security
   ```
5. **验证**：访问 `http://localhost:8848/nacos`，初始化密码后登录。

### 方式3：源码编译部署（二次开发）
```bash
# 克隆源码
git clone https://github.com/alibaba/nacos.git
cd nacos

# 编译（跳过测试）
mvn -Prelease-nacos -Dmaven.test.skip=true clean install -U

# 启动
cd distribution/target/nacos-server-3.1.1/nacos/bin
sh startup.sh -m standalone  # Linux/Mac
# Windows 执行 startup.cmd -m standalone
```

## 三、控制台基础操作
1. **登录**：访问 `http://localhost:8848/nacos`，输入账号密码登录。
2. **命名空间管理**：左侧「命名空间」→「新建」，创建 dev、test、prod 环境隔离空间。
3. **配置管理**：
   - 左侧「配置管理」→「配置列表」→「+」新建配置。
   - 填写：Data ID（如 `application-dev.yml`）、Group（DEFAULT_GROUP）、配置内容（YAML/Properties）→「发布」。
4. **服务管理**：左侧「服务管理」→「服务列表」，查看已注册服务、实例健康状态、权重配置。

## 四、Spring Boot 集成实战
### 1. 配置中心（动态配置）
#### （1）添加依赖
```xml
<!-- Spring Cloud Alibaba Nacos Config -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
    <version>2023.0.1.0</version>
</dependency>
```

#### （2）配置文件（bootstrap.yml，优先级高于 application.yml）
```yaml
spring:
  application:
    name: nacos-config-demo  # 服务名，对应 Nacos Data ID 前缀
  cloud:
    nacos:
      config:
        server-addr: localhost:8848  # Nacos 地址
        namespace: dev  # 命名空间ID（控制台复制）
        group: DEFAULT_GROUP
        file-extension: yml  # 配置文件格式
  profiles:
    active: dev  # 环境标识，拼接 Data ID：nacos-config-demo-dev.yml
```

#### （3）动态配置使用
```java
@RestController
@RefreshScope  # 开启配置自动刷新
public class ConfigController {
    // 从 Nacos 读取配置
    @Value("${user.name:default}")
    private String userName;

    @Value("${user.age:0}")
    private Integer userAge;

    @GetMapping("/config")
    public Map<String, Object> getConfig() {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        map.put("userAge", userAge);
        return map;
    }
}
```

#### （4）Nacos 新建配置
- Data ID：`nacos-config-demo-dev.yml`
- Group：DEFAULT_GROUP
- 配置内容：
  ```yaml
  user:
    name: nacos-user
    age: 25
  ```
- 发布后，访问接口即可获取配置，修改 Nacos 配置后无需重启服务，自动刷新。

### 2. 服务注册与发现
#### （1）添加依赖
```xml
<!-- Spring Cloud Alibaba Nacos Discovery -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    <version>2023.0.1.0</version>
</dependency>
```

#### （2）配置文件（application.yml）
```yaml
spring:
  application:
    name: nacos-discovery-provider  # 服务提供者名称
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
        namespace: dev
```

#### （3）启动类开启服务发现
```java
@SpringBootApplication
@EnableDiscoveryClient  // 开启服务注册发现
public class NacosProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosProviderApplication.class, args);
    }
}
```

#### （4）服务提供者接口
```java
@RestController
@RequestMapping("/provider")
public class ProviderController {
    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return "Hello " + name + ", from Nacos Provider!";
    }
}
```

#### （5）服务消费者调用（使用 OpenFeign）
- 添加 Feign 依赖：
  ```xml
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-openfeign</artifactId>
      <version>4.1.0</version>
  </dependency>
  ```
- Feign 客户端接口：
  ```java
  @FeignClient("nacos-discovery-provider")  // 对应服务提供者名称
  public interface ProviderFeignClient {
      @GetMapping("/provider/hello")
      String hello(@RequestParam String name);
  }
  ```
- 消费者控制器：
  ```java
  @RestController
  @RequestMapping("/consumer")
  public class ConsumerController {
      @Autowired
      private ProviderFeignClient providerFeignClient;
  
      @GetMapping("/hello")
      public String hello(@RequestParam String name) {
          return providerFeignClient.hello(name);
      }
  }
  ```
- 启动消费者，访问 `http://localhost:端口/consumer/hello?name=nacos`，即可调用提供者服务。

## 五、集群部署（生产环境）
### 1. 环境准备
- 至少3台服务器（Nacos 集群推荐奇数节点）。
- 安装 MySQL 8.0+（Nacos 集群需持久化配置到 MySQL）。

### 2. 配置 MySQL
1. 执行 Nacos 提供的 SQL 脚本：`conf/mysql-schema.sql`，创建 `nacos_config` 数据库及表。
2. 修改 `conf/application.properties`，配置 MySQL 连接：
   ```properties
   spring.datasource.platform=mysql
   db.num=1
   db.url.0=jdbc:mysql://localhost:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useSSL=false
   db.user=root
   db.password=your-password
   ```

### 3. 配置集群节点
修改 `conf/cluster.conf`，添加所有集群节点 IP:端口：
```
192.168.1.100:8848
192.168.1.101:8848
192.168.1.102:8848
```

### 4. 启动集群
每台服务器执行启动命令（**不指定 -m standalone**，默认集群模式）：
```bash
# Linux/Mac
sh startup.sh

# Windows
startup.cmd
```

### 5. 验证集群
访问任意节点控制台，进入「集群管理 → 节点列表」，所有节点状态为 `UP` 即集群成功。

## 六、常见问题
1. **启动失败**：检查端口 8848/9848/9849 是否被占用；3.0+ 版本需正确配置鉴权参数。
2. **服务注册失败**：检查 Nacos 地址、命名空间、网络连通性；查看客户端日志。
3. **配置不刷新**：确保添加 `@RefreshScope` 注解；检查 Nacos 配置 Data ID、Group 与客户端一致。
4. **集群节点异常**：检查 `cluster.conf` 配置；确保 MySQL 连接正常；查看 `logs/naming.log` 日志。
