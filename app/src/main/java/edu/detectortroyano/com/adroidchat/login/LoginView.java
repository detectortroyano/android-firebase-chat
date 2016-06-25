package edu.detectortroyano.com.adroidchat.login;

/**
 * Created by detectortroyano on 25/06/2016.
 */
public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleSignUp();
    void handleSignIn();

    void navigateToMainScreen();
    void loginError(String Error);

    void newUserSucces();
    void newUserError(String Error);
}
