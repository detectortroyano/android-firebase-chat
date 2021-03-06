package edu.detectortroyano.com.adroidchat.contactlist.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.detectortroyano.com.adroidchat.R;
import edu.detectortroyano.com.adroidchat.domain.AvatarHelper;
import edu.detectortroyano.com.adroidchat.entities.User;
import edu.detectortroyano.com.adroidchat.lib.ImageLoader;

/**
 * Created by detectortroyano on 05/07/2016.
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    private List<User> contactList;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;

    public ContactListAdapter(List<User> contactList, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        this.contactList = contactList;
        this.imageLoader = imageLoader;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = contactList.get(position);
        holder.setClickListener(user, onItemClickListener);

        String email = user.getEmail();
        boolean online = user.isOnline();
        String status = online ? "online" : "offline";
        int color = online ? Color.GREEN : Color.RED;

        holder.txtUser.setText(email);
        holder.txtStatus.setText(status);
        holder.txtStatus.setTextColor(color);

        imageLoader.load(holder.imgAvatar, AvatarHelper.getAvatarURL(email));
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void add(User user) {
        if (!contactList.contains(user)) {
            this.contactList.add(user);
            this.notifyDataSetChanged();
        }
    }

    public void remove(User user) {
        //if (!contactList.contains(user)) {
            this.contactList.remove(user);
            this.notifyDataSetChanged();
        //}
    }

    public void update(User user) {
        //if (!contactList.contains(user)) {
            int index = this.contactList.indexOf(user);
            this.contactList.set(index, user);
            this.notifyDataSetChanged();
        //}
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgAvatar)
        CircleImageView imgAvatar;
        @Bind(R.id.txtUser)
        TextView txtUser;
        @Bind(R.id.txtStatus)
        TextView txtStatus;
        private View view;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        private void setClickListener(final User user, final OnItemClickListener onItemClickListener){
            view.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(user);
                }


            });

            view.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(user);
                    return false;
                }
            });
        }
    }
}
