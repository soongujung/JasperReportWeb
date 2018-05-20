package com.spring.scrapper.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter{
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);

	private void saveBefore(HttpServletRequest request){
		String url = request.getRequestURI();
		String queryString = request.getQueryString();
		
		if(queryString!=null){
			url = url + "?" + queryString;
		}
		
		request.getSession().setAttribute("beforeURL", url);
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginVO") == null){
			// redirect 전 미리 이전 경로를 저장 
			saveBefore(request);
			response.sendRedirect("/scrapper/user/login");
			// 로그인 성공이후에는 "beforeURL"에 저장해놓은 데이터로 이동한다.
			// 따라서 LoginInterceptor에 해당 redirect 기술 
			return false;
		}
		
		return true;
	}
}
