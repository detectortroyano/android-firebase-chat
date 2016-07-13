package edu.detectortroyano.com.adroidchat.chat;

import edu.detectortroyano.com.adroidchat.chat.event.ChatEvent;

/**
 * Created by detectortroyano on 12/07/2016.
 */
public interface ChatPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void setChatRecipient(String recipient);

    void sendMessage(String msg);
    void onEventMainThread(ChatEvent event);
}
