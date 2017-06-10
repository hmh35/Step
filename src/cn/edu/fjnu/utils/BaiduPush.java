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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: linqiu
 * @Date: 2016/3/17 23:53
 * @Description: 推送工具类
 */
public class BaiduPush {

    public static void pushEctivity(NewsEntity newsEntity,User user/* List<User> monitoredList*/) throws PushClientException, PushServerException {

        String apiKey = "D9wrN7mzYLYXljgGVO7jjUH9N1sTzLHT";
        String secretKey = "7qNwFpbTh6EMlRVU4QOifRM5GLfap973";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);


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
            //notification.put("title", newsEntity.getLinkText());
            //notification.put("description",newsEntity.getLinkHref());
            notification.put("title", "咨询小助手");
            notification.put("description", newsEntity.getLinkText());
            //notification.put("description","新闻标题");
            JSONObject jsonCustormCont = new JSONObject();

            jsonCustormCont.put("push_Type", "activities"); //自定义内容，key-value
            jsonCustormCont.put("LinkText",newsEntity.getLinkText());
            jsonCustormCont.put("LinkHref",newsEntity.getLinkHref());
            notification.put("custom_content",jsonCustormCont);
            List<String> channelIdList = new ArrayList<>();
           /* for (int i = 0; i < monitoredList.size(); i++) {
                if(monitoredList.get(i).getChannelId() == null || monitoredList.get(i).getChannelId().toString().equals("")){
                    continue;
                }
                channelIdList.add(monitoredList.get(i).getChannelId());
            }*/
           if(user.getChannelId() !=null || user.getChannelId().toString().equals("")){
                channelIdList.add(user.getChannelId());
            }
            String[] channelIds = new String[channelIdList.size()];
            for(int i = 0; i < channelIdList.size();i++){
                channelIds[i] = channelIdList.get(i);
            }
            //定时推送
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



}
