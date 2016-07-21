package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.AppAuthorizationBean;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16-6-14.
 */
public class EventsViewHolder extends RecyclerView.ViewHolder {
    public final LinearLayout mUserLayout;

    public final LinearLayout mEventLayout;

    public final CircleImageView mUserAvatarIV;

    public final AppCompatTextView mUsernameTV;

    public final AppCompatTextView mActionTV;

    public final AppCompatTextView mRepoTV;

    public EventsViewHolder(View itemView) {
        super(itemView);

        mUserLayout = (LinearLayout) itemView.findViewById(R.id.events_user_layout);
        mEventLayout = (LinearLayout) itemView.findViewById(R.id.events_event_layout);
        mUserAvatarIV = (CircleImageView) itemView.findViewById(R.id.events_avatar_CIV);
        mUsernameTV = (AppCompatTextView) itemView.findViewById(R.id.events_loginname_TV);
        mActionTV = (AppCompatTextView) itemView.findViewById(R.id.events_action_TV);
        mRepoTV = (AppCompatTextView) itemView.findViewById(R.id.events_repo_TV);
    }
}
