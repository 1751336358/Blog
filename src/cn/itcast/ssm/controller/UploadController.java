package cn.itcast.ssm.controller;


//ʹ��ע�⿪����������Ҫ����İ�

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.ssm.pojo.User;
import cn.itcast.ssm.service.UserService;

@Controller("UploadController")
public class UploadController{
	
	
	
	//��ת���ϴ�����ҳ��uploadPage.jsp
	@RequestMapping("/gotoUploadPage.action")
	public String gotoUploadPage(){
		return "/WEB-INF/jsp/uploadPage.jsp";
	}
	//��ת�������б�ҳ��
	@RequestMapping("/gotoDownloadpage.action")
	public String gotoDownloadpage(){
		return "/WEB-INF/jsp/listfile.jsp";
	}
	
	//��ת��С��Ϸ
	@RequestMapping("/gotoGame.action")
	public String gotoGame(){
		return "/WEB-INF/jsp/game.html";
	}
	
}
