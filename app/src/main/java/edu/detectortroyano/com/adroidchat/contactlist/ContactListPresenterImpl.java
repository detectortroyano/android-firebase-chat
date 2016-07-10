package edu.detectortroyano.com.adroidchat.contactlist;

import org.greenrobot.eventbus.Subscribe;

import edu.detectortroyano.com.adroidchat.contactlist.event.ContactListEvent;
import edu.detectortroyano.com.adroidchat.contactlist.ui.ContactListView;
import edu.detectortroyano.com.adroidchat.entities.User;
import edu.detectortroyano.com.adroidchat.lib.EventBus;
import edu.detectortroyano.com.adroidchat.lib.GreenRobotEventBus;

/**
 * Created by detectortroyano on 09/07/2016.
 */
public class ContactListPresenterImpl implements ContactListPresenter {
    private EventBus eventBus;
    private ContactListView contactListView;
    private ContactListInteractor contactListInteractor;
    private ContactListSessionInteractor contactListSessionInteractor;

    public ContactListPresenterImpl(ContactListView contactListView) {
        this.contactListView = contactListView;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.contactListInteractor = new ContactListInteractorImpl();
        this.contactListSessionInteractor = new ContactListSessionInteractorImpl();
    }

    @Override
    public void onPause() {
        contactListSessionInteractor.changeConectionStatus(User.OFFLINE);
        contactListInteractor.unsubscribe();
    }

    @Override
    public void onResume() {
        contactListSessionInteractor.changeConectionStatus(User.ONLINE);
        contactListInteractor.subscribe();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        contactListInteractor.destroyListener();
        contactListView = null;
    }

    @Override
    public void signOff() {
        contactListSessionInteractor.changeConectionStatus(User.OFFLINE);
        contactListInteractor.subscribe();
        contactListInteractor.destroyListener();
        contactListSessionInteractor.sessionOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return contactListSessionInteractor.getCurrentUserEmail();
    }

    @Override
    public void removeContact(String email) {
        contactListInteractor.removeContact(email);
    }

        @Override
    @Subscribe
    public void onEventMainThread(ContactListEvent event) {
        User user = event.getUser();
        switch (event.getEventType()) {
            case ContactListEvent.onContactAdded:
                onContactAdded(user);
                break;
            case ContactListEvent.onContactChanged:
                onContactChanged(user);
                break;
            case ContactListEvent.onContactRemoved:
                onContactRemoved(user);
                break;
        }
    }

    private void onContactAdded(User user) {
        if (contactListView != null){
            contactListView.onContactAdded(user);
        }
    }

    private void onContactChanged(User user) {
        if (contactListView != null){
            contactListView.onContactChanged(user);
        }
    }

    private void onContactRemoved(User user) {
        if (contactListView != null){
            contactListView.onContactRemoved(user);
        }
    }
}
