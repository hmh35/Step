package cn.edu.fjnu.dao;

/*import cn.edu.fjnu.dao.base.GenericDao;
import cn.edu.fjnu.beans.UserAndRing;

*//**
 * Created by HMH on 2016/11/26.
 */
import cn.edu.fjnu.beans.UserAndRing;
import cn.edu.fjnu.dao.base.GenericDao;

/**
 * Created by Administrator on 2016/10/16.
 */
public interface UserAndRingDao extends GenericDao<UserAndRing,Integer> {
    void deleteByRingNo(Integer ringNo);

}
