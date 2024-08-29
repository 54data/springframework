<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">
		Session에 저장된 데이터 이용
	</div>

	<div class="card-body">
		<div>
			<p>mid: ${member.mid}</p> <!-- 먼저 request 범위에서 찾는다. 없다면 session 범위에서 찾게 됨 -->
			<p>mname: ${member.mname}</p>
			<p>memail: ${member.memail}</p> 
		</div>
   </div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"></jsp:include>