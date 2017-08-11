package cn.zry.modules.data.mysql;

import java.util.List;
import java.util.Map;

/**
 * 标准的基础Dao接口声明
 * @param <T>	管理的实体类
 */
public interface StandardDao<T, K> {

	public T get(K id);

	public void save(T entity);

	public void update(T entity);

	void deleteByIds(List<String> ids);

	public long count();

	/**
	 * 基本参数：
	 *  pageSize   : 每页数据
	 *  orderField : 排序字段
	 *  orderRule  : 排序规则  ASC or DESC
	 *
	 * Mysql :
	 * 	limitStart : 起始索引， start = (pageNumber - 1) * pageSize
	 *
	 * MongoDB :
	 * 	pageNumber : 当前页
	 */
	public List<T> searchPage(Map<String, Object> map);

	public List<T> search();

}