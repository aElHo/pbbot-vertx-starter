package com.elho.pbbot.verticle;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import com.elho.pbbot.bot.Bot;
import com.elho.pbbot.bot.BotContainer;
import com.elho.pbbot.bot.BotPlugin;
import com.elho.pbbot.bot.PluginContainer;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import onebot.OnebotFrame;


/**
 * @author zyf
 * @Date 2022-03-22
 */
public class RespHandlerVerticle extends AbstractVerticle {

    /**
     * 缓存最近50条记录的messageId;
     */
    Cache<String,Integer> fifoCache = CacheUtil.newFIFOCache(50);

    @Override
    public void start() throws Exception {
        EventBus eventBus = vertx.eventBus();
        eventBus.<OnebotFrame.Frame>consumer(OnebotFrame.Frame.FrameType.TSendPrivateMsgResp.name(), message->{
            OnebotFrame.Frame frame = message.body();
            fifoCache.put(frame.getEcho(),frame.getSendPrivateMsgResp().getMessageId());
        });
        eventBus.<OnebotFrame.Frame>consumer(OnebotFrame.Frame.FrameType.TSendGroupMsgResp.name(), message->{
            OnebotFrame.Frame frame = message.body();
            fifoCache.put(frame.getEcho(),frame.getSendPrivateMsgResp().getMessageId());
        });
    }
}
