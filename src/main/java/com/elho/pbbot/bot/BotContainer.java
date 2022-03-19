package com.elho.pbbot.bot;

import cn.hutool.core.map.MapUtil;

import java.util.Map;

/**
 * @author zyf
 * @Date 2022-03-19
 */
public enum BotContainer {
    /**
     * BOT容器实例
     */
    INSTANCE;

    public BotContainer getInstance() {
        return INSTANCE;
    }

    private Map<Long, Bot> bots = MapUtil.newHashMap();

    public Map<Long, Bot> getBots() {
        return bots;
    }

    public Bot getBot(Long qq) {
        return bots.get(qq);
    }

    public void addBot(Long qq, Bot bot) {
        bots.put(qq, bot);
    }

    public Bot removeBot(Long qq) {
        return bots.remove(qq);
    }

}
