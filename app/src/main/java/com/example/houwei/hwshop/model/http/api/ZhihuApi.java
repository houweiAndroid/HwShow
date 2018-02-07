package com.example.houwei.hwshop.model.http.api;

import com.example.houwei.hwshop.model.bean.WelcomeBean;

import io.reactivex.Flowable;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by houwei on 2017/6/6.
 */

public interface ZhihuApi {
    String HOST = "http://news-at.zhihu.com/api/4/";
    /**
     * 启动的API
     */
    @GET("start-image/{res}")
    Flowable<WelcomeBean> getWelcomeInfo(@Path("res")String res);
}
