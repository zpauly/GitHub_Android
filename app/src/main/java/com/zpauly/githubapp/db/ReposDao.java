package com.zpauly.githubapp.db;

import com.zpauly.githubapp.entity.response.RepositoriesBean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by zpauly on 16-7-18.
 */

public class ReposDao {
    public static boolean insertRepo(RepositoriesBean repos) {
        ReposModel model = new ReposModel();
        model.setContributors_url(repos.getContributors_url());
        model.setCreated_at(repos.getCreated_at());
        model.setDefault_branch(repos.getDefault_branch());
        model.setDeployments_url(repos.getDeployments_url());
        model.setEvents_url(repos.getEvents_url());
        model.setForks_count(repos.getForks_count());
        model.setDownloads_url(repos.getDownloads_url());
        model.setForks_url(repos.getForks_url());
        model.setWatchers_count(repos.getWatchers_count());
        model.setUrl(repos.getUrl());
        model.setTeams_url(repos.getTeams_url());
        model.setTags_url(repos.getTags_url());
        model.setSubscription_url(repos.getSubscription_url());
        model.setSubscribers_url(repos.getSubscribers_url());
        model.setStargazers_url(repos.getStargazers_url());
        model.setStargazers_count(repos.getStargazers_count());
        model.setSize(repos.getSize());
        model.setPushed_at(repos.getPushed_at());
        model.setUpdated_at(repos.getUpdated_at());
        model.setOpen_issue_count(repos.getOpen_issues_count());
        model.setName(repos.getName());
        model.setMerges_url(repos.getMerges_url());
        model.setFull_name(repos.getFull_name());
        model.setLanauages_url(repos.getLanguages_url());
        model.setLanguage(repos.getLanguage());
        model.setHtml_url(repos.getHtml_url());
        model.setHooks_url(repos.getHooks_url());
        model.setDescription(repos.getDescription());
        model.setPrivateX(repos.isPrivateX());
        model.setFork(repos.isFork());
        model.setHas_downloads(repos.isHas_downloads());
        model.setHas_pages(repos.isHas_pages());
        model.setHas_issues(repos.isHas_issues());
        model.setHas_wiki(repos.isHas_wiki());
        model.setLogin(repos.getOwner().getLogin());
        model.setAvatar_url(repos.getOwner().getAvatar_url());
        return model.save();
    }

    public static List<ReposModel> queryRepos(String tip, String data) {
        return DataSupport.where(tip + " = ?", data).find(ReposModel.class);
    }

    public static List<ReposModel> queryRepos() {
        return DataSupport.findAll(ReposModel.class);
    }

    public static void deleteRepos() {
        DataSupport.deleteAll(ReposModel.class);
    }
}
