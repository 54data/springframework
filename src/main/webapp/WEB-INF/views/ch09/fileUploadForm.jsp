<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">
		파일 업로드
	</div>

	<div class="card-body">
	    <form method="post" enctype="multipart/form-data" action="singleFileUpload">
	       <div class="form-group">
	          <label for="title">File Title</label> 
	          <input type="text" class="form-control" id="title" name="title" placeholder="제목">
	       </div>
	       <div class="mt-2 form-group">
	          <label for="desc">File Description</label> 
	          <input type="text" class="form-control" id="desc" name="desc" placeholder="설명">
	       </div>
	       <div class="mt-2 form-group">
	           <label for="attach">File Attach</label>
	           <input type="file" class="form-control-file" id="attach" name="attach">
	         </div>	         
	         <button type="submit" class="mt-2 btn btn-info btn-sm">싱글 파일 업로드</button>
	    </form>
	    
	    <hr/>
	    
	   	<form method="post" enctype="multipart/form-data" action="multiFileUpload">
	       <div class="form-group">
	          <label for="title">File Title</label> 
	          <input type="text" class="form-control" id="title" name="title" placeholder="제목">
	       </div>
	       <div class="mt-2 form-group">
	          <label for="desc">File Description</label> 
	          <input type="text" class="form-control" id="desc" name="desc" placeholder="설명">
	       </div>
	       <div class="mt-2 form-group">
	           <label for="attach">File Attach</label>
	           <input type="file" multiple class="form-control-file" id="attach" name="attach">
	         </div>	         
	         <button type="submit" class="mt-2 btn btn-info btn-sm">멀티 파일 업로드</button>
	    </form>
	    
	    <hr/>

	   	<form>
	       <div class="form-group">
	          <label for="title">File Title</label> 
	          <input type="text" class="form-control" id="ajax-title" name="title" placeholder="제목">
	       </div>
	       <div class="mt-2 form-group">
	          <label for="desc">File Description</label> 
	          <input type="text" class="form-control" id="ajax-desc" name="desc" placeholder="설명">
	       </div>
	       <div class="mt-2 form-group">
	           <label for="attach">File Attach</label>
	           <input type="file" multiple class="form-control-file" id="ajax-attach" name="attach">
           </div>
           <button type="button" onclick="uploadFileFromAjax()" class="mt-2 btn btn-info btn-sm">AJAX로 파일 업로드</button>	         
	    </form>
	    
	    <script>
	    	function uploadFileFromAjax() {
	    		// multipart/form-data 로 구조로 본문을 생성하는 객체 생성
	    		const formData = new FormData();
	    		formData.append("title", $("#ajax-title").val());
	    		formData.append("desc", $("#ajax-desc").val());
	    		formData.append("attach", $("#ajax-attach")[0].files[0]);
	    		// 위와 동일 - formData.append("attach", document.querySeletor("#attach").files[0]);
	    		
	    		$.ajax({
	    			url: "uploadFileFromAjax",
	    			method: "post",
	    			data: formData,
	    			cache: false, // 브라우저 데이터를 메모리에 저장하지 말아라
	    			processData: false, // 요청 HTTP 본문의 내용을 가공 처리하지 말아라 => 쿼리 스트링으로 변환하지 말아라
	    			contentType: false, // 본문에는 여러개의 contentType이 존재하므로 한 개로 표현할 수 없음, 그러니 지정을 하지 않겠다
	    			success: function (data) {
	    				// {"result": "ok"}
	    				if (data.result == "ok") {
	    					location.href = "downloadFileList";
	    				} else {
	    					console.log("전송 실패");
	    				}
	    			}
	    		});
	    	}
	    </script>
   </div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"></jsp:include>