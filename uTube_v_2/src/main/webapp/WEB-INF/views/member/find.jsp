<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<html>
<head>
<title>비밀번호찾기</title>
<jsp:include page="../layout/head.jsp"/>
<style>

</style>
</head>
<body class="text-center">
	<jsp:include page="../layout/menu.jsp"></jsp:include>
	<div class="my-5">
		<img class="mb-4" src="${pageContext.request.contextPath}/resources/images/login.png" alt="" width="90">
		<h1 class="fs-3">비밀번호 찾기</h1>
	</div>
	<div class="container text-center my-5">
		<form id="findFrm" class="vertical-center" action="./loginSubmit" method="post">
			<div class="input-box my-4 w-30">
				<div class="mb-2">Id</div><input name="memName" type="text" class="input form-control" id="findId" placeholder="Id">
			</div>
			<div class="input-box my-4 w-30">
				<div class="mb-2">Email</div><input name="email" type="text" class="input form-control" id="findEmail" placeholder="Email">
			</div>
			<div id="findResult"></div>
			<div id="findBtnBox" class="btn-box my-4">
				<button id="findPasswordBtn" class="btn mybtn3" type="button">찾기</button>
				
			</div>
		</form>
	</div>


	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/function.js"></script>
	<script type="text/javascript">
		$("#findPasswordBtn").on("click", function(){
			const memName = $("#findId").val();
			const email = $("#findEmail").val();
			$.ajax({
				url:"./findPassword",
				type:"post",
				data:{
					memName,
					email
				},
				success: function(re){
					if(re.length == 0){
						alert("입력하신 아이디와 이메일에 해당하는 회원은 없습니다. 다시 시도해주세요.");
						$("#findId").val('');
						$("#findEmail").val('');
						return false;
					}
					const result = "<h4>비밀번호는 <h2>"+re+"</h2>입니다.</h4>";
					$("#findResult").html(result);
					$("#findBtnBox").html('<a href="./login" class="btn mybtn3">로그인</a>');
				}
			})
		})
	</script>
</body>
<body>




</body>
</html>