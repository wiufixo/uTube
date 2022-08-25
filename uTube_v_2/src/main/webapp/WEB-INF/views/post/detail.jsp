<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ page import="com.topia.myapp.entity.Member"%>
<!DOCTYPE html>
<html>
<head>
<title>상세페이지</title>
<jsp:include page="../layout/head.jsp" />
</head>
<body>
	<jsp:include page="../layout/menu.jsp" />
	<div class="container">
		<div class="detail-container row">
			<div class="col-md-8">
				<div class="video-header">
					<div>
						<i class="fab fa-youtube" style="color: red"></i>
						<span>Youtube</span>
					</div>
					<div class="icons">
						<i class="fas fa-search"></i> <i class="fas fa-ellipsis-v"></i>
					</div>
				</div>

				<!-- Video Player -->
				<iframe width="100%" height="400" src="https://www.youtube.com/embed/${post.urlId }" frameborder="0" allowfullscreen></iframe>

				<!-- Video Info -->
				<div class="video-info py-2">
					<div class="title">${post.postTitle }</div>
					<div class="info">조회수 ${post.hit }회 <fmt:formatDate pattern="yyyy-MM-dd" value="${post.registDate}"/></div>
					<ul class="actions">
						<li id="likeBtnBox">
							<button onclick="like(this)">
								<i class="fas fa-thumbs-up"></i>
								<span></span>
							</button>
						</li>
						<li id="hateBtnBox">
							<button onclick="hate(this)">
								<i class="fas fa-thumbs-down"></i>
								<span></span>
							</button>
						</li>
						<li>
							<button>
								<i class="fas fa-share"></i>
								<span>공유</span>
							</button>
						</li>
						<li>
							<button>
								<i class="fas fa-plus"></i>
								<span>저장</span>
							</button>
						</li>
						<li>
							<button>
								<i class="fab fa-font-awesome-flag"></i>
								<span>신고</span>
							</button>
						</li>
					</ul>

					<div class="px-2 py-1 fs-6">
						<c:if test="${fn:length(post.postContent) >= 50 }">

							<div id="postText" style="display: block;">
								<textarea rows="3" cols="50" style="width: 100%; height: 120px; border: none; outline: none; resize: none; overflow: hidden;" readonly="readonly">${fn:substring(post.postContent,0,50) }..</textarea>
							</div>
							<div id="showMore" style="display: block;">더보기</div>

							<div id="postTextArea" style="display: none;">
								<textarea rows="3" cols="50" style="width: 100%; height: 500px; border: none; outline: none; resize: none; overflow: hidden;" readonly="readonly">${post.postContent }</textarea>
							</div>
							<div id="showShort" style="display: none;">간략히</div>

						</c:if>

						<c:if test="${fn:length(post.postContent) < 50 }">
							<div id="postText" style="display: block;">
								<textarea rows="3" cols="50" style="width: 100%; height: 100px; border: none; outline: none; resize: none; overflow: hidden;" readonly="readonly">${post.postContent }</textarea>
							</div>
						</c:if>
					</div>
					<c:if test="${not empty login and (login.memId==post.memId or (login.auth eq 'MASTER') or (login.auth eq 'MANAER'))}">
						<div class="right-align ml-3">
							<a href="./update?postId=${post.postId }" class="btn mybtn2">수정</a>
							<button id="detailDeleteBtn" class="btn mybtn2">삭제</button>
						</div>
					</c:if>
					<div class="channel my-3 py-2 align-items-center">
						<div class="uploader">
							<a href="${pageContext.request.contextPath}/member/detail?memId=${post.memId }">
								<img src="${pageContext.request.contextPath}/resources/upload/member/${post.image}" alt="" style="width: 65px;">
							</a>
							<div class="info fs-6">
								<a href="${pageContext.request.contextPath}/member/detail?memId=${post.memId }">
									<span class="name">${post.memName }</span>
								</a>
								<span class="subscribers">구독자 ${post.followers }명</span>
							</div>
						</div>
						<div id="followBtnBox"></div>
						<%-- <c:if test="${not empty login and login.memId!=post.memId }">
							<c:if test="${isFollowed == 1}">
								<button id="followCancelBtn" class="subscribe fs-6">구독취소</button>
							</c:if>
							<c:if test="${isFollowed == 0}">
								<button id="followBtn" class="subscribe fs-6">구독</button>
							</c:if>
						</c:if> --%>
					</div>

				</div>

				<!-- Cmt -->
				<div>
					<form id="commentFrm">
						<input type="hidden" name="postId" value="${post.postId }">
						<input type="hidden" name="memId" value="${login.memId }" /> <!-- null or 회원번호 -->
						<table width="100%" class="mb-4">
							<colgroup>
								<col width="30%" />
								<col width="60%" />
								<col width="10%" />
							</colgroup>
							<c:if test="${not empty login }">
								<tr>
									<td>
										<input type="text" class="form-control" style="width: 90%;" value="${login.memName }" readonly="readonly">
									</td>
									<td>
										<input type="text" class="form-control" id="cmtContent" name="cmtContent" style="width: 95%;" placeholder="댓글을 입력하세요">
									</td>
									<td>
										<button id="cmtInsertBtn" class="btn mybtn3" type="button">등록</button>
									</td>
								</tr>
							</c:if>
						</table>
					</form>
					<div>
					<div>
						<c:choose>
							<c:when test="${cmtsCnt == 0 }">
								<div class="listTitle text-center mt-4 fs-6">등록된 댓글이 없습니다.</div>
							</c:when>
							<c:otherwise>
								<div class="listTitle fs-6">댓글 총 <span id="cmtsCnt">${cmtsCnt }</span>개</div>
							</c:otherwise>
						</c:choose>
					</div>
						<ul class="cmtList">
							<!-- <li class="cmtItem">
								<div class="cmt-img">
									<img src="resources/images/profile.png" alt="">
								</div>
								<div class="cmt-info">
									<div class="info-top">
										<span class="cmtWriter" style="font-size: 1.0em;">민브로 코딩</span> <span style="font-size: 0.9em; color: grey">5개월전</span>
									</div>
									<div class="info-top">
										<span class="my-2 cmtText">댓글내용입니다.</span>
										<div>
											<span id="cmtDelete">❌</span> <input type="hidden" value="14"> <span id="cmtUpdate">✏️</span>
										</div>
									</div>
									<span class="mb-1" style="font-size: 0.9em; color: blue">좋아요</span> <span class="mb-1" style="font-size: 0.9em; color: grey">답글</span>
								</div>
							</li> -->

						</ul>
						<div id="cmtMore" class="text-center p-3 fw-bold" style="cursor: pointer; display: none;">더보기</div>
						<div id="paging" class="d-flex justify-content-center"></div>
						
						
					</div>
				</div>
			</div>

			<div class="col-md-4 list-container">
				<div>
					<c:choose>
						<c:when test="${recommendsCnt == 0 }">
							<div class="listTitle text-center mt-4">추천영상이 없습니다.</div>
						</c:when>
						<c:otherwise>
							<div class="listTitle">추천영상 ${recommendsCnt }개</div>
							<ul id="recommendList"></ul>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
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
		
		const loginMemId = <%=loginMemId%>
		//0 or 회원번호

		const memId = ${post.memId};
		const postId = ${post.postId};
		const recommendsCnt = ${recommendsCnt};
		const cmtsCnt = ${cmtsCnt};

		var cmtPage = 1;

		textAreaResize(0);
		
		printLikeBtn();
		printHateBtn();
		printLikeCnt();
		printHateCnt();
		
		//팔로우 한 채널인지 아닌지 ajax으로 통신하여 구독/구독취소 버튼 생성
		printFollowBtn(loginMemId, memId);
		
	
		$("#showMore").on("click", function() {
			$("#postTextArea").css("display", "block");
			$("#postText").css("display", "none");
			$("#showMore").css("display", "none");
			$("#showShort").css("display", "block");
			textAreaResize(1);
		})

		$("#showShort").on("click", function() {
			$("#postTextArea").css("display", "none");
			$("#postText").css("display", "block");
			$("#showMore").css("display", "block");
			$("#showShort").css("display", "none");
			textAreaResize(0);
		})

		let page = 1;
		let flag = false;

		//페이지 들어가자마자 1페이지 추천영상 불러오기
		printRecommends(page);

		//무한스크롤
		$(document).scroll(function() {
			const maxHeight = $(document).height();
			const currentScroll = $(window).scrollTop() + $(window).height();

			if (!flag) {
				if (maxHeight <= currentScroll + 50) {
					flag = true;
					printRecommends(page);
				}
			}
		});
		
		printCmtList(cmtPage);
		
		if($(".parentItem").length < cmtsCnt){
			$("#cmtMore").css("display","block");
		}
		
		$("#cmtMore").on("click", function(){
			printCmtList(cmtPage+1);
		})
		
		$(document).on("click", "#cmtUpdateCancel", function() {
			$(".cmtList").empty();
            printCmtList(1);
		})
		
		
	</script>
</body>
</html>