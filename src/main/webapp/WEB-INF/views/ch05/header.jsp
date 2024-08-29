<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">
		요청 헤더 값 얻기
	</div>
	<div class="card-body">
		<p>브라우저 종류 : ${browser}</p>
		<p>클라이언트 IP : ${clientIp}</p>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"/>