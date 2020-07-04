package com.webcrawler.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer# onStartup(javax.servlet. ServletContext )
	 */
	@Override
	public void onStartup( ServletContext servletContext ) throws ServletException {
		super.onStartup( servletContext );
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.support. AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses ()
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.support. AbstractAnnotationConfigDispatcherServletInitializer# getServletConfigClasses ()
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer# getServletMappings()
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer# getServletFilters()
	 */
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding( "UTF-8" );

		return new Filter[] { characterEncodingFilter };
	}
}
