package cn.itcast.ssm.service.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;


import cn.itcast.ssm.dao.ClassDao;
import cn.itcast.ssm.dao.TipDao;
import cn.itcast.ssm.dao.UserDao;
import cn.itcast.ssm.mapper.UserMapper;
import cn.itcast.ssm.pojo.Classes;
import cn.itcast.ssm.pojo.Pager;
import cn.itcast.ssm.pojo.Tip;
import cn.itcast.ssm.pojo.TipUser;
import cn.itcast.ssm.pojo.User;
import cn.itcast.ssm.service.ClassService;
import cn.itcast.ssm.service.TipService;
import cn.itcast.ssm.service.UserService;
import cn.itcast.ssm.util.BigDataPager;
import cn.itcast.ssm.util.JdbcUtils;


@Service("TipService")
public class TipServiceImpl implements TipService {
	@Resource(name="tipDao")
	private TipDao tipdao;//ע��
	
	//���û���ӵ����Ӳ��뵽���ݿ�
	@Override
	public void insertTip(Tip tip) throws Exception {
		tipdao.insertTip(tip);
		
	}
	
	//�鿴�ҷ������������,�����û�id
	public List<Tip> getALlTip(int userid) throws Exception{
		return tipdao.getALlTip(userid);
	}
	
	//����tipidɾ������ 
	public void deleteTipById(TipUser tipUser) throws Exception{
		tipdao.deleteTipById(tipUser);
	}
	
	//��ѯ�����ӵ���ϸ����,����tipid��userid 
	public Tip getDetail(TipUser tipUser) throws Exception{
		return tipdao.getDeatil(tipUser);
	}
	
	//���޸����ӷ���ʱ������userid��ѯ�����û����еķ���
	public List<String> getAllClassByUserId(int id) throws Exception{
		return tipdao.getAllClassByUserId(id);
	}
	
	//����userid��tipid�޸�tip����
	public void updateTipById(TipUser tipUser) throws Exception{
		tipdao.updateTipById(tipUser);
	}
	
	//��ҳ���鿴���е�tip
	public Pager<Tip> getTipList(int currentPage, int pageSize) throws Exception{
		
		int count = BigDataPager.getCount();//���ݿ���ܼ�¼����ͨ��count(*)��ѯ��
		int totalPage = BigDataPager.getTotalPage(pageSize,count);	//�õ���ҳ��
		int prePage = 0;
		int nextPage = 0;
		int bar[] = null;

		
		prePage = BigDataPager.getPrePage(currentPage);	//��һҳ
		nextPage = BigDataPager.getNextpage(currentPage, totalPage);	//��һҳ

		//���ɷ�ҳ��
		bar = BigDataPager.getBar(totalPage, currentPage);
		
		int limit = BigDataPager.getLimit(currentPage, pageSize);	//limit

		Pager pager = new Pager();
		pager.setCurrentPage(currentPage);	//Ҫ���ҵ�ҳ��,ͨ��request�õ�
		pager.setPageSize(pageSize);	//ҳ���С
		pager.setTotalPage(totalPage);	//��ҳ��	��ͨ��count(*) / pageSize��ѯ��
		pager.setTotalRecord(count);	//�ܼ�¼����ͨ��count(*)��ѯ��
		pager.setPrePage(prePage);
		pager.setNextPage(nextPage);
		pager.setBar(bar);
		List<Tip> list = tipdao.getTipList(limit);	//Ӧ��ʹ��mybatis
		pager.setDataList(list);	//��ѯ�����ͨ����ѯ���ݿ�õ�
		return pager;
	}
	public TipDao getTipdao() {
		return tipdao;
	}

	public void setTipdao(TipDao tipdao) {
		this.tipdao = tipdao;
	}
	
	//�鿴�ҵ�tip����Ϣ������tipid��userid������Tip
	public Tip getNeirong(Tip tip) throws Exception{
		return this.tipdao.getNeirong(tip);
	}
	
	/****************************************************************/

	//��ѯѧ����Ϣ
	/*public List<Tip> getTip(int pageNum, int pageSize) throws Exception{
		PreparedStatement pstm = null;
		String sql = null;
		ResultSet rs = null;
		Connection conn = null;
		
		List<Tip> list = new ArrayList();

		int limit = (pageNum-1)* pageSize;
		sql = "select * from tip limit " + limit +","+pageSize;
		System.out.println(sql);
		conn = JdbcUtils.getConnection();
		pstm = conn.prepareStatement(sql);
		rs = pstm.executeQuery();
		while(rs.next()){
			int id = rs.getInt(1);
			int user_id = rs.getInt(2);
			String clazz = rs.getString(3);
			Date time = rs.getDate(4);
			String title = rs.getString(5);
			String detail = rs.getString(6);
			
			Tip tip = new Tip();
			tip.setId(id);
			tip.setUserid(user_id);
			tip.setClasses(clazz);
			tip.setTime(time);
			tip.setTitle(title);
			tip.setDetail(detail);
			list.add(tip);
		}

		return list;
		
	}*/

}
