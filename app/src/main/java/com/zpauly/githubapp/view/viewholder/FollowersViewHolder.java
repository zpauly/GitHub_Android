package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zpauly.githubapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16-6-15.
 */
public class FollowersViewHolder extends RecyclerView.ViewHolder {
    private CircleImageView mAvatarIV;

    private TextView mPlaceTV;

    private TextView mEmailTV;

    private TextView mTimeTV;

    public FollowersViewHolder(View itemView) {
        super(itemView);

        mAvatarIV = (CircleImageView) itemView.findViewById(R.id.followers_avatar_IV);
        mPlaceTV = (TextView) itemView.findViewById(R.id.followers_place_TV);
        mEmailTV = (TextView) itemView.findViewById(R.id.followers_email_TV);
        mTimeTV = (TextView) itemView.findViewById(R.id.followers_time_TV);
    }

    public CircleImageView getmAvatarIV() {
        return mAvatarIV;
    }

    public TextView getmPlaceTV() {
        return mPlaceTV;
    }

    public TextView getmEmailTV() {
        return mEmailTV;
    }

    public TextView getmTimeTV() {
        return mTimeTV;
    }
}
