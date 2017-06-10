package cn.edu.fjnu.service;

import cn.edu.fjnu.beans.Comment;
import cn.edu.fjnu.beans.User;

import java.util.List;

/**
 * Created by HMH on 2016/12/5.
 */
public interface CommentService {
    Comment createComment(Comment comment);

    Integer CountComment(Integer synamicId);

    List getAllComment(Integer ringId);

    void deleteComment(Comment comment, User user);

}
