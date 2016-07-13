package edu.detectortroyano.com.adroidchat.addcontact;

import org.greenrobot.eventbus.Subscribe;

import edu.detectortroyano.com.adroidchat.addcontact.AddContactPresenter;
import edu.detectortroyano.com.adroidchat.addcontact.event.AddContactEvent;
import edu.detectortroyano.com.adroidchat.addcontact.ui.AddContactView;
import edu.detectortroyano.com.adroidchat.lib.EventBus;
import edu.detectortroyano.com.adroidchat.lib.GreenRobotEventBus;

/**
 * Created by detectortroyano on 10/07/2016.
 */
public class AddContactPresenterImpl implements AddContactPresenter {
    private EventBus eventBus;
    private AddContactView addContactView;
    private AddContactInteractor addContactInteractor;

    public AddContactPresenterImpl(AddContactView addContactView) {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.addContactView = addContactView;
        this.addContactInteractor = new AddContactInteractorImpl(new AddContactRepositoryImpl());
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.addContactView = null;
        eventBus.unregister(this);
    }

    @Override
    public void addContact(String email) {
        if (this.addContactView != null) {
            addContactView.hideInput();
            addContactView.showProgress();
        }
        this.addContactInteractor.execute(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddContactEvent event) {
        if (addContactView != null) {
            addContactView.hideProgress();
            addContactView.showInput();

            if (event.isError()) {
                addContactView.contactNotAdded();
            } else {
                addContactView.contactAdded();
            }
        }
    }
}
