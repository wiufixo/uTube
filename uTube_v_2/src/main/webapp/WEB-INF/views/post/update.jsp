<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>영상수정</title>
<jsp:include page="../layout/head.jsp" />
</head>
<body class="text-center">
	<jsp:include page="../layout/menu.jsp"></jsp:include>
	<div class="my-5">
		<img class="mb-4" src="${pageContext.request.contextPath}/resources/images/video.png" width="90" />
		<div class="fs-3">동영상 수정</div>
	</div>

	<div class="container mb-5">
		<form id="commitFrm" class="text-center" action="./updateSubmit" method="post">
			<div class="vertical-center">
			<input type="hidden" name="postId" value="${post.postId }">
				<div class="input-box">
					<p>제목</p>
					<input id="postTitle" name="postTitle" class="input form-control" type="text" value="${post.postTitle }">
				</div>
				<div class="input-box">
					<p>URL</p>
					<input id="postUrl" name="postUrl" class="input form-control" type="text" value="${post.postUrl }">
				</div>
				<div class="input-box">
					<p>내용</p>
					<textarea id="postContent" name="postContent" class="input form-control" rows="3" style="resize: none; overflow: hidden;">${post.postContent }</textarea>
				</div>
				<div class="my-5">
					<button id="commitBtn" class="btn mybtn3" type="button">등록</button>
					<a href="./detail?postId=${post.postId }" class="btn mybtn3">취소</a>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="../layout/footer.jsp"></jsp:include>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/function.js"></script>
	<script type="text/javascript">
	
	textAreaResize(0);
	
	postValidate();
	
	$("#postContent").on("keyup", function(){
	    textAreaResize(0)
	})
	
	</script>
</body>
</html>