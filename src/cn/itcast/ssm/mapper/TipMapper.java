package cn.itcast.ssm.mapper;

import java.util.List;

import cn.itcast.ssm.pojo.Tip;
import cn.itcast.ssm.pojo.TipUser;
import cn.itcast.ssm.pojo.User;

public interface TipMapper {
	//���û���������Ӳ��뵽���ݿ���
	public void insertTip(Tip tip) throws Exception;
	
	//�鿴�ҷ������������,�����û�id
	public List<Tip> getALlTip(int userid) throws Exception;
	
	//����tipidɾ������ 
	public void deleteTipById(TipUser tipUser) throws Exception;
	
	//��ѯ�����ӵ���ϸ����,����tipid��userid 
	public Tip getDetail(TipUser tipUser) throws Exception;
	
	//���޸����ӷ���ʱ������userid��ѯ�����û����еķ���
	public List<String> getAllClassByUserId(int id) throws Exception;
	
	//����userid��tipid�޸�tip����
	public void updateTipById(TipUser tipUser) throws Exception;
	
	//��ҳ���鿴���е�tip
	public List<Tip> getTipList(int currentPage) throws Exception;
	
	//�鿴�ҵ�tip����Ϣ������tipid��userid������Tip
	public Tip getNeirong(Tip tip) throws Exception;
}
