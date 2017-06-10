package cn.edu.fjnu.service;

import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.dao.base.Page;

import java.io.IOException;
import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/3 11:00
 * @Description: user测试类Service接口层
 */
public interface UserService {
    User login(String userName, String password);

    /**
     * 新增监护人
     *
     * @param user
     */
    void saveUser(User user);

    void updateUser(User user, Integer userId)throws IOException;

    void updateHeadPicture(String base64String, Integer userId,String savePath) throws IOException;

   /* String ReturnPicture(String base64String, Integer ringId)throws IOException;*/
    /**
    忘记密码：
     */
    void forgetPwd(String pwd, Integer userId);

    void modifyPwd(String newPwd, String oldPwd, User user);

/*** 通过accesstoken获取监护人数据
     *
     * @param accesstoken
     * @return
 */
    User getUserByAccesstoken(String accesstoken);

    User getUserByPhoneNum(String phoneNum);

    String ToHeadPicture(String base64String, Integer userId,String savePath)throws IOException;

    User getPushObjectByuserId(String userId);


    void UpdateChannelId(String channelId, Integer userId);

    User getUserInformation(Integer userId);
}
