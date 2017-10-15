package cn.itcast.ssm.controller;


//ʹ��ע�⿪����������Ҫ����İ�
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.ssm.filter.HtmlFilter;
import cn.itcast.ssm.pojo.Say;
import cn.itcast.ssm.pojo.Tip;
import cn.itcast.ssm.pojo.TipUser;
import cn.itcast.ssm.service.SayService;
import cn.itcast.ssm.service.TipService;

@Controller("SayController")
public class SayController{
	@Resource(name="SayService")
	private SayService sayService;
	
	//��ת������ҳ��
	@RequestMapping("/gotoSayPage.action")
	public String gotoSayPage(Say say,HttpServletRequest request){
		
		request.setAttribute("say", say);
		return "/WEB-INF/jsp/sayPage.jsp";
	}
	
	//���û������۲��뵽say����
	@RequestMapping("/say.action")
	public String say(Say say) throws Exception{
		System.out.println("�û�id��"+say.getId());
		System.out.println("����tip���û�id��"+say.getUserid());
		System.out.println("���۵�tipid��"+say.getTipid());
		System.out.println("���۵������ǣ�"+say.getDetail());
		String detail = say.getDetail();
		detail = HtmlFilter.filter(detail);	//�����۵����ݽ���html�ı�ת��
		
		System.out.println("ת���������ǣ�"+detail);
		say.setDetail(detail);	//����������������
		sayService.say(say);	//���������ݷ������ݿ���
		//�ض���alltiplist.jsp
		return "redirect:/gotoGetTipList.action";
	}
	//�ض���
	@RequestMapping("/gotoGetTipList.action")
	public String gotoGetTipList(){
		return "/getTipList.action";
	}
	
	//�鿴���е�����,����tipid
	@RequestMapping("/getAllSay.action")
	public ModelAndView getAllSay(int tipid) throws Exception{
		List<Say> sayList = sayService.getAllSay(tipid);
	/*	if(sayList != null){
			for(Say s:sayList){
				System.out.println(s.getId()+"������"+s.getUserid()+"����tip,tipid:"+s.getTipid()+",���۵�������"+s.getDetail());
			}
		}*/
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("sayList",sayList);
		modelAndView.setViewName("/WEB-INF/jsp/getAllSay.jsp");
		return modelAndView;
	}
	
	
	public SayService getSayService() {
		return sayService;
	}

	public void setSayService(SayService sayService) {
		this.sayService = sayService;
	}
	
	
	
	
	
}
