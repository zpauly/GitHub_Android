package com.zpauly.githubapp.view.comment;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.CommentBean;
import com.zpauly.githubapp.presenter.comment.CommentCreateContract;
import com.zpauly.githubapp.presenter.comment.CommentCreatePresenter;
import com.zpauly.githubapp.widget.FloatingActionButton;
import com.zpauly.githubapp.view.ToolbarActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zpauly on 16/9/8.
 */
public class CommentCreateActivity extends ToolbarActivity implements CommentCreateContract.View {
    private final String TAG = getClass().getName();

    public static final String COMMENT_TYPE = "COMMENT_TYPE";
    public static final int TYPE_COMMIT = 0;
    public static final int TYPE_ISSUE = 1;

    public static final String OWNER = "OWNER";
    public static final String REPO = "REPO";
    public static final String NUM = "NUM";
    public static final String SHA = "SHA";

    private CommentCreateContract.Presenter mPresenter;

    @BindView(R.id.create_comment_ET) public AppCompatEditText mCommentET;
    @BindView(R.id.create_comment_FAB) FloatingActionButton mCommentFAB;

    private int commentType;
    private String repo;
    private String owner;
    private int number;
    private String sha;

    private MaterialDialog uploadDialog;
    private CommentBean commentBean;

    @Override
    protected void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    public void initViews() {
        new CommentCreatePresenter(this, this);

        uploadDialog = new MaterialDialog.Builder(this)
                .progress(true, 0)
                .cancelable(false)
                .title(R.string.please_wait)
                .content(R.string.uploading)
                .build();

        mCommentET.setText("");
    }

    @Override
    protected void getParams() {
        commentType = getIntent().getIntExtra(COMMENT_TYPE, -1);
        owner = getIntent().getStringExtra(OWNER);
        repo = getIntent().getStringExtra(REPO);
        number = getIntent().getIntExtra(NUM, -1);
        sha = getIntent().getStringExtra(SHA);
    }

    @Override
    public void initContent() {
        super.initContent();
        setContent(R.layout.content_create_comment);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.create_comment);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public static void launchIssueCommentActivity(Context context, String owner, String repo, int number) {
        Intent intent = new Intent();
        intent.putExtra(COMMENT_TYPE, TYPE_ISSUE);
        intent.putExtra(OWNER, owner);
        intent.putExtra(REPO, repo);
        intent.putExtra(NUM, number);
        intent.setClass(context, CommentCreateActivity.class);
        context.startActivity(intent);
    }

    public static void launchCommitCommentActivity(Context context, String owner, String repo,
                                                   String sha) {
        Intent intent = new Intent();
        intent.putExtra(COMMENT_TYPE, TYPE_COMMIT);
        intent.putExtra(OWNER, owner);
        intent.putExtra(REPO, repo);
        intent.putExtra(SHA, sha);
        intent.setClass(context, CommentCreateActivity.class);
        context.startActivity(intent);
    }

    @OnClick(R.id.create_comment_FAB)
    public void comment() {
        if (mCommentET.getText() == null || mCommentET.getText().toString().equals("")) {
            Snackbar.make(getCurrentFocus(), R.string.please_input_body_first, Snackbar.LENGTH_SHORT).show();
        } else {
            createAComment();
        }
    }

    private void createAComment() {
        uploadDialog.show();
        mPresenter.createAComment();
    }

    @Override
    public void createCommentSuccess() {
        uploadDialog.dismiss();
        Snackbar.make(getCurrentFocus(), R.string.upload_success, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void createCommentFail() {
        uploadDialog.dismiss();
        Snackbar.make(getCurrentFocus(), R.string.upload_fail, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void creatingComment(CommentBean commentBean) {
        this.commentBean = commentBean;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public String getRepo() {
        return repo;
    }

    @Override
    public int getNum() {
        return number;
    }

    @Override
    public String getSha() {
        return sha;
    }

    @Override
    public String getCommentBody() {
        return mCommentET.getText().toString();
    }

    @Override
    public int getCommentType() {
        return commentType;
    }

    @Override
    public void setPresenter(CommentCreateContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
