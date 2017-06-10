package cn.edu.fjnu.service;

import cn.edu.fjnu.beans.Synamic;

/**
 * Created by HMH on 2016/11/26.
 */
public interface UserAndRingService {
    void saveRing(Synamic activities, String thumbNo, String reviewerNo);
    void deleteRing(Integer urNo);
}
