package edu.detectortroyano.com.adroidchat.login;

import android.util.Log;

import edu.detectortroyano.com.adroidchat.domain.FirebaseHelper;
import edu.detectortroyano.com.adroidchat.lib.EventBus;
import edu.detectortroyano.com.adroidchat.lib.GreenRobotEventBus;
import edu.detectortroyano.com.adroidchat.login.events.LoginEvent;

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
        //Log.e("LoginRepositoryImpl","SignUp");
        postEvent(LoginEvent.onSignUpSuccess);
    }

    @Override
    public void signIn(String email, String password) {
        //Log.e("LoginRepositoryImpl","SignIn");
        postEvent(LoginEvent.onSignInSuccess);
    }

    @Override
    public void checkSession() {
        Log.e("LoginRepositoryImpl","checkSession");
        postEvent(LoginEvent.onFileToRecovereSession);
    }

    private void postEvent(int type, String errorMessage){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if(errorMessage!=null){
            loginEvent.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type){
        postEvent(type, null);
    }
}
