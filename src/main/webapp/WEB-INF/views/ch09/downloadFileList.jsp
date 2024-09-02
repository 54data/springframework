<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">
		파일 다운로드 목록
	</div>
	
	<div class="card-body">
		<ul>
			<c:forEach items="${fileNames}" var="fileName">
				<li class="m-2">
					<img class="rounded-circle" src="downloadFile?fileName=${fileName}" width="30" height="30"/>
					<a href="downloadFile?fileName=${fileName}">${fileName}</a>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"></jsp:include>