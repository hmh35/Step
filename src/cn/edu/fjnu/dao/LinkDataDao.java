package cn.edu.fjnu.dao;

import cn.edu.fjnu.beans.NewsEntity;
import cn.edu.fjnu.dao.base.GenericDao;

import java.util.List;

/**
 * Created by HMH on 2016/12/10.
 */
public interface LinkDataDao extends GenericDao<NewsEntity,Integer> {
    List getData();
}
