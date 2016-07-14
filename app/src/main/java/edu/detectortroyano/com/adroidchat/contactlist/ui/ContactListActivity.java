package edu.detectortroyano.com.adroidchat.contactlist.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.detectortroyano.com.adroidchat.R;
import edu.detectortroyano.com.adroidchat.addcontact.ui.AddContactFragment;
import edu.detectortroyano.com.adroidchat.chat.ui.ChatActivity;
import edu.detectortroyano.com.adroidchat.contactlist.ContactListPresenter;
import edu.detectortroyano.com.adroidchat.contactlist.ContactListPresenterImpl;
import edu.detectortroyano.com.adroidchat.contactlist.ui.adapters.ContactListAdapter;
import edu.detectortroyano.com.adroidchat.contactlist.ui.adapters.OnItemClickListener;
import edu.detectortroyano.com.adroidchat.entities.User;
import edu.detectortroyano.com.adroidchat.lib.GlideImageLoader;
import edu.detectortroyano.com.adroidchat.lib.ImageLoader;
import edu.detectortroyano.com.adroidchat.login.ui.LoginActivity;

public class ContactListActivity extends AppCompatActivity
        implements ContactListView, OnItemClickListener{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerViewContacts)
    RecyclerView recyclerViewContacts;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    ContactListAdapter contactListAdapter;
    ContactListPresenter contactListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);

        setupAdapter();
        setupRecyclerView();
        this.contactListPresenter = new ContactListPresenterImpl(this);
        contactListPresenter.onCreate();
        setupToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contactlist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout){
            contactListPresenter.signOff();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView() {
        this.recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerViewContacts.setAdapter(this.contactListAdapter);
    }

    private void setupAdapter() {
        ImageLoader imageLoader = new GlideImageLoader(this.getApplicationContext());
        this.contactListAdapter = new ContactListAdapter(new ArrayList<User>(), imageLoader, this);
    }

    private void setupToolbar() {
        toolbar.setSubtitle(contactListPresenter.getCurrentUserEmail());
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
        new AddContactFragment().show(getSupportFragmentManager(), getString(R.string.addcontact_message_title));
    }

    @Override
    public void onContactAdded(User user) {
        this.contactListAdapter.add(user);
    }

    @Override
    public void onContactChanged(User user) {
        this.contactListAdapter.update(user);
    }

    @Override
    public void onContactRemoved(User user) {
        this.contactListAdapter.remove(user);
    }

    @Override
    public void onItemClick(User user) {
        //Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(ChatActivity.EMAIL_KEY, user.getEmail());
        intent.putExtra(ChatActivity.ONLINE_KEY, user.isOnline());
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(User user) {
        contactListPresenter.removeContact(user.getEmail());
    }
}
