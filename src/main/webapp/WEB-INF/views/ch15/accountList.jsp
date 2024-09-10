<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">
		계좌 정보 보기 및 계좌 이체 하기
	</div>

	<div class="card-body">
		<table style="width:auto" class="table table-sm table-bordered">
		   <tr>
		      <th style="width:50px">번호</th>
		      <th style="width:100px">소유주</th>
		      <th style="width:200px">잔고</th>
		   </tr>
		   
		   <c:forEach var="account" items="${list}"> <!-- Controller에서 list 라는 객체가 저장되어야 함 -->
		      <tr>
		         <td>${account.ano}</td>
		         <td>${account.owner}</td>
		         <td style="text-align:right;">${account.balance}</td>
		      </tr>
		   </c:forEach>
		</table>
		
		<hr/>
		
		<form id="accountTransferForm" method="post" action="transfer">
		   <table style="width:auto" class="table table-sm table-bordered">
		      <tr>
		         <th style="width:150px">출금 계좌</th>
		         <td><input id="fromAccount" type="text" name="fromAno" value="1"/></td>
		      </tr>
		      <tr>
		         <th>입금 계좌</th>
		         <td><input id="toAccount" type="text" name="toAno" value="2"/></td>
		      </tr>
		      <tr>
		         <th>이체 금액</th>
		         <td><input id="amount" type="text" name="amount" value="10000"/></td>
		      </tr>
		   </table>
		   <input class="btn btn-info" type="submit" value="이체하기"/>                  
		</form>
   </div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"></jsp:include>