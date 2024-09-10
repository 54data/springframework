<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">
		권한별 내용보기
	</div>

	<div class="card-body">
		<ul>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li>ROLE_ADMIN이 볼 수 있는 내용</li>
			</sec:authorize>
			
			<sec:authorize access="hasRole('ROLE_MANAGER')">
				<li>ROLE_MANAGER가 볼 수 있는 내용</li>
			</sec:authorize>
			
			<sec:authorize access="hasRole('ROLE_USER')">
				<li>ROLE_USER가 볼 수 있는 내용</li>
			</sec:authorize>
		</ul>
   </div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"></jsp:include>