package cn.itcast.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

public class HandlerInterceptor implements
		org.springframework.web.servlet.HandlerInterceptor {

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		//��Ҫ�������۵�url
		String url = request.getRequestURI();
		System.out.println("���ص�url��:"+url);
		if(url.startsWith("/Blog/gotoSayPage.action")){	//���ص�url�����۵�url
			//˵���Ѿ���½
			HttpSession session =  request.getSession();
			Object obj = session.getAttribute("userid");	
			if(obj != null){
				System.out.println("�Ѿ���½");
				return true;
			}else{
				System.out.println("δ��½");
				//��ת����½ҳ��
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return false;
			}
		}
		//������ַ������
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	

}
