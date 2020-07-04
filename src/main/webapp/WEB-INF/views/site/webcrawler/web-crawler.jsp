<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Web-Crawler</title>
		<link rel="stylesheet" href="/resources/css/lib/bootstrap.min.css">
		<link rel="stylesheet" href="/resources/css/common/common.css">
	</head>
	<body>
		<header>
			<div class="navbar navbar-dark bg-dark shadow-sm">
				<div class="container d-flex justify-content-between">
					<a href="/" class="navbar-brand d-flex align-items-center">
						<strong>Web-Crawler</strong>
					</a>
				</div>
			</div>
		</header>

		<main role="main">
			<section class="jumbotron text-center">
				<div class="container">
					<h1>Web-Crawler</h1>
					<p class="lead text-muted">URL을 입력한 후 Type을 선택, 출력 묶음 단위를 입력하시고<br/>출력 버튼을 클릭하시면 해당 묶음 단위에 맞는<br/>몫과 나머지를 보여줍니다.</p>
				</div>
			</section>
	
			<div class="album py-5 bg-light">
				<div class="container">
				
					<h4 class="mb-3">URL</h4>
					<div class="row">
						<div class="col-md-10 auto-center mb-60px">
							<input type="text" id="requestUrl" class="form-control" placeholder="https://www.url.com" autocomplete="off">
						</div>
					</div>
					
					<h4 class="mb-3">Type</h4>
					<div class="row">
						<div class="col-md-10 auto-center mb-60px">
							<select id="type" class="custom-select d-block w-100">
								<option value="R">HTML 태그제외</option>
								<option value="F">Text 전체</option>
							</select>
						</div>
					</div>
					
					<h4 class="mb-3">출력 묶음 단위</h4>
					<div class="row">
						<div class="col-md-10 auto-center mb-90px">
							<input type="text" id="printUnit" class="form-control" placeholder="Ex) 10" autocomplete="off">
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-10 auto-center mb-30px">
							<button id="btnPrint" class="btn btn-primary btn-lg btn-block" type="button">Print</button>
						</div>
					</div>
					
					<hr class="mb-60px"/>
					
					
					<h4 class="mb-3">몫</h4>
					<p>(글자수 : <span id="quotientCnt">0</span>)</p>
					<div class="row">
						<div class="col-md-10 auto-center mb-60px">
							<pre id="quotientArea" class="result-box"></pre>
						</div>
					</div>
					
					<h4 class="mb-3">나머지</h4>
					<p>(글자수 : <span id="remainderCnt">0</span>)</p>
					<div class="row">
						<div class="col-md-10 auto-center mb-60px">
							<pre id="remainderArea" class="result-box"></pre>
						</div>
					</div>
					
				</div>
			</div>
		</main>
		
		<footer class="text-muted">
			<div class="container">
				<p>Phone 010-7711-0325</p>
				<p>Email claraster84@gmail.com</p>
				<p>COPYRIGHT@JeongJiHo</p>
			</div>
		</footer>
		
		
		<%@ include file="/WEB-INF/views/site/common/js-include.jsp"%>
		<script type="text/javascript" src="/resources/js/webcrawler/web-crawler.js"></script>
		
		<script type="text/javascript">
		$( document ).ready(function() {
			webCrawler.init();
		});
		</script>
	</body>
</html>