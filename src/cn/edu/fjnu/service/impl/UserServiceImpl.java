package cn.edu.fjnu.service.impl;

import cn.edu.fjnu.beans.LoginLog;
import cn.edu.fjnu.beans.Synamic;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.dao.CommentDao;
import cn.edu.fjnu.dao.SynamicDao;
import cn.edu.fjnu.dao.UserDao;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.LoginLogService;
import cn.edu.fjnu.service.SynamicService;
import cn.edu.fjnu.service.UserService;
import cn.edu.fjnu.utils.Md5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/3 11:00
 * @Description: user测试类Service实现层
 */
@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Resource
    private UserDao userDao;

    @Resource
    private LoginLogService loginLogService;

    @Resource
    private SynamicService synamicService;

    @Resource
    private SynamicDao synamicDao;
    
    @Resource
    private CommentDao commentDao;


    @Override
    public User login(String phoneNum, String userPwd) {
        if (phoneNum == null || phoneNum == "" || userPwd == null ) {
            logger.info("login | monitor userName or use_pwd is not null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "监护人用户或密码为空");
        }
        User user = userDao.uniqueResult("phoneNum", phoneNum);
        if (user == null) {
            logger.info("login | this monitor is not exists!");
            throw new AppRTException(AppExCode.U_IS_EXISTS, "监护人用户名不存在");
        }

        System.out.println("  service"+user.getUserPwd()+"  "+userPwd+"  "+user.getUserName());
        if (!user.getUserPwd().equals(userPwd)) {
            logger.info("login | monitor userName or use_pwd is no valid!");
            throw new AppRTException(AppExCode.U_ERROR_PWD, "监护人用户名或密码错误");
        }

        user.setUserPwd(null);
        return user;
    }
    @Override
    public void saveUser(User user) {
        if (user == null || user.getPhoneNum() == null
                || user.getPhoneNum() == ""
                || user.getUserPwd() == null
                || user.getUserPwd() == "") {
            logger.info("saveMonitor | this monitor is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "监护人含必填注册项为空");
        }
        if(user.getUserName() ==null){
            StringBuffer buf = new StringBuffer("用户");
            buf.append(user.getPhoneNum());//加上日期
            user.setUserName(buf.toString());
        }
        User userTemp = userDao
                .uniqueResult("phoneNum", user.getPhoneNum());
        if (userTemp != null) {
            logger.info("saveMonitor | this monitor is exists!");
            throw new AppRTException(AppExCode.U_IS_EXISTS, "该监护人用户已存在");
        }
        userDao.save(user);
    }

    @Override
    public void updateUser(User user, Integer userId)throws IOException {
        if(user ==null || user.equals(userDao.getUser(userId.toString()))){
            logger.info("updateUser | userInformation is exists!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "信息没有更新，不用更改");
        }
        if(user.getUserName()!=null) {
            userDao.updateuserName(user.getUserName(), userId);
            List<Synamic> synamicList =synamicService.getMySynamic(userId.toString());
            for(int i=0;i<synamicList.size();i++){
                Synamic synamic =synamicList.get(i);
                synamicDao.updateSynamicCreatorName(user.getUserName(),synamic.getRingId());
                commentDao.updateuserName(user.getUserName(),userId);
            }
        }
        if(user.getUserAge()!=null)
            userDao.updateuserAge(user.getUserAge(),userId);
        if(user.getUserHeight()!=null)
            userDao.updateHeight(user.getUserHeight(),userId);
        if(user.getUserWeight()!=null)
            userDao.updateWeight(user.getUserWeight(),userId);
        if(user.getUserSex()!=null)
            userDao.updateuserSex(user.getUserSex(),userId);
        /*if(user.getPhoneNum()!=null){
            loginLogService.modifyUserName(user.getPhoneNum(),accesstoken);
        }*/
       // userDao.update(user);

    }

    @Override
    public void updateHeadPicture(String base64String, Integer userId,String savePath) throws IOException {
        User user =userDao.getUser(userId.toString());
       // String path = ToHeadPicture(user.getUserHeadPicture(), userId,savePath);
        String path =ToHeadPicture(base64String,userId,savePath);
        /*if(user.getUserHeadPicture()!=null ||user.getUserHeadPicture()!="") {*/
        userDao.updateHeadPicture(path,userId);
        synamicService.updateHeadPicture(path,user.getUserId());
        commentDao.updateHeadPicture(path,user.getUserId());
        
        /*}else
            user.setUserHeadPicture(path);*/

    }

    @Override
    public User getUserByAccesstoken(String accesstoken) {
        if(accesstoken == null || accesstoken == ""){
            logger.info("getUserByAccesstoken | accesstoken is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "用户accesstoken错误");
        }
        //获取用户用户名
        String userName = loginLogService.checkAccesstoken(accesstoken);
        System.out.println(userName);
        //通过电话号码获取用户
        User user = userDao.uniqueResult("phoneNum", userName);
        if (user == null) {
            logger.info("getUserByAccesstoken | this user is not exists!");
            throw new AppRTException(AppExCode.U_IS_EXISTS, "该用户不存在,无法获取用户信息");
        }

        return user;
    }


    @Override
    public User getUserByPhoneNum(String phoneNum)
    {
        if(phoneNum==null||phoneNum==null)
        {
            logger.info("phoneNum is null!!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "phoneNum为空");
        }
        User user=userDao.uniqueResult("phoneNum",phoneNum);
        return user;
    }

    @Override
    public String ToHeadPicture(String base64String, Integer userId,String savePath) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes1 = decoder.decodeBuffer(base64String);
        StringBuffer buf = new StringBuffer();
        buf.append(userId);//加上用户的编号
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String dt = sdf.format(date);
        buf.append(dt);//加上日期
        String filename=buf.toString();
        File tmpFile = new File(savePath);
        if (!tmpFile.exists()) {
            //创建临时目录
            tmpFile.mkdir();
        }
        File file=new File(savePath+filename+".png");
        //File file=new File("E:\\JAVA Work\\Step\\out\\artifacts\\Step_war_exploded\\HeadPicture\\"+filename+".png");
        FileOutputStream fos=new FileOutputStream(file);
        fos.write(bytes1);
        fos.close();
        System.out.println(file);
        String path=file.getPath();
        //String path2=path.replace(savePath,"http://zqhstep.applinzi.com/HeadPicture/");
        String path2=path.replace(savePath,"http://114.215.99.158:8080/Step/HeadPicture/");
        return path2;
    }

    @Override
    public void UpdateChannelId(String channelId, Integer userId){
        if (userId == null || channelId == "") {
            logger.info("UpdateChannelId | studentNo or channelId is null!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "channelId为空");
        }
        userDao.UpdateChannelId(channelId,userId);
    }

    @Override
    public User getUserInformation(Integer userId) {
        User user=userDao.getUser(userId.toString());
        if(user.getUserHeadPicture()==null ||user.getUserHeadPicture()=="")
         // user.setUserHeadPicture("http://zqhstep.applinzi.com/HeadPicture/120161220074002.png");
        user.setUserHeadPicture("http://114.215.99.158:8080/Step/HeadPicture/120161220074002.png");
        return user;
    }


    @Override
    public void forgetPwd(String pwd, Integer userId) {
        userDao.updatePwd(pwd,userId);
    }

    @Override
    public void modifyPwd(String newPwd, String oldPwd,User user) {
        if(oldPwd !=user.getUserPwd()){
            logger.info("modifyPwd | oldpassword error!");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "旧密码不正确");
        }
        userDao.updatePwd(newPwd,user.getUserId());

    }
    @Override
    public User getPushObjectByuserId(String userId) {
        if(userId == null || userId == "") {
            logger.info("userId is null");
            throw new AppRTException(AppExCode.U_COMMON_ERROR, "该对象不存在");
        }
        User user = userDao.getUser(userId);
        if(user == null){
            logger.info("user is null");
            throw new AppRTException(AppExCode.U_COMMON_ERROR,"用户不存在");
        }
        return user;
    }


}
