package cn.edu.fjnu.dao;

import cn.edu.fjnu.beans.Comment;
import cn.edu.fjnu.dao.base.GenericDao;

import java.util.List;

/**
 * Created by HMH on 2016/12/5.
 */
public interface CommentDao extends GenericDao<Comment,Integer> {

    Comment getMyCommentByRingId(String ringId);

    List CountComment(Integer synamicId);

    void setCmmentCount(String ringId, Integer count);

    void updateHeadPicture(String path, Integer userId);

    void updateuserName(String userName,Integer userId);


}
