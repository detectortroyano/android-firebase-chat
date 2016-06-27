package edu.detectortroyano.com.adroidchat.login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.detectortroyano.com.adroidchat.R;
import edu.detectortroyano.com.adroidchat.contactlist.ContactListActivity;

public class LoginActivity extends AppCompatActivity implements LoginView{

    @Bind(R.id.editTxtEmail)
    EditText editTxtEmail;
    @Bind(R.id.editTxtPassword)
    EditText editTxtPassword;
    @Bind(R.id.btnSignup)
    Button btnSignup;
    @Bind(R.id.btnSignin)
    Button btnSignin;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.layoutMainContainer)
    RelativeLayout container;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnSignin)
    @Override
    public void handleSignIn(){
        //Log.e("Click",editTxtEmail.getText().toString());
        loginPresenter.registerNewUser(editTxtEmail.getText().toString()
                , editTxtPassword.getText().toString());
    }

    @OnClick(R.id.btnSignup)
    @Override
    public void handleSignUp(){
        //Log.e("Click",editTxtPassword.getText().toString());
        loginPresenter.valideLogin(editTxtEmail.getText().toString()
                , editTxtPassword.getText().toString());
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this,ContactListActivity.class));
    }

    @Override
    public void loginError(String Error) {
        editTxtPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signin));
        editTxtPassword.setError(msgError);
    }

    @Override
    public void newUserSucces() {
        Snackbar.make(container, R.string.login_notice_message_signup, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void newUserError(String Error) {
        editTxtPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signup));
        editTxtPassword.setError(msgError);
    }

    private void setInputs(boolean enabled){
        editTxtEmail.setEnabled(enabled);
        editTxtPassword.setEnabled(enabled);
        btnSignup.setEnabled(enabled);
        btnSignin.setEnabled(enabled);
    }
}
