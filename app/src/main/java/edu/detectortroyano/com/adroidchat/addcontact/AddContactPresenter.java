package edu.detectortroyano.com.adroidchat.addcontact;

import edu.detectortroyano.com.adroidchat.addcontact.event.AddContactEvent;

/**
 * Created by detectortroyano on 10/07/2016.
 */
public interface AddContactPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}
