package com.elho;

import com.elho.pbbot.utils.ConfigUtil;
import com.elho.pbbot.verticle.EventHandlerVerticle;
import com.elho.pbbot.verticle.SocketVerticle;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

/**
 * @author yf.zhuang
 */
public class BotVerticle extends AbstractVerticle {

    @Override
    public void start() {
        ConfigStoreOptions fileStore = new ConfigStoreOptions().setType("file").setConfig(new JsonObject().put("path", "application.json"));
        ConfigRetrieverOptions options = new ConfigRetrieverOptions().addStore(fileStore);
        ConfigRetriever retriever = ConfigRetriever.create(vertx, options);
        retriever.getConfig(config->{
            ConfigUtil.initConfig(config);
            vertx.deployVerticle(new SocketVerticle());
        });
        vertx.deployVerticle(new EventHandlerVerticle());
    }
}
