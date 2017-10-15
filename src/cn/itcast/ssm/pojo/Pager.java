package cn.itcast.ssm.pojo;

import java.io.Serializable;
import java.util.List;

//��װ��ѯ����Ϣ
public class  Pager<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;	//���л�id
	private int pageSize = 5;	//ҳ���С
	private int currentPage;	//Ҫ��ת��ҳ��
	private int totalPage;	//��ҳ��
	private int totalRecord;	//�ܼ�¼��
	private int prePage;	//��һҳ
	private int nextPage;	//��һҳ
	private List<T> dataList;	//�����ҳ����ʾ������
	int bar[];	//������
	
	public Pager() {
		
	}
	public Pager(int pageSize, int currentPage, int totalPage, int totalRecord,
			List<T> dataList) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.totalRecord = totalRecord;
		this.dataList = dataList;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int[] getBar() {
		return bar;
	}
	public void setBar(int[] bar) {
		this.bar = bar;
	}
	
	
}
