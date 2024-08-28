<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">
		GET 방식
	</div>
	
	<div class="card-body">
		<div>
			<a href="getAtag">링크1</a><br/> <!-- 현재 파일의 폴더 경로 까지 / geAtag-->
			<a href="getAtag?bno=5&bkind=free">링크2</a><br/> <!-- getAtag?bno=5&kind=free <- 시작 행에 들어감 -->
		</div>
		
		<hr/>
		
		<div class="mt-2">
			<form method="get" action="getFormTag">
				<div class="mb-3">
					<label for="bno" class="form-label">게시물 번호</label>
					<input type="number" class="form-control" id="bno" name="bno" value="5"> <!-- 파라미터 이름을 name이라는 속성으로 주면 된다. -->
				</div>
				<div class="mb-3">
					<label for="bkind" class="form-label">게시물 종류</label>
					<input type="text" class="form-control" id="bkind" name="bkind" value="free">
				</div>
				<input type="submit" value="제출" class="btn btn-info btn-sm"/>
			</form>
		</div>
		
		<hr/>
		
		<div class="mt-2">
			<button onclick="requestGet1()" class="btn btn-info btn-sm mb-2">JavaScript: location.href 속성 이용</button>
			<br/><br/>
			<button onclick="requestGet2()" class="btn btn-info btn-sm mb-2">JavaScript: AJAX 이용</button>
			<button onclick="requestGet3()" class="btn btn-info btn-sm mb-2">JavaScript: AJAX 이용</button>
			<div id="ajaxResult" class="border mt-2">
			</div>
			
			<script>
				function requestGet1() {
					location.href = "getLocationHref?bno=5&bkind=free"; // window는 생략 가능
				}
				
				function requestGet2() {
					$.ajax({
						url: "getAjax1",
						method: "get",
						data: {bno:5, bkind:"free"},
						success: function(data) {
							// data가 HTML 조각일 경우
							$("#ajaxResult").html(data);
							// document.querySelector("#ajaxResult").innerHTML = data;
						}
					});
				}
				
				function requestGet3() {
					$.ajax({
						url: "getAjax2",
						method: "get",
						data: {bno:5, bkind:"free"},
						success: function(data) {
							console.log(data);
							var content = "<div class='card'>";
							content += "	<div class='card-header'>AJAX JSON 응답</div>";
							content += "	<div class='card-body'>";
							data.data.forEach((item) => {
								content += "<img height='100' src='${pageContext.request.contextPath}/resources/image/photos/" 
								+ item + "' class='me-2'>";
							});
							content += "	</div>";
							content += "</div>";
							$("#ajaxResult").html(content);
						}
					});
				}
			</script>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/bottom.jsp" %>