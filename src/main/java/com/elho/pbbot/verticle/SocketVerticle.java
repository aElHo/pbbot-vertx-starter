package com.elho.pbbot.verticle;

import com.elho.pbbot.bot.Bot;
import com.elho.pbbot.bot.BotContainer;
import com.elho.pbbot.utils.ConfigUtil;
import com.elho.pbbot.utils.FrameCodes;
import com.google.protobuf.InvalidProtocolBufferException;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.ServerWebSocket;
import onebot.OnebotFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zyf
 * @Date 2022-03-19
 */
public class SocketVerticle extends AbstractVerticle {
    Logger logger = LoggerFactory.getLogger(SocketVerticle.class);

    @Override
    public void start() throws Exception {
        Integer port = ConfigUtil.getInteger("port", 8081);
        EventBus eventBus = vertx.eventBus();
        eventBus.registerDefaultCodec(OnebotFrame.Frame.class, new FrameCodes());
        vertx.createHttpServer().webSocketHandler(websocket -> {
            MultiMap headers = websocket.headers();
            String selfId = headers.get("x-self-id");
            if ("/ws/cq/".equals(websocket.path()) && !"0".equals(selfId)) {
                logger.info("{} connected", selfId);
                BotContainer.BOTS.put(Long.valueOf(selfId), createBot(selfId, websocket));
                websocket.handler(buffer -> {
                    try {
                        OnebotFrame.Frame frame = OnebotFrame.Frame.parseFrom(buffer.getBytes());
                        eventBus.publish(frame.getFrameType().name(), frame);
                    } catch (InvalidProtocolBufferException e) {
                        e.printStackTrace();
                    }
                });
            }
            websocket.closeHandler(handle -> {
                logger.info("{} disconnected", selfId);
                BotContainer.BOTS.remove(Long.valueOf(selfId));
            });
        }).listen(port, r -> {
            if (r.succeeded()) {
                logger.info("started on {}", port);
            } else {
                logger.info("Start failed , cause:{}", r.cause().getMessage());
            }
        });
    }

    private Bot createBot(String selfId, ServerWebSocket websocket) {
        return new Bot(Long.valueOf(selfId), websocket);
    }
}
