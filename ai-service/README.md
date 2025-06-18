# Spring AI 服务

## 打包时

```
mvn clean install -DskipTests
```

## 启动服务

```
java -Dspring.ai.openai.api-key=你的密钥 -jar ai-service/target/ai-service-1.0-SNAPSHOT.jar
```

```
### 测试

```

curl --location 'localhost:8080/ai/deepseek/chat' \
--header 'Content-Type: application/json' \
--data '{
"message": "你是一个负责电商平台退款审核的 AI 工具。请根据以下信息判断退款请求是否合理并返回 json 格式的结果。\n 订单信息：\n 订单编号: 123456789\n 订单金额: 1000\n 购买时间: 2025-06-16 15:29:40\n 退款申请时间: 2025-06-17 15:30:00\n 商品类型: 虚拟卡号\n 用户历史退款次数: 0\n 发货状态：未发货\n 退款理由: 不想要了\n 请判断是否可以自动批准退款并返回以下 JSON 格式的数据,不需要代码块包装：\n{\n\"approve\": true/false,\n\"reason\": \"简短说明\"\n}"
}'

```

```
