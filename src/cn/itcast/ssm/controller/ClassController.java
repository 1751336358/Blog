package cn.itcast.ssm.controller;


//ʹ��ע�⿪����������Ҫ����İ�
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.ssm.pojo.Classes;
import cn.itcast.ssm.pojo.User;
import cn.itcast.ssm.service.ClassService;
import cn.itcast.ssm.service.UserService;

//��ÿһ����ע����û������ӷ���浽���ݿ�class����
@Controller("ClassController")
public class ClassController{
	@Resource(name="ClassService")
	private ClassService classService;
	
	@RequestMapping("setClasses.action")
	public String getClasses(HttpSession session,String []classes) throws Exception{
		User u = (User) session.getAttribute("user");
	/*	System.out.println("ID��"+u.getId());
		System.out.println("�û��� ��"+u.getUsername());
		if(Class != null){
			for(String c:Class){
				System.out.println("���ࣺ"+c);
			}
		}*/
		System.out.println("��ǰ�û���ID�ǣ�"+u.getId());
		Classes classe = new Classes();
		classe.setUser_id(u.getId());
		if(classes != null){
			for(String clas:classes){
				classe.setUser_id(u.getId());
				classe.setClasses(clas);
				//�������ݿ�
				classService.addClass(classe);
			}
		}
		//��ӷ���ɹ�,�ض��򵽵�½ҳ��
		return "redirect:/login.action";
	}
	
	// ��ӷ���
	@RequestMapping("/addClass.action")
	public String addClass(HttpSession session,HttpServletRequest request) throws Exception{
		System.out.println("session:id"+session.getAttribute("userid"));
		String userid = session.getAttribute("userid").toString();
		System.out.println("ת�����userid��"+userid);
		//���ҳ����û�ԭ�еķ���,����userid from classes
		List<Classes> classes = classService.getClassesByUserId(Integer.parseInt(userid));
		request.setAttribute("classes", classes);
		
		return "/WEB-INF/jsp/addClass.jsp";
	}
	//����ӵķ�����뵽classses����
	@RequestMapping("/addClassIntoDB.action")
	public String addClassIntoDB(String []classes,HttpServletRequest request,HttpSession session) throws Exception{
		String userid = session.getAttribute("userid").toString();
		System.out.println("��ӷ������id�ǣ�"+userid);
		if(classes != null){
			for(String s:classes){
				System.out.println("������ӵķ����ǣ�"+s);
			}
		}
		Classes classe = new Classes();
		classe.setUser_id(Integer.parseInt(userid));
		if(classes != null){
			for(String clas:classes){
				classe.setUser_id(Integer.parseInt(userid));
				classe.setClasses(clas);
				//�������ݿ�
				classService.addClass(classe);
			}
		}
		System.out.println("��ӳɹ�");
		//�ض���loginSuccess.jsp
		return "redirect:/gotoIndex.action";
	}
	
	//����loginSuccess.jspҳ�棬�൱����ҳ
	@RequestMapping("/gotoIndex.action")
	public String gotoIndex(HttpSession session,HttpServletRequest request) throws Exception{
		int userid = Integer.parseInt(session.getAttribute("userid").toString());
		
		//����userid��ѯ���û������з���
		List<Classes> classes = classService.getClassesByUserId(userid);
		System.out.println("���ҵ��ķ�������ǣ�"+classes.size());
		request.setAttribute("classes",classes);
		request.setAttribute("userid",userid);
		return "/WEB-INF/jsp/login.jsp";
	}
	public ClassService getClassService() {
		return classService;
	}

	public void setClassService(ClassService classService) {
		this.classService = classService;
	}
	
	
	
	
}
