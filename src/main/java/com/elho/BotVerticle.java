package com.elho;

import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.ClassUtil;
import com.elho.pbbot.bot.BotPlugin;
import com.elho.pbbot.bot.PluginContainer;
import com.elho.pbbot.verticle.EventHandlerVerticle;
import com.elho.pbbot.verticle.SocketVerticle;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yf.zhuang
 */
public class BotVerticle extends AbstractVerticle {
    Logger logger = LoggerFactory.getLogger(BotVerticle.class);

    @Override
    public void start() {
        ConfigStoreOptions fileStore = new ConfigStoreOptions().setType("file").setConfig(new JsonObject().put("path", "application.json"));
        ConfigRetrieverOptions options = new ConfigRetrieverOptions().addStore(fileStore);
        ConfigRetriever retriever = ConfigRetriever.create(vertx, options);
        retriever.getConfig(config -> {
            DeploymentOptions deploymentOptions = new DeploymentOptions().setConfig(config.result());
            vertx.deployVerticle(new SocketVerticle(), deploymentOptions);
            loadPlugin(config.result());
        });
        vertx.deployVerticle(new EventHandlerVerticle());
    }

    private void loadPlugin(JsonObject result) {
        JsonArray jsonArray = result.getJsonArray("plugin");
        PluginContainer.botPlugins.clear();
        for (int i = 0; i < jsonArray.size(); i++) {
            String className = jsonArray.getString(i);
            try {
                if (ClassLoaderUtil.isPresent(className)) {
                    Class<? extends BotPlugin> aClass1 = ClassUtil.loadClass(className);
                    BotPlugin botPlugin = aClass1.newInstance();
                    PluginContainer.botPlugins.add(botPlugin);
                    logger.info("{}加载成功", className);
                } else {
                    logger.info("{}不存在，跳过。", className);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("{}加载失败,原因{}", className, e.getMessage());
            }
        }
    }
}
