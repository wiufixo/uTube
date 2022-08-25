<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<html>
<head>
<title>로그인</title>
<jsp:include page="../layout/head.jsp"/>
<style>

</style>
</head>
<body class="text-center">
	<jsp:include page="../layout/menu.jsp"></jsp:include>
	<div class="my-5">
		<img class="mb-4" src="${pageContext.request.contextPath}/resources/images/login.png" alt="" width="90">
		<h1 class="fs-3">로그인</h1>
	</div>
	
	<div class="container text-center mb-5">
		<form id="loginFrm" class="vertical-center" action="./loginSubmit" method="post">
			<div class="input-box my-4 w-30">
				<div class="mb-2">Id</div><input name="memName" type="text" class="input form-control" id="loginId" placeholder="Id">
			</div>
			<div class="input-box my-4 w-30">
				<div class="mb-2">Password</div><input name="password" type="password" class="input form-control" id="loginPwd" placeholder="Password">
			</div>
			<div class="btn-box my-5 py-2">
				<button id="loginBtn" class="btn mybtn3" type="button">로그인</button>
				<a href="./find" class="btn mybtn3">비밀번호찾기</a>
				<a href="./join" class="btn mybtn3">회원가입</a>
			</div>
		</form>
	</div>


	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/function.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
<body>




</body>
</html>