package com.rbsn.schedule.common.base;

import com.github.pagehelper.PageInfo;
import com.rbsn.schedule.common.pojo.param.PageParam;

import java.util.List;

/**
 * 基础服务
 */
public interface BaseService<T> {

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
	public PageInfo listPage(T entity, PageParam pageParam);

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