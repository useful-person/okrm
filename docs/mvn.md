# Maven 仓库配置指南

为保证团队成员在构建和发布项目时能够访问公司私有仓库（阿里云 Maven 仓库），请按如下说明配置 Maven。
maven 由[阿里云云效](https://packages.aliyun.com/)友情赞助

---

## 项目中统一配置（自动加载）

项目已经提供了统一的 Maven 仓库配置文件：

```
.your-project/
├── .mvn/
│   └── settings.xml   ← 项目级 Maven 配置，已配置仓库地址
```

此文件配置了：

| 仓库类型 | 说明           | 地址                                                                                 |
| -------- | -------------- | ------------------------------------------------------------------------------------ |
| Snapshot | 开发中快照版本 | `https://packages.aliyun.com/61fb5dd3c5006adb19fe45f9/maven/2184455-snapshot-m1elwf` |
| Release  | 发布稳定版本   | `https://packages.aliyun.com/61fb5dd3c5006adb19fe45f9/maven/2184455-release-jka6nb`  |

⚠️ **该配置文件中不包含用户名密码**，需开发者手动配置本地认证信息。

---

## 开发者本地认证配置（必须）

请每位开发者手动配置你本地的 Maven 认证信息：

### 修改/创建 `~/.m2/settings.xml`

```xml
<settings>
  <servers>
    <!-- Release 仓库认证 -->
    <server>
      <id>2184455-snapshot-M1Elwf</id>
      <username>你的账号（例如`61fb5dc3d748dff2a3ea6c86`）</username>
      <password>你的密码</password>
    </server>

    <!-- Snapshot 仓库认证 -->
    <server>
      <id>2184455-snapshot-M1Elwf</id>
      <username>你的账号（例如`61fb5dc3d748dff2a3ea6c86`）</username>
      <password>你的密码</password>
    </server>
  </servers>
</settings>
```

💡 `id` 必须和 `.mvn/settings.xml` 中定义的 `<repository><id>` 完全一致，大小写敏感。

---

## 密码安全建议（可选）

不建议将账号密码明文提交到 Git，可使用以下方式增强安全：

- 加密密码：使用 `mvn --encrypt-password` 和 `settings-security.xml`
- CI/CD 场景：使用环境变量注入认证信息（如 GitHub Secrets）

或者账号密码只配置在本地 `.m2/settings.xml` 中，不提交到 Git。

---

## 部署发布说明（适用于发布人员）

如果你有发布快照/正式版本需求，请确保：

1. 本地 `.m2/settings.xml` 中已配置正确的认证
2. `pom.xml` 中已配置如下 `distributionManagement`：

```xml
  <distributionManagement>
    <repository>
      <id>2184455-release-JKA6Nb</id>
      <url>https://packages.aliyun.com/61fb5dd3c5006adb19fe45f9/maven/2184455-release-jka6nb</url>
    </repository>
    <snapshotRepository>
      <id>2184455-snapshot-M1Elwf</id>
      <url>https://packages.aliyun.com/61fb5dd3c5006adb19fe45f9/maven/2184455-snapshot-m1elwf</url>
    </snapshotRepository>
  </distributionManagement>
```

然后执行：

```bash
# 发布快照版本
# 没有传入-P则默认使用dev环境，由application.yml中的
mvn clean deploy -DskipTests
mvn clean install org.apache.maven.plugins:maven-deploy-plugin:2.8:deploy -DskipTests

# 或正式发布
mvn clean deploy -Pprod -DskipTests
mvn clean install org.apache.maven.plugins:maven-deploy-plugin:2.8:deploy -Pprod -DskipTests
```

如果版本号带 SNAPSHOT，则会发布到快照仓库；如果版本号不带 SNAPSHOT，则会发布到正式仓库。

修改版本号，请参考 README.md 中的[版本号规范](../README.md##版本号规范)。

## 拉取

### 配置仓库和包信息

在`settings.xml`文件`<repositories></repositories>`节点中加入对应的仓库使用地址。

```xml
<repositories>
  <repository>
    <id>2184455-snapshot-M1Elwf</id>
    <url>https://packages.aliyun.com/61fb5dd3c5006adb19fe45f9/maven/2184455-snapshot-m1elwf</url>
    <releases>
      <enabled>true</enabled>
    </releases>
    <snapshots>
      <enabled>true</enabled>
    </snapshots>
  </repository>
</repositories>
```

若您的制品库仅存储 `release` 制品，可将`<snapshot></snapshot>`节点更改为 false。

若您的制品库仅存储 `snapshot`制品，可将`<release></release>`节点更改为 false。

在你的 pom.xml 文件`<denpendencies></denpendencies>`节点中加入你要引用的文件信息。

```xml
<dependencies>
  <dependency>
    <groupId>[GROUP_ID]</groupId>
    <artifactId>[ARTIFACT_ID]</artifactId>
    <version>[VERSION]</version>
  </dependency>
</dependencies>
```

运行以下命令完成制品拉取。

```bash
mvn install
```

---

## 完成！

完成以上配置后，你就可以无障碍地使用公司 Maven 仓库，无需额外手动配置依赖源。

事实上上面的操作只需要执行一次，之后的构建和发布都将自动使用配置好的仓库。

开发者只需要做[开发者本地认证配置（必须）](##开发者本地认证配置（必须）)即可。

如有权限问题、构建失败等，可联系仓库管理员处理。
