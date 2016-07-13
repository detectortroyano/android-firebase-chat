package edu.detectortroyano.com.adroidchat.addcontact;

/**
 * Created by detectortroyano on 10/07/2016.
 */
public class AddContactInteractorImpl implements AddContactInteractor {
    AddContactRepository addContactRepository;

    public AddContactInteractorImpl(AddContactRepositoryImpl addContactRepository) {
        this.addContactRepository = addContactRepository;
    }

    @Override
    public void execute(String email) {
        addContactRepository.addContact(email);
    }
}
