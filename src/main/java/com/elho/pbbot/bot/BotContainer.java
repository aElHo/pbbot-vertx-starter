package com.elho.pbbot.bot;

import cn.hutool.core.map.MapUtil;

import java.util.Map;

/**
 * @author zyf
 * @Date 2022-03-19
 */
public class BotContainer {

    public static Map<Long, Bot> BOTS = MapUtil.newConcurrentHashMap();


}
