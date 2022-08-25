<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<title>영상등록</title>
<jsp:include page="../layout/head.jsp"/>
</head>
<body class="text-center">
	<jsp:include page="../layout/menu.jsp"></jsp:include>
	<div class="my-5">
		<img class="mb-4" src="${pageContext.request.contextPath}/resources/images/video.png" width="90" />
		<div class="fs-3">동영상 등록</div>
	</div>

	<div class="container mb-5">
		<form id="commitFrm" class="text-center" action="./insertSubmit" method="post">
			<div class="vertical-center">
			<c:if test="${login != null }">
				<input type="hidden" name="memId" value="${login.memId }">
			</c:if>
				<div class="input-box">
					<p>제목</p>
					<input id="postTitle" name="postTitle" class="input form-control" type="text">
				</div>
				<div class="input-box">
					<p>URL</p>
					<input id="postUrl" name="postUrl" class="input form-control" type="text">
				</div>
				<div class="input-box">
					<p>내용</p>
					<textarea id="postContent" name="postContent" class="input form-control" rows="3" style="resize: none; overflow: hidden;"></textarea>
				</div>
				<div class="btn-box my-5 py-2">
					<button id="commitBtn" class="btn mybtn3" type="button">등록</button>
					<input type="reset" class="btn mybtn3" value="리셋">
					<a href="./" class="btn mybtn3">취소</a>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="../layout/footer.jsp"></jsp:include>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/function.js"></script>
	<script type="text/javascript">
		
	postValidate();		
		
	$("#postContent").on("keyup", function(){
	    textAreaResize(0)
	});
	
	</script>
</body>
</html>