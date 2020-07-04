package com.webcrawler.framework.constract;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.webcrawler.framework.base.Constract;

public enum CommonConstract implements Constract{

	COMMON_RESULT_CODE_SUCCESS( "SUCCESS", "성공적으로 작업을 수행하였습니다." ),
	COMMON_RESULT_CODE_ERROR( "ERROR", "작업 중 에러가 발생했습니다." ),
	COMMON_RESULT_CODE_EMPTY( "EMPTY", "데이터가 없습니다." )
	;
	
	public static final String CRAWLING_TYPE_REMOVE_HTML_TAG = "R";
	public static final String CRAWLING_TYPE_FULL_TEXT = "F";
	
	private static final Map<String, String> itemToValue = new HashMap<String, String>();
	private String key;
	private String value;

	static {
		for (CommonConstract value : values()) {
			itemToValue.put(value.getKey(), value.getValue());
		}
	}

	public static String getValue( String key ) {
		if ( itemToValue.containsKey(key) ) {
			return itemToValue.get(key);
		}
		return "";
	}

	public static String getKey( String value ) {

		String key = "";

		Iterator<Map.Entry<String, String>> iterator = itemToValue.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			if ( entry.getValue().equals(value) ) {
				key = entry.getKey();
				break;
			}
		}

		return key;

	}

	CommonConstract( String key, String value ) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setKey( String key ) {
		this.key = key;
	}

	@Override
	public void setValue( String value ) {
		this.value = value;
	}

}
