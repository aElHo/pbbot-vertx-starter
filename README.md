# pbbot-vertx-starter

[![QQ群](https://img.shields.io/static/v1?label=QQ%E7%BE%A4&message=335783090&color=blue)](https://jq.qq.com/?_wv=1027&k=B7Of3GMZ)

这是一个vert.x starter，可以用于快速开发对应于 [Go-Mirai-Client](https://github.com/protobufbot/go-Mirai-Client)
或 [Spring-Mirai-Client](https://github.com/ProtobufBot/Spring-Mirai-Client) 的消息处理中心。

仅用于编写业务逻辑，不涉及登陆等功能，建议配合[Go-Mirai-Client](https://github.com/protobufbot/Go-Mirai-Client)使用，下载地址：[Go-Mirai-Client-Release](https://github.com/ProtobufBot/Go-Mirai-Client/releases)

## 使用方法

- pom.xml

```xml
<dependency>
    <groupId>io.github.aelho</groupId>
    <artifactId>pbbot-vertx-starter</artifactId>
    <version>0.0.1</version>
</dependency>
```

- Main

```java
import com.elho.BotVerticle;
import io.vertx.core.AbstractVerticle;

/**
 * @author zyf
 * @Date 2022-03-19
 */
public class DemoVerticle extends AbstractVerticle {
    @Override
    public void start() {
        vertx.deployVerticle(new BotVerticle());
    }
}

```

- DemoPlugin

```java
package plugin;

import com.elho.pbbot.bot.Bot;
import com.elho.pbbot.bot.BotPlugin;
import io.vertx.core.Future;
import onebot.OnebotApi;
import onebot.OnebotEvent;


public class DemoPlugin extends BotPlugin {
    @Override
    public int onPrivateMessage(Bot bot, OnebotEvent.PrivateMessageEvent event) {

        Future<OnebotApi.SendPrivateMsgResp> future = bot.sendPrivateMsg(event.getUserId(), "这是DEMO", false);
        future.onSuccess(a -> {
            logger.info("返回消息");
        });
        return MESSAGE_BLOCK;
    }
}
```

- resources/application.json

```yaml
{
    "port": 8081,
    "plugin": [
            "plugin.DemoPlugin",
            "plugin.TextPlugin"
    ]
}
```
