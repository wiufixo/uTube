<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>insert</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./resources/css/style.css">
</head>
<body>
	<jsp:include page="./header.jsp"></jsp:include>
	<div style="height: 150px; width: 100%; display: flex; justify-content: center; align-items: center">
		<div>
			<img class="logo" src="resources/images/video.png" />
		</div>
		<div style="font-size: 30px;">동영상 등록</div>
	</div>

	<div class="container list">
		<form id="insertFrm" class="insertFrm" action="./insertPostSubmit" method="post">
			<div class="inputBox">
				<p>제목</p>
				<input id="postTitle" name="postTitle" class="input" type="text">
			</div>
			<div class="inputBox">
				<p>URL</p>
				<input id="postUrl" name="postUrl" class="input" type="text">
			</div>
			<div class="inputBox">
				<p>내용</p>
				<textarea id="postContent" name="postContent" class="input" rows="5"></textarea>
			</div>
			<button id="insertBtn" type="button">등록</button>
			<button type="button">취소</button>
		</form>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
		$("#insertBtn").on(
				"click",
				function() {
					if ($("#postTitle").val() == ''
							|| $("#postTitle").val() == null) {
						alert("제목을 입력해주세요!");
						$("#postTitle").focus();
						return false;
					}
					if ($("#postUrl").val() == ''
							|| $("#postUrl").val() == null) {
						alert("url을 입력해주세요!");
						$("#postUrl").focus();
						return false;
					}
					if ($("#postContent").val() == ''
							|| $("#postContent").val() == null) {
						alert("내용을 입력해주세요!");
						$("#postContent").focus();
						return false;
					}
					
					$("#insertFrm").submit();

				})
	</script>
</body>
</html>