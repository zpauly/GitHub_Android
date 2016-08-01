package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zpauly.githubapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16-6-15.
 */
public class UsersViewHolder extends RecyclerView.ViewHolder {
    public final CircleImageView mAvatarIV;

    public final AppCompatTextView mUsernameTV;

    public final LinearLayout mLayout;

    public UsersViewHolder(View itemView) {
        super(itemView);

        mLayout = (LinearLayout) itemView.findViewById(R.id.followers_layout);
        mAvatarIV = (CircleImageView) itemView.findViewById(R.id.followers_avatar_IV);
        mUsernameTV = (AppCompatTextView) itemView.findViewById(R.id.followers_username_TV);
    }
}
