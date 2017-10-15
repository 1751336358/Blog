package cn.itcast.ssm.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.itcast.ssm.dao.UserDao;
import cn.itcast.ssm.mapper.ClassMapper;
import cn.itcast.ssm.mapper.UserMapper;
import cn.itcast.ssm.pojo.User;

@Repository("userDao")
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao{
	
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	//�û�ע��ʱ����û����Ƿ����
	@Override
	public List<User> findUserByUsername(User u) throws Exception {
		
		return this.getSqlSession().getMapper(UserMapper.class).findUserByUsername(u);
	}
	
	//�û���¼ʱ�ж��û����û�������������ȷ
	@Override
	public User findUsernameAndPassword(User u) throws Exception {
		
		return this.getSqlSession().getMapper(UserMapper.class).findUsernameAndPassword(u);
	}
	
	//����ע����û�����ӵ����ݿ�
	@Override
	public void insertUser(User u) throws Exception {
		this.getSqlSession().getMapper(UserMapper.class).insertUser(u);
		
	}

	//����user_id�����û������ӷ��� 
	public List<String> getClassByUserId(User u) throws Exception{
		return this.getSqlSession().getMapper(UserMapper.class).getClassByUserId(u);
	}

	//Ajax���Ե�¼����
	@Override
	public int testPassword(String password) throws Exception {
		
		return this.getSqlSession().getMapper(UserMapper.class).testPassword(password);
	}
	//Ajax���Ե�¼�û���
	@Override
	public int testUsername(String username) throws Exception {
		
		return this.getSqlSession().getMapper(UserMapper.class).testUsername(username);
	}
	
}
