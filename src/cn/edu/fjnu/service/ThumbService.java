package cn.edu.fjnu.service;

import cn.edu.fjnu.beans.Thumb;

import java.util.List;

/**
 * Created by HMH on 2016/12/3.
 */
public interface ThumbService {
    Thumb getMyThumb(String ringId);

    Thumb createThumb(Thumb thumb);

    List getAllThumb(String ringId);

    int getThumb(String ringId);

    int countThumb(String ringId);

    boolean judge(Integer ringId, String thumberId);

}
