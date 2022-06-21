package com.elho.pbbot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zyf
 * @Date 2022-03-15
 */
public class BotPlugin {
    public Logger logger = LoggerFactory.getLogger(getClass());
    public static final int MESSAGE_IGNORE = 1;
    public static final int MESSAGE_BLOCK = 0;

    public int onPrivateMessage(Bot bot, onebot.OnebotEvent.PrivateMessageEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 收到群消息时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupMessage(Bot bot, onebot.OnebotEvent.GroupMessageEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 群内有文件上传时调用此方法
     * 仅群文件上传表现为事件，好友发送文件在 酷Q 中没有独立的事件，而是直接表现为好友消息，请注意在编写业务逻辑时进行判断。
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupUploadNotice(Bot bot, onebot.OnebotEvent.GroupUploadNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 群管理员变动时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupAdminNotice(Bot bot, onebot.OnebotEvent.GroupAdminNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 群成员减少时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupDecreaseNotice(Bot bot, onebot.OnebotEvent.GroupDecreaseNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 群成员增加时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupIncreaseNotice(Bot bot, onebot.OnebotEvent.GroupIncreaseNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 群禁言时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupBanNotice(Bot bot, onebot.OnebotEvent.GroupBanNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 好友添加时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onFriendAddNotice(Bot bot, onebot.OnebotEvent.FriendAddNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 群撤回消息时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupRecallNotice(Bot bot, onebot.OnebotEvent.GroupRecallNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 好友撤回消息时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onFriendRecallNotice(Bot bot, onebot.OnebotEvent.FriendRecallNoticeEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 加好友请求时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onFriendRequest(Bot bot, onebot.OnebotEvent.FriendRequestEvent event) {
        return MESSAGE_IGNORE;
    }

    /**
     * 加群请求/邀请时调用此方法
     *
     * @param bot   机器人对象
     * @param event 事件内容
     * @return 是否继续处理下一个插件, MESSAGE_BLOCK表示不继续，MESSAGE_IGNORE表示继续
     */
    public int onGroupRequest(Bot bot, onebot.OnebotEvent.GroupRequestEvent event) {
        return MESSAGE_IGNORE;
    }
}
