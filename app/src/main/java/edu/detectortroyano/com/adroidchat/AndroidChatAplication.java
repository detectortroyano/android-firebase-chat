package edu.detectortroyano.com.adroidchat;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

import edu.detectortroyano.com.adroidchat.lib.GlideImageLoader;
import edu.detectortroyano.com.adroidchat.lib.ImageLoader;

/**
 * Created by detectortroyano on 22/06/2016.
 */
public class AndroidChatAplication extends Application {
    private ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
        setupImageLoader();
    }

    private void setupImageLoader() {
        imageLoader = new GlideImageLoader(this);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    private void setupFirebase(){
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
