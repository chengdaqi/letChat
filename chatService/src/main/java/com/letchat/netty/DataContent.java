package com.letchat.netty;

import com.letchat.pojo.ChatMsg;

import java.io.Serializable;

/**
 * @author alice
 */
public class DataContent implements Serializable {

    private static final long serialVersionUID = 8021381444738260454L;

	/** 动作类型 **/
    private Integer action;
	/** 用户的聊天内容entity **/
    private ChatMsg chatMsg;
    /** 扩展字段 **/
    private String extand;

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public ChatMsg getChatMsg() {
        return chatMsg;
    }

    public void setChatMsg(ChatMsg chatMsg) {
        this.chatMsg = chatMsg;
    }

    public String getExtand() {
        return extand;
    }

    public void setExtand(String extand) {
        this.extand = extand;
    }
}
