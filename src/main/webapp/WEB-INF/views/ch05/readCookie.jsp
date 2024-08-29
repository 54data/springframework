<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">
		쿠키값 읽기
	</div>
	<div class="card-body">
		<p>mid: ${mid}</p>
		<p>memail: ${memail}</p>		
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"/>