package edu.detectortroyano.com.adroidchat.contactlist.ui.adapters;

import edu.detectortroyano.com.adroidchat.entities.User;

/**
 * Created by detectortroyano on 05/07/2016.
 */
public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
