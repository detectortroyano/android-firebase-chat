package edu.detectortroyano.com.adroidchat.contactlist.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.detectortroyano.com.adroidchat.R;
import edu.detectortroyano.com.adroidchat.contactlist.ContactListPresenter;
import edu.detectortroyano.com.adroidchat.contactlist.ui.adapters.ContactListAdapter;
import edu.detectortroyano.com.adroidchat.contactlist.ui.adapters.OnItemClickListener;
import edu.detectortroyano.com.adroidchat.entities.User;
import edu.detectortroyano.com.adroidchat.lib.GlideImageLoader;
import edu.detectortroyano.com.adroidchat.lib.ImageLoader;

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
        //contactListPresenter.onCreate();
        setupToolbar();
    }

    private void setupRecyclerView() {
        this.recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerViewContacts.setAdapter(this.contactListAdapter);
    }

    private void setupAdapter() {
        ImageLoader imageLoader = new GlideImageLoader(this.getApplicationContext());
        User user = new User();
        user.setOnline(false);
        user.setEmail("angelricardo.uthh@gmail.com");
        this.contactListAdapter = new ContactListAdapter(Arrays.asList(new User[]{user}), imageLoader, this);
    }

    private void setupToolbar() {
        //toolbar.setSubtitle(contactListPresenter.getCurrentUserEmail());
        setSupportActionBar(toolbar);
    }
/*
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
*/
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

    @Override
    public void onItemClick(User user) {

    }

    @Override
    public void onItemLongClick(User user) {

    }
}
