package cn.itcast.ssm.dao;

import java.util.List;

import cn.itcast.ssm.pojo.Classes;
import cn.itcast.ssm.pojo.User;

public interface ClassDao {
	//���û���ӵ����ӷ�����뵽���ݿ�
	public void addClass(Classes classes) throws Exception;
	
	//��ѯ�û��ķ��� ,����userid
	public List<Classes> getClassesByUserId(int id) throws Exception;
	
}
