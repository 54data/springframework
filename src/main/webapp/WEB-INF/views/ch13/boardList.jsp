<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">
		게시물 목록
	</div>

	<div class="card-body">
		<table class="table table-sm table-bordered">
	        <tr>
	           <th style="width:30px">번호</th>
	           <th style="width:300px">제목</th>
	           <th style="width:70px">글쓴이</th>
	           <th style="width:70px">날짜</th>
	           <th style="width:70px">조회수</th>
	        </tr>
	        
	        <c:forEach items="${list}" var="board"> <!-- board - Ch13Board 객체 참조 -->
       	        <tr>
       	        	<td>${board.bno}</td>
       	        	<td><a href="detailBoardAddHitcount?bno=${board.bno}">${board.btitle}</a></td>
       	        	<td>${board.mid}</td>
       	        	<td><fmt:formatDate value="${board.bdate}" pattern="yyyy-MM-dd"/></td>
       	        	<!-- value : 집어넣은 데이터 / pattern : 출력시 보여주고 싶은 패턴 -->
       	        	<td>${board.bhitcount}</td>
	     	   </tr>
	        </c:forEach>
	        <tr>
	        	<td colspan="5" class="text-center">
					<div>
						<a href="boardList?pageNo=1" class="btn btn-outline-primary btn-sm">처음</a>
						<c:if test="${pager.groupNo > 1}">
							<a href="boardList?pageNo=${pager.startPageNo - 1}" class="btn btn-outline-info btn-sm">이전</a>
						</c:if>
						
						<c:forEach begin="${pager.startPageNo}" end="${pager.endPageNo}" step="1" var="i">
							<c:if test="${pager.pageNo == i}">
								<a href="boardList?pageNo=${i}" class="btn btn-danger btn-sm">${i}</a>
							</c:if>
							<c:if test="${pager.pageNo != i}">
								<a href="boardList?pageNo=${i}" class="btn btn-outline-success btn-sm">${i}</a>
							</c:if>
						</c:forEach>
						
						<c:if test="${pager.groupNo < pager.totalGroupNo}">
							<a href="boardList?pageNo=${pager.endPageNo + 1}" class="btn btn-outline-info btn-sm">다음</a>
						</c:if>
						
						<a href="boardList?pageNo=${pager.totalPageNo}" class="btn btn-outline-primary btn-sm">맨끝</a>
					</div>
	        	</td>
	        </tr>
	    </table>
   </div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"></jsp:include>