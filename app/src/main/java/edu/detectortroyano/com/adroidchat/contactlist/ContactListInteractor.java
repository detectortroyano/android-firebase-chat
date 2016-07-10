package edu.detectortroyano.com.adroidchat.contactlist;

/**
 * Created by detectortroyano on 04/07/2016.
 */
public interface ContactListInteractor {
    void subscribe();
    void onSubscribe();
    void destroyListener();
    void removeContact(String email);
}
