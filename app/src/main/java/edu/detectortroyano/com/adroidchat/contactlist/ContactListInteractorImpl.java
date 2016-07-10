package edu.detectortroyano.com.adroidchat.contactlist;

/**
 * Created by detectortroyano on 09/07/2016.
 */
public class ContactListInteractorImpl implements ContactListInteractor {
    ContactListRepository contactListRepository;

    public ContactListInteractorImpl() {
        this.contactListRepository = new ContactListRepositoryImpl();
    }

    @Override
    public void subscribe() {
        this.contactListRepository.suscribeToContactListEvent();
    }

    @Override
    public void unsubscribe() {
        this.contactListRepository.unsuscribeToContactListEvent();
    }

    @Override
    public void destroyListener() {
        this.contactListRepository.destroyListener();
    }

    @Override
    public void removeContact(String email) {
        this.contactListRepository.removeContact(email);
    }
}
