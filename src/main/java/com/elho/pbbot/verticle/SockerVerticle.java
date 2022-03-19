package com.elho.pbbot.verticle;

import com.elho.pbbot.bot.Bot;
import com.elho.pbbot.bot.BotContainer;
import com.elho.pbbot.handler.FrameHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;

/**
 * @author zyf
 * @Date 2022-03-19
 */
public class SockerVerticle extends AbstractVerticle {
    Logger logger = LoggerFactory.getLogger(SockerVerticle.class);

    @Override
    public void start() throws Exception {
        JsonObject config = config();
        Integer port = config.getInteger("port", 8081);
        vertx.createHttpServer().webSocketHandler(websocket -> {
            MultiMap headers = websocket.headers();
            String selfId = headers.get("x-self-id");
            if ("/ws/cq/".equals(websocket.path()) && !"0".equals(selfId)) {
                logger.info(selfId + " connected");
                BotContainer.INSTANCE.addBot(Long.valueOf(selfId), createBot(selfId, websocket));
                websocket.handler(buffer -> {
                    try {
                        onebot.OnebotFrame.Frame frame = onebot.OnebotFrame.Frame.parseFrom(buffer.getBytes());
                        FrameHandler.handleFrame(frame);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            websocket.closeHandler(handle -> {
                logger.info(selfId + " disconnected");
                BotContainer.INSTANCE.removeBot(Long.valueOf(selfId));
            });
        }).listen(port, r -> logger.info("started on " + port));
    }

    private Bot createBot(String selfId, ServerWebSocket websocket) {
        return new Bot(Long.valueOf(selfId), websocket);
    }
}
