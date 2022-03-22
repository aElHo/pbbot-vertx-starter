package com.elho.pbbot.verticle;

import com.elho.pbbot.bot.Bot;
import com.elho.pbbot.bot.BotContainer;
import com.elho.pbbot.utils.FrameCodes;
import com.google.protobuf.InvalidProtocolBufferException;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.json.JsonObject;
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
        JsonObject config = config();
        Integer port = config.getInteger("port", 8081);
        EventBus eventBus = vertx.eventBus();
        eventBus.registerCodec(new FrameCodes());
        vertx.createHttpServer().webSocketHandler(websocket -> {
            MultiMap headers = websocket.headers();
            String selfId = headers.get("x-self-id");
            if ("/ws/cq/".equals(websocket.path()) && !"0".equals(selfId)) {
                logger.info( "{} connected",selfId);
                BotContainer.INSTANCE.addBot(Long.valueOf(selfId), createBot(selfId, websocket));
                websocket.handler(buffer -> {
                    try {
                        OnebotFrame.Frame frame = OnebotFrame.Frame.parseFrom(buffer.getBytes());
                        DeliveryOptions options = new DeliveryOptions().setCodecName("FrameCodes");
                        eventBus.publish(frame.getFrameType().name(),frame,options);
                    } catch (InvalidProtocolBufferException e) {
                        e.printStackTrace();
                    }
                });
            }
            websocket.closeHandler(handle -> {
                logger.info( "{} disconnected",selfId);
                BotContainer.INSTANCE.removeBot(Long.valueOf(selfId));
            });
        }).listen(port, r -> logger.info("started on {}", port ));
    }

    private Bot createBot(String selfId, ServerWebSocket websocket) {
        return new Bot(Long.valueOf(selfId), websocket);
    }
}
