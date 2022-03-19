package com.elho.pbbot.handler;


import com.elho.pbbot.bot.Bot;
import com.elho.pbbot.bot.BotPlugin;
import com.elho.pbbot.bot.PluginContainer;
import onebot.OnebotEvent;

import java.util.List;

/**
 * @author zyf
 * @Date 2022-03-17
 */
public class EventHandler {

    public static void  handlePrivateMessageEvent(Bot bot, OnebotEvent.PrivateMessageEvent event){
        List<BotPlugin> plugins = PluginContainer.INSTANCE.getPlugins();
        for (BotPlugin botPlugin : plugins) {
            if(botPlugin.onPrivateMessage(bot, event)==botPlugin.MESSAGE_BLOCK) {
                break;
            }
        }
    }

    public static void handleGroupMessageEvent(Bot bot, OnebotEvent.GroupMessageEvent event) {
        List<BotPlugin> plugins = PluginContainer.INSTANCE.getPlugins();
        for (BotPlugin botPlugin : plugins) {
            if(botPlugin.onGroupMessage(bot, event)==botPlugin.MESSAGE_BLOCK) {
                break;
            }
        }
    }
}
