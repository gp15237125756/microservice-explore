package com.rbsn.schedule.common.base;

import java.util.List;

/**
 * 基础数据服务
 */
public interface BaseDao<T> {

	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id);

	/**
	 * 查询数据列表
	 * @param entity
	 * @return
	 */
	public List<T> list(T entity);

	/**
	 * 查询数据列表,分页
	 * @param entity
	 * @return
	 */
	public List<T> listPage(T entity);
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(T entity);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(T entity);
	
	/**
	 * 删除数据（逻辑删除，更新del_flag字段为1）
	 * @param id
	 * @see public int delete(T entity)
	 * @return
	 */
	public int delete(String id);
	
}