package edu.detectortroyano.com.adroidchat.contactlist;

/**
 * Created by detectortroyano on 04/07/2016.
 */
public interface ContactListRepository {
    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void destroyListener();
    void suscribeToContactListEvent();
    void unsuscribeToContactListEvent();
    void changeConectionStatus(boolean online);

}
