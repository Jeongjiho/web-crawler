var webCrawler = {
	
	init : function() {
		webCrawler.bind();
	},
	
	bind : function() {
		
		// 숫자만 입력
		common.onlyNumber( $("#printUnit") );
		
		$("#btnPrint").on("click", function() {
			webCrawler.callPrint();
		});
		
	},
	
	validation : function() {
		
		if( !$("#requestUrl").val() ) {
			alert("URL을 입력해주세요.");
			$("#requestUrl").focus();
			return false;
		}
		
		if( !CONSTRACT.REG_EXP.URL.test($("#requestUrl").val().toLowerCase()) ) {
			alert("URL형식을 입력해주세요.");
			$("#requestUrl").focus();
			return false;
		}
		
		if( !$("#type").val() ) {
			alert("타입을 선택해주세요.");
			$("#type").focus();
			return false;
		}
		
		if( !$("#printUnit").val() ) {
			alert("출력 묶음 단위를 입력해주세요.");
			$("#printUnit").focus();
			return false;
		}
		
		return true;
		
	},
	
	getWebCralwerParam : function() {
		
		var requestUrl = $("#requestUrl").val();
		var type = $("#type").val();
		var printUnit = $("#printUnit").val();
		
		return {
			"requestUrl" : requestUrl,
			"type" : type,
			"printUnit" : printUnit
		}
		
	},
	
	callPrint : function() {
		
		if( !webCrawler.validation() ) {
			return false;
		}
		
		var param = webCrawler.getWebCralwerParam();
		
		webCrawler.initDisplay();
		
		$.ajax({
			url: "/web-crawler.json",
			method: "GET",
			data: param,
			success : function( data ) {
				try {
					common.checkResultData(data, webCrawler.display);
				}
				catch (ex) {
					console.error(ex);
					alert("에러가 발생했습니다.\n관리자에게 문의하세요.");
				}
				
			}
		});
		
	},
	
	
	display : function( data ) {
		var quotientList = data.quotientList;
		var remainder = data.remainder;
		
		var quotientHtml = "";
		$.each( quotientList, function(index, item) {
			
			if( quotientList.length - 1 == index ) {
				quotientHtml += item;
			}
			else {
				quotientHtml += item + "<br/>";
			}
			
		} );
		
		var printUnit = $("#printUnit").val();
		$("#quotientArea").html( quotientHtml );
		$("#remainderArea").text( remainder );
		$("#quotientCnt").text( quotientList.length * printUnit );
		$("#remainderCnt").text( remainder.length );
		
	},
	
	initDisplay : function() {
		
		$("#quotientArea").empty();
		$("#remainderArea").empty();
		$("#quotientCnt").text( "0" );
		$("#remainderCnt").text( "0" );
		
	}
	
};