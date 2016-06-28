package edu.detectortroyano.com.adroidchat.login;

/**
 * Created by detectortroyano on 25/06/2016.
 */
public interface LoginRepository {
    void signUp(String email, String password);
    void signIn(String email, String password);
    void checkSession();
}
