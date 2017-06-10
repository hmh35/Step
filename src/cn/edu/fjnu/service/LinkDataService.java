package cn.edu.fjnu.service;

import cn.edu.fjnu.beans.NewsEntity;

import java.util.List;

/**
 * Created by HMH on 2016/12/10.
 */
public interface LinkDataService {
    List<NewsEntity> getLinkData();//得到爬的数据

    void saveLinkData(List<NewsEntity> data);//将数据保存到数据库

    List<NewsEntity> getData();//将数据从数据库里拿出来


}
