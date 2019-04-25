package com.rbsn.schedule.common.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rbsn.schedule.common.pojo.param.PageParam;
import com.rbsn.schedule.common.support.key.IdManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;

/**
 * 基础服务实现
 */
public abstract class BaseServiceImpl<D extends BaseDao<T>, T extends BaseDO> implements BaseService<T> {

	@Autowired
	private D baseDao;

	@Value("${page.size}")
	public String pageSize;

	@Autowired
	protected IdManager idManager;

	@Override
	public T get(String id) {
		return baseDao.get(id);
	}

	@Override
	public List<T> list(T entity) {
		return baseDao.list(entity);
	}

	@Override
	public PageInfo listPage(T entity, PageParam pageParam){
		//每页数为0，则取默认每页数量
//		if(pageParam.getPageSize()==0){
//			if(StringUtils.isNotBlank(pageSize)){
//				pageParam.setPageSize(Integer.parseInt(pageSize));
//			}
//		}
		if(StringUtils.isBlank(pageParam.getOrderBy())){
			PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
		} else {
			PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), pageParam.getOrderBy());
		}
		List<T> list = baseDao.listPage(entity);
		return new PageInfo<>(list);
	}

	@Override
	public int insert(T entity) {
		entity.setCreateDate(new Date());
		entity.setUpdateDate(new Date());
		if(StringUtils.isBlank(entity.getId())){
			entity.setId(idManager.genetateId());
		}
		return baseDao.insert(entity);
	}

	@Override
	public int update(T entity) {
		entity.setUpdateDate(new Date());
		return baseDao.update(entity);
	}

	@Override
	public int delete(String id) {
		return baseDao.delete(id);
	}

}