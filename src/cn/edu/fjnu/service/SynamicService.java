package cn.edu.fjnu.service;

import cn.edu.fjnu.beans.Synamic;
import cn.edu.fjnu.beans.User;
import cn.edu.fjnu.dao.base.Page;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;



/**
 * Created by HMH on 2016/11/25.
 */
public interface SynamicService {
    /**
     * 发布动态
     */
    Synamic publishRunningRing(Synamic synamic,String savepath)throws ParseException, IOException;
    /**
     * 通过创建者ID获取活动
     */
    Synamic getRunningRingByCreator(String creatorNo, Page p);

    List<Synamic> getMySynamic(String creatorNo, Page p);

    List<Synamic> getMySynamic(String creatorNo);

    List<Synamic> getAllSynamic();
    /**
     * 删除动态
     */
    void deleteRunningRing(Synamic synamic, User user);

    /**
     * 获取指定动态号动态
     */
    Synamic getRunningRingByNo(Integer ringId);


    /**
     * 将二进制文件转化成image并保存
     *
     */
    String ReturnPicture(String base64String, Integer ringId,String savePath)throws IOException;
    /**
     * 将二进制文件转化成image
     *
     */
    String ToImageBinary(Integer ringId)throws IOException;

    void updateHeadPicture(String path,Integer userId);





}
