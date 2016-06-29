package edu.detectortroyano.com.adroidchat.login;

import edu.detectortroyano.com.adroidchat.login.events.LoginEvent;

/**
 * Created by detectortroyano on 25/06/2016.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void valideLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent loginEvent);
}
