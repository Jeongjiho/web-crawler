var common = {
	
	setAjax : function() {
		
		$.ajaxSetup({
			beforeSend : function() {
				common.showLoading();
			},
			complete : function() {
				common.hideLoading();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert( "[" + jqXHR.status + "-" + textStatus + "]에러가 발생하였습니다." );
			}
		});
		
	},

	onlyNumber : function( $el ) {
		
		$el.on( "keyup", function() {
			
			var numPattern = /[^0-9\.]+/g;
			var text = $( this ).val();
			text = text.replace( numPattern, "" );
			
			$( this ).val( text );
			
		} );
		
		$el.on( "keydown", function() {
			
			var numPattern = /[^0-9\.]+/g;
			var text = $( this ).val();
			text = text.replace( numPattern, "" );
			
			$( this ).val( text );
			
		} );
		
		$el.on( "keypress", function() {
			
			var numPattern = /[^0-9\.]+/g;
			var text = $( this ).val();
			text = text.replace( numPattern, "" );
			
			$( this ).val( text );
			
		} );
		
		$el.on( "blur", function() {
			
			var numPattern = /[^0-9\.]+/g;
			var text = $( this ).val();
			text = text.replace( numPattern, "" );
			
			$( this ).val( text );
			
		} );
		
	},
	
	showLoading : function() {
		var loadingHtml = "<div id=\"pageLoading\" class=\"page-loading\"><img alt=\"loading\" src=\"/resources/image/common/loading.gif\"></div>";
		$("main").after( loadingHtml );
	},
	
	hideLoading : function() {
		$("#pageLoading").remove();
	},
	
	checkResultData : function( data, callback ) {
		
		if( $.isEmptyObject(data) ) {
			alert("결과값이 없습니다.\n관리자에게 문의하세요.");
			return false;
		}
		
		if( data[CONSTRACT.CODE] != CONSTRACT.SUCCESS ) {
			alert(data[CONSTRACT.MESSAGE]);
		}
		else {
			if ( callback != null && typeof callback == "function" ) {
				callback( data[CONSTRACT.DATA] );
			}
			else {
				throw new Error("callback은 function타입으로 보내주세요.");
			}
		}
		
	}
	
};