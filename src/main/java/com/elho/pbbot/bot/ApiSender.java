package com.elho.pbbot.bot;

import cn.hutool.core.lang.UUID;
import com.google.protobuf.MessageLiteOrBuilder;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ServerWebSocket;
import onebot.OnebotApi;
import onebot.OnebotFrame;

/**
 * @author zyf
 * @Date 2022-03-15
 */
public class ApiSender {

    private Object callApi(ServerWebSocket webSocket, Long botId, MessageLiteOrBuilder apiReq) {
        String echo = UUID.fastUUID().toString();
        OnebotFrame.Frame.Builder frameBuilder = OnebotFrame.Frame.newBuilder();
        frameBuilder.setEcho(echo);
        frameBuilder.setBotId(botId);
        if (apiReq instanceof OnebotApi.SendPrivateMsgReq) {
            frameBuilder.setSendPrivateMsgReq((OnebotApi.SendPrivateMsgReq) apiReq);
            frameBuilder.setFrameType(OnebotFrame.Frame.FrameType.TSendPrivateMsgReq);
        } else if (apiReq instanceof OnebotApi.SendGroupMsgReq) {
            frameBuilder.setSendGroupMsgReq((OnebotApi.SendGroupMsgReq) apiReq);
            frameBuilder.setFrameType(OnebotFrame.Frame.FrameType.TSendGroupMsgReq);
        }
        frameBuilder.setOk(true);
        OnebotFrame.Frame build = frameBuilder.build();
        webSocket.write(Buffer.buffer(build.toByteArray()));
        return null;
    }

    public OnebotApi.SendPrivateMsgResp sendPrivateMsg(ServerWebSocket session, Long botId, OnebotApi.SendPrivateMsgReq apiReq) {
        return (OnebotApi.SendPrivateMsgResp) callApi(session, botId, apiReq);
    }


    OnebotApi.SendGroupMsgResp sendGroupMsg(ServerWebSocket session, Long botId, OnebotApi.SendGroupMsgReq apiReq) {
        return (OnebotApi.SendGroupMsgResp) callApi(session, botId, apiReq);
    }
//
//
//    OnebotApi.SendMsgResp sendMsg(ServerWebSocket session, Long botId, OnebotApi.SendMsgReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//
//    OnebotApi.DeleteMsgResp deleteMsg(ServerWebSocket session, Long botId, OnebotApi.DeleteMsgReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//
//    OnebotApi.GetMsgResp getMsg(ServerWebSocket session, Long botId, OnebotApi.GetMsgReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.GetForwardMsgResp getForwardMsg(ServerWebSocket session, Long botId, OnebotApi.GetForwardMsgReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.SendLikeResp sendLike(ServerWebSocket session, Long botId, OnebotApi.SendLikeReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.SetGroupKickResp setGroupKick(ServerWebSocket session, Long botId, OnebotApi.SetGroupKickReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//   OnebotApi.SetGroupBanResp setGroupBan(ServerWebSocket session, Long botId, OnebotApi.SetGroupBanReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.SetGroupAnonymousBanResp setGroupAnonymousBan(ServerWebSocket session, Long botId, OnebotApi.SetGroupAnonymousBanReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.SetGroupWholeBanResp setGroupWholeBan(ServerWebSocket session, Long botId, OnebotApi.SetGroupWholeBanReq apiReq) {
//        return  callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.SetGroupAdminResp setGroupAdmin(ServerWebSocket session, Long botId, OnebotApi.SetGroupAdminReq apiReq) {
//        return  callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.SetGroupAnonymousResp setGroupAnonymous(ServerWebSocket session, Long botId, OnebotApi.SetGroupAnonymousReq apiReq) {
//        return  callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.SetGroupCardResp setGroupCard(ServerWebSocket session, Long botId, OnebotApi.SetGroupCardReq apiReq) {
//        return  callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.SetGroupNameResp setGroupName(ServerWebSocket session, Long botId, OnebotApi.SetGroupNameReq apiReq) {
//        return  callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.SetGroupLeaveResp setGroupLeave(ServerWebSocket session, Long botId, OnebotApi.SetGroupLeaveReq apiReq) {
//        return  callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.SetGroupSpecialTitleResp setGroupSpecialTitle(ServerWebSocket session, Long botId, OnebotApi.SetGroupSpecialTitleReq apiReq) {
//        return  callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.SetFriendAddRequestResp setFriendAddRequest(ServerWebSocket session, Long botId, OnebotApi.SetFriendAddRequestReq apiReq) {
//        return  callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.SetGroupAddRequestResp setGroupAddRequest(ServerWebSocket session, Long botId, OnebotApi.SetGroupAddRequestReq apiReq) {
//        return  callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.GetLoginInfoResp getLoginInfo(ServerWebSocket session, Long botId, OnebotApi.GetLoginInfoReq apiReq) {
//        return  callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.GetStrangerInfoResp getStrangerInfo(ServerWebSocket session, Long botId, OnebotApi.GetStrangerInfoReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.GetFriendListResp getFriendList(ServerWebSocket session, Long botId, OnebotApi.GetFriendListReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.GetGroupInfoResp getGroupInfo(ServerWebSocket session, Long botId, OnebotApi.GetGroupInfoReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.GetGroupListResp getGroupList(ServerWebSocket session, Long botId, OnebotApi.GetGroupListReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.GetGroupMemberInfoResp getGroupMemberInfo(ServerWebSocket session, Long botId, OnebotApi.GetGroupMemberInfoReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
//
//    OnebotApi.GetGroupMemberListResp getGroupMemberList(ServerWebSocket session, Long botId, OnebotApi.GetGroupMemberListReq apiReq) {
//        return callApi(session, botId, apiReq);
//    }
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
