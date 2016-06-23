package edu.detectortroyano.com.adroidchat;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by detectortroyano on 22/06/2016.
 */
public class AndroidChatAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupAndroidContext();
    }

    private void setupAndroidContext() {
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
