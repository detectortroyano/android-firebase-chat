package edu.detectortroyano.com.adroidchat.login;

/**
 * Created by detectortroyano on 25/06/2016.
 */
public interface LoginRepository {
    void SignUp(String email, String password);
    void SignIn(String email, String password);
    void checkSession();
}
