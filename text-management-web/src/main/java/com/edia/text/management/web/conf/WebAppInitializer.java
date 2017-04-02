package com.edia.text.management.web.conf;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer{

	private static final Logger LOG = Logger.getLogger(WebAppInitializer.class);
	
	private static final String PACKAGE_WEB_CONFIGIRATION = "com.edia.text.management.web" ;
	
	public void onStartup(ServletContext servletContext) throws ServletException {
		LOG.info("Initializing Web Layer");
		
		WebApplicationContext applicationContext = getContext();
		
		servletContext.addListener(new ContextLoaderListener(applicationContext));
		
		ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcherServlet", new DispatcherServlet(applicationContext));
		dispatcherServlet.setLoadOnStartup(1);
		dispatcherServlet.addMapping("/");
		
	}

	private AnnotationConfigWebApplicationContext getContext(){
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation(PACKAGE_WEB_CONFIGIRATION);
		return context;
	}
}
