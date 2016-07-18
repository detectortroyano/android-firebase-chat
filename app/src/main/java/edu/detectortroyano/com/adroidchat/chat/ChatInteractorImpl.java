package edu.detectortroyano.com.adroidchat.chat;

/**
 * Created by detectortroyano on 17/07/2016.
 */
public class ChatInteractorImpl implements ChatInteractor {
    ChatRepository chatRepository;

    public ChatInteractorImpl() {
        this.chatRepository = new ChatRepositoryImpl();
    }

    @Override
    public void sendMessage(String msg) {
        this.chatRepository.sendMessage(msg);
    }

    @Override
    public void setRecipient(String recipient) {
        this.chatRepository.setReceiver(recipient);
    }

    @Override
    public void subscribeForChatUpates() {
        this.chatRepository.subscribeForChatUpates();
    }

    @Override
    public void unSubscribeForChatUpates() {
        this.chatRepository.unSubscribeForChatUpates();
    }

    @Override
    public void destroyChatListener() {
        this.chatRepository.destroyChatListener();
    }
}
