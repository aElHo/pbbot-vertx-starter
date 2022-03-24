package com.elho.pbbot.bot;

import com.elho.pbbot.utils.Msg;
import io.vertx.core.Future;
import io.vertx.core.http.ServerWebSocket;
import onebot.OnebotApi;
import onebot.OnebotBase;

import java.util.List;

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

    /**
     * 发送私聊消息
     *
     * @param userId  对方QQ号
     * @param message 消息内容
     * @return 结果
     */
    public Future<OnebotApi.SendPrivateMsgResp> sendPrivateMsg(Long userId, String message) {
        return this.sendPrivateMsg(userId, Msg.Builder().text(message).builder(), false);
    }

    /**
     * 发送私聊消息
     *
     * @param userId     对方QQ号
     * @param message    消息内容
     * @param autoEscape 消息内容是否作为纯文本发送（即不解析 CQ 码），只在 message 字段是字符串时有效
     * @return 结果
     */
    public Future<OnebotApi.SendPrivateMsgResp> sendPrivateMsg(Long userId, String message, boolean autoEscape) {
        return this.sendPrivateMsg(userId, Msg.Builder().text(message).builder(), autoEscape);
    }

    /**
     * 发送私聊消息
     *
     * @param userId     对方QQ号
     * @param msg        消息
     * @param autoEscape 消息内容是否作为纯文本发送（即不解析 CQ 码），只在 message 字段是字符串时有效
     * @return 结果
     */
    public Future<OnebotApi.SendPrivateMsgResp> sendPrivateMsg(Long userId, Msg msg, boolean autoEscape) {
        return this.sendPrivateMsg(userId, msg.getMessageList(), autoEscape);
    }

    /**
     * 发送私聊消息
     *
     * @param userId      对方QQ号
     * @param messageList 消息链
     * @param autoEscape  消息内容是否作为纯文本发送（即不解析 CQ 码），只在 message 字段是字符串时有效
     * @return 结果
     */
    public Future<OnebotApi.SendPrivateMsgResp> sendPrivateMsg(Long userId, List<OnebotBase.Message> messageList, boolean autoEscape) {
        OnebotApi.SendPrivateMsgReq.Builder reqBuilder = OnebotApi.SendPrivateMsgReq.newBuilder();
        reqBuilder.setUserId(userId).addAllMessage(messageList).setAutoEscape(autoEscape);
        return apiSender.sendPrivateMsg(serverWebSocket, selfId, reqBuilder.build());
    }

    /**
     * 发送群消息
     *
     * @param groupId 群号
     * @param message 要发送的内容
     * @return 结果
     */
    public Future<OnebotApi.SendGroupMsgResp> sendGroupMsg(Long groupId, String message) {
        return sendGroupMsg(groupId, Msg.Builder().text(message).builder(), false);
    }

    /**
     * 发送群消息
     *
     * @param groupId    群号
     * @param message    要发送的内容
     * @param autoEscape 消息内容是否作为纯文本发送（即不解析 CQ 码），只在 message 字段是字符串时有效
     * @return 结果
     */
    public Future<OnebotApi.SendGroupMsgResp> sendGroupMsg(Long groupId, String message, boolean autoEscape) {
        return sendGroupMsg(groupId, Msg.Builder().text(message).builder(), autoEscape);
    }

    /**
     * 发送群消息
     *
     * @param groupId    群号
     * @param msg        消息
     * @param autoEscape 消息内容是否作为纯文本发送（即不解析 CQ 码），只在 message 字段是字符串时有效
     * @return 结果
     */
    public Future<OnebotApi.SendGroupMsgResp> sendGroupMsg(Long groupId, Msg msg, boolean autoEscape) {
        return sendGroupMsg(groupId, msg.getMessageList(), autoEscape);
    }

    /**
     * 发送群消息
     *
     * @param groupId     群号
     * @param messageList 消息链
     * @param autoEscape  消息内容是否作为纯文本发送（即不解析 CQ 码），只在 message 字段是字符串时有效
     * @return 结果
     */
    public Future<OnebotApi.SendGroupMsgResp> sendGroupMsg(Long groupId, List<OnebotBase.Message> messageList, boolean autoEscape) {
        OnebotApi.SendGroupMsgReq.Builder builder = OnebotApi.SendGroupMsgReq.newBuilder();
        builder.setGroupId(groupId).addAllMessage(messageList).setAutoEscape(autoEscape);
        return apiSender.sendGroupMsg(serverWebSocket, selfId, builder.build());
    }

    /**
     * 撤回消息
     *
     * @param messageId 消息ID
     * @return 结果
     */
    public Future<OnebotApi.DeleteMsgResp> deleteMsg(int messageId) {
        OnebotApi.DeleteMsgReq.Builder builder = OnebotApi.DeleteMsgReq.newBuilder();
        builder.setMessageId(messageId);
        return apiSender.deleteMsg(serverWebSocket, selfId, builder.build());
    }

    /**
     * 获取消息
     *
     * @param messageId 消息ID
     * @return 结果
     */
    public Future<OnebotApi.GetMsgResp> getMsg(int messageId) {
        OnebotApi.GetMsgReq.Builder builder = OnebotApi.GetMsgReq.newBuilder();
        builder.setMessageId(messageId);
        return apiSender.getMsg(serverWebSocket, selfId, builder.build());
    }

    /**
     * 群组踢人
     *
     * @param groupId          群号
     * @param userId           要踢的 QQ 号
     * @param rejectAddRequest 拒绝此人的加群请求
     * @return 结果
     */
    public Future<OnebotApi.SetGroupKickResp> setGroupKick(Long groupId, Long userId, boolean rejectAddRequest) {
        OnebotApi.SetGroupKickReq.Builder builder = OnebotApi.SetGroupKickReq.newBuilder();
        builder.setGroupId(groupId).setUserId(userId).setRejectAddRequest(rejectAddRequest);
        return apiSender.setGroupKick(serverWebSocket, selfId, builder.build());
    }

    /**
     * 群组单人禁言
     *
     * @param groupId  群号
     * @param userId   要禁言的 QQ 号
     * @param duration 禁言时长，单位秒，0 表示取消禁言
     * @return 结果
     */
    public Future<OnebotApi.SetGroupBanResp> setGroupBan(Long groupId, Long userId, int duration) {
        OnebotApi.SetGroupBanReq.Builder builder = OnebotApi.SetGroupBanReq.newBuilder();
        builder.setGroupId(groupId).setUserId(userId).setDuration(duration);
        return apiSender.setGroupBan(serverWebSocket, selfId, builder.build());
    }

//    /**
//     * 群组全员禁言
//     *
//     * @param group_id 群号
//     * @param enable   是否禁言
//     * @return 结果
//     */
//    public Future<setGroupWholeBan(group_id: Long, enable: boolean): SetGroupWholeBanResp? {
//        val reqBuilder = SetGroupWholeBanReq.newBuilder()
//        reqBuilder.groupId = group_id
//        reqBuilder.enable = enable
//        return apiSender.setGroupWholeBan(botSession, selfId, reqBuilder.build())
//    }
//
//    /**
//     * 设置群名片（群备注）
//     *
//     * @param group_id 群号
//     * @param user_id  要设置的 QQ 号
//     * @param card     群名片内容，不填或空字符串表示删除群名片
//     * @return 结果
//     */
//    public Future<setGroupCard(group_id: Long, user_id: Long, card: String?): SetGroupCardResp? {
//        val reqBuilder = SetGroupCardReq.newBuilder()
//        reqBuilder.groupId = group_id
//        reqBuilder.userId = user_id
//        reqBuilder.card = card
//        return apiSender.setGroupCard(botSession, selfId, reqBuilder.build())
//    }
//
//    /**
//     * @param group_id   群号
//     * @param is_dismiss 是否解散，如果登录号是群主，则仅在此项为 true 时能够解散
//     * @return 结果
//     */
//    public Future<setGroupLeave(group_id: Long, is_dismiss: boolean): SetGroupLeaveResp? {
//        val reqBuilder = SetGroupLeaveReq.newBuilder()
//        reqBuilder.groupId = group_id
//        reqBuilder.isDismiss = is_dismiss
//        return apiSender.setGroupLeave(botSession, selfId, reqBuilder.build())
//    }
//
//    /**
//     * 设置群组专属头衔
//     *
//     * @param group_id      群号
//     * @param user_id       要设置的 QQ 号
//     * @param special_title 专属头衔，不填或空字符串表示删除专属头衔
//     * @param duration      专属头衔有效期，单位秒，-1 表示永久，不过此项似乎没有效果，可能是只有某些特殊的时间长度有效，有待测试
//     * @return 结果
//     */
//    public Future<setGroupSpecialTitle(
//        group_id: Long,
//        user_id: Long,
//        special_title: String?,
//        duration: Long
//    ): SetGroupSpecialTitleResp? {
//        val reqBuilder = SetGroupSpecialTitleReq.newBuilder()
//        reqBuilder.groupId = group_id
//        reqBuilder.userId = user_id
//        reqBuilder.specialTitle = special_title
//        reqBuilder.duration = duration
//        return apiSender.setGroupSpecialTitle(botSession, selfId, reqBuilder.build())
//    }
//
//
//    /**
//     * 处理加好友请求
//     *
//     * @param flag    加好友请求的 flag（需从上报的数据中获得）
//     * @param approve 是否同意请求
//     * @param remark  添加后的好友备注（仅在同意时有效）
//     * @return 结果
//     */
//    public Future<setFriendAddRequest(flag: String, approve: boolean, remark: String?): SetFriendAddRequestResp? {
//        val reqBuilder = SetFriendAddRequestReq.newBuilder()
//        reqBuilder.flag = flag
//        reqBuilder.approve = approve
//        reqBuilder.remark = remark
//        return apiSender.setFriendAddRequest(botSession, selfId, reqBuilder.build())
//    }
//
//    /**
//     * 处理加群请求／邀请
//     *
//     * @param flag     加群请求的 flag（需从上报的数据中获得）
//     * @param sub_type add 或 invite，请求类型（需要和上报消息中的 sub_type 字段相符）
//     * @param approve  是否同意请求／邀请
//     * @param reason   拒绝理由（仅在拒绝时有效）
//     * @return 结果
//     */
//    public Future<setGroupAddRequest(
//        flag: String,
//        sub_type: String?,
//        approve: boolean,
//        reason: String?
//    ): SetGroupAddRequestResp? {
//        val reqBuilder = SetGroupAddRequestReq.newBuilder()
//        reqBuilder.flag = flag
//        reqBuilder.subType = sub_type
//        reqBuilder.approve = approve
//        reqBuilder.reason = reason
//        return apiSender.setGroupAddRequest(botSession, selfId, reqBuilder.build())
//    }
//
//    /**
//     * 获取登录号信息
//     *
//     * @return 结果
//     */
//    public Future<getLoginInfo(): GetLoginInfoResp? {
//        val reqBuilder = GetLoginInfoReq.newBuilder()
//        return apiSender.getLoginInfo(botSession, selfId, reqBuilder.build())
//    }
//
//    /**
//     * 获取陌生人信息
//     *
//     * @param user_id   QQ号
//     * @return 结果
//     */
//    public Future<getStrangerInfo(user_id: Long): GetStrangerInfoResp? {
//        val reqBuilder = GetStrangerInfoReq.newBuilder()
//        reqBuilder.userId = user_id
//        return apiSender.getStrangerInfo(botSession, selfId, reqBuilder.build())
//    }
//
//    /**
//     * 获取好友列表
//     *
//     * @return 结果
//     */
//    public Future<getFriendList(): GetFriendListResp? {
//        val reqBuilder = GetFriendListReq.newBuilder()
//        return apiSender.getFriendList(botSession, selfId, reqBuilder.build())
//    }
//
//    /**
//     * 获取群列表
//     *
//     * @return 结果
//     */
//    public Future<getGroupList(): GetGroupListResp? {
//        val reqBuilder = GetGroupListReq.newBuilder()
//        return apiSender.getGroupList(botSession, selfId, reqBuilder.build())
//    }
//
//    /**
//     * 获取群信息
//     *
//     * @param group_id 群号
//     * @param no_cache 是否不使用缓存（使用缓存可能更新不及时，但响应更快）
//     * @return 结果
//     */
//    public Future<getGroupInfo(group_id: Long, no_cache: boolean): GetGroupInfoResp? {
//        val reqBuilder = GetGroupInfoReq.newBuilder()
//        reqBuilder.groupId = group_id
//        reqBuilder.noCache = no_cache
//        return apiSender.getGroupInfo(botSession, selfId, reqBuilder.build())
//    }
//
//    /**
//     * 获取群成员信息
//     *
//     * @param group_id 群号
//     * @param user_id  QQ 号
//     * @param no_cache 是否不使用缓存（使用缓存可能更新不及时，但响应更快）
//     * @return 结果
//     */
//    public Future<getGroupMemberInfo(group_id: Long, user_id: Long, no_cache: boolean): GetGroupMemberInfoResp? {
//        val reqBuilder = GetGroupMemberInfoReq.newBuilder()
//        reqBuilder.groupId = group_id
//        reqBuilder.userId = user_id
//        reqBuilder.noCache = no_cache
//        return apiSender.getGroupMemberInfo(botSession, selfId, reqBuilder.build())
//    }
//
//    /**
//     * 获取群成员列表
//     *
//     *
//     * 响应内容为 JSON 数组，每个元素的内容和上面的 /get_group_member_info 接口相同，但对于同一个群组的同一个成员，获取列表时和获取单独的成员信息时，某些字段可能有所不同，例如 area、title 等字段在获取列表时无法获得，具体应以单独的成员信息为准。
//     *
//     * @param group_id 群号
//     * @return 结果
//     */
//    public Future<getGroupMemberList(group_id: Long): GetGroupMemberListResp? {
//        val reqBuilder = GetGroupMemberListReq.newBuilder()
//        reqBuilder.groupId = group_id
//        return apiSender.getGroupMemberList(botSession, selfId, reqBuilder.build())
//    }
}
