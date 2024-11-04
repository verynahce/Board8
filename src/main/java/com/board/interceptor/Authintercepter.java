package com.board.interceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Component
public class Authintercepter implements HandlerInterceptor {
	
	
	@Override
	//전처리
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String requestURI = request.getRequestURI();
		String menu_id =request.getParameter("menu_id");
		int nowpage =1;
		
	    System.out.println("요청주소 : " + requestURI);
	
	    // /Users/LoginForm,  //Users/Login 를 제외
	    if(requestURI.contains("/Users/LoginForm")) {
	    	return true;
	    }
	    if(requestURI.contains("/Users/Login")) {
	    	return true;
	    }
	    // /Board/List  BoardPaging/List
	    String uri =requestURI.split("/")[1];
	    
	    HttpSession session = request.getSession();
	    //사용자가 로그인을 하면 세션메모리에 저장된 user
	    Object login = session.getAttribute("login");
	    System.out.println(login);
	    if(login == null) {


	    	String fmt ="/Users/LoginForm?uri=%s&menu_id=%s&nowpage=%d" ;
	    	String loc = String.format(fmt, uri,menu_id,nowpage);
	    	//로그인되어 있지 않다. 로그인페이지로 이동
	    	response.sendRedirect(loc);
	    	return false;
	    }
	    //preHandle의 return문의 의미는?
	    //컨트롤롤의 요청 url로 가도되나 안되나는 결정한다.
	    // return: 컨트롤러 url로 가게 된다 
	    
	    
		return true;
	}


	/*
    //후처리
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("===========postHandle()==========");

		System.out.println("==================================");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
*/
	
	
	
}
