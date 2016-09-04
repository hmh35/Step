package cn.edu.fjnu.controller;

import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.beans.base.ResultData;
import cn.edu.fjnu.common.AppExCode;
import cn.edu.fjnu.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: linqiu
 * @Date: 2016/3/3 11:00
 * @Description: UserController 测试类控制层
 */
@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    private ResultData resultData;

    @ResponseBody
    @RequestMapping(value = "/User/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResultData getUserInfo(@PathVariable Integer id) {
        resultData = new ResultData();
        User user = userService.getUserById(id);
        resultData.setData(JSON.toJSON(user));
        resultData.setStatus(ResultData.SUCCESS);
        resultData.setErrorCode(AppExCode.testCode);
        return resultData;
    }

    @RequestMapping("/login")
    public void getjson(HttpServletRequest req, HttpServletResponse rep) throws Exception {
        String username = req.getParameter("username");
        String pass = req.getParameter("pass");
        System.out.println(" 直接 PrintWriter输出json :" + username + "--->" + pass);
        PrintWriter writer = rep.getWriter();
        JSONObject object = new JSONObject();
        if (username.equals("123012014018") && pass.equals("123456")) {
            object.put("results", "login success");
        } else {
            object.put("results", "login fail");
        }
        writer.println(object.toString());
        writer.flush();
        writer.close();

    }

    @ResponseBody
    @RequestMapping(value = "/User", method = RequestMethod.GET, produces = "application/json")
    public ResultData findAll() {
        resultData = new ResultData();
        List<User> users = userService.findAll();
        resultData.setData(JSON.toJSON(users));
        //User user = userService.getUserById(id);
        //resultData.setData(JSON.toJSON(user));
        resultData.setStatus(ResultData.SUCCESS);
        resultData.setErrorCode(AppExCode.testCode);
        return resultData;
    }

    @ResponseBody
    @RequestMapping(value = "/User/order/{isAsc}", method = RequestMethod.GET, produces = "application/json")
    public String findAllByorder(@PathVariable boolean isAsc) {
        resultData = new ResultData();
        List<User> users = userService.findAllByOrder("id", isAsc);
        resultData.setData(JSON.toJSON(users));
        resultData.setStatus(ResultData.SUCCESS);
        resultData.setErrorCode(AppExCode.testCode);
        return JSON.toJSONString(resultData);
    }

    @ResponseBody
    @RequestMapping(value = "/User/property", method = RequestMethod.GET, produces = "application/json")
    public ResultData findAllByproperty() {
        Map<String,Object> propertyNameMap = new HashMap<>();
        propertyNameMap.put("names","name");
        propertyNameMap.put("age",1);
        resultData = new ResultData();
        List<User> users = userService.findAllByProperty(propertyNameMap);
        resultData.setData(JSON.toJSON(users));
        resultData.setStatus(ResultData.SUCCESS);
        resultData.setErrorCode(AppExCode.testCode);
        return resultData;
    }
}
