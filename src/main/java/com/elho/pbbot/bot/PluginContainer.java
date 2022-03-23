package com.elho.pbbot.bot;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author zyf
 * @Date 2022-03-19
 */
public class PluginContainer {

    public static ConcurrentLinkedQueue<BotPlugin> botPlugins = new ConcurrentLinkedQueue<>();

}
