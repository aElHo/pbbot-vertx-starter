package com.elho.pbbot.bot;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zyf
 * @Date 2022-03-19
 */
public enum PluginContainer {
    /**
     * plugin 集合
     */
    INSTANCE;

    public PluginContainer getInstance() {
        return INSTANCE;
    }

    private List<BotPlugin> plugins = new LinkedList<>();

    public List<BotPlugin> getPlugins() {
        return plugins;
    }

    public void addPlugins(BotPlugin plugin) {
        this.plugins.add(plugin);
    }
}
