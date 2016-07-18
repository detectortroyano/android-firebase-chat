package edu.detectortroyano.com.adroidchat.chat;

import org.greenrobot.eventbus.Subscribe;

import edu.detectortroyano.com.adroidchat.chat.entities.ChatMessage;
import edu.detectortroyano.com.adroidchat.chat.event.ChatEvent;
import edu.detectortroyano.com.adroidchat.chat.ui.ChatView;
import edu.detectortroyano.com.adroidchat.entities.User;
import edu.detectortroyano.com.adroidchat.lib.EventBus;
import edu.detectortroyano.com.adroidchat.lib.GreenRobotEventBus;

/**
 * Created by detectortroyano on 12/07/2016.
 */
public class ChatPresenterImpl implements  ChatPresenter{
    private EventBus eventBus;
    private ChatView chatView;
    private ChatInteractor chatInteractor;
    private ChatSessionInteractor chatSessionInteractor;

    public ChatPresenterImpl(ChatView chatView) {
        this.chatView = chatView;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.chatInteractor = new ChatInteractorImpl();
        this.chatSessionInteractor = new ChatSessionInteractorImpl();
    }

    @Override
    public void onPause() {
        this.chatInteractor.unSubscribeForChatUpates();
        chatSessionInteractor.changeConnectionStatus(User.OFFLINE);
    }

    @Override
    public void onResume() {
        this.chatInteractor.subscribeForChatUpates();
        chatSessionInteractor.changeConnectionStatus(User.ONLINE);
    }

    @Override
    public void onCreate() {
        this.eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.eventBus.unregister(this);
        chatInteractor.destroyChatListener();
        this.chatView = null;
    }

    @Override
    public void setChatRecipient(String recipient) {
        chatInteractor.setRecipient(recipient);
    }

    @Override
    public void sendMessage(String msg) {
        chatInteractor.sendMessage(msg);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ChatEvent event) {
        if (chatView != null) {
            ChatMessage msg = event.getMessage();
            chatView.onMessageReceived(msg);
        }
    }
}
