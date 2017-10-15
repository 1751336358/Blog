package cn.itcast.ssm.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.ssm.dao.ClassDao;
import cn.itcast.ssm.dao.UserDao;
import cn.itcast.ssm.mapper.UserMapper;
import cn.itcast.ssm.pojo.Classes;
import cn.itcast.ssm.pojo.User;
import cn.itcast.ssm.service.ClassService;
import cn.itcast.ssm.service.UserService;
@Service("ClassService")
public class ClassServiceImpl implements ClassService {
	@Resource(name="classDao")
	private ClassDao classdao;//ע��

	public ClassDao getClassdao() {
		return classdao;
	}

	public void setClassdao(ClassDao classdao) {
		this.classdao = classdao;
	}
	
	//���û���ӵ����ӷ�����뵽���ݿ�
	@Override
	public void addClass(Classes classes) throws Exception {
		classdao.addClass(classes);
		
	}
	
	//��ѯ�û��ķ��� ,����userid
	@Override
	public List<Classes> getClassesByUserId(int id) throws Exception{
		return classdao.getClassesByUserId(id);
	}
	
	
}
