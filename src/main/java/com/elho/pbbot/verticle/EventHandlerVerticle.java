package com.elho.pbbot.verticle;

import com.elho.pbbot.bot.ApiSender;
import com.elho.pbbot.bot.Bot;
import com.elho.pbbot.bot.BotContainer;
import com.elho.pbbot.bot.BotPlugin;
import com.elho.pbbot.plugin.PluginContainer;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
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
        /*
         处理私聊消息
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TPrivateMessageEvent.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            Bot bot = BotContainer.BOTS.get(frame.getBotId());
            ConcurrentLinkedQueue<BotPlugin> plugins = PluginContainer.botPlugins;
            for (BotPlugin botPlugin : plugins) {
                if (botPlugin.onPrivateMessage(bot, frame.getPrivateMessageEvent()) == BotPlugin.MESSAGE_BLOCK) {
                    break;
                }
            }
        });

        /*
          处理群聊消息
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TGroupMessageEvent.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            Bot bot = BotContainer.BOTS.get(frame.getBotId());
            ConcurrentLinkedQueue<BotPlugin> plugins = PluginContainer.botPlugins;
            for (BotPlugin botPlugin : plugins) {
                if (botPlugin.onGroupMessage(bot, frame.getGroupMessageEvent()) == BotPlugin.MESSAGE_BLOCK) {
                    break;
                }
            }
        });

        /*
          处理群内文件上传
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TGroupUploadNoticeEvent.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            Bot bot = BotContainer.BOTS.get(frame.getBotId());
            ConcurrentLinkedQueue<BotPlugin> plugins = PluginContainer.botPlugins;
            for (BotPlugin botPlugin : plugins) {
                if (botPlugin.onGroupUploadNotice(bot, frame.getGroupUploadNoticeEvent()) == BotPlugin.MESSAGE_BLOCK) {
                    break;
                }
            }
        });

        /*
          处理群管理员变动
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TGroupAdminNoticeEvent.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            Bot bot = BotContainer.BOTS.get(frame.getBotId());
            ConcurrentLinkedQueue<BotPlugin> plugins = PluginContainer.botPlugins;
            for (BotPlugin botPlugin : plugins) {
                if (botPlugin.onGroupAdminNotice(bot, frame.getGroupAdminNoticeEvent()) == BotPlugin.MESSAGE_BLOCK) {
                    break;
                }
            }
        });

         /*
          处理群成员减少
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TGroupDecreaseNoticeEvent.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            Bot bot = BotContainer.BOTS.get(frame.getBotId());
            ConcurrentLinkedQueue<BotPlugin> plugins = PluginContainer.botPlugins;
            for (BotPlugin botPlugin : plugins) {
                if (botPlugin.onGroupDecreaseNotice(bot, frame.getGroupDecreaseNoticeEvent()) == BotPlugin.MESSAGE_BLOCK) {
                    break;
                }
            }
        });

         /*
          处理群成员增加
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TGroupIncreaseNoticeEvent.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            Bot bot = BotContainer.BOTS.get(frame.getBotId());
            ConcurrentLinkedQueue<BotPlugin> plugins = PluginContainer.botPlugins;
            for (BotPlugin botPlugin : plugins) {
                if (botPlugin.onGroupIncreaseNotice(bot, frame.getGroupIncreaseNoticeEvent()) == BotPlugin.MESSAGE_BLOCK) {
                    break;
                }
            }
        });

         /*
          处理群禁言
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TGroupBanNoticeEvent.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            Bot bot = BotContainer.BOTS.get(frame.getBotId());
            ConcurrentLinkedQueue<BotPlugin> plugins = PluginContainer.botPlugins;
            for (BotPlugin botPlugin : plugins) {
                if (botPlugin.onGroupBanNotice(bot, frame.getGroupBanNoticeEvent()) == BotPlugin.MESSAGE_BLOCK) {
                    break;
                }
            }
        });

        /*
          处理好友添加
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TFriendAddNoticeEvent.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            Bot bot = BotContainer.BOTS.get(frame.getBotId());
            ConcurrentLinkedQueue<BotPlugin> plugins = PluginContainer.botPlugins;
            for (BotPlugin botPlugin : plugins) {
                if (botPlugin.onFriendAddNotice(bot, frame.getFriendAddNoticeEvent()) == BotPlugin.MESSAGE_BLOCK) {
                    break;
                }
            }
        });

         /*
          处理群撤回消息
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TGroupRecallNoticeEvent.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            Bot bot = BotContainer.BOTS.get(frame.getBotId());
            ConcurrentLinkedQueue<BotPlugin> plugins = PluginContainer.botPlugins;
            for (BotPlugin botPlugin : plugins) {
                if (botPlugin.onGroupRecallNotice(bot, frame.getGroupRecallNoticeEvent()) == BotPlugin.MESSAGE_BLOCK) {
                    break;
                }
            }
        });

         /*
          处理好友撤回消息
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TFriendRecallNoticeEvent.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            Bot bot = BotContainer.BOTS.get(frame.getBotId());
            ConcurrentLinkedQueue<BotPlugin> plugins = PluginContainer.botPlugins;
            for (BotPlugin botPlugin : plugins) {
                if (botPlugin.onFriendRecallNotice(bot, frame.getFriendRecallNoticeEvent()) == BotPlugin.MESSAGE_BLOCK) {
                    break;
                }
            }
        });

        /*
          处理加好友请求
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TFriendRequestEvent.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            Bot bot = BotContainer.BOTS.get(frame.getBotId());
            ConcurrentLinkedQueue<BotPlugin> plugins = PluginContainer.botPlugins;
            for (BotPlugin botPlugin : plugins) {
                if (botPlugin.onFriendRequest(bot, frame.getFriendRequestEvent()) == BotPlugin.MESSAGE_BLOCK) {
                    break;
                }
            }
        });

         /*
          处理加群请求/邀请
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TGroupRequestEvent.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            Bot bot = BotContainer.BOTS.get(frame.getBotId());
            ConcurrentLinkedQueue<BotPlugin> plugins = PluginContainer.botPlugins;
            for (BotPlugin botPlugin : plugins) {
                if (botPlugin.onGroupRequest(bot, frame.getGroupRequestEvent()) == BotPlugin.MESSAGE_BLOCK) {
                    break;
                }
            }
        });

        /*
        处理私聊消息返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TSendPrivateMsgResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.SendPrivateMsgResp> promise = (Promise<onebot.OnebotApi.SendPrivateMsgResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getSendPrivateMsgResp());
        });

        /*
        处理群组消息返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TSendGroupMsgResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.SendGroupMsgResp> promise = (Promise<onebot.OnebotApi.SendGroupMsgResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getSendGroupMsgResp());
        });

        /*
        处理撤回消息返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TDeleteMsgResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.DeleteMsgResp> promise = (Promise<onebot.OnebotApi.DeleteMsgResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getDeleteMsgResp());
        });

         /*
        处理发送消息返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TSendMsgResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.SendMsgResp> promise = (Promise<onebot.OnebotApi.SendMsgResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getSendMsgResp());
        });

        /*
        处理获取消息返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TGetMsgResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.DeleteMsgResp> promise = (Promise<onebot.OnebotApi.DeleteMsgResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getDeleteMsgResp());
        });

        /*
        处理群组踢人事件返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TSetGroupKickResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.SetGroupKickResp> promise = (Promise<onebot.OnebotApi.SetGroupKickResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getSetGroupKickResp());
        });

         /*
        处理群组单人禁言事件返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TSetGroupBanResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.SetGroupBanResp> promise = (Promise<onebot.OnebotApi.SetGroupBanResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getSetGroupBanResp());
        });

        /*
        处理设置群组全员禁言事件返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TSetGroupWholeBanResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.SetGroupWholeBanResp> promise = (Promise<onebot.OnebotApi.SetGroupWholeBanResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getSetGroupWholeBanResp());
        });

         /*
        处理设置群名片（群备注）事件返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TSetGroupCardResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.SetGroupCardResp> promise = (Promise<onebot.OnebotApi.SetGroupCardResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getSetGroupCardResp());
        });

         /*
        处理解散群组事件返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TSetGroupLeaveResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.SetGroupLeaveResp> promise = (Promise<onebot.OnebotApi.SetGroupLeaveResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getSetGroupLeaveResp());
        });

         /*
        处理设置群组专属头衔事件返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TSetGroupSpecialTitleResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.SetGroupSpecialTitleResp> promise = (Promise<onebot.OnebotApi.SetGroupSpecialTitleResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getSetGroupSpecialTitleResp());
        });

        /*
        处理加好友请求事件返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TSetFriendAddRequestResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.SetFriendAddRequestResp> promise = (Promise<onebot.OnebotApi.SetFriendAddRequestResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getSetFriendAddRequestResp());
        });

         /*
        处理加群请求／邀请返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TSetGroupAddRequestResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.SetGroupAddRequestResp> promise = (Promise<onebot.OnebotApi.SetGroupAddRequestResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getSetGroupAddRequestResp());
        });

        /*
        处理获取登录号信息返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TGetLoginInfoResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.GetLoginInfoResp> promise = (Promise<onebot.OnebotApi.GetLoginInfoResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getGetLoginInfoResp());
        });

         /*
        处理获取陌生人信息返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TGetStrangerInfoResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.GetStrangerInfoResp> promise = (Promise<onebot.OnebotApi.GetStrangerInfoResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getGetStrangerInfoResp());
        });

          /*
        处理获取好友列表返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TGetFriendListResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.GetFriendListResp> promise = (Promise<onebot.OnebotApi.GetFriendListResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getGetFriendListResp());
        });

         /*
        处理获取群列表返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TGetGroupListResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.GetGroupListResp> promise = (Promise<onebot.OnebotApi.GetGroupListResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getGetGroupListResp());
        });

         /*
        处理获取群信息返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TGetGroupInfoResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.GetGroupInfoResp> promise = (Promise<onebot.OnebotApi.GetGroupInfoResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getGetGroupInfoResp());
        });

        /*
        处理获取群成员信息返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TGetGroupMemberInfoResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.GetGroupMemberInfoResp> promise = (Promise<onebot.OnebotApi.GetGroupMemberInfoResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getGetGroupMemberInfoResp());
        });

         /*
        处理获取群成员列表返回
         */
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TGetGroupMemberListResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<onebot.OnebotApi.GetGroupMemberListResp> promise = (Promise<onebot.OnebotApi.GetGroupMemberListResp>) ApiSender.ECHO_MAP.remove(echo);
            promise.complete(frame.getGetGroupMemberListResp());
        });
    }
}
