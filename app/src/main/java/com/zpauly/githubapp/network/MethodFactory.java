package com.zpauly.githubapp.network;

import android.util.Log;

import com.zpauly.githubapp.base.BaseNetMethod;
import com.zpauly.githubapp.network.activity.ActivityMethod;
import com.zpauly.githubapp.network.gists.GistsMethod;
import com.zpauly.githubapp.network.gitdata.GitDataMethod;
import com.zpauly.githubapp.network.issues.IssuesMethod;
import com.zpauly.githubapp.network.organizations.OrganizationsMethod;
import com.zpauly.githubapp.network.overview.OverviewMethod;
import com.zpauly.githubapp.network.pullRequests.PullRequestsMethod;
import com.zpauly.githubapp.network.repositories.RepositoriesMethod;
import com.zpauly.githubapp.network.search.SearchMethod;
import com.zpauly.githubapp.network.user.UserMethod;

/**
 * Created by zpauly on 16/9/4.
 */
public class MethodFactory {
    public static BaseNetMethod getMethod(Class<? extends BaseNetMethod> clazz) {
        if (clazz.getName().equals(ActivityMethod.class.getName())) {
            return ActivityMethod.getInstance();
        } else if (clazz.getName().equals(GistsMethod.class.getName())) {
            return GistsMethod.getInstance();
        } else if (clazz.getName().equals(GitDataMethod.class.getName())) {
            return GitDataMethod.getInstance();
        } else if (clazz.getName().equals(IssuesMethod.class.getName())) {
            return IssuesMethod.getInstance();
        } else if (clazz.getName().equals(OrganizationsMethod.class.getName())) {
            return OrganizationsMethod.getInstance();
        } else if (clazz.getName().equals(OverviewMethod.class.getName())) {
            return OverviewMethod.getInstance();
        } else if (clazz.getName().equals(RepositoriesMethod.class.getName())) {
            return RepositoriesMethod.getInstance();
        } else if (clazz.getName().equals(SearchMethod.class.getName())) {
            return SearchMethod.getInstance();
        } else if (clazz.getName().equals(UserMethod.class.getName())) {
            return UserMethod.getInstance();
        } else if (clazz.getName().equals(PullRequestsMethod.class.getName())) {
            return PullRequestsMethod.getInstance();
        } else {
            return null;
        }
    }
}
