package com.elho.pbbot.verticle;

import com.elho.pbbot.bot.Bot;
import com.elho.pbbot.bot.BotContainer;
import com.elho.pbbot.bot.BotPlugin;
import com.elho.pbbot.bot.PluginContainer;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import onebot.OnebotFrame;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author zyf
 * @Date 2022-03-21
 */
public class EventHandlerVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        EventBus eventBus = vertx.eventBus();
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TPrivateMessageEvent.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            Bot bot = BotContainer.BOTS.get(frame.getBotId());
            ConcurrentLinkedQueue<BotPlugin> plugins = PluginContainer.botPlugins;
            for (BotPlugin botPlugin : plugins) {
                if (botPlugin.onPrivateMessage(bot, frame.getPrivateMessageEvent()) == botPlugin.MESSAGE_BLOCK) {
                    break;
                }
            }
        });
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TGroupMessageEvent.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            Bot bot = BotContainer.BOTS.get(frame.getBotId());
            ConcurrentLinkedQueue<BotPlugin> plugins = PluginContainer.botPlugins;
            for (BotPlugin botPlugin : plugins) {
                if (botPlugin.onGroupMessage(bot, frame.getGroupMessageEvent()) == botPlugin.MESSAGE_BLOCK) {
                    break;
                }
            }
        });
    }
}
