package edu.detectortroyano.com.adroidchat.login;

/**
 * Created by detectortroyano on 25/06/2016.
 */
public interface LoginInteractor {
    void checkSession();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
}
