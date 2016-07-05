package edu.detectortroyano.com.adroidchat.contactlist;

import edu.detectortroyano.com.adroidchat.contactlist.event.ContactListEvent;

/**
 * Created by detectortroyano on 04/07/2016.
 */
public interface ContactListPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void onEventMainThread(ContactListEvent contactListEvent);
}
