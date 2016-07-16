package edu.detectortroyano.com.adroidchat.chat.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.detectortroyano.com.adroidchat.AndroidChatAplication;
import edu.detectortroyano.com.adroidchat.R;
import edu.detectortroyano.com.adroidchat.chat.ChatPresenter;
import edu.detectortroyano.com.adroidchat.chat.ChatPresenterImpl;
import edu.detectortroyano.com.adroidchat.chat.adapter.ChatAdapter;
import edu.detectortroyano.com.adroidchat.chat.entities.ChatMessage;
import edu.detectortroyano.com.adroidchat.domain.AvatarHelper;
import edu.detectortroyano.com.adroidchat.lib.ImageLoader;

public class ChatActivity extends AppCompatActivity implements ChatView{

    @Bind(R.id.imgAvatar)
    CircleImageView imgAvatar;
    @Bind(R.id.txtUser)
    TextView txtUser;
    @Bind(R.id.txtStatus)
    TextView txtStatus;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.messageRecyclerView)
    RecyclerView messageRecyclerView;
    @Bind(R.id.editTxtMessage)
    EditText editTxtMessage;
    @Bind(R.id.btnSendMessage)
    ImageButton btnSendMessage;

    public final static String EMAIL_KEY = "email";
    public final static String ONLINE_KEY = "online";

    private ChatAdapter chatAdapter;
    private ChatPresenter chatPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        this.chatPresenter = new ChatPresenterImpl(this);
        chatPresenter.onCreate();

        setupAdapter();
        setupRecyclerView();

        Intent intent = getIntent();
        setToolbarData(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        chatPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        chatPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        chatPresenter.onDestroy();
        super.onDestroy();
    }

    private void setToolbarData(Intent i) {
        String recipient = i.getStringExtra(EMAIL_KEY);
        chatPresenter.setChatRecipient(recipient);

        boolean online = i.getBooleanExtra(ONLINE_KEY, false);
        String status = online ? "online" : "offline";
        int color = online ? Color.GREEN : Color.RED;

        txtUser.setText(recipient);
        txtStatus.setText(status);
        txtStatus.setTextColor(color);

        AndroidChatAplication app = (AndroidChatAplication)getApplication();
        ImageLoader imageLoader = app.getImageLoader();
        imageLoader.load(imgAvatar, AvatarHelper.getAvatarURL(recipient));
    }

    private void setupAdapter() {
        ChatMessage msg1 = new ChatMessage();
        ChatMessage msg2 = new ChatMessage();
        msg1.setMsg("Hola");
        msg2.setMsg("Como estas");
        msg1.setSentByMe(true);
        msg2.setSentByMe(false);
        chatAdapter = new ChatAdapter(this, new ArrayList<ChatMessage>());//Arrays.asList(new ChatMessage[]{msg1, msg2}));//
        Log.e("Click","setupAdapter()");
    }

    private void setupRecyclerView() {
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageRecyclerView.setAdapter(chatAdapter);
    }

    @Override
    public void sendMessage() {

    }

    @Override
    public void onMessageReceived(ChatMessage msg) {
        chatAdapter.add(msg);
        messageRecyclerView.scrollToPosition(chatAdapter.getItemCount()-1);
    }
}
