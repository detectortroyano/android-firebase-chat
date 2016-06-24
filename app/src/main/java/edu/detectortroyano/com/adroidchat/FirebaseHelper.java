package edu.detectortroyano.com.adroidchat;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import java.util.Map;

/**
 * Created by detectortroyano on 24/06/2016.
 */
public class FirebaseHelper {

    private Firebase dataReference;
    private final static String SEPARATOR = "___";
    private final static String CHAT_PATH = "chat";
    private final static String CONTACT_PATH = "contact";
    private final static String USER_PATH = "users";
    private final static String FIREBASE_URL = "https://android-chat-example.firebaseio.com";

    private static class SingletonHolder{
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public FirebaseHelper() {
        this.dataReference = new Firebase(FIREBASE_URL);
    }

    public Firebase getDataReference() {
        return dataReference;
    }

    public String getAuthUserEmail(){
        AuthData authData = dataReference.getAuth();
        String email = null;
        if( authData != null ){
            Map<String, Object> providerData = authData.getProviderData();
            email = providerData.get("email").toString();
        }
        return  email;
    }
}
