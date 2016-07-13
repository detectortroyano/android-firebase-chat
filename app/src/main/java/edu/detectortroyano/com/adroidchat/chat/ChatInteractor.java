package edu.detectortroyano.com.adroidchat.chat;

/**
 * Created by detectortroyano on 12/07/2016.
 */
public interface ChatInteractor {
    void sendMessage(String msg);
    void setRecipient(String recipient);

    void destroyChatListener();
    void subscribeForChatUpates();
    void unSubscribeForChatUpates();
}
