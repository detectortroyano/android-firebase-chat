package edu.detectortroyano.com.adroidchat.chat.event;

import edu.detectortroyano.com.adroidchat.chat.entities.ChatMessage;

/**
 * Created by detectortroyano on 12/07/2016.
 */
public class ChatEvent {
    ChatMessage msg;

    public ChatEvent(ChatMessage msg) {
        this.msg = msg;
    }

    public ChatMessage getMessage() {
        return msg;
    }
}
