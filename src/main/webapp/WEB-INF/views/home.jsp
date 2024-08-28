<!-- 태그 주석 (응답에 포함) -->
<%-- JSP 주석 (응답에 포함 안됨) --%>

<%-- page 지시자 --%>
<%-- 
language : 프로그래밍 언어의 종류
pageEncoding : JSP 소스를 작성할 때 사용할 문자셋 (다국어 이용 => UTF-8)
contentType : JSP 실행 결과(응답 내용)의 종류 (MIME타입; charset=응답을 구성하는 문자셋)
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%--
include 지시자의 역할 : 외부의 파일의 내용을 가져와서 삽입시켜줌 
--%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">
		Home
	</div>

	<div class="card-body">
		<img
			src="${pageContext.request.contextPath}/resources/image/photos/photo1.jpg"
			height="100" /> 
		<img 
			src="resources/image/photos/photo2.jpg" 
			height="100"/>
   </div>
</div>

<%-- include 액션의 역할: 외부의 JSP를 실행하고 그 결과를 삽입시켜줌 --%>
<jsp:include page="/WEB-INF/views/common/bottom.jsp"></jsp:include>