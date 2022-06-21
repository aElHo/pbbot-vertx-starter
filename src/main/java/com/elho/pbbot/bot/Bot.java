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
    private Long selfId;
    private ServerWebSocket serverWebSocket;
    private ApiSender apiSender;

    public Bot(Long selfId, ServerWebSocket serverWebSocket) {
        this.selfId = selfId;
        this.serverWebSocket = serverWebSocket;
        this.apiSender = new ApiSender();
    }

    private Bot() {
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
        OnebotApi.SendPrivateMsgReq.Builder builder = OnebotApi.SendPrivateMsgReq.newBuilder();
        builder.setUserId(userId).addAllMessage(messageList).setAutoEscape(autoEscape);
        return apiSender.sendPrivateMsg(serverWebSocket, selfId, builder.build());
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
    public Future<OnebotApi.DeleteMsgResp> deleteMsg(OnebotBase.MessageReceipt messageId) {
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

    /**
     * 群组全员禁言
     *
     * @param groupId 群号
     * @param enable  是否禁言
     * @return 结果
     */
    public Future<OnebotApi.SetGroupWholeBanResp> setGroupWholeBan(Long groupId, boolean enable) {
        OnebotApi.SetGroupWholeBanReq.Builder builder = OnebotApi.SetGroupWholeBanReq.newBuilder();
        builder.setGroupId(groupId).setEnable(enable);
        return apiSender.setGroupWholeBan(serverWebSocket, selfId, builder.build());
    }

    /**
     * 设置群名片（群备注）
     *
     * @param groupId 群号
     * @param userId  要设置的 QQ 号
     * @param card    群名片内容，不填或空字符串表示删除群名片
     * @return 结果
     */
    public Future<OnebotApi.SetGroupCardResp> setGroupCard(Long groupId, Long userId, String card) {
        OnebotApi.SetGroupCardReq.Builder builder = OnebotApi.SetGroupCardReq.newBuilder();
        builder.setGroupId(groupId).setUserId(userId).setCard(card);
        return apiSender.setGroupCard(serverWebSocket, selfId, builder.build());
    }

    /**
     * 解散群组
     *
     * @param groupId   群号
     * @param isDismiss 是否解散，如果登录号是群主，则仅在此项为 true 时能够解散
     * @return 结果
     */
    public Future<OnebotApi.SetGroupLeaveResp> setGroupLeave(Long groupId, boolean isDismiss) {
        OnebotApi.SetGroupLeaveReq.Builder builder = OnebotApi.SetGroupLeaveReq.newBuilder();
        builder.setGroupId(groupId).setIsDismiss(isDismiss);
        return apiSender.setGroupLeave(serverWebSocket, selfId, builder.build());
    }

    /**
     * 设置群组专属头衔
     *
     * @param groupId      群号
     * @param userId       要设置的 QQ 号
     * @param specialTitle 专属头衔，不填或空字符串表示删除专属头衔
     * @param duration     专属头衔有效期，单位秒，-1 表示永久，不过此项似乎没有效果，可能是只有某些特殊的时间长度有效，有待测试
     * @return 结果
     */
    public Future<OnebotApi.SetGroupSpecialTitleResp> setGroupSpecialTitle(Long groupId, Long userId, String specialTitle, Long duration) {
        OnebotApi.SetGroupSpecialTitleReq.Builder builder = OnebotApi.SetGroupSpecialTitleReq.newBuilder();
        builder.setGroupId(groupId).setUserId(userId).setSpecialTitle(specialTitle).setDuration(duration);
        return apiSender.setGroupSpecialTitle(serverWebSocket, selfId, builder.build());
    }


    /**
     * 处理加好友请求
     *
     * @param flag    加好友请求的 flag（需从上报的数据中获得）
     * @param approve 是否同意请求
     * @param remark  添加后的好友备注（仅在同意时有效）
     * @return 结果
     */
    public Future<OnebotApi.SetFriendAddRequestResp> setFriendAddRequest(String flag, boolean approve, String remark) {
        OnebotApi.SetFriendAddRequestReq.Builder builder = OnebotApi.SetFriendAddRequestReq.newBuilder();
        builder.setFlag(flag).setApprove(approve).setRemark(remark);
        return apiSender.setFriendAddRequest(serverWebSocket, selfId, builder.build());
    }

    /**
     * 处理加群请求／邀请
     *
     * @param flag    加群请求的 flag（需从上报的数据中获得）
     * @param subType add 或 invite，请求类型（需要和上报消息中的 subType 字段相符）
     * @param approve 是否同意请求／邀请
     * @param reason  拒绝理由（仅在拒绝时有效）
     * @return 结果
     */
    public Future<OnebotApi.SetGroupAddRequestResp> setGroupAddRequest(String flag, String subType, boolean approve, String reason) {
        OnebotApi.SetGroupAddRequestReq.Builder builder = OnebotApi.SetGroupAddRequestReq.newBuilder();
        builder.setFlag(flag).setSubType(subType).setApprove(approve).setReason(reason);
        return apiSender.setGroupAddRequest(serverWebSocket, selfId, builder.build());
    }

    /**
     * 获取登录号信息
     *
     * @return 结果
     */
    public Future<OnebotApi.GetLoginInfoResp> getLoginInfo() {
        OnebotApi.GetLoginInfoReq.Builder builder = OnebotApi.GetLoginInfoReq.newBuilder();
        return apiSender.getLoginInfo(serverWebSocket, selfId, builder.build());
    }

    /**
     * 获取陌生人信息
     *
     * @param userId QQ号
     * @return 结果
     */
    public Future<OnebotApi.GetStrangerInfoResp> getStrangerInfo(Long userId) {
        OnebotApi.GetStrangerInfoReq.Builder builder = OnebotApi.GetStrangerInfoReq.newBuilder();
        builder.setUserId(userId);
        return apiSender.getStrangerInfo(serverWebSocket, selfId, builder.build());
    }

    /**
     * 获取好友列表
     *
     * @return 结果
     */
    public Future<OnebotApi.GetFriendListResp> getFriendList() {
        OnebotApi.GetFriendListReq.Builder builder = OnebotApi.GetFriendListReq.newBuilder();
        return apiSender.getFriendList(serverWebSocket, selfId, builder.build());
    }

    /**
     * 获取群列表
     *
     * @return 结果
     */
    public Future<OnebotApi.GetGroupListResp> getGroupList() {
        OnebotApi.GetGroupListReq.Builder builder = OnebotApi.GetGroupListReq.newBuilder();
        return apiSender.getGroupList(serverWebSocket, selfId, builder.build());
    }

    /**
     * 获取群信息
     *
     * @param groupId 群号
     * @param noCache 是否不使用缓存（使用缓存可能更新不及时，但响应更快）
     * @return 结果
     */
    public Future<OnebotApi.GetGroupInfoResp> getGroupInfo(Long groupId, boolean noCache) {
        OnebotApi.GetGroupInfoReq.Builder builder = OnebotApi.GetGroupInfoReq.newBuilder();
        builder.setGroupId(groupId).setNoCache(noCache);
        return apiSender.getGroupInfo(serverWebSocket, selfId, builder.build());
    }

    /**
     * 获取群成员信息
     *
     * @param groupId 群号
     * @param userId  QQ 号
     * @param noCache 是否不使用缓存（使用缓存可能更新不及时，但响应更快）
     * @return 结果
     */
    public Future<OnebotApi.GetGroupMemberInfoResp> getGroupMemberInfo(Long groupId, Long userId, boolean noCache) {
        OnebotApi.GetGroupMemberInfoReq.Builder builder = OnebotApi.GetGroupMemberInfoReq.newBuilder();
        builder.setGroupId(groupId).setUserId(userId).setNoCache(noCache);
        return apiSender.getGroupMemberInfo(serverWebSocket, selfId, builder.build());
    }

    /**
     * 获取群成员列表
     * <p>
     * <p>
     * 响应内容为 JSON 数组，每个元素的内容和上面的 /get_group_member_info 接口相同，但对于同一个群组的同一个成员，获取列表时和获取单独的成员信息时，某些字段可能有所不同，例如 area、title 等字段在获取列表时无法获得，具体应以单独的成员信息为准。
     *
     * @param groupId 群号
     * @return 结果
     */
    public Future<OnebotApi.GetGroupMemberListResp> getGroupMemberList(Long groupId) {
        OnebotApi.GetGroupMemberListReq.Builder builder = OnebotApi.GetGroupMemberListReq.newBuilder();
        builder.setGroupId(groupId);
        return apiSender.getGroupMemberList(serverWebSocket, selfId, builder.build());
    }
}
