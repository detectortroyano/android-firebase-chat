package edu.detectortroyano.com.adroidchat;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by detectortroyano on 22/06/2016.
 */
public class AndroidChatAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase(){
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
