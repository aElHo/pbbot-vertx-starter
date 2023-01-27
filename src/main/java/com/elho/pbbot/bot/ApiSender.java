package com.elho.pbbot.bot;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ServerWebSocket;
import onebot.OnebotApi;
import onebot.OnebotFrame;

import java.util.Map;

/**
 * @author zyf
 * @Date 2022-03-15
 */
public enum ApiSender {
    INSTANCE;
    public static final Map<String, Object> ECHO_MAP = MapUtil.newConcurrentHashMap();

    public Future<OnebotApi.SendPrivateMsgResp> sendPrivateMsg(ServerWebSocket webSocket, Long botId, OnebotApi.SendPrivateMsgReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId).setSendPrivateMsgReq(apiReq)
            .setFrameType(OnebotFrame.Frame.FrameType.TSendPrivateMsgReq).setOk(true).build();
        webSocket.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.SendPrivateMsgResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }

    public Future<OnebotApi.SendGroupMsgResp> sendGroupMsg(ServerWebSocket webSocket, Long botId, OnebotApi.SendGroupMsgReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId).setSendGroupMsgReq(apiReq)
            .setFrameType(OnebotFrame.Frame.FrameType.TSendGroupMsgReq).setOk(true).build();
        webSocket.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.SendGroupMsgResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }

    public Future<OnebotApi.DeleteMsgResp> deleteMsg(ServerWebSocket webSocket, Long botId, OnebotApi.DeleteMsgReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId)
            .setDeleteMsgReq(apiReq).setFrameType(OnebotFrame.Frame.FrameType.TDeleteMsgReq).setOk(true).build();
        webSocket.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.DeleteMsgResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }


    Future<OnebotApi.SendMsgResp> sendMsg(ServerWebSocket session, Long botId, OnebotApi.SendMsgReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId)
            .setSendMsgReq(apiReq).setFrameType(OnebotFrame.Frame.FrameType.TSendMsgReq).setOk(true).build();
        session.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.SendMsgResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }

    Future<OnebotApi.GetMsgResp> getMsg(ServerWebSocket session, Long botId, OnebotApi.GetMsgReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId)
            .setGetMsgReq(apiReq).setFrameType(OnebotFrame.Frame.FrameType.TGetMsgReq).setOk(true).build();
        session.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.GetMsgResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }

    Future<OnebotApi.SetGroupKickResp> setGroupKick(ServerWebSocket session, Long botId, OnebotApi.SetGroupKickReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId)
            .setSetGroupKickReq(apiReq).setFrameType(OnebotFrame.Frame.FrameType.TSetGroupKickReq).setOk(true).build();
        session.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.SetGroupKickResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }

    Future<OnebotApi.SetGroupBanResp> setGroupBan(ServerWebSocket session, Long botId, OnebotApi.SetGroupBanReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId)
            .setSetGroupBanReq(apiReq).setFrameType(OnebotFrame.Frame.FrameType.TSetGroupBanReq).setOk(true).build();
        session.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.SetGroupBanResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }

    Future<OnebotApi.SetGroupWholeBanResp> setGroupWholeBan(ServerWebSocket session, Long botId, OnebotApi.SetGroupWholeBanReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId)
            .setSetGroupWholeBanReq(apiReq).setFrameType(OnebotFrame.Frame.FrameType.TSetGroupWholeBanReq).setOk(true).build();
        session.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.SetGroupWholeBanResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }

    Future<OnebotApi.SetGroupCardResp> setGroupCard(ServerWebSocket session, Long botId, OnebotApi.SetGroupCardReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId)
            .setSetGroupCardReq(apiReq).setFrameType(OnebotFrame.Frame.FrameType.TSetGroupCardReq).setOk(true).build();
        session.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.SetGroupCardResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }

    Future<OnebotApi.SetGroupLeaveResp> setGroupLeave(ServerWebSocket session, Long botId, OnebotApi.SetGroupLeaveReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId)
            .setSetGroupLeaveReq(apiReq).setFrameType(OnebotFrame.Frame.FrameType.TSetGroupLeaveReq).setOk(true).build();
        session.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.SetGroupLeaveResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }

    Future<OnebotApi.SetGroupSpecialTitleResp> setGroupSpecialTitle(ServerWebSocket session, Long botId, OnebotApi.SetGroupSpecialTitleReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId)
            .setSetGroupSpecialTitleReq(apiReq).setFrameType(OnebotFrame.Frame.FrameType.TSetGroupSpecialTitleReq).setOk(true).build();
        session.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.SetGroupSpecialTitleResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }

    Future<OnebotApi.SetFriendAddRequestResp> setFriendAddRequest(ServerWebSocket session, Long botId, OnebotApi.SetFriendAddRequestReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId)
            .setSetFriendAddRequestReq(apiReq).setFrameType(OnebotFrame.Frame.FrameType.TSetFriendAddRequestReq).setOk(true).build();
        session.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.SetFriendAddRequestResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }

    Future<OnebotApi.SetGroupAddRequestResp> setGroupAddRequest(ServerWebSocket session, Long botId, OnebotApi.SetGroupAddRequestReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId)
            .setSetGroupAddRequestReq(apiReq).setFrameType(OnebotFrame.Frame.FrameType.TSetGroupAddRequestReq).setOk(true).build();
        session.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.SetGroupAddRequestResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }

    Future<OnebotApi.GetLoginInfoResp> getLoginInfo(ServerWebSocket session, Long botId, OnebotApi.GetLoginInfoReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId)
            .setGetLoginInfoReq(apiReq).setFrameType(OnebotFrame.Frame.FrameType.TGetLoginInfoReq).setOk(true).build();
        session.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.GetLoginInfoResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }

    Future<OnebotApi.GetStrangerInfoResp> getStrangerInfo(ServerWebSocket session, Long botId, OnebotApi.GetStrangerInfoReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId)
            .setGetStrangerInfoReq(apiReq).setFrameType(OnebotFrame.Frame.FrameType.TGetStrangerInfoReq).setOk(true).build();
        session.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.GetStrangerInfoResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }

    Future<OnebotApi.GetFriendListResp> getFriendList(ServerWebSocket session, Long botId, OnebotApi.GetFriendListReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId)
            .setGetFriendListReq(apiReq).setFrameType(OnebotFrame.Frame.FrameType.TGetFriendListReq).setOk(true).build();
        session.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.GetFriendListResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }

    Future<OnebotApi.GetGroupInfoResp> getGroupInfo(ServerWebSocket session, Long botId, OnebotApi.GetGroupInfoReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId)
            .setGetGroupInfoReq(apiReq).setFrameType(OnebotFrame.Frame.FrameType.TGetGroupInfoReq).setOk(true).build();
        session.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.GetGroupInfoResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }

    Future<OnebotApi.GetGroupListResp> getGroupList(ServerWebSocket session, Long botId, OnebotApi.GetGroupListReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId)
            .setGetGroupListReq(apiReq).setFrameType(OnebotFrame.Frame.FrameType.TGetGroupListReq).setOk(true).build();
        session.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.GetGroupListResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }

    Future<OnebotApi.GetGroupMemberInfoResp> getGroupMemberInfo(ServerWebSocket session, Long botId, OnebotApi.GetGroupMemberInfoReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId)
            .setGetGroupMemberInfoReq(apiReq).setFrameType(OnebotFrame.Frame.FrameType.TGetGroupMemberInfoReq).setOk(true).build();
        session.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.GetGroupMemberInfoResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }

    Future<OnebotApi.GetGroupMemberListResp> getGroupMemberList(ServerWebSocket session, Long botId, OnebotApi.GetGroupMemberListReq apiReq) {
        String echo = UUID.fastUUID().toString(true);
        OnebotFrame.Frame build = OnebotFrame.Frame.newBuilder().setEcho(echo).setBotId(botId)
            .setGetGroupMemberListReq(apiReq).setFrameType(OnebotFrame.Frame.FrameType.TGetGroupMemberListReq).setOk(true).build();
        session.write(Buffer.buffer(build.toByteArray()));
        Promise<OnebotApi.GetGroupMemberListResp> promise = Promise.promise();
        ECHO_MAP.put(echo, promise);
        return promise.future();
    }
    //        OnebotApi.GetForwardMsgResp getForwardMsg(ServerWebSocket session, Long botId, OnebotApi.GetForwardMsgReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.SendLikeResp sendLike(ServerWebSocket session, Long botId, OnebotApi.SendLikeReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
    //
//    OnebotApi.SetGroupAnonymousBanResp setGroupAnonymousBan(ServerWebSocket session, Long botId, OnebotApi.SetGroupAnonymousBanReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
    //
//    OnebotApi.SetGroupAdminResp setGroupAdmin(ServerWebSocket session, Long botId, OnebotApi.SetGroupAdminReq apiReq) {
//        return  callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.SetGroupAnonymousResp setGroupAnonymous(ServerWebSocket session, Long botId, OnebotApi.SetGroupAnonymousReq apiReq) {
//        return  callApi(session, botId, apiReq);
//    }
//
    //
//    OnebotApi.SetGroupNameResp setGroupName(ServerWebSocket session, Long botId, OnebotApi.SetGroupNameReq apiReq) {
//        return  callApi(session, botId, apiReq);
//    }
//
//
//    OnebotApi.GetGroupHonorInfoResp getGroupHonorInfo(ServerWebSocket session, Long botId, OnebotApi.GetGroupHonorInfoReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.GetCookiesResp getCookies(ServerWebSocket session, Long botId, OnebotApi.GetCookiesReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.GetCsrfTokenResp getCsrfToken(ServerWebSocket session, Long botId, OnebotApi.GetCsrfTokenReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.GetCredentialsResp getCredentials(ServerWebSocket session, Long botId, OnebotApi.GetCredentialsReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.GetRecordResp getRecord(ServerWebSocket session, Long botId, OnebotApi.GetRecordReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.GetImageResp getImage(ServerWebSocket session, Long botId, OnebotApi.GetImageReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.CanSendImageResp canSendImage(ServerWebSocket session, Long botId, OnebotApi.CanSendImageReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.CanSendRecordResp canSendRecord(ServerWebSocket session, Long botId, OnebotApi.CanSendRecordReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.GetStatusResp getStatus(ServerWebSocket session, Long botId, OnebotApi.GetStatusReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.GetVersionInfoResp getVersionInfo(ServerWebSocket session, Long botId, OnebotApi.GetVersionInfoReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.SetRestartResp setRestart(ServerWebSocket session, Long botId, OnebotApi.SetRestartReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.CleanCacheResp cleanCache(ServerWebSocket session, Long botId, OnebotApi.CleanCacheReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
}
