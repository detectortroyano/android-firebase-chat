package edu.detectortroyano.com.adroidchat.login;

/**
 * Created by detectortroyano on 25/06/2016.
 */
public interface LoginPresenter {
    void onDestroy();
    void checkForAuthenticatedUser();
    void valideLogin(String email, String password);
    void registerNewUser(String email, String password);
}
