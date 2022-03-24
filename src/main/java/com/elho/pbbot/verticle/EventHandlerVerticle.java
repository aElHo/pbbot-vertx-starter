package com.elho.pbbot.verticle;

import com.elho.pbbot.bot.ApiSender;
import com.elho.pbbot.bot.Bot;
import com.elho.pbbot.bot.BotContainer;
import com.elho.pbbot.bot.BotPlugin;
import com.elho.pbbot.bot.PluginContainer;
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
    }
}
