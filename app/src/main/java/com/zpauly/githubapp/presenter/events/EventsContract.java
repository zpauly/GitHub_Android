package com.zpauly.githubapp.presenter.events;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.events.EventsBean;

import java.util.List;

/**
 * Created by root on 16-7-20.
 */

public class EventsContract {
    public interface Presenter extends BasePresenter {
        void getUserEvents();

        void getReceivedEvents();
    }

    public interface View extends BaseView<Presenter> {
        void loadEvents(List<EventsBean> eventsBeanList);

        void loadFail();

        void loadSuccess();

        String getUsername();
    }
}
