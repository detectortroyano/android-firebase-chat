package edu.detectortroyano.com.adroidchat.contactlist;

/**
 * Created by detectortroyano on 04/07/2016.
 */
public interface ContactListInteractor {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void removeContact(String email);
}
