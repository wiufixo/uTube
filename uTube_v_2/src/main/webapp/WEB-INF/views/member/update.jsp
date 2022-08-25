<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<title>회원수정페이지</title>
<jsp:include page="../layout/head.jsp" />
<style>
</style>
</head>
<body class="text-center">
	<jsp:include page="../layout/menu.jsp"></jsp:include>
	<div class="my-3 pt-5">
		<h1 class="fs-3">회원정보수정</h1>
		<img class="profile-img" alt="프로필이미지" src="${pageContext.request.contextPath}/resources/upload/member/${member.image}" style="width: 150px; border-radius: 50%">
	</div>
	<div class="container text-center">
		<form id="updateFrm" class="vertical-center" action="./joinSubmit" method="post" enctype="multipart/form-data">
			<input type="hidden" name="memId" id="memId" value="${member.memId }">
			<c:if test="${login.auth == 'MASTER' }">
				<div class="input-box my-4 w-30">
					<div class="mb-2">Auth (select)</div>
					<select name="auth" class="input form-select">
						<option value="USER" <c:if test="${member.auth == 'USER' }">selected</c:if>>user</option>
						<option value="MANAGER" <c:if test="${member.auth == 'MANAGER' }">selected</c:if>>manager</option>
						<option value="MASTER" <c:if test="${member.auth == 'MASTER' }">selected</c:if>>master</option>
					</select>
				</div>
			</c:if>
			
			<div class="input-box my-4 w-50">
				<div class="mb-2">Id</div>
				<input name="memName" type="text" class="input form-control" id="id" placeholder="Id" value="${member.memName }">
				<div id="idOK" class="alert alert-success" style="display: none;">🙆‍♂️사용가능한 아이디입니다.</div>
				<div id="idNO" class="alert alert-danger" style="display: none;">🙅‍♀️아이디는 5자 이상 15자 미만으로 입력해주세요.</div>
			</div>
			<div class="input-box my-4 w-50">
				<div class="mb-2">Password</div>
				<input type="password" class="input form-control my-3" id="pwd1" placeholder="비밀번호"> 
				<input name="password" type="password" class="input form-control my-3" id="pwd2" placeholder="비밀번호 확인">
				<div id="pwdEqOK" class="alert alert-success" style="display: none;">🙆‍♂️비밀번호가 일치합니다.</div>
				<div id="pwdEqNO" class="alert alert-danger" style="display: none;">🙅‍♀️비밀번호가 일치하지 않습니다.</div>
			</div>
			<div class="input-box my-4 w-50">
				<div class="mb-2">Channel</div>
				<input name="channelName" type="text" class="input form-control" id="channelName" placeholder="ChannelName" value="${member.channelName }">
			</div>
			<div class="input-box my-4 w-50">
				<div class="mb-2">Email</div>
				<input name="email" type="email" class="input form-control" id="email" placeholder="Email" value="${member.email }">
				<div id="emailOK" class="alert alert-success" style="display: none;">🙆‍♂️이메일 형식이 올바릅니다.</div>
				<div id="emailNO" class="alert alert-danger" style="display: none;">🙅‍♀️이메일 형식이 올바르지 않습니다.</div>
			</div>
			<div class="input-box my-4 w-50">
				<div class="mb-2">Profile Image</div>
						<label for="uploadFile" id='thumbnail'>
							<img class="profile-img" alt="프로필이미지" src="${pageContext.request.contextPath}/resources/upload/member/${member.image}" style="width: 100px; border-radius: 50%">
						</label> 
				<div class="d-flex justify-content-between align-items-center row">
					<div class="my-4 col-md-10">
						<input type="file" name="uploadFile" id="uploadFile" class="form-control" onchange="previewImage(this)" style="width: 100%">
					</div>
					<div class="col-md-2">
					<button id="fileReset" class="btn mybtn3" type="button">삭제</button>
					</div>
				</div>
			</div>
			<div class="btnBox my-4">
				<button id="updateBtn" class="btn mybtn3" type="button">수정</button>
				<button onclick="history.back();" class="btn mybtn3" type="button">돌아가기</button>
			</div>
		</form>
	</div>

	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/function.js"></script>
	<script type="text/javascript">
	function previewImage(target) {
		//console.log(target.files[0])
		const fileName = target.files[0].name;
		const str = `<img src="${pageContext.request.contextPath}/file/display?fileName=\${fileName}" style="width: 100px; border-radius: 50%">`
		$("#thumbnail").html(str);
	}
	
	$("#fileReset").on("click", function(){
		$("#uploadFile").val("");
		const str = `<img alt="프로필이미지" src="${pageContext.request.contextPath}/resources/upload/member/${member.image}" style="width: 100px; border-radius: 50%">`
		$("#thumbnail").html(str);
	})
	
	function emailCheck(str) {
		var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
		if (!reg_email.test(str)) {
			return false;
		} else {
			return true;
		}
	}
	
		$("#id").on("keyup", function() {
			const id = $(this).val();
			if (id.length < 5 || id.length >14) {
				$("#idOK").css("display", "none");
				$("#idNO").css("display", "block");
			}else{
				$("#idOK").css("display", "block");
				$("#idNO").css("display", "none");
			}
		})
		
		$("#pwd2").on("keyup", function() {
			const pwd2 = $(this).val();
			const pwd1 = $("#pwd1").val();
			
			if (pwd2 == pwd1) {
				$("#pwdEqOK").css("display", "block");
				$("#pwdEqNO").css("display", "none");
			}else{
				$("#pwdEqOK").css("display", "none");
				$("#pwdEqNO").css("display", "block");
			}
		})
		
		$("#email").on("keyup", function() {
			const email = $(this).val();
			if (!emailCheck(email)) {
				$("#emailOK").css("display", "none");
				$("#emailNO").css("display", "block");
			}else{
				$("#emailOK").css("display", "block");
				$("#emailNO").css("display", "none");
			}
		})

		$("#updateBtn").on("click", function() {
			const id = $("#id").val();
			const pwd = $("#pwd2").val();
			const channelName = $("#channelName").val();
			const email = $("#email").val();
			const memId = $("#memId").val();
			
			if (id == '') {
				alert("아이디를 입력하세요!");
				$("#id").focus();
				return false;
			}
			
			if (pwd == '') {
				alert("비밀번호를 입력하세요!");
				$("#pwd1").focus();
				return false;
			}

			if (channelName == '') {
				alert("채널명을 입력하세요!");
				$("#channelName").focus();
				return false;
			}
			
			if (email == '') {
				alert("이메일을 입력하세요!");
				$("#email").focus();
				return false;
			}
			
			if($("#idNO").css("display")=="block" || $("#pwdEqNO").css("display")=="block" || $("#emailNO").css("display")=="block"){
				alert("형식에 맞게 작성해주세요!");
				return false;
			}
			
			const updateFrm = $("#updateFrm")[0];
			const frmData = new FormData(updateFrm);
			for (let key of frmData.keys()) {
				  console.log(key);
			}
			for (let value of frmData.values()) {
			  console.log(value);
			}
			
			$.ajax({
		        url : './updateSubmit',
		        type : 'post',
		        data : frmData,
		        contentType : false,
		        processData : false,
				success:function(re){
					if(re==1){
						location.href=`./detail?memId=\${memId}`
					}else{
						alert("회원수정에 실패하였습니다!");
					}
					
				}     
		    });
			
			//$("#updateFrm").submit();
		})
		
		
		
	</script>
</body>
</html>