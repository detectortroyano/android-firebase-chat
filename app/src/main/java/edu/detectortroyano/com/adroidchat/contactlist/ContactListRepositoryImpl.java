package edu.detectortroyano.com.adroidchat.contactlist;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import edu.detectortroyano.com.adroidchat.contactlist.event.ContactListEvent;
import edu.detectortroyano.com.adroidchat.domain.FirebaseHelper;
import edu.detectortroyano.com.adroidchat.entities.User;
import edu.detectortroyano.com.adroidchat.lib.EventBus;
import edu.detectortroyano.com.adroidchat.lib.GreenRobotEventBus;

/**
 * Created by detectortroyano on 09/07/2016.
 */
public class ContactListRepositoryImpl implements ContactListRepository {
    private FirebaseHelper firebaseHelper;

    private ChildEventListener contactListEventListener;

    public ContactListRepositoryImpl(){
        firebaseHelper = FirebaseHelper.getInstance();
    }

    @Override
    public void signOff() {
        firebaseHelper.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return firebaseHelper.getAuthUserEmail();
    }

    @Override
    public void removeContact(String email) {
        String currentUserEmail = firebaseHelper.getAuthUserEmail();
        firebaseHelper.getOneContactReference(currentUserEmail, email).removeValue();
        firebaseHelper.getOneContactReference(email, currentUserEmail).removeValue();
    }

    @Override
    public void destroyListener() {
        contactListEventListener = null;
    }

    @Override
    public void suscribeToContactListEvent() {
        if (contactListEventListener == null) {
            contactListEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {
                    handleContact(dataSnapshot, ContactListEvent.onContactAdded);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildKey) {
                    handleContact(dataSnapshot, ContactListEvent.onContactChanged);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handleContact(dataSnapshot, ContactListEvent.onContactRemoved);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError firebaseError) {}
            };
        }

        firebaseHelper.getMyContactsReference().addChildEventListener(contactListEventListener);
    }

    private void handleContact(DataSnapshot dataSnapshot, int type){
        String email = dataSnapshot.getKey();
        email = email.replace("_",".");
        boolean online = ((Boolean)dataSnapshot.getValue()).booleanValue();
        User user = new User(email, online, null);
        postEvent(type, user);
    }

    @Override
    public void unsuscribeToContactListEvent() {
        if (contactListEventListener != null) {
            firebaseHelper.getMyContactsReference().removeEventListener(contactListEventListener);
        }
    }

    @Override
    public void changeConectionStatus(boolean online) {
        firebaseHelper.changeUserConnectionStatus(online);
    }

    private void postEvent(int type, User user) {
        ContactListEvent contactListEvent = new ContactListEvent();
        contactListEvent.setEventType(type);
        contactListEvent.setUser(user);
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(contactListEvent);
    }
}
