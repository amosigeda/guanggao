package com.ymei_inc.ymadvert.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springmore.core.datasource.DynamicSqlSessionTemplate;

/**
 * 
 * <p>Title:BaseDaoImpl</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author yehb
 * @date 2016年3月16日 上午11:50:29
 */
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements IBaseDao<T> {
	@Autowired
	protected DynamicSqlSessionTemplate session;

    public DynamicSqlSessionTemplate getSession() {
        return session;
    }
    public void setSession(DynamicSqlSessionTemplate session) {
        this.session = session;
    } 
	private Class<T> clazz;

	String perString = "mybatis.mapper.";
	String name;
	
	public BaseDaoImpl() {
		// 使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
		String[] ss = this.clazz.toString().split("\\.");
		this.name = ss[ss.length-1];
	}
	public int save(T entity) {
		String statments = perString +name+ ".add" + name;
		return session.insert(statments, entity);
	}
	public int delete(T entity) {
		String statments =perString +name+  ".delete" + name;
		return session.delete(statments, entity);		
	}
	public int update(T entity) {
		String statments = perString +name+  ".update" + name;
		return session.update(statments, entity);
	}
	public T getById(T entity) {
		String statments = perString +name+  ".getById" + name;
		return session.selectOne(statments, entity);
	}
	public List<T> getByIds(Long[] ids) {
		String statments = perString +name+  ".getByIds" + name;
		return session.selectList(statments, ids);	
	}
	public List<T> findAll() {
		String statments = perString +name+  ".listAll" + name;
		return session.selectList(statments);	
	}
	
	
}
