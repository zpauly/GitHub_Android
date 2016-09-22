package com.zpauly.githubapp.view.commit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.repos.FileBean;
import com.zpauly.githubapp.ui.ColoredLineTextView;

/**
 * Created by zpauly on 16/9/22.
 */

public class CommitFilePatchFragment extends BaseFragment {
    private final String TAG = getClass().getName();

    private ColoredLineTextView mPatchCLTV;
    private AppCompatTextView mFilenameTV;
    private AppCompatTextView mChangesTV;
    private AppCompatTextView mAdditonsTV;
    private AppCompatTextView mDeletionsTV;

    private FileBean fileBean;

    @Override
    protected void initViews(View view) {
        getAttrs();

        mPatchCLTV = (ColoredLineTextView) view.findViewById(R.id.commit_file_patch_CLTV);
        mFilenameTV = (AppCompatTextView) view.findViewById(R.id.commit_file_name_TV);
        mChangesTV = (AppCompatTextView) view.findViewById(R.id.commit_file_changes_count_TV);
        mAdditonsTV = (AppCompatTextView) view.findViewById(R.id.commit_file_additions_count_TV);
        mDeletionsTV = (AppCompatTextView) view.findViewById(R.id.commit_file_deletions_count_TV);

        mFilenameTV.setText(fileBean.getFilename());
        mChangesTV.setText(String.valueOf(fileBean.getChanges()));
        mAdditonsTV.setText(String.valueOf(fileBean.getAdditions()));
        mDeletionsTV.setText(String.valueOf(fileBean.getDeletions()));

        String[] contents = fileBean.getPatch().split("\n");

        mPatchCLTV.setText(contents);

        int i = -1;
        mPatchCLTV.setLineBackgroundColor(0, getResources().getColor(R.color.commitColor));
        for (String line : contents) {
            i++;
            if (line.startsWith("+")) {
                mPatchCLTV.setLineBackgroundColor(i, getResources().getColor(R.color.commitAdditionColor));
//                mPatchCLTV.getTextView(i).setBackgroundColor(R.color.colorAccent);
            }
            if (line.startsWith("-")) {
                mPatchCLTV.setLineBackgroundColor(i, getResources().getColor(R.color.commitDeletionColor));
            }
        }
    }

    private void getAttrs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            fileBean = bundle.getParcelable(CommitFilePatchActivity.FILE);
        }
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_commit_file_patch, container, false);
    }
}
