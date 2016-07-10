package edu.detectortroyano.com.adroidchat.addcontact;

import edu.detectortroyano.com.adroidchat.addcontact.AddContactPresenter;
import edu.detectortroyano.com.adroidchat.addcontact.event.AddContactEvent;
import edu.detectortroyano.com.adroidchat.addcontact.ui.AddContactView;

/**
 * Created by detectortroyano on 10/07/2016.
 */
public class AddContactPresenterImpl implements AddContactPresenter {
    private AddContactView addContactView;

    public AddContactPresenterImpl(AddContactView addContactView) {
        this.addContactView = addContactView;
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void addContact(String email) {

    }

    @Override
    public void onEventMainThread(AddContactEvent event) {

    }
}
