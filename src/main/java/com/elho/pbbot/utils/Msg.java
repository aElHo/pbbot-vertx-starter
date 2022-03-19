package com.elho.pbbot.utils;

import cn.hutool.core.map.MapUtil;
import onebot.OnebotBase;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zyf
 * @Date 2022-03-19
 */
public class Msg {

    private List<OnebotBase.Message> messageList = new LinkedList<>();


    public List<OnebotBase.Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<OnebotBase.Message> messageList) {
        this.messageList = messageList;
    }

    public void addMessageList(OnebotBase.Message message) {
        this.messageList.add(message);
    }

    public static Builder Builder() {
        return new Builder();
    }

    public static class Builder {
        private Msg msg = new Msg();

        public Msg builder() {
            return msg;
        }

        public Builder text(String text) {
            HashMap<String, String> messageMap = MapUtil.newHashMap(1);
            messageMap.put("text", text);
            msg.addMessageList(OnebotBase.Message.newBuilder().setType("text").putAllData(messageMap).build());
            return this;
        }

        public Builder image(String url) {
            HashMap<String, String> messageMap = MapUtil.newHashMap(1);
            messageMap.put("url", url);
            msg.addMessageList(OnebotBase.Message.newBuilder().setType("image").putAllData(messageMap).build());
            return this;
        }

        public Builder face(Integer id) {
            HashMap<String, String> messageMap = MapUtil.newHashMap(1);
            messageMap.put("id", String.valueOf(id));
            msg.addMessageList(OnebotBase.Message.newBuilder().setType("face").putAllData(messageMap).build());
            return this;
        }

        public Builder at(Long qq) {
            HashMap<String, String> messageMap = MapUtil.newHashMap(1);
            messageMap.put("qq", String.valueOf(qq));
            msg.addMessageList(OnebotBase.Message.newBuilder().setType("at").putAllData(messageMap).build());
            return this;
        }

        public Builder atAll() {
            HashMap<String, String> messageMap = MapUtil.newHashMap(1);
            messageMap.put("qq", "all");
            msg.addMessageList(OnebotBase.Message.newBuilder().setType("at").putAllData(messageMap).build());
            return this;
        }
    }
}
