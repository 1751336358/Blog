package cn.itcast.ssm.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.itcast.ssm.dao.ClassDao;
import cn.itcast.ssm.dao.UserDao;
import cn.itcast.ssm.mapper.ClassMapper;
import cn.itcast.ssm.mapper.UserMapper;
import cn.itcast.ssm.pojo.Classes;
import cn.itcast.ssm.pojo.User;

@Repository("classDao")
public class ClassDaoImpl extends SqlSessionDaoSupport implements ClassDao{
	
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	////���û���ӵ����ӷ�����뵽���ݿ�
	@Override
	public void addClass(Classes classes) throws Exception {
		this.getSqlSession().getMapper(ClassMapper.class).addClass(classes);
		
	}

	

	//��ѯ�û��ķ��� ,����userid
	@Override
	public List<Classes> getClassesByUserId(int id) throws Exception{
		return  this.getSqlSession().getMapper(ClassMapper.class).getClassesByUserId(id);
	}
	
	
}
