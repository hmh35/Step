package cn.edu.fjnu.service.impl;

import cn.edu.fjnu.beans.NewsEntity;
import cn.edu.fjnu.dao.LinkDataDao;
import cn.edu.fjnu.service.LinkDataService;
import cn.edu.fjnu.utils.ExtractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by HMH on 2016/12/10.
 */
@Service
public class LinkDataServiceImpl implements LinkDataService{
    @Resource
    private LinkDataDao linkDataDao;

    Logger logger = LoggerFactory.getLogger(LinkDataService.class);

    @Override
    public List<NewsEntity> getLinkData() {
        String url = "http://www.lady8844.com/shoushen/";	//爬取的网址
        List<NewsEntity> extracts = ExtractService.extract(url);
        return  extracts;
    }

    @Override
    public void saveLinkData(List<NewsEntity> data) {
        for(int i =0;i<data.size();i++){
            NewsEntity l=data.get(i);
            linkDataDao.save(l);
        }
    }

    @Override
    public List<NewsEntity> getData() {
        List<NewsEntity> newsEntities =linkDataDao.getData();
        return newsEntities;
    }
}
