package com.webcrawler.web;

import java.io.IOException;
import java.net.IDN;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import com.webcrawler.config.CommaBaseModelTest;


public class WebCrawlerTest extends CommaBaseModelTest {
	
	@Test
	public void 한글도메인_테스트() throws IOException, URISyntaxException {
		
		
		//http://xn--ob0b570cnxa.xn--3e0b707e/
		//String requestUrl = "http://공정위.한국/";
		//https://www.baidu.com/s?cl=3&tn=baidutop10&fr=top1000&wd=曝川师大副院长对女教师强摸吻啃&rsv_idx=2&rsv_dl=fyb_n_homepage&hisfilter=1
		String requestUrl = "https://www.baidu.com/s?cl=3&tn=baidutop10&fr=top1000&wd=曝川师大副院长对女教师强摸吻啃&rsv_idx=2&rsv_dl=fyb_n_homepage&hisfilter=1";
		
		URL url= new URL(requestUrl);
		URI uri = new URI(url.getProtocol(), url.getUserInfo(), IDN.toASCII(url.getHost()), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
		String correctEncodedURL=uri.toASCIIString(); 
		System.out.println(correctEncodedURL);

		Document doc = Jsoup.connect(correctEncodedURL).get();
		
		System.out.println( doc.html() );
		
		
	}
	
	@Test
	public void 클롤링_테스트() {
		
		String url = "https://front.wemakeprice.com/";
		
		int printUnit = 10;
		
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println( "에러발생" );
		}
		
		String html = doc.html();
		
		System.out.println( html );
		
		//System.out.println( html );
		
		String removeHtmlTagString = html.replaceAll("\\<.*?\\>", "");
		//System.out.println( removeHtmlTagString );
		
		String removeGapString = removeHtmlTagString.replaceAll("(\r\n|\r|\n|\n\r|\\p{Z}|\\t)", "");
		//System.out.println( removeGapString );
		
		String resultString = removeGapString.replaceAll("[^a-zA-Z0-9]", "");
		//System.out.println( resultString );
		
		String alphabetStr = removeGapString.replaceAll("[^a-zA-Z]", "");
		String numberStr = removeGapString.replaceAll("[^0-9]", "");
		
		//System.out.println( alphabetStr );
		//System.out.println( numberStr );
		
		String[] alphabetStrArr = alphabetStr.split("");
		String[] numberStrArr = numberStr.split("");
		
		alphabetStrArr = Arrays.stream( alphabetStrArr ).sorted( (str1, str2) -> {
			int res = str1.compareToIgnoreCase( str2 );
			return res == 0 ? str1.compareTo( str2 ) : res;
		} ).toArray(String[]::new);
		
		
		//Arrays.sort( alphabetArr );
		Arrays.sort( numberStrArr );
		
		/*
		 * for( char c : alphabetArr ) { System.out.println( c ); }
		 */
		
		//for( String str : numberStrArr ) { System.out.println( str ); }

		StringBuilder resultStr = new StringBuilder();
		
		int loopNum = alphabetStrArr.length;
		if( loopNum < numberStrArr.length ) {
			loopNum = numberStrArr.length;
		}
		
		boolean isAlphabetContinue = true;
		boolean isNumberContinue = true;
		
		for( int i = 0; i < loopNum; i++ ) {
			
			if( isAlphabetContinue ) {
				resultStr.append( alphabetStrArr[i] );
				
				if( alphabetStrArr.length - 1 <= i ) {
					isAlphabetContinue = false;
				}
				
			}
			
			if( isNumberContinue ) {
				resultStr.append( numberStrArr[i] );
				
				if( numberStrArr.length - 1 <= i ) {
					isNumberContinue = false;
				}
				
			}
			
		}
		
		//System.out.println( resultStr.toString() );
		
		int divisionResult = resultStr.length() / printUnit;
		List<String> resultList = new ArrayList<String>();
		String result = resultStr.toString();
		
		for( int i = 0; i < divisionResult; i++ ) {
			
			resultList.add( result.substring( 0, printUnit ) );
			result = result.substring( printUnit );
			
		}
		
		int checkNum = 0;
		for( String str : resultList ) {
			System.out.println( str );
			checkNum += str.length();
		}
		
		//System.out.println( "몫 갯수 ::: " + resultList.size() );
		
		//System.out.println( "나머지 ::: " + result );
		//System.out.println( "나머지 갯수 ::: " + result.length() );
		
		//System.out.println( "원래 갯수 ::: " + resultStr.length() );
		
		//System.out.println( "갯수1 ::: " + checkNum );
		//System.out.println( "갯수2 ::: " + result.length() );
		
	}
	
}
		
