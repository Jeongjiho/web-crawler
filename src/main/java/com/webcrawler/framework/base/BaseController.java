package com.webcrawler.framework.base;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

	public static Map<String, Object> returnResultMap( Constract constract ) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put(Constract.CODE, constract.getKey());
		resultMap.put(Constract.MESSAGE, constract.getValue());
		
		return resultMap;
		
	}
	
	public static Map<String, Object> returnResultMap( Constract constract, Object dataObj ) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put(Constract.CODE, constract.getKey());
		resultMap.put(Constract.MESSAGE, constract.getValue());
		resultMap.put(Constract.DATA, dataObj);
		
		return resultMap;
		
	}
	 

}
