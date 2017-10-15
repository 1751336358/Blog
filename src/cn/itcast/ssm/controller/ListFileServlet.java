package cn.itcast.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListFileServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = this.getServletContext().getRealPath("/WEB-INF/upload");	//����ϴ����ļ���Ŀ¼
		Map map = new HashMap();
		fileList(new File(path),map);
		request.setAttribute("map", map);
		request.getRequestDispatcher("/WEB-INF/jsp/listfile.jsp").forward(request, response);
		
	}

	
	public void fileList(File file,Map map){
		if(!file.isFile()){//�����Ŀ¼
			File child[] = file.listFiles();//����������ļ�
			if(child != null && child.length > 0){
				for(File f:child){
					fileList(f,map);
				}
			}
			
		}else{//������ļ�
			String filename = file.getName().substring(file.getName().indexOf("_")+1);	//�õ��ļ�����ʵ�ļ���
			//key���ļ��ڷ������е��ļ�����UUID_�ļ���.txt��
			//value���ļ�����ʵ�ļ������ļ���.txt��
			map.put(file.getName(), filename);
			//<a href="?file.getName()">fileName</a>
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	
	}
	

}
