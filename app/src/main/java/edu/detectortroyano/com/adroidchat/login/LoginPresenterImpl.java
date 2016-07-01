package edu.detectortroyano.com.adroidchat.login;

import android.util.Log;
import org.greenrobot.eventbus.Subscribe;

import edu.detectortroyano.com.adroidchat.lib.EventBus;
import edu.detectortroyano.com.adroidchat.lib.GreenRobotEventBus;
import edu.detectortroyano.com.adroidchat.login.events.LoginEvent;
import edu.detectortroyano.com.adroidchat.login.ui.LoginView;

/**
 * Created by detectortroyano on 26/06/2016.
 */
public class LoginPresenterImpl implements LoginPresenter{
    private EventBus eventBus;
    LoginView loginView;
    LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
        eventBus.unregister(this);
    }

    @Override
    public void checkForAuthenticatedUser() {
        Log.e("LoginPresenterImpl","checkForAuthenticatedUser");
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.checkSession();
    }

    @Override
    public void valideLogin(String email, String password) {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignIn(email,password);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignUp(email,password);
    }

    private void onFileToRecoreSession(){
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
        }
        Log.e("LoginPresenterImpl", "onFileToRecoreSession");
    }

    private void onSignInSuccess(){
        if(loginView != null){
            loginView.navigateToMainScreen();
        }
    }

    private void onSignUpSuccess(){
        if(loginView != null){
            loginView.newUserSucces();
        }
    }

    private void onSignInError(String error){
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.loginError(error);
        }
    }

    private void onSignUpError(String error){
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.newUserError(error);
        }
    }

    @Override
    @Subscribe //Refactor gradle y eventbus
    public void onEventMainThread(LoginEvent loginEvent) {
        switch (loginEvent.getEventType()){
            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onSignUpSuccess:
                onSignUpSuccess();
                break;
            case LoginEvent.onSignInError:
                onSignInError(loginEvent.getErrorMessage());
                break;
            case LoginEvent.onSignUpError:
                onSignUpError(loginEvent.getErrorMessage());
                break;
            case LoginEvent.onFileToRecoverSession:
                onFileToRecoreSession();
                break;
        }
    }
}
