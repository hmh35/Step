package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.Monitor;
import cn.edu.fjnu.beans.Monitored;
import cn.edu.fjnu.beans.Position;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.exception.AppRTException;
import cn.edu.fjnu.service.MonitoredAndMonitorService;
import cn.edu.fjnu.service.MonitoredService;
import cn.edu.fjnu.service.PositionService;
import cn.edu.fjnu.service.UserService;
import com.alibaba.fastjson.JSON;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import javafx.geometry.Pos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
@Controller
@RequestMapping(value = "/help")
public class EmergencyController {
    private Logger logger = LoggerFactory.getLogger(PositionController.class);

    @Resource
    private PositionService positionService;

    @Resource
    private UserService userService;

    @Resource
    private MonitoredService monitoredService;


    /**
     * 获取被监控人即时地理位置并进行紧急推送
     *
     * @param position
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/savePosition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String savePosition(@RequestParam(value = "position") String position,
                               @RequestParam(value = "accesstoken") String accesstoken) {
   /* @ResponseBody
    @RequestMapping(value = "/hello")
    public String test() {
        Monitored monitored = new Monitored();
        monitored.setMonitoredNo(26);
        monitored.setStudentNo("123012014039");
        Position savePosition = new Position();
        savePosition.setStudentNo(monitored.getStudentNo());
        savePosition.setRealName("聪狗");
        savePosition.setMonitoredNo("26");
        ResultData resultData = new ResultData();
        savePosition.setMonitoredNo("1");*/
        ResultData resultData = new ResultData();
        System.out.println(position);
        Position saveposition = JSON.parseObject(position,Position.class);
        positionService.savePosition(saveposition);
        User user = userService.getUserByAccesstoken(accesstoken);
        saveposition.setMonitoredNo(user.getUserId().toString());
        List<User> monitorList = userService.getMonitorByMonitoredNo(user.getUserId());
        System.out.println(monitorList);
        try {
            //推送紧急求救通知
            cn.edu.fjnu.utils.BaiduPush.pushEmergency(saveposition, user, monitorList);
            resultData.setStatus(ResultData.SUCCESS);
            System.out.println("推送成功");
        } catch (PushClientException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(AppExCode.PUSH_ERROR);
            resultData.setData("推送请求失败");
            e.printStackTrace();
        } catch (PushServerException e) {
            resultData.setStatus(ResultData.ERROR);
            resultData.setErrorCode(AppExCode.PUSH_ERROR);
            resultData.setData("推送请求失败");
            e.printStackTrace();
        } catch (AppRTException e) {
            resultData.setStatus(ResultData.ERROR);
            //查找不到对应的监护人，也属于推送失败
            if (e.getCode().equals(AppExCode.U_NOT_FIND_MONITOR)) {
                resultData.setErrorCode(AppExCode.PUSH_ERROR);
                resultData.setData("推送请求失败");
            } else {
                resultData.setErrorCode(e.getCode());
                resultData.setData(e.getMessage());
            }
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(resultData, true));
        return JSON.toJSONString(resultData, true);
        //return "index.jsp";
    }
}
