package edu.detectortroyano.com.adroidchat.login;

import android.util.Log;

import edu.detectortroyano.com.adroidchat.domain.FirebaseHelper;

/**
 * Created by detectortroyano on 27/06/2016.
 */
public class LoginRepositoryImpl implements LoginRepository {
    private FirebaseHelper firebaseHelper;

    public LoginRepositoryImpl() {
        this.firebaseHelper = FirebaseHelper.getInstance();
    }

    @Override
    public void signUp(String email, String password) {
        Log.e("LoginRepositoryImpl","SignUp");
    }

    @Override
    public void signIn(String email, String password) {
        Log.e("LoginRepositoryImpl","SignIn");
    }

    @Override
    public void checkSession() {
        Log.e("LoginRepositoryImpl","checkSession");
    }
}
