package edu.detectortroyano.com.adroidchat.chat;

/**
 * Created by detectortroyano on 12/07/2016.
 */
public interface ChatRepository {
    void sendMessage(String msg);
    void setReceiver(String receiver);

    void destroyChatListener();
    void subscribeForChatUpates();
    void unSubscribeForChatUpates();

    void changeUserConnectionStatus(boolean online);
}
