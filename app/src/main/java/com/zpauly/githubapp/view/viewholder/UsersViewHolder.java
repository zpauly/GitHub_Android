package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseRecyclerViewViewHolder;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16-6-15.
 */
public class UsersViewHolder extends BaseRecyclerViewViewHolder {
    @BindView(R.id.followers_layout) public CircleImageView mAvatarIV;

    @BindView(R.id.followers_avatar_IV) public AppCompatTextView mUsernameTV;

    @BindView(R.id.followers_username_TV) public LinearLayout mLayout;

    public UsersViewHolder(View itemView) {
        super(itemView);
    }
}
