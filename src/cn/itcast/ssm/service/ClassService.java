package cn.itcast.ssm.service;

import java.util.List;

import cn.itcast.ssm.pojo.Classes;
import cn.itcast.ssm.pojo.User;

public interface ClassService {
	//���û���ӵ����ӷ�����뵽���ݿ�
	public void addClass(Classes classes) throws Exception;
	
	//��ѯ�û��ķ��� ,����userid
	public List<Classes> getClassesByUserId(int id) throws Exception;
	
}
