package cn.edu.fjnu.utils;

import cn.edu.fjnu.beans.*;
import cn.edu.fjnu.service.UserService;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushBatchUniMsgRequest;
import com.baidu.yun.push.model.PushBatchUniMsgResponse;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/17 23:53
 * @Description: 推送工具类
 */
public class BaiduPush {

    public static void pushActivity(Activities activities, List<User> monitoredList) throws PushClientException, PushServerException {

        // 1. get apiKey and secretKey from developer console
        /*String apiKey = "uVmeh9fQ67cXM1NK57D4iIs0SKFvByFy";
        String secretKey = "LLdHZ29iKsp6cGUeWW6ErrBHDZYkYzS3";*/
        String apiKey = "1GjoGSwWKFGsEAm49FkYVx1Z7RBExKtE";
        String secretKey = "Zma1du6crQuetN8zBYL8rX9DTBjfRdrG";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            //创建Android通知
            JSONObject notification = new JSONObject();
            notification.put("title", activities.getActName());
            notification.put("description", activities.getDescription());
/*            notification.put("notification_builder_id", 0);
            notification.put("notification_basic_style", 4);
            notification.put("open_type", 3);*/
            //notification.put("url", "http://push.baidu.com");
            JSONObject jsonCustormCont = new JSONObject();
            jsonCustormCont.put("key", "value"); //自定义内容，key-value
            notification.put("custom_content", jsonCustormCont);
            List<String> channelIdList = new ArrayList<>();
            for (int i = 0; i < monitoredList.size(); i++) {
                if(monitoredList.get(i).getChannelId() == null || monitoredList.get(i).getChannelId().toString().equals("")){
                    continue;
                }
                channelIdList.add(monitoredList.get(i).getChannelId());
            }
            String[] channelIds = new String[channelIdList.size()];
            for(int i = 0; i < channelIdList.size();i++){
                channelIds[i] = channelIdList.get(i);
            }

            PushBatchUniMsgRequest request = new PushBatchUniMsgRequest()
                    .addChannelIds(channelIds)
                    .addMsgExpires(new Integer(3600))
                    .addMessageType(1)
                    .addMessage(notification.toString())
                    .addDeviceType(3)
                    .addTopicId("BaiduPush");// 设置类别主题
            // 5. http request
            PushBatchUniMsgResponse response = pushClient
                    .pushBatchUniMsg(request);
            // Http请求结果解析打印
            System.out.println(String.format("msgId: %s, sendTime: %d",
                    response.getMsgId(), response.getSendTime()));
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    /*
    * 推送危险通知
    * */
    public static void pushEmergency(Position position, User user, List<User> monitorList) throws PushServerException, PushClientException {
/*        String apiKey = "uVmeh9fQ67cXM1NK57D4iIs0SKFvByFy";
        String secretKey = "LLdHZ29iKsp6cGUeWW6ErrBHDZYkYzS3";*/
        String apiKey = "1GjoGSwWKFGsEAm49FkYVx1Z7RBExKtE";
        String secretKey = "Zma1du6crQuetN8zBYL8rX9DTBjfRdrG";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            //创建Android通知
            JSONObject notification = new JSONObject();
            notification.put("title", user.getRealName()+"发起SOS");
            notification.put("description","姓名:"+user.getRealName()+"在"+position.getAddrLat()+","+position.getAddrLong()+"向你紧急求救，点击获取他的位置");
/*            notification.put("notification_builder_id", 0);
            notification.put("notification_basic_style", 4);
            notification.put("open_type", 3);*/
            //notification.put("url", "http://push.baidu.com");
            JSONObject jsonCustormCont = new JSONObject();
            jsonCustormCont.put("key", "value"); //自定义内容，key-value
            notification.put("custom_content", jsonCustormCont);
            List<String> channelIdList = new ArrayList<>();
            for (int i = 0; i < monitorList.size(); i++) {
                if (monitorList.get(i).getChannelId() == null || monitorList.get(i).getChannelId().toString().equals("")) {
                    continue;
                }
                channelIdList.add(monitorList.get(i).getChannelId());
            }
            String[] channelIds = new String[channelIdList.size()];
            for (int i = 0; i < channelIdList.size(); i++) {
                channelIds[i] = channelIdList.get(i);
            }
            //String[] channelIds = {"4307042709570704277"};

            /*
            *批量单播
            * */

            PushBatchUniMsgRequest request = new PushBatchUniMsgRequest()
                    .addChannelIds(channelIds)
                    .addMsgExpires(new Integer(3600))
                    .addMessageType(1)
                    .addMessage(notification.toString())
                    .addDeviceType(3)
                    .addTopicId("BaiduPush");// 设置类别主题
            // 5. http request
            PushBatchUniMsgResponse response = pushClient
                    .pushBatchUniMsg(request);
            // Http请求结果解析打印
            System.out.println(String.format("msgId: %s, sendTime: %d",
                    response.getMsgId(), response.getSendTime()));
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }
            /*
            * 单播推送
            * */
            // 4. 设置请求参数，创建请求实例
           /* PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().
                    addChannelId("3589264064374978855").
                    addMsgExpires(new Integer(3600)).   //设置消息的有效时间,单位秒,默认3600*5.
                    addMessageType(1).              //设置消息类型,0表示透传消息,1表示通知,默认为0.
                    addMessage("{\"title\":\"通知\",\"description\":\"这是一条推送\"}").
                    addDeviceType(3);      //设置设备类型，deviceType => 1 for web, 2 for pc,
            //3 for android, 4 for ios, 5 for wp.
            // 5. 执行Http请求
            PushMsgToSingleDeviceResponse response = pushClient.
                    pushMsgToSingleDevice(request);
            // 6. Http请求返回值解析
            System.out.println("msgId: " + response.getMsgId()
                    + ",sendTime: " + response.getSendTime());
        } catch (PushClientException e) {
            //ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
            //'true' 表示抛出, 'false' 表示捕获。
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }*/
}
