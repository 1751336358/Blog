package cn.itcast.ssm.service.impl;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.itcast.ssm.dao.UserDao;
import cn.itcast.ssm.mapper.UserMapper;
import cn.itcast.ssm.pojo.User;
import cn.itcast.ssm.service.UserService;
import cn.itcast.ssm.util.CreateMD5;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource(name="userDao")
	private UserDao userdao;//ע��
	
	//�û�ע��ʱ����û����Ƿ����
	@Override
	public List<User> findUserByUsername(User u) throws Exception {
		
		return userdao.findUserByUsername(u);
	}
	
	//�û���¼ʱ�ж��û����û�������������ȷ
	@Override
	public User findUsernameAndPassword(User u) throws Exception {
		
		return userdao.findUsernameAndPassword(u);
	}
	
	//����ע����û�����ӵ����ݿ�
	@Override
	public void insertUser(User u) throws Exception {
		userdao.insertUser(u);
		
	}
	
	//����user_id�����û������ӷ��� 
	public List<String> getClassByUserId(User u) throws Exception{
		return userdao.getClassByUserId(u);
	}

	public UserDao getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}
	
	//Ajax���Ե�¼����
	@Override
	public int testPassword(HttpServletRequest request) throws Exception {
		String password = CreateMD5.getMD5(request.getParameter("password"));
		int count = userdao.testPassword(password);
		return count;
	}
	
	//Ajax���Ե�¼�û���
	@Override
	public int testUsername(HttpServletRequest request) throws Exception {
		String username = request.getParameter("username");
		
		int count = userdao.testUsername(username);
		return count;
	}

	

	

}
