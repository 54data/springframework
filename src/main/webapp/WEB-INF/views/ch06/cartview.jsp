<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">
		상품 목록
	</div>

	<div class="card-body">
		<ul>
			<c:forEach items="${cart.contents}" var="item"> 
				<!-- 
					session.getAttribute() 에서 cart라는 이름으로 저장했기 때문에 cart.contents로 가져옴
					item이라는 변수에 저장하게 된다. 
				--> 
				<li class="m-2">
					<span>${item.pname}</span>
					<a href="deleteitem?pno=${item.pno}" class="btn btn-danger btn-sm">삭제</a>
				</li>
			</c:forEach> 
			<!-- EL 태그는 바로 필드로 접근하는 것 처럼 보이지만 getPname 이런 식으로 get 메소드를 사용하는 것임 -->
		</ul>
   </div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"></jsp:include>