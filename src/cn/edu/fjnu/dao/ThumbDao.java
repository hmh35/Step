package cn.edu.fjnu.dao;

import cn.edu.fjnu.beans.Thumb;
import cn.edu.fjnu.dao.base.GenericDao;
import java.util.List;

/**
 * Created by HMH on 2016/12/3.
 */
public interface ThumbDao extends GenericDao<Thumb,Integer> {
    Thumb getMyThumbByRingId(String ringId);

    List<Thumb> getMyThumb(String ringId);

    //int getThumb(String ringId);

    List countThumb(String ringId);//得到点赞数

    int judge(Integer ringId, String thumberId);

}
