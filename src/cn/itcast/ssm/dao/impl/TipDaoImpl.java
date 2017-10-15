package cn.itcast.ssm.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.itcast.ssm.dao.ClassDao;
import cn.itcast.ssm.dao.TipDao;
import cn.itcast.ssm.dao.UserDao;
import cn.itcast.ssm.mapper.ClassMapper;
import cn.itcast.ssm.mapper.TipMapper;
import cn.itcast.ssm.mapper.UserMapper;
import cn.itcast.ssm.pojo.Classes;
import cn.itcast.ssm.pojo.Tip;
import cn.itcast.ssm.pojo.TipUser;
import cn.itcast.ssm.pojo.User;

@Repository("tipDao")
public class TipDaoImpl extends SqlSessionDaoSupport implements TipDao{
	
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	//���û���ӵ����ӷ�����뵽���ݿ�
	@Override
	public void insertTip(Tip tip) throws Exception {
		this.getSqlSession().getMapper(TipMapper.class).insertTip(tip);
		
	}
	
	//�鿴�ҷ������������,�����û�id
	public List<Tip> getALlTip(int userid) throws Exception{
		return this.getSqlSession().getMapper(TipMapper.class).getALlTip(userid);
	}
	
	//����tipidɾ������ 
	public void deleteTipById(TipUser tipUser) throws Exception{
		this.getSqlSession().getMapper(TipMapper.class).deleteTipById(tipUser);
	}
	
	
	//��ѯ�����ӵ���ϸ����,����tipid��userid 
	public Tip getDeatil(TipUser tipUser) throws Exception{
		return this.getSqlSession().getMapper(TipMapper.class).getDetail(tipUser);
	}
	
	//���޸����ӷ���ʱ������userid��ѯ�����û����еķ���
	public List<String> getAllClassByUserId(int id) throws Exception{
		return this.getSqlSession().getMapper(TipMapper.class).getAllClassByUserId(id);
	}
	
	
	//����userid��tipid�޸�tip����
	public void updateTipById(TipUser tipUser) throws Exception{
		this.getSqlSession().getMapper(TipMapper.class).updateTipById(tipUser);
	}
	
	//��ҳ���鿴���е�tip
	public List<Tip> getTipList(int currentPage) throws Exception{
		List<Tip> tipList = this.getSqlSession().getMapper(TipMapper.class).getTipList(currentPage);
		return tipList;
	}
	
	//�鿴�ҵ�tip����ϸ���ݣ�����tipid��userid
	public Tip getNeirong(Tip tip) throws Exception{
		return this.getSqlSession().getMapper(TipMapper.class).getNeirong(tip);
	}
}
