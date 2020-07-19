package com.webcrawler.web;

import java.io.Serializable;

public class WebVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6640500874707651002L;
	
	private String name;
	private transient String pwd;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Override
	public String toString() {
		return "WebVO [name=" + name + ", pwd=" + pwd + "]";
	}
	
}
