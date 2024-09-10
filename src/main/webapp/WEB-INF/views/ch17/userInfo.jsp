<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">
		로그인한 사용자 정보 보기
	</div>

	<div class="card-body">
		<p>mid: ${member.mid}</p>
		<p>mname: ${member.mname}</p>
		<p>mpassword: ${member.mpassword}</p>
		<p>menabled: ${member.menabled}</p>
		<p>mrole: ${member.mrole}</p>
		<p>memail: ${member.memail}</p>
   </div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"></jsp:include>