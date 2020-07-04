package com.webcrawler.framework.util;

import java.io.IOException;
import java.net.IDN;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.StringUtils;

public class WebCrawlerUtils {
	
	public static final String REG_EXP_HTML_TAG_ = "\\<.*?\\>";
	public static final String REG_EXP_ALPHABET_AND_NUMBER = "[^a-zA-Z0-9]";
	public static final String REG_EXP_ALPHABET = "[^a-zA-Z]";
	public static final String REG_EXP_NUMBER = "[^0-9]";
	
	private WebCrawlerUtils() {}
	
	public static String getPunycodeEncoding( String requestUrl ) throws MalformedURLException, URISyntaxException {
		
		if( StringUtils.isEmpty( requestUrl ) ) {
			return "";
		}
		
		URL url= new URL(requestUrl);
		URI uri = new URI(url.getProtocol(), url.getUserInfo(), IDN.toASCII(url.getHost()), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
		String correctEncodedURL = uri.toASCIIString();
		
		return correctEncodedURL;
		
	}
	
	/**
	 * 
	 * 해당 URL을 접속하여 HTML문서를 받아 String으로 반환해줍니다.
	 * 
	 * @param url String
	 * @return html String
	 * @throws IOException
	 * @author JeongJiHo
	 * @since 2020-07-03
	 */
	public static String getCrawlingHtmlString( String url ) throws IOException {
		
		if( StringUtils.isEmpty( url ) ) {
			return "";
		}

		Document doc = Jsoup.connect( url ).get();
		
		if( doc == null ) {
			return "";
		}
		
		return doc.html();
		
	}
	
	/**
	 * 
	 * html 태그가 포함된 문자열에서 HTML태그를 제거하고 문자열을 반환합니다.
	 * 
	 * @param htmlString String
	 * @return String
	 * @author JeongJiHo
	 * @since 2020-07-03
	 */
	public static String removeHtmlTag( String htmlString ) {
		
		if( StringUtils.isEmpty( htmlString ) ) {
			return "";
		}
		
		return htmlString.replaceAll(REG_EXP_HTML_TAG_, "");
		
	}
	
	/**
	 * 
	 * 문자열 중에 영어와 숫자만 반환합니다.
	 * 
	 * @param text String
	 * @return AlphabetWithNumber String
	 * @author JeongJiHo
	 * @since 2020-07-03
	 */
	public static String getAlphabetWithNumber( String text ) {
		
		if( StringUtils.isEmpty( text ) ) {
			return "";
		}
		
		return text.replaceAll(REG_EXP_ALPHABET_AND_NUMBER, "");
		
	}
	
	/**
	 * 
	 * 문자열 중에 영어만 반환합니다.
	 * 
	 * @param text String
	 * @return alphabet String
	 * @author JeongJiHo
	 * @since 2020-07-03
	 */
	public static String getAlphabet( String text ) {
		
		if( StringUtils.isEmpty( text ) ) {
			return "";
		}
		
		return text.replaceAll(REG_EXP_ALPHABET, "");
		
	}
	
	/**
	 * 
	 * 문자열 중에 숫자만 반환합니다.
	 * 
	 * @param text String
	 * @return number String
	 * @author JeongJiHo
	 * @since 2020-07-03
	 */
	public static String getNumber( String text ) {
		
		if( StringUtils.isEmpty( text ) ) {
			return "";
		}
		
		return text.replaceAll(REG_EXP_NUMBER, "");
		
	}
	
}
