package com.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.board.interceptor.Authintercepter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    
	@Autowired
	private Authintercepter authintercepter;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// authintercepter를 동작시킬 때 모든 페이지(/**)를 대상으로 
		// redirect 한다
		// http://localhost:9090 밑에 모든 파일
		// 제외 "/css/**", "/img/**","/js/**" 경로는 interceptor의 대상아님
		//.addPathPatterns("/Board/**")
		// http://localhost:9090/Board/** 밑에 모든 파일
		registry.addInterceptor(authintercepter)
		      //   .addPathPatterns("/**")
		         .addPathPatterns("/BoardPaging/**","/Board/**")
		         .excludePathPatterns("/css/**","/img/**","/js/**");
		WebMvcConfigurer.super.addInterceptors(registry);
		
		
	}
   
	
	
}
