package edu.detectortroyano.com.adroidchat.chat.ui;

import edu.detectortroyano.com.adroidchat.chat.entities.ChatMessage;

/**
 * Created by detectortroyano on 12/07/2016.
 */
public interface ChatView {
    void sendMessage();
    void onMessageReceived(ChatMessage msg);
}
