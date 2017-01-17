package com.zpauly.githubapp.view.commit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.TextView;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.repos.FileBean;
import com.zpauly.githubapp.ui.ColoredLineTextView;
import com.zpauly.githubapp.view.ToolbarActivity;

import java.util.Iterator;

import butterknife.BindView;

/**
 * Created by zpauly on 16/9/22.
 */

public class CommitFilePatchActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String FILE = "FILE";

    @BindView(R.id.commit_file_patch_CLTV) public ColoredLineTextView mPatchCLTV;
    @BindView(R.id.commit_file_name_TV) public AppCompatTextView mFilenameTV;
    @BindView(R.id.commit_file_changes_count_TV) public AppCompatTextView mChangesTV;
    @BindView(R.id.commit_file_additions_count_TV) public AppCompatTextView mAdditonsTV;
    @BindView(R.id.commit_file_deletions_count_TV) public AppCompatTextView mDeletionsTV;

    private FileBean fileBean;

    @Override
    public void initViews() {
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
            }
            if (line.startsWith("-")) {
                mPatchCLTV.setLineBackgroundColor(i, getResources().getColor(R.color.commitDeletionColor));
            }
        }

        Iterator<TextView> it = mPatchCLTV.iterator();
        while (it.hasNext()) {
            it.next().setTextColor(Color.BLACK);
        }
    }

    @Override
    public void initContent() {
        super.initContent();
        setContent(R.layout.content_commit_file_patch);
    }

    @Override
    protected void getParams() {
        fileBean = getIntent().getParcelableExtra(FILE);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(fileBean.getSha().substring(0, 8));
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public static void launchActivity(Context context, FileBean fileBean) {
        Intent intent = new Intent();
        intent.putExtra(FILE, fileBean);
        intent.setClass(context, CommitFilePatchActivity.class);
        context.startActivity(intent);
    }
}
