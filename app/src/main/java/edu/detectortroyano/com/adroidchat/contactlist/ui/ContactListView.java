package edu.detectortroyano.com.adroidchat.contactlist.ui;

import edu.detectortroyano.com.adroidchat.entities.User;

/**
 * Created by detectortroyano on 04/07/2016.
 */
public interface ContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
