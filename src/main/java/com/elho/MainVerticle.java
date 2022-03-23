package com.elho;

import com.elho.pbbot.verticle.EventHandlerVerticle;
import com.elho.pbbot.verticle.LoadPluginVerticle;
import com.elho.pbbot.verticle.RespHandlerVerticle;
import com.elho.pbbot.verticle.SocketVerticle;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

/**
 * @author yf.zhuang
 */
public class MainVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) {
        ConfigStoreOptions fileStore = new ConfigStoreOptions().setType("file").setConfig(new JsonObject().put("path", "application.json"));
        ConfigRetrieverOptions options = new ConfigRetrieverOptions().addStore(fileStore);
        ConfigRetriever retriever = ConfigRetriever.create(vertx, options);
        retriever.getConfig(config -> {
            DeploymentOptions deploymentOptions = new DeploymentOptions().setConfig(config.result());
            vertx.deployVerticle(new SocketVerticle(), deploymentOptions.setWorker(false));
            vertx.deployVerticle(new EventHandlerVerticle(), deploymentOptions.setWorker(true));
            vertx.deployVerticle(new RespHandlerVerticle(), deploymentOptions.setWorker(false));
            vertx.deployVerticle(new LoadPluginVerticle(), deploymentOptions.setWorker(true));
        });
    }
}
