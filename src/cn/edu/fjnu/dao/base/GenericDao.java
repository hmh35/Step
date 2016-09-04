package cn.edu.fjnu.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author: linqiu
 * @Date: 2016/3/3 14:54
 * @Description: 泛型数据库操作接口
 */
public interface GenericDao<T, ID extends Serializable> {
    /**
     * <p>
     * 根据主键查询对象
     * </p>
     *
     * @param id
     * @return
     */
    public T findById(ID id);

    /**
     * <p>
     * 根据主键查询对象，同时根据传入的<tt>lock</tt>标识确定是否锁定该列。
     * </p>
     * <p>
     * 当<tt>lock==true</tt>时，类似于sql语句：
     * <tt>select * from TABLE WHERE ID=? FOR UPDATE</tt>
     * </p>
     *
     * @param id
     * @param lock
     * @return
     */
    public T findById(ID id, boolean lock);

    /**
     * <p>
     * 查询所有数据
     * </p>
     *
     * @return 所有数据列表
     */
    public List<T> findAll();

    /**
     * <p>
     * 保存实体对象
     * </p>
     *
     * @param entity 待保存实体
     * @return
     */
    public T save(T entity);

    /**
     * <p>
     * 更新实体对象
     * </p>
     *
     * @param entity 待更新对象
     * @return
     */
    public void update(T entity);

    /**
     * <p>
     * 保存或更新实体对象
     * </p>
     * <p/>
     * <p>
     * 当判定<tt>entity</tt>不存在时，调用保存方法(<code>{@link #save(Object)}</code>) 当判定
     * <tt>entity</tt>存在时，调用更新方法(<code>{@link #update(Object)}</code>)
     * </p>
     *
     * @param entity
     * @return
     */
    public void saveOrUpdate(T entity);

    /**
     * <p>
     * 删除实体
     * </p>
     *
     * @param entity 待删除实体
     */
    public void delete(T entity);

    /**
     * <p>
     * 根据ID删除对象
     * </p>
     *
     * @param id 对象ID
     */
    public void deleteById(ID id);

    /**
     * <p>
     * 根据ID判断对象是否存在
     * </p>
     *
     * @param id
     * @return
     */
    public boolean exists(ID id);

    /**
     * <p>
     * 根据orderby字段进行排序，isAsc等于true为升序，等于fales为降序
     * </p>
     *
     * @param orderBy 排序字段
     * @param isAsc   是否进行升序排序
     * @return
     */
    public List<T> findAllByOrder(String orderBy, boolean isAsc);

    /**
     * <p>
     * 根据多个字段进行组合模糊查询(该方法不太全面，不宜全部试用)
     * </p>
     *
     * @param propertyNameMap key为查询字段，value为模糊查询的值
     * @return
     */
    public List<T> findAllByProperty(Map<String, Object> propertyNameMap);

    /**
     * 通过某一个属性查询所有符合的数据
     * 比对方式eq
     *
     * @param propertyName
     * @param value
     * @return
     */
    public List<T> findAllByOneProperty(String propertyName, Object value);

    /**
     * <p>
     * 按属性查询唯一对象，匹配方式为相等
     * </p>
     *
     * @param propertyName
     * @param value
     * @return
     */
    public T uniqueResult(String propertyName, Object value);

    /**
     * <p>
     * 简单查询条件
     * </p>
     *
     * @param condition
     * @return
     */
    /*public List<T> excuteSimpleQuery(SimpleQueryCondition<? extends T> condition);*/

    /**
     * 分页查询
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<T> findPage(int pageNo, int pageSize);

    /**
     * 通过某一个属性分页查询所有符合的数据
     * 比对方式eq
     *
     * @param propertyName
     * @param value
     * @return
     */
    public List<T> findAllByProPage(String propertyName, Object value, int pageNo, int pageSize);
}
