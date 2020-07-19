package com.webcrawler.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webcrawler.framework.base.BaseController;
import com.webcrawler.framework.constract.CommonConstract;
import com.webcrawler.framework.util.WebCrawlerUtils;

@Controller
public class WebCrawlerController extends BaseController {
	
	static final Logger logger = LoggerFactory.getLogger(WebCrawlerController.class);

	@RequestMapping( value = "/web-crawler", method = RequestMethod.GET )
	public void webCrawler() {
		
	}
	
	@RequestMapping( value = "/web-crawler.json", method = RequestMethod.GET )
	public @ResponseBody Map<String, Object> webCrawler( @RequestParam( value = "requestUrl", required = true ) String requestUrl, @RequestParam( value = "type", required = true ) String type, @RequestParam( value = "printUnit", required = true ) Integer printUnit ) throws IOException, ClassNotFoundException {

		String correctEncodedURL = "";
		
		try {
			correctEncodedURL = WebCrawlerUtils.getPunycodeEncoding(requestUrl);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			logger.error( e1.getMessage() );
			return returnResultMap(CommonConstract.COMMON_RESULT_CODE_ERROR);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
			logger.error( e1.getMessage() );
			return returnResultMap(CommonConstract.COMMON_RESULT_CODE_ERROR);
		}
		
		String htmlStr = "";
		
		try {
			htmlStr = WebCrawlerUtils.getCrawlingHtmlString(correctEncodedURL);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println( e.getMessage() );
			logger.error( "[" + CommonConstract.COMMON_RESULT_CODE_NOT_CONNECT.getKey() + "] ::: " + e.getMessage() );
			return returnResultMap(CommonConstract.COMMON_RESULT_CODE_NOT_CONNECT);
		}
		
		if( StringUtils.isEmpty( htmlStr ) ) {
			return returnResultMap(CommonConstract.COMMON_RESULT_CODE_EMPTY);
		}
		
		if( CommonConstract.CRAWLING_TYPE_REMOVE_HTML_TAG.equals( type ) ) {
			htmlStr = WebCrawlerUtils.removeHtmlTag(htmlStr);
		}
		
		String alphabetWithNumberStr = WebCrawlerUtils.getAlphabetWithNumber(htmlStr);
		
		if( StringUtils.isEmpty( alphabetWithNumberStr ) ) {
			return returnResultMap(CommonConstract.COMMON_RESULT_CODE_EMPTY);
		}
		
		String alphabetStr = WebCrawlerUtils.getAlphabet(alphabetWithNumberStr);
		String numberStr = WebCrawlerUtils.getNumber(alphabetWithNumberStr);
		
		String[] alphabetStrArr = alphabetStr.split("");
		String[] numberStrArr = numberStr.split("");
		
		alphabetStrArr = Arrays.stream( alphabetStrArr ).sorted( (str1, str2) -> {
			int res = str1.compareToIgnoreCase( str2 );
			return res == 0 ? str1.compareTo( str2 ) : res;
		} ).toArray(String[]::new);
		
		Arrays.sort( numberStrArr );
		
		StringBuilder sortStr = new StringBuilder();
		
		int loopNum = alphabetStrArr.length;
		if( loopNum < numberStrArr.length ) {
			loopNum = numberStrArr.length;
		}
		
		boolean isAlphabetContinue = true;
		boolean isNumberContinue = true;
		
		for( int i = 0; i < loopNum; i++ ) {
			
			if( isAlphabetContinue ) {
				sortStr.append( alphabetStrArr[i] );
				
				if( alphabetStrArr.length - 1 <= i ) {
					isAlphabetContinue = false;
				}
				
			}
			
			if( isNumberContinue ) {
				sortStr.append( numberStrArr[i] );
				
				if( numberStrArr.length - 1 <= i ) {
					isNumberContinue = false;
				}
				
			}
			
		}
		
		int divisionResult = sortStr.length() / printUnit;
		LinkedList<String> quotientList = new LinkedList<String>();
		String resultStr = sortStr.toString();
		
		for( int i = 0; i < divisionResult; i++ ) {
			quotientList.add( resultStr.substring(0, printUnit) );
			resultStr = resultStr.substring(printUnit);
		}
		
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("quotientList", quotientList);
		dataMap.put("remainder", resultStr);
		
		WebVO vo = new WebVO();	
		
		vo.setName("이름");
		vo.setPwd("1234");
		
		System.out.println( vo );
		
		 byte[] serializedVO;
		    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
		        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
		            oos.writeObject(vo);
		            // serializedMember -> 직렬화된 member 객체
		            serializedVO = baos.toByteArray();
		        }
		    }
		
		String seri = Base64.getEncoder().encodeToString(serializedVO);
		
		System.out.println( seri );
		
		WebVO vo2 = null;
	    byte[] serializedMember = Base64.getDecoder().decode(seri);
	    try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedMember)) {
	        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
	            // 역직렬화된 Member 객체를 읽어온다.
	            Object objectMember = ois.readObject();
	            vo2 = (WebVO) objectMember;
	            System.out.println(vo2);
	        }
	    }
		
		
		return returnResultMap(CommonConstract.COMMON_RESULT_CODE_SUCCESS, vo2);
		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		WebVO vo = new WebVO();
		
		vo.setName("이름");
		vo.setPwd("1234");
		
		System.out.println( vo );
		
		 byte[] serializedVO;
		    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
		        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
		            oos.writeObject(vo);
		            // serializedMember -> 직렬화된 member 객체
		            serializedVO = baos.toByteArray();
		        }
		    }
		
		String seri = Base64.getEncoder().encodeToString(serializedVO);
		
		System.out.println( seri );
		
	    byte[] serializedMember = Base64.getDecoder().decode(seri);
	    try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedMember)) {
	        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
	            // 역직렬화된 Member 객체를 읽어온다.
	            Object objectMember = ois.readObject();
	            WebVO vo2 = (WebVO) objectMember;
	            System.out.println(vo2);
	        }
	    }
		
		
		
	}
	
}
