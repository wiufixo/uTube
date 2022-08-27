<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="com.topia.myapp.entity.Member"%>
<!DOCTYPE html>
<html>
<head>
<title>회원상세페이지</title>
<jsp:include page="../layout/head.jsp" />
</head>
<body class="text-center">
	<jsp:include page="../layout/menu.jsp"></jsp:include>

	<div class="container text-center my-5">
	<div class="row">
		<div class="vertical-center col-lg-12">
			<div class="d-flex justify-content-center align-items-center">
			<span class="fs-3">${member.channelName }</span><div id="followBtnBox" class="mx-3"></div>
			</div>
			<div class="input-box my-4 py-2 d-flex align-items-center row">
				<div class="col-md-3">
					<img alt="프로필이미지" src="${pageContext.request.contextPath}/resources/upload/member/${member.image}" style="width: 100px; border-radius: 50%; padding: 5px; border: 6px solid orange;">
				</div>
				<div class="col-md-3">
					<div class="my-2">구독자수</div>
					<div id="followerCnt" class="dropdownBtn my-2 fw-bold" onclick="printFollowersList()">${followerList.cnt }</div>
					<div id="followerList" class="dropdown-content">
						<c:forEach items="${followerList.list }" var="follower">
							<a href="./detail?memId=${follower.follower}">${follower.memName }</a>
						</c:forEach>
					</div>
				</div>
				<div class="col-md-3">
					<div class="my-2">구독채널수</div>
					<div class="dropdownBtn my-2 fw-bold" onclick="printFollowingsList()">${followingList.cnt }</div>
					<div id="followingList" class="dropdown-content">
						<c:forEach items="${followingList.list }" var="following">
							<a href="./detail?memId=${following.followed}">${following.memName }</a>
						</c:forEach>
					</div>
				</div>
				<div class="col-md-3">
					<div class="my-2">업로드수</div>
					<div class="my-2 fw-bold">${uploadCnt }</div>
				</div>
				
			</div>
			<div>
				<%-- <div class="dropdown">
					<button onclick="printFollowersList()" class="dropbtn">Dropdown</button>
					<div id="followerList" class="dropdown-content">
						<c:forEach items="${followerList.list }" var="follower">
							<a href="#">${follower.memName }</a>
						</c:forEach>
					</div>
				</div>
				<div class="dropdown">
					<button onclick="printFollowingsList()" class="dropbtn">Dropdown</button>
					<div id="followingList" class="dropdown-content">
						<c:forEach items="${followingList.list }" var="following">
							<a href="#">${following.memName }</a>
						</c:forEach>
					</div>
				</div> --%>
			</div>
			<div class="input-box2 my-4">
				<div class="mb-2">Id</div>
				<div class="form-control" style="text-align: left;">${member.memName }</div>
			</div>
			<div class="input-box2 my-4">
				<div class="mb-2">Auth</div>
				<div class="form-control" style="text-align: left;">${member.auth }</div>
			</div>
			<div class="input-box2 my-4">
				<div class="mb-2">Email</div>
				<div class="form-control" style="text-align: left;">${member.email }</div>
			</div>
			<c:if test="${not empty login }">
				<c:if test="${login.memId==member.memId }">
					<div class="btnBox my-4">
						<a href="./update?memId=${member.memId }" class="btn mybtn3">수정</a>
						<button id="resignBtn" class="btn mybtn3" type="button">탈퇴</button>
					</div>
				</c:if>
				<c:if test="${login.memId!=member.memId and ((login.auth=='MANAGER'and member.auth!='MASTER') or login.auth=='MASTER')}">
					<div class="btnBox my-4">
						<a href="./update?memId=${member.memId }" class="btn mybtn3">회원수정</a>
						<button id="deleteBtn" class="btn mybtn3" type="button">회원삭제</button>
					</div>
				</c:if>
			</c:if>
		</div>
		<div id="uploads" class="row"></div>
		<%-- <c:forEach items="${uploadList.list }" var="upload">
			<div class="col-lg-3 p-3">
				<div class="mycard">
					<a href="${pageContext.request.contextPath}/post/detail?postId=${upload.postId}"><img class="top" src="https://img.youtube.com/vi/${upload.urlId }/sddefault.jpg" alt="thumsnail"></a>
					<div>
						<c:if test="${fn:length(upload.postTitle) > 50}">
							<div class="title">${fn:substring(upload.postTitle,0,50)}..</div>
						</c:if>
						<c:if test="${fn:length(upload.postTitle) <= 50}">
							<div class="title">${upload.postTitle}</div>
						</c:if>
						<div class="text"><span class="fw-bold">${member.memName }</span> 조회수${upload.hit }회 ${upload.registDate }</div>
					</div>
				</div>
			</div>
		</c:forEach> --%>
	</div>
	</div>

	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/function.js"></script>
	<script type="text/javascript">
	
		<%
		Member login = (Member) session.getAttribute("login");
		int loginMemId = 0; //로그인 상태 초기화값
		if (login != null) {
			loginMemId = login.getMemId();
		}
		%>
		
		const loginMemId = <%=loginMemId%> //0 or 로그인한 회원아이디
		const memId = ${member.memId}; //보고있는 회원아이디
		const memName = '${member.memName}'; //보고있는 회원아이디
		const uploadCnt = ${uploadCnt}
		
		printFollowBtn(loginMemId, memId);
		
		
		let page = 1;
		let flag = false;
		
		printmemberPosts(page);
		
		//무한스크롤
		$(document).scroll(function() {
			//console.log($(window).scrollTop());
			//console.log($(window).height());
			//console.log($(document).height());

			const maxHeight = $(document).height();
			const currentScroll = $(window).scrollTop() + $(window).height();

			if (!flag) {
				if (maxHeight <= currentScroll + 50) {
					flag = true;
					printmemberPosts(page);
				}
			}
		});
		
		
		
		function printFollowingsList(){
			deleteClassShow();
			$("#followingList").toggleClass("show");
		}
		function printFollowersList() {
			deleteClassShow();
			$("#followerList").toggleClass("show");
		}
		/* function printUploadList() {
			deleteClassShow();
			$("#uploadList").toggleClass("show");
		} */
		
		$(window).on("click", function(e){
			if(!$(e.target).is($(".dropdownBtn"))){
				const dropdowns = $(".dropdown-content");
				for(dropdown of dropdowns){
					if($(dropdown).hasClass("show")){
						$(dropdown).removeClass("show");
					}
				}
			}
		})
	</script>
</body>
</html>