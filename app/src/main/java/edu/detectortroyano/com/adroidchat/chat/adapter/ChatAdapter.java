package edu.detectortroyano.com.adroidchat.chat.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.detectortroyano.com.adroidchat.R;
import edu.detectortroyano.com.adroidchat.chat.entities.ChatMessage;

/**
 * Created by detectortroyano on 12/07/2016.
 */
public class ChatAdapter extends RecyclerView.Adapter <ChatAdapter.ViewHolder>{
    private Context context;
    private List<ChatMessage> chatMessages;

    public ChatAdapter(Context context,
                       List<ChatMessage> chatMessages) {
        this.context = context;
        this.chatMessages = chatMessages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private boolean alreadyInAdapter(ChatMessage newMsg){
        boolean alreadyInAdapter = false;
        for (ChatMessage msg : this.chatMessages) {
            if (msg.getMsg().equals(newMsg.getMsg()) &&
                    msg.getSender().equals(newMsg.getSender())) {
                alreadyInAdapter = true;
                break;
            }
        }

        return alreadyInAdapter;
    }

    public void add(ChatMessage message) {
        if (!alreadyInAdapter(message)) {
            this.chatMessages.add(message);
            this.notifyDataSetChanged();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txtMessage)
        TextView txtMessage;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
