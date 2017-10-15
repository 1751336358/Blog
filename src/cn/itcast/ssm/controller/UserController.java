package cn.itcast.ssm.controller;


//ʹ��ע�⿪����������Ҫ����İ�
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.BASE64Encoder;

import cn.itcast.ssm.pojo.Message;
import cn.itcast.ssm.pojo.User;
import cn.itcast.ssm.service.UserService;
import cn.itcast.ssm.util.CreateMD5;

@Controller("UserController01")
public class UserController{
	@Resource(name="userService")
	private UserService userService;
	
	
	//����ע����û���ӵ����ݿ���
	@RequestMapping("/insertUser.action")
	public String  insertUser(User u,HttpSession session) throws Exception{
		//������תΪMD5��
		String s = CreateMD5.getMD5(u.getPassword());
		u.setPassword(s);
		List<User> userList = userService.findUserByUsername(u);
		if(userList == null || userList.size()==0){//�û��������ݿ��в�����
			session.setAttribute("user",u);
			userService.insertUser(u);	//ע����û������뵽���ݿ���
			return "redirect:/registerSuccess.action";	//�ض���ע��ɹ�ҳ��
		}else{
			return "redirect:/register.action"; //�ض���ע��ҳ������ע��
		}
		
	}
	//Ajax����ע���û����Ƿ�Ϸ�
	@RequestMapping("/testRegisterUserName.action")
	public void testRegisterUserName(HttpServletRequest request,HttpServletResponse response) throws Exception{
		int ucount = userService.testUsername(request);
		Message m = new Message();
		if(ucount != 0){
			m.setMsg("�û����Ѵ���");
		}
		JSONObject json = JSONObject.fromObject(m);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
//		response.getWriter().flush();
		
	}
	//Ajax���Ե�¼�û����Ƿ���ȷ
	@RequestMapping("/testLoginUserName.action")
	public void testLoginUserName(HttpServletRequest request,HttpServletResponse response) throws Exception{

		int ucount = userService.testUsername(request);
		Message m = new Message();
		if(ucount == 0){
			m.setMsg("�û���������");
		}
		JSONObject json = JSONObject.fromObject(m);
		System.out.println("����תjson��"+json.toString());
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
		response.getWriter().flush();
	}
	//Ajax���Ե�¼�����Ƿ���ȷ
	@RequestMapping("/testLoginPassword.action")
	public void testLoginPassword(HttpServletRequest request,HttpServletResponse response) throws Exception{
		int pcount = userService.testPassword(request);
		Message m = new Message();
		if(pcount == 0){
			m.setMsg("�����������");
		}
		JSONObject json = JSONObject.fromObject(m);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json.toString());
		response.getWriter().flush();
	}
	//��½���鿴��ǰ�û��Ƿ������ݿ��д���
	@RequestMapping("/findUser.action")
	public ModelAndView findUser(User u,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		//�����ｫpasswordת�루MD5��
		String s = CreateMD5.getMD5(u.getPassword());
		u.setPassword(s);
		User user = userService.findUsernameAndPassword(u);//����û�������û����������Ƿ���ȷ
		if(user != null){//������û�����������ȷ
			System.out.println("��½���û�id��"+user.getId());
			System.out.println("��½���û���username��"+user.getUsername());
			session.setAttribute("userid",user.getId());	//����½���û�id���õ�session��
			session.setAttribute("username",user.getUsername());//����½���û�username���õ�session��
			//��Ҫ�����û������ӷ�����ҳ���
			List<String> classList = userService.getClassByUserId(user);
			
			if(classList !=  null){
				session.setAttribute("classList", classList);//�����û���tip�������session��
				Cookie cookie = new Cookie("JSESSIONID",session.getId());
				cookie.setPath("/Blog");
				cookie.setMaxAge(60*20);
				response.addCookie(cookie);
			}	
			modelAndView.setViewName("/WEB-INF/jsp/loginSuccess.jsp");//��ת����½�ɹ�ҳ��
		}else{
			Message m = new Message();
			modelAndView.setViewName("/WEB-INF/jsp/login.jsp");//��ת����½ҳ�����µ�½
		}
		return modelAndView;
	}
	
	//��ת���û���¼ҳ��
	@RequestMapping("/login.action")
	public ModelAndView login() throws Exception{
		System.out.println("�û���½");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/WEB-INF/jsp/login.jsp");
		return modelAndView;	
	}
	
	//��ת���û�ע��ҳ��
	@RequestMapping("/register.action")
	public ModelAndView register() throws Exception{
		System.out.println("�û�ע��");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/WEB-INF/jsp/register.jsp");
		return modelAndView;	
	}
	
	//��ת���û�ע��ɹ�ҳ��
	@RequestMapping("/registerSuccess.action")
	public ModelAndView registerSuccess() throws Exception{
		System.out.println("�û�ע��");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/WEB-INF/jsp/registerSuccess.jsp");
		return modelAndView;	
	}
	
	//ע��
	@RequestMapping("/logout.action")
	public String logout(HttpServletRequest request) throws Exception{
		request.getSession(true).removeAttribute("userid");
		return "index.jsp";	//�ض����û���½ҳ��
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}	
	
}
