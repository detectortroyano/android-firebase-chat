package edu.detectortroyano.com.adroidchat.contactlist.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.OnClick;
import edu.detectortroyano.com.adroidchat.R;
import edu.detectortroyano.com.adroidchat.contactlist.ContactListPresenter;
import edu.detectortroyano.com.adroidchat.entities.User;

public class ContactListActivity extends AppCompatActivity implements ContactListView{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerViewContacts)
    RecyclerView recyclerViewContacts;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    ContactListPresenter contactListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        contactListPresenter.onCreate();
        toolbar.setTitle(contactListPresenter.getCurrentUserEmail());
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onDestroy(){
        contactListPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        contactListPresenter.onResume();
    }

    @Override
    protected void onPause() {
        contactListPresenter.onPause();
        super.onPause();
    }

    @OnClick(R.id.fab)
    public void addContact(){

    }

    @Override
    public void onContactAdded(User user) {

    }

    @Override
    public void onContactChanged(User user) {

    }

    @Override
    public void onContactRemoved(User user) {

    }
}
