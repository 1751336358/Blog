package cn.itcast.ssm.dao;

import java.util.List;
import cn.itcast.ssm.pojo.User;

public interface UserDao {
	//�û�ע��ʱ����û����Ƿ����
	public List<User> findUserByUsername(User u) throws Exception;
	
	//Ajax��¼��֤�û���
	public int testUsername(String username) throws Exception;
	
	//Ajax��¼��֤����
	public int testPassword(String password) throws Exception;
	
	//����ע����û�����ӵ����ݿ�
	public void insertUser(User u) throws Exception;
	
	//�û���¼ʱ�ж��û����û�������������ȷ
	public User findUsernameAndPassword(User u) throws Exception;
	
	//����user_id�����û������ӷ��� 
	public List<String> getClassByUserId(User u) throws Exception;
}
