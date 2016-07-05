package edu.detectortroyano.com.adroidchat.contactlist;

/**
 * Created by detectortroyano on 04/07/2016.
 */
public interface ContactListSessionInteractor {
    void sessionOff();
    String getCurrentUserEmail();
    void changeConectionStatus(boolean online);
}
