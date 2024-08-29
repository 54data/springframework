<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">
		request에 저장된 데이터 이용
	</div>

	<div class="card-body">
		<div class="m-3">
			<p>mid: ${memeber1.mid}</p>
			<p>mname: ${memeber1.mname}</p>
			<p>memail: ${memeber1.memail}</p>
		</div>
				
		<hr>
		
		<div class="m-3">
			<p>mid: ${memeber2.mid}</p>
			<p>mname: ${memeber2.mname}</p>
			<p>memail: ${memeber2.memail}</p>
		</div>
   </div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"></jsp:include>