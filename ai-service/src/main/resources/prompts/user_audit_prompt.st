用户请求修改实名信息。请分析前后信息变化是否合理，是否频繁修改，是否有疑点。

当前实名信息: ${current}
修改后信息: ${modified}
用户最近修改次数: ${count}

请判断修改是否可以自动批准？返回以下 JSON：
{
  "approve": true/false,
  "reason": "简要说明"
}
