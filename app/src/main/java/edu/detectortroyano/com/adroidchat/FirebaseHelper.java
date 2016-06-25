package edu.detectortroyano.com.adroidchat;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    public Firebase getUserReference(String email){
        Firebase userReference = null;
        if( email != null ){
            String emailKey = email.replace(".","_");
            userReference = dataReference.getRoot().child(USER_PATH).child(emailKey);
        }
        return userReference;
    }

    public Firebase getMyUserReference(){
        return getUserReference(getAuthUserEmail());
    }

    public Firebase getContactsReference(String email){
        return getUserReference(email).child(CONTACT_PATH);
    }

    public Firebase getMyContactsReference(){
        return getContactsReference(getAuthUserEmail());
    }

    public Firebase getOneContactReference(String mainEmail, String childEmail){
        String childKey = childEmail.replace(".","_");
        return getUserReference(mainEmail).child(CONTACT_PATH).child(childKey);

    }

    public Firebase getChatsReference(String receiver){
        String keySender = getAuthUserEmail().replace(".","_");
        String keyReceiver = receiver.replace(".","_");

        String keyChat = keySender + SEPARATOR + keyReceiver;
        if (keySender.compareTo(keyReceiver) > 0){
            keyChat = keyReceiver + SEPARATOR + keySender;
        }
        return dataReference.getRoot().child(CHAT_PATH).child(keyChat);
    }

    public void changeUserConnectionStatus(boolean online){
        if ( getMyUserReference() != null ){
            Map<String,Object> update = new HashMap<String, Object>();
            update.put("online",online);
            getMyUserReference().updateChildren(update);
            notifyContactsOfConecttionChange(online);
        }
    }

    public void notifyContactsOfConecttionChange(boolean online){
        notifyContactsOfConecttionChange(online,false);
    }

    public void signOff(){
        notifyContactsOfConecttionChange(false,true);
    }

    public void notifyContactsOfConecttionChange(final boolean online, final boolean signOff){
        final String myEmail = getAuthUserEmail();
        getMyContactsReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child:dataSnapshot.getChildren()){
                    String email = child.getKey();
                    Firebase reference = getOneContactReference(email, myEmail);
                    reference.setValue(online);
                }
                if(signOff){
                    dataReference.unauth();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
