package edu.detectortroyano.com.adroidchat.chat;

/**
 * Created by detectortroyano on 17/07/2016.
 */
public class ChatSessionInteractorImpl implements ChatSessionInteractor {
    ChatRepository chatRepository;

    public ChatSessionInteractorImpl() {
        this.chatRepository = new ChatRepositoryImpl();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        this.chatRepository.changeUserConnectionStatus(online);
    }
}
