package com.cbsbankui;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.cbsbankui.MyServletConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// we must show spring the location of ServletConfig class.
		return new Class[] {MyServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		 //start loading from the first request / 
		return new String[] {"/"};
	}
}
