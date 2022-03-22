package com.elho.pbbot.verticle;

import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.ClassUtil;
import com.elho.MainVerticle;
import com.elho.pbbot.bot.BotPlugin;
import com.elho.pbbot.bot.PluginContainer;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zyf
 * @Date 2022-03-22
 */
public class LoadPluginVerticle extends AbstractVerticle {

    Logger logger = LoggerFactory.getLogger(LoadPluginVerticle.class);
    @Override
    public void start() throws Exception {
        super.start();
        JsonObject result = config();
        JsonArray jsonArray = result.getJsonArray("plugin");
        for (int i = 0; i < jsonArray.size(); i++) {
            String className = jsonArray.getString(i);
            try {
                if(ClassLoaderUtil.isPresent(className)){
                    Class<? extends BotPlugin> aClass1 = ClassUtil.loadClass(className);
                    BotPlugin botPlugin = aClass1.newInstance();
                    PluginContainer.INSTANCE.addPlugins(botPlugin);
                    logger.info("{}加载成功",className);
                }else{
                    logger.info("{}不存在，跳过。",className);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.info( "{}加载失败,原因{}", className,e.getMessage());
            }
        }
    }
}
