package com.webcrawler.framework.base;

public interface Constract {
	
	public static final String CODE = "CODE";
	
	public static final String MESSAGE = "MESSAGE";
	
	public static final String DATA = "DATA";

	public String getKey();

	public String getValue();

	public void setKey( String key );

	public void setValue( String value );
	
}
