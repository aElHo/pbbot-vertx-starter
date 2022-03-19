package com.elho.pbbot.handler;

import com.elho.pbbot.bot.Bot;
import com.elho.pbbot.bot.BotContainer;
import onebot.OnebotFrame;

/**
 * @author zyf
 * @Date 2022-03-17
 */
public class FrameHandler {

    public static void handleFrame(OnebotFrame.Frame frame){
        long botId = frame.getBotId();
        Bot bot = BotContainer.INSTANCE.getBot(botId);
        OnebotFrame.Frame.FrameType frameType = frame.getFrameType();
        switch (frameType){
            case TUNKNOWN:
                break;
            case TPrivateMessageEvent:
               EventHandler.handlePrivateMessageEvent(bot,frame.getPrivateMessageEvent());
            case TGroupMessageEvent:
                EventHandler.handleGroupMessageEvent(bot,frame.getGroupMessageEvent());
                break;
            case TGroupUploadNoticeEvent:
                break;
            case TGroupAdminNoticeEvent:
                break;
            case TGroupDecreaseNoticeEvent:
                break;
            case TGroupIncreaseNoticeEvent:
                break;
            case TGroupBanNoticeEvent:
                break;
            case TFriendAddNoticeEvent:
                break;
            case TGroupRecallNoticeEvent:
                break;
            case TFriendRecallNoticeEvent:
                break;
            case TFriendRequestEvent:
                break;
            case TGroupRequestEvent:
                break;
            case TSendPrivateMsgReq:
                break;
            case TSendGroupMsgReq:
                break;
            case TSendMsgReq:
                break;
            case TDeleteMsgReq:
                break;
            case TGetMsgReq:
                break;
            case TGetForwardMsgReq:
                break;
            case TSendLikeReq:
                break;
            case TSetGroupKickReq:
                break;
            case TSetGroupBanReq:
                break;
            case TSetGroupAnonymousReq:
                break;
            case TSetGroupWholeBanReq:
                break;
            case TSetGroupAdminReq:
                break;
            case TSetGroupAnonymousBanReq:
                break;
            case TSetGroupCardReq:
                break;
            case TSetGroupNameReq:
                break;
            case TSetGroupLeaveReq:
                break;
            case TSetGroupSpecialTitleReq:
                break;
            case TSetFriendAddRequestReq:
                break;
            case TSetGroupAddRequestReq:
                break;
            case TGetLoginInfoReq:
                break;
            case TGetStrangerInfoReq:
                break;
            case TGetFriendListReq:
                break;
            case TGetGroupInfoReq:
                break;
            case TGetGroupListReq:
                break;
            case TGetGroupMemberInfoReq:
                break;
            case TGetGroupMemberListReq:
                break;
            case TGetGroupHonorInfoReq:
                break;
            case TGetCookiesReq:
                break;
            case TGetCsrfTokenReq:
                break;
            case TGetCredentialsReq:
                break;
            case TGetRecordReq:
                break;
            case TGetImageReq:
                break;
            case TCanSendImageReq:
                break;
            case TCanSendRecordReq:
                break;
            case TGetStatusReq:
                break;
            case TGetVersionInfoReq:
                break;
            case TSetRestartReq:
                break;
            case TCleanCacheReq:
                break;
            case TSendPrivateMsgResp:
            case TSendGroupMsgResp:
                break;
            case TSendMsgResp:
                break;
            case TDeleteMsgResp:
                break;
            case TGetMsgResp:
                break;
            case TGetForwardMsgResp:
                break;
            case TSendLikeResp:
                break;
            case TSetGroupKickResp:
                break;
            case TSetGroupBanResp:
                break;
            case TSetGroupAnonymousResp:
                break;
            case TSetGroupWholeBanResp:
                break;
            case TSetGroupAdminResp:
                break;
            case TSetGroupAnonymousBanResp:
                break;
            case TSetGroupCardResp:
                break;
            case TSetGroupNameResp:
                break;
            case TSetGroupLeaveResp:
                break;
            case TSetGroupSpecialTitleResp:
                break;
            case TSetFriendAddRequestResp:
                break;
            case TSetGroupAddRequestResp:
                break;
            case TGetLoginInfoResp:
                break;
            case TGetStrangerInfoResp:
                break;
            case TGetFriendListResp:
                break;
            case TGetGroupInfoResp:
                break;
            case TGetGroupListResp:
                break;
            case TGetGroupMemberInfoResp:
                break;
            case TGetGroupMemberListResp:
                break;
            case TGetGroupHonorInfoResp:
                break;
            case TGetCookiesResp:
                break;
            case TGetCsrfTokenResp:
                break;
            case TGetCredentialsResp:
                break;
            case TGetRecordResp:
                break;
            case TGetImageResp:
                break;
            case TCanSendImageResp:
                break;
            case TCanSendRecordResp:
                break;
            case TGetStatusResp:
                break;
            case TGetVersionInfoResp:
                break;
            case TSetRestartResp:
                break;
            case TCleanCacheResp:
                break;
            case UNRECOGNIZED:
                break;
            default:
                break;
        }
    }
}
