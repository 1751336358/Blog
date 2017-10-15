package cn.itcast.ssm.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BigDataPager {
	//���ɷ�ҳ��,������ҳ���͵�ǰҳ��
	public static int []getBar(int totalPage,int currentPage){
		int startPage;
		int endPage;
		int bar[] = null;
		if(totalPage <= 5){
			bar = new int[totalPage];
			startPage = 1;
			endPage = totalPage;
		}else{
			bar = new int[5];
			startPage = currentPage-2;
			endPage = currentPage+2;
			if(startPage < 1){
				startPage = 1;
				endPage = 5;
			}
			if(endPage > totalPage){
				endPage = totalPage;
				startPage = endPage-4;
			}
		}
		int index = 0;
		for(int i = startPage;i <= endPage;i++){
			bar[index++] = i;
		}
		return bar;
	}
	//�õ����ݿ���ܼ�¼��
	public static int getCount() throws Exception{
		PreparedStatement pstm = null;
		String sql = null;
		ResultSet rs = null;
		Connection conn = null;
		
		conn = JdbcUtils.getConnection();
		sql = "select count(*) from tip";
		pstm = conn.prepareStatement(sql);
		rs = pstm.executeQuery();
		int count = 0;
		while(rs.next()){
			count = rs.getInt(1);
		}
		System.out.println("�����ǣ�"+count);
		return count;
	}
	//����userid�õ����ݿ�ļ�¼��
	public static int getCount(int userid) throws Exception{
		PreparedStatement pstm = null;
		String sql = null;
		ResultSet rs = null;
		Connection conn = null;
		conn = JdbcUtils.getConnection();
		sql = "select count(*) from tip where user_id="+userid;
		pstm = conn.prepareStatement(sql);
		rs = pstm.executeQuery();
		int count = 0;
		while(rs.next()){
			count = rs.getInt(1);
		}
		JdbcUtils.release(conn, pstm, rs);	//�ͷ�����
		System.out.println("�����ǣ�"+count);
		return count;
	}
	//�õ���ҳ��
	public static int  getTotalPage(int pageSize,int count){
		int totalPage = 0;
		if(count % pageSize == 0){
			totalPage = count / pageSize;
		}else{
			totalPage = count / pageSize + 1;
		}
		return totalPage;
	}
	//�õ���һҳ��ҳ��
	public static int getPrePage(int currentPage){
		int prePage = currentPage -1;
		if(currentPage == 1){
			prePage = 1;
		}
		return prePage;
	}
	//�õ���һҳ��ҳ��
	public static int getNextpage(int currentPage,int totalPage){
		int nextPage = currentPage + 1;
		if(currentPage == totalPage){
			nextPage = totalPage;
		}
		return nextPage;
	}
	//�õ�limitֵ,���ݵ�ǰҳ����ҳ���С
	public static int getLimit(int currentPage,int pageSize){
		return (currentPage-1)* pageSize;
	}
}
