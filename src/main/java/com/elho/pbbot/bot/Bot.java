package com.elho.pbbot.bot;

import com.elho.pbbot.utils.Msg;
import io.vertx.core.Future;
import io.vertx.core.http.ServerWebSocket;
import onebot.OnebotApi;

/**
 * @author zyf
 * @Date 2022-03-15
 */
public class Bot {
    private final Long selfId;
    private final ServerWebSocket serverWebSocket;
    private final ApiSender apiSender;

    public Bot(Long selfId, ServerWebSocket serverWebSocket) {
        this.selfId = selfId;
        this.serverWebSocket = serverWebSocket;
        this.apiSender = new ApiSender();
    }

    public Long getSelfId() {
        return selfId;
    }

    public ServerWebSocket getServerWebSocket() {
        return serverWebSocket;
    }

    public ApiSender getApiSender() {
        return apiSender;
    }

    public Future<OnebotApi.SendPrivateMsgResp> sendPrivateMsg(Long userId, String message, Boolean autoEscape) {
        return this.sendPrivateMsg(userId, Msg.Builder().text(message).builder(), autoEscape);
    }

    public Future<OnebotApi.SendPrivateMsgResp> sendPrivateMsg(Long userId, Msg msg, Boolean autoEscape) {
        OnebotApi.SendPrivateMsgReq.Builder reqBuilder = OnebotApi.SendPrivateMsgReq.newBuilder();
        reqBuilder.setUserId(userId);
        reqBuilder.addAllMessage(msg.getMessageList());
        reqBuilder.setAutoEscape(autoEscape);
        return apiSender.sendPrivateMsg(serverWebSocket, userId, reqBuilder.build());
    }


    public Future<OnebotApi.SendGroupMsgResp> sendGroupMsg(Long groupId, String message, Boolean autoEscape) {
        return sendGroupMsg(groupId, Msg.Builder().text(message).builder(), autoEscape);
    }

    public Future<OnebotApi.SendGroupMsgResp> sendGroupMsg(Long groupId, Msg msg, Boolean autoEscape) {
        OnebotApi.SendGroupMsgReq.Builder builder = OnebotApi.SendGroupMsgReq.newBuilder();
        builder.setGroupId(groupId);
        builder.addAllMessage(msg.getMessageList());
        builder.setAutoEscape(autoEscape);
        return apiSender.sendGroupMsg(serverWebSocket, groupId, builder.build());
    }
}
