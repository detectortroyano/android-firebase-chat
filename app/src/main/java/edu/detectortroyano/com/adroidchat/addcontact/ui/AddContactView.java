package edu.detectortroyano.com.adroidchat.addcontact.ui;

/**
 * Created by detectortroyano on 10/07/2016.
 */
public interface AddContactView {
    void showInput();
    void hideInput();
    void showProgress();
    void hideProgress();

    void contactAdded();
    void contactNotAdded();
}
