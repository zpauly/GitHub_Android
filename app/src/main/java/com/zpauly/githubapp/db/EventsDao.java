package com.zpauly.githubapp.db;

import com.zpauly.githubapp.entity.response.events.EventsBean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by zpauly on 16-7-21.
 */

public class EventsDao {
    public static boolean insert(EventsBean bean) {
        EventsModel model = new EventsModel();
        model.setType(bean.getType());
        EventsBean.ActorBean actorBean = bean.getActor();
        EventsBean.PayloadBean payloadBean = bean.getPayload();
        EventsBean.OrgBean orgBean = bean.getOrg();
        EventsBean.RepoBean repoBean = bean.getRepo();
        if (actorBean != null) {
            model.setActorName(actorBean.getLogin());
            model.setAvatarUrl(actorBean.getAvatar_url());
        }
        if (orgBean != null) {
            model.setOrgName(orgBean.getLogin());
            model.setOrgAvatarUrl(orgBean.getAvatar_url());
            model.setOrgUrl(orgBean.getUrl());
        }
        if (repoBean != null) {
            model.setRepoName(repoBean.getName());
            model.setRepoUrl(repoBean.getUrl());
        }
        model.setCreateAt(model.getCreateAt());
        if (payloadBean != null) {
            model.setPayload(payloadBean);
        }
        return model.save();
    }

    public static List<EventsModel> query() {
        return DataSupport.findAll(EventsModel.class);
    }

    public static List<EventsModel> queryTenItems(int offset) {
        return DataSupport.limit(10).offset(offset).find(EventsModel.class);
    }

    public static void deleteAll() {
        DataSupport.deleteAll(EventsModel.class);
    }
}
