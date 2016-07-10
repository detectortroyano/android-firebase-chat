package edu.detectortroyano.com.adroidchat.contactlist;

/**
 * Created by detectortroyano on 09/07/2016.
 */
public class ContactListSessionInteractorImpl implements ContactListSessionInteractor {
    ContactListRepository contactListRepository;

    public ContactListSessionInteractorImpl() {
        this.contactListRepository = new ContactListRepositoryImpl();
    }

    @Override
    public void sessionOff() {
        this.contactListRepository.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return this.contactListRepository.getCurrentUserEmail();
    }

    @Override
    public void changeConectionStatus(boolean online) {
        this.contactListRepository.changeConectionStatus(online);
    }
}
