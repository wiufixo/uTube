<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<title>회원가입</title>
<jsp:include page="../layout/head.jsp" />
<style>
</style>
</head>
<body class="text-center">
	<jsp:include page="../layout/menu.jsp"></jsp:include>
	<div class="my-5">
		<img class="mb-4" src="${pageContext.request.contextPath}/resources/images/login.png" alt="" width="90">
		<h1 class="fs-3">${title }</h1>
	</div>

	<div class="container text-center mb-5">
		<form id="joinFrm" class="vertical-center" action="./joinSubmit" method="post" enctype="multipart/form-data">
			<div class="input-box my-4 w-30">
				<div class="mb-2">Id</div>
				<input name="memName" type="text" class="input form-control" id="joinId" placeholder="Id">
				<div id="idOK" class="alert alert-success" style="display: none;">🙆‍♂️사용가능한 아이디입니다.</div>
				<div id="idNO" class="alert alert-danger" style="display: none;">🙅‍♀️아이디는 5자 이상 15자 미만으로 입력해주세요.</div>
				<div id="idDouble" class="alert alert-danger" style="display: none;">🙅‍♀️중복된 아이디입니다.</div>
			</div>
			<div class="input-box my-4 w-30">
				<div class="mb-2">Password</div>
				<input type="password" class="input form-control my-3" id="joinPwd1" placeholder="비밀번호"> 
				<input name="password" type="password" class="input form-control my-3" id="joinPwd2" placeholder="비밀번호 확인">
				<div id="pwdEqOK" class="alert alert-success" style="display: none;">🙆‍♂️비밀번호가 일치합니다.</div>
				<div id="pwdEqNO" class="alert alert-danger" style="display: none;">🙅‍♀️비밀번호가 일치하지 않습니다.</div>
			</div>
			<div class="input-box my-4 w-30">
				<div class="mb-2">Channel</div>
				<input name="channelName" type="text" class="input form-control" id="joinChannelName" placeholder="ChannelName">
			</div>
			<div class="input-box my-4 w-30">
				<div class="mb-2">Email</div>
				<input name="email" type="email" class="input form-control" id="joinEmail" placeholder="Email">
				<div id="emailOK" class="alert alert-success" style="display: none;">🙆‍♂️이메일 형식이 올바릅니다.</div>
				<div id="emailNO" class="alert alert-danger" style="display: none;">🙅‍♀️이메일 형식이 올바르지 않습니다.</div>
			</div>
			<div class="input-box my-4 w-30">
				<div class="mb-2">Profile Image</div>
				<label for="uploadFile" id='thumbnail'>
					<img class="mb-4" src="${pageContext.request.contextPath}/resources/images/photo.png" alt="" width="100">
				</label> 
				<input type="file" id="uploadFile" name="uploadFile" class="form-control" onchange="previewImage(this)"> 
			</div>
			<div class="btnBox my-5 py-2">
				<button id="joinBtn" class="btn mybtn3" type="button">${button }</button>
				<a href="./find" class="btn mybtn3">비밀번호찾기</a>
			</div>
		</form>
	</div>

	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/function.js"></script>
	<script type="text/javascript">
	const login = '${login}';
	</script>
</body>
</html>