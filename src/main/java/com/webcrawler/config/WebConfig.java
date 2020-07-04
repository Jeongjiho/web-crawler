package com.webcrawler.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@ComponentScan( basePackages = { "com.webcrawler" }, useDefaultFilters = true )
@EnableTransactionManagement( order = 1 )
public class WebConfig extends WebMvcConfigurationSupport {

	@Autowired
	ApplicationContext applicationContext;

	@SuppressWarnings( "deprecation" )
	@Bean
	public ViewResolver contentNegotiatingViewResolver() {

		ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
		List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>( 1 );

		BeanNameViewResolver bnv = new BeanNameViewResolver();
		bnv.setOrder( 1 );
		viewResolvers.add( bnv );

		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix( "/WEB-INF/views/site/webcrawler/" );
		resolver.setSuffix( ".jsp" );
		resolver.setOrder( 2 );
		viewResolvers.add( resolver );

		viewResolver.setViewResolvers( viewResolvers );

		Map<String, String> mediaTypes = new HashMap<String, String>();
		mediaTypes.put( "atom", "application/atom+xml" );
		mediaTypes.put( "html", "text/html" );
		mediaTypes.put( "json", "application/json" );
		mediaTypes.put( "text", "text/plain" );

		viewResolver.setMediaTypes( mediaTypes );

		List<View> defaultViews = new ArrayList<View>( 1 );
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setContentType( "application/json;charset=UTF-8" );
		defaultViews.add( jsonView );
		viewResolver.setDefaultViews( defaultViews );

		return viewResolver;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport# addResourceHandlers(org. springframework .web.servlet.config.annotation.ResourceHandlerRegistry) <resources mapping="/resources/**" location="/resources/" />
	 */
	@Override
	protected void addResourceHandlers( ResourceHandlerRegistry registry ) {
		registry.addResourceHandler( "/resources/**" ).addResourceLocations( "/resources/" );
		registry.setOrder(0).addResourceHandler( "/robots.txt" ).addResourceLocations( "/" );
	}
	
}
