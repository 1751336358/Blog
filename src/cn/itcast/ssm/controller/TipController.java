package cn.itcast.ssm.controller;


//ʹ��ע�⿪����������Ҫ����İ�
import java.util.List;
import java.util.Scanner;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import cn.itcast.ssm.filter.HtmlFilter;
import cn.itcast.ssm.pojo.Pager;
import cn.itcast.ssm.pojo.Tip;
import cn.itcast.ssm.pojo.TipUser;
import cn.itcast.ssm.service.TipService;


@Controller("TipController")
public class TipController{
	@Resource(name="TipService")
	private TipService tipService;
	
	//���û������Ӳ��뵽���ݿ���
	@RequestMapping("/insertTip.action")
	public ModelAndView insertTip(Tip tip,HttpServletRequest request) throws Exception{
		String [] classesValues = request.getParameterValues("classes");	//�õ��û���������ӷ��࣬��option�ύ����������
		tip.setClasses(classesValues[0]);
		//��tip�����ݽ���html�ı�ת��,��ô����������?
		String title = tip.getTitle();
		String detail = tip.getDetail();
		title = HtmlFilter.filter(title);
		detail = HtmlFilter.filter(detail);
		System.out.println("���˺��title��"+title);
		System.out.println("���˺��detail:"+detail);
		tip.setTitle(title);	//���¸�ֵ
		tip.setDetail(detail);
		
		tipService.insertTip(tip);	//���û������tip�������ݿ�
		//��ת���������ӵ���ϸ��
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("tip",tip);
		modelAndView.setViewName("/WEB-INF/jsp/tipDetail.jsp");
		return modelAndView;	
	}
	
	//�鿴�ҷ������������,�����û�id
	@RequestMapping("/getAllTip.action")
	public ModelAndView getAllTip(int userid) throws Exception{
		
		List<Tip> tipList = tipService.getALlTip(userid);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userid",userid);
		modelAndView.addObject("tipList",tipList);
		modelAndView.setViewName("/WEB-INF/jsp/tiplist.jsp");
		return modelAndView;
	}
	//��ҳ��������е�tip
	@RequestMapping("/getTipList.action")
	public void getTipList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String current = request.getParameter("currentPage");
		if(current == null || current == "" || current.equals("0")){
			current = "1";
		}
		int currentPage = Integer.parseInt(current);
		System.out.println("Controller�����������-----------");
		System.out.println("Ҫ���ʵ�ҳ����"+currentPage);
		
		
		Pager<Tip> pager = tipService.getTipList(currentPage, 5);
		
		System.out.println("prePage="+pager.getPrePage()+"++++");
		System.out.println("nextPage="+pager.getNextPage());
		request.setAttribute("pager",pager);
		request.getRequestDispatcher("/WEB-INF/jsp/alltiplist.jsp").forward(request, response);
				
	}
	
	/*------------------------------------------------------------*/
	//�鿴�ҷ����tip������ϸ���ݣ�����tipid��userid
	@RequestMapping("/getNeirong.action")
	public ModelAndView getNeirong(HttpServletRequest request) throws Exception{
		String tipid = request.getParameter("tipid");
		String userid = request.getParameter("userid");
		System.out.println("tipid=:"+tipid);
		System.out.println("userid=:"+userid);
		Tip tip= new Tip();
		tip.setId(Integer.parseInt(tipid));
		tip.setUserid(Integer.parseInt(userid));
		tip = tipService.getNeirong(tip);
		System.out.println("�õ��ķ����ǣ�"+tip.getClasses()+"====================");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("tip",tip);
		modelAndView.setViewName("/WEB-INF/jsp/neirong.jsp");
		return modelAndView;
	}
	
	
	
	
	//����tipidɾ������,��ת��tiplist.jsp
	@RequestMapping("/deleteTipById.action")
	public ModelAndView deleteTipById(TipUser tipUser) throws Exception{
		tipService.deleteTipById(tipUser);
		System.out.println("����id"+tipUser.getTip().getId());
		System.out.println("�û�id��"+tipUser.getUser().getId());
		List<Tip> tipList = tipService.getALlTip(tipUser.getUser().getId());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userid",tipUser.getUser().getId());
		modelAndView.addObject("tipList",tipList);
		modelAndView.setViewName("/WEB-INF/jsp/tiplist.jsp");
		return modelAndView;
	}
	
	//�޸��������ݣ����Ȳ�ѯ���ӵ���ϸ��Ϣ,����userid��tipid
	@RequestMapping("/getTipDetail.action")
	public ModelAndView getTipDetail(TipUser tipUser)throws Exception{
		//��ѯ�����ӵ���ϸ����,����tipid��userid
		Tip tip = tipService.getDetail(tipUser);
		//���޸����ӷ���ʱ������userid��ѯ�����û����еķ���
	//	System.out.println("�޸ĵ��û��ķ����id��"+tipUser.getUser().getId()+"-------------------");
		List<String> classesList = tipService.getAllClassByUserId(tipUser.getUser().getId());
	/*	if(classesList != null){
			for(String c:classesList){
				System.out.println(tipUser.getUser().getId()+"��tip���ࣺ"+c);
			}
		}*/
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("tip",tip);
		modelAndView.addObject("classesList", classesList);
		modelAndView.setViewName("/WEB-INF/jsp/updateTip.jsp");
		return modelAndView;
	}
	
	
	//����userid��tipid�޸���������
	@RequestMapping("/updateTipById.action")
	public String updateTipById(TipUser tipUser) throws Exception{
		tipService.updateTipById(tipUser);	
		return "/getAllTip.action?userid="+tipUser.getTip().getUserid();	//��ת��tipList.jsp
	}
	
	
	// ��ת��д���͵�ҳ��
	@RequestMapping("/writeTip.action")
	public String writeTip(){
		return "/WEB-INF/jsp/writeTip.jsp";
	}
	public TipService getTipService() {
		return tipService;
	}

	public void setTipService(TipService tipService) {
		this.tipService = tipService;
	}
	
	
}
