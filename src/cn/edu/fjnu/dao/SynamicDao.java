package cn.edu.fjnu.dao;

import cn.edu.fjnu.beans.Synamic;
import cn.edu.fjnu.dao.base.GenericDao;
import cn.edu.fjnu.dao.base.Page;

import java.util.List;

/**
 * Created by HMH on 2016/11/25.
 */
public interface SynamicDao extends GenericDao<Synamic,Integer>{
    /**
     * 通过创建者获取活动
     *
     * @param CreatorNo
     * @return
     */

    List getRunningRingByCreator(String CreatorNo, Page page);

    List getSynamicByCreator(String CreatorNo);

    List getAllSynamic();

    Synamic getRunningRingByCCT(String creatorNo, String createTime);

    Synamic getActNoRunningRing(Integer ringId);
  /*  void SavePicture(String path);*/

    void updateComment(Integer reviewId, Integer ringId);

    void updateThumb(Integer thumb, Integer ringId);

    void updateHeadPicture(String path,String creatorNo);

    void updateSynamicCreatorName(String userName,Integer ringId);
}
