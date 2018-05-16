package com.ymei_inc.ymadvert.base;

import java.util.List;

/**
 * 
 * <p>Title:IBaseDao</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author yehb
 * @date 2016年3月16日 上午11:56:29
 */
public interface IBaseDao<T> {
	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	int save(T entity);

	/**
	 * 删除实体
	 * 
	 * @param entity
	 */
	int delete(T entity);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	int update(T entity);

	/**
	 * 按id查询
	 * 
	 * @param id
	 * @return
	 */
	T getById(T entity);

	/**
	 * 按id查询
	 * 
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Long[] ids);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<T> findAll();
}
