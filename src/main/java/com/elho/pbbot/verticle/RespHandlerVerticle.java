package com.elho.pbbot.verticle;

import com.elho.pbbot.bot.ApiSender;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import onebot.OnebotApi;
import onebot.OnebotFrame;


/**
 * @author zyf
 * @Date 2022-03-22
 */
public class RespHandlerVerticle extends AbstractVerticle {


    @Override
    public void start() throws Exception {
        EventBus eventBus = vertx.eventBus();
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TSendGroupMsgResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<OnebotApi.SendGroupMsgResp> promise = (Promise<OnebotApi.SendGroupMsgResp>) ApiSender.echoMap.get(echo);
            promise.complete(frame.getSendGroupMsgResp());
        });
        eventBus.<OnebotFrame.Frame>localConsumer(OnebotFrame.Frame.FrameType.TSendPrivateMsgResp.name(), message -> {
            OnebotFrame.Frame frame = message.body();
            String echo = frame.getEcho();
            Promise<OnebotApi.SendPrivateMsgResp> promise = (Promise<OnebotApi.SendPrivateMsgResp>) ApiSender.echoMap.get(echo);
            promise.complete(frame.getSendPrivateMsgResp());
        });
    }
}
