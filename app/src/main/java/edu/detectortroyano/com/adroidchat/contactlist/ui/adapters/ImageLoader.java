package edu.detectortroyano.com.adroidchat.contactlist.ui.adapters;

import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by detectortroyano on 05/07/2016.
 */
public interface ImageLoader {
    void load(ImageView imgAvatar, String url);
}
