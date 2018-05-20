package com.spring.scrapper.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private static final String LOGIN = "loginVO";
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		Object userVO = modelMap.get("userVO");
		
		if(userVO != null){
			session.setAttribute(LOGIN, userVO);
			LOGGER.info("LOGIN SUCCESS ");
			String responseURL = "/scrapper";
			
			// AuthInterceptor의 preHandle이 끝나고 난 후에 대한 처리
			if(session.getAttribute("beforeURL")!=null){
				responseURL = (String) session.getAttribute("beforeURL");
			}
			response.sendRedirect(responseURL);
		}
		else{
			LOGGER.info("LOGIN FAIL ");
		}
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		Object loginVO = session.getAttribute(LOGIN);
		if(loginVO != null){
			LOGGER.info("preHandle, clear LOGIN INFORMATION... ");
			session.removeAttribute(LOGIN);
		}
		return true;
	}
	
}
