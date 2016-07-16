package edu.detectortroyano.com.adroidchat.chat.entities;

import com.google.firebase.database.Exclude;

import edu.detectortroyano.com.adroidchat.entities.User;

/**
 * Created by detectortroyano on 12/07/2016.
 */
//@JsonIgnoreProperties("sentByMe")
public class ChatMessage {
    String msg;
    String sender;
    @Exclude
    boolean sentByMe;

    public ChatMessage(){}

    public ChatMessage(String sender, String msg){
        this.msg = msg;
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isSentByMe() {
        return sentByMe;
    }

    public void setSentByMe(boolean sentByMe) {
        this.sentByMe = sentByMe;
    }

    public boolean equals(Object object){
        boolean equal = false;
        if (object instanceof ChatMessage){
            ChatMessage msg = (ChatMessage) object;
            equal = this.msg.equals(msg.getMsg()) && this.sender.equals(msg.getSender()) && this.sentByMe == msg.sentByMe;
        }
        return equal;
    }
}
