package com.project.base;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class BaseApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}
	
	@Override 
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
	    servletContext.addListener(new RequestContextListener());
	}
	
}
