package edu.detectortroyano.com.adroidchat.addcontact;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import edu.detectortroyano.com.adroidchat.addcontact.event.AddContactEvent;
import edu.detectortroyano.com.adroidchat.domain.FirebaseHelper;
import edu.detectortroyano.com.adroidchat.entities.User;
import edu.detectortroyano.com.adroidchat.lib.EventBus;
import edu.detectortroyano.com.adroidchat.lib.GreenRobotEventBus;

/**
 * Created by detectortroyano on 10/07/2016.
 */
public class AddContactRepositoryImpl implements AddContactRepository {
    private EventBus eventBus;
    private FirebaseHelper firebaseHelper;

    public AddContactRepositoryImpl() {
        this.firebaseHelper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void addContact(final String email) {

        final String key = email.replace(".","_");

        //FirebaseHelper helper = FirebaseHelper.getInstance();
        final DatabaseReference userReference = firebaseHelper.getUserReference(email);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    boolean online = user.isOnline();

                    DatabaseReference userContactsReference = firebaseHelper.getMyContactsReference();
                    userContactsReference.child(key).setValue(user.isOnline());

                    String currentUserEmailKey = firebaseHelper.getAuthUserEmail();
                    currentUserEmailKey = currentUserEmailKey.replace(".","_");

                    DatabaseReference reverseUserContactsReference = firebaseHelper.getContactsReference(email);
                    reverseUserContactsReference.child(currentUserEmailKey).setValue(User.ONLINE);
                    postSuccess();
                } else {
                    postError();
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                postError();
            }
        });
    }

    private void postSuccess(){
        this.post(false);
    }

    private void postError(){
        this.post(true);
    }

    private void post(boolean error){
        AddContactEvent addContactEvent = new AddContactEvent();
        addContactEvent.setError(error);
        eventBus.post(addContactEvent);
    }
}
