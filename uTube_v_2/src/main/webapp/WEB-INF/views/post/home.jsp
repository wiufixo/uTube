<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>홈</title>
<jsp:include page="../layout/head.jsp" />
<script type="text/javascript">
const context = "${pageContext.request.contextPath}";
sessionStorage.setItem("contextpath", context);
</script>
</head>
<body>
	<jsp:include page="../layout/menu.jsp" />
	<div class="main-logo">UTube</div>
	<div class="p-5">
		<div class="row">
			<div <c:if test="${login != null }">class="col-sm-10"</c:if><c:if test="${login != null }">class="col-sm-12"</c:if>>
				<div class="d-flex align-items-center">
					<select id="mainCriteria" class="form-select mx-3" style="width: 10%">
						<option value="post_id">최신순</option>
						<option value="hit">조회수순</option>
						<option value="likes">좋아요순</option>
						<option value="cmts">댓글순</option>
					</select>	
					<div style="width: 10%">총&nbsp;${postsCnt}건</div>
				</div>
				<c:choose>
					<c:when test="${postsCnt == 0}">
						<div class="text-center">조회된 데이터가 없습니다.</div>
					</c:when>
					<c:otherwise>
						<div class="row" id="postList">
							<%-- <c:forEach items="${re.list }" var="post">
						<div class="col-lg-3 p-3">
							<div class="mycard">
								<a href="post/detail?postId=${post.postId }"><img class="top" src="https://img.youtube.com/vi/${post.urlId }/sddefault.jpg" alt="thumsnail"></a>
								<div>
									<c:if test="${fn:length(post.postTitle) > 50}">
										<div class="title">${fn:substring(post.postTitle,0,50)}..</div>
							        </c:if> 
							        <c:if test="${fn:length(post.postTitle) <= 50}">
							       		<div class="title">${post.postTitle}</div>
							        </c:if>
									<div class="text">${post.memName }<br>${post.hit }회<br>${post.registDate }</div>
								</div>
							</div>
						</div>
						</c:forEach> --%>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<c:if test="${login != null }">
				<div class="col-sm-2">
					<div id="ChannelList">
						<c:if test="${followingResult.cnt == 0 }">
							<div class="listTitle text-center mt-4">구독하고 있는 채널이 없습니다.</div>
						</c:if>
						<c:if test="${followingResult.cnt != 0 }">
							<div class="listTitle m-0 mt-2">구독채널 ${followingResult.cnt }개</div>
							<ul class="p-0 py-3">
								<c:forEach items="${followingResult.list }" var="following">
									<li class="py-1 my-3 d-flex align-items-center" style="border-bottom: solid 1px lightgrey;">
										<div style="padding-right: 12px;">
											<img src="${pageContext.request.contextPath}/resources/upload/member/${following.saveImage}" alt="" style="width: 40px;">
										</div>
										<div class="d-flex">
											<div class="d-flex flex-column">
												<a href="${pageContext.request.contextPath}/member/detail?memId=${following.followed }">
													<span>${following.channelName }</span>
												</a>
												<div>
													<span class="id fw-bold">${following.memName }</span>
													<span class="id">구독자${following.followers }명</span>
												</div>
											</div>
										</div>
									</li>
								</c:forEach>
							</ul>
						</c:if>

						<%-- <c:choose>
							<c:when test="${recommendsCnt == 0 }">
								<div class="listTitle text-center mt-4">추천영상이 없습니다.</div>
							</c:when>
							<c:otherwise>
								<div class="listTitle">추천영상 ${recommendsCnt }개</div>
							</c:otherwise>
						</c:choose> --%>
					</div>
				</div>
			</c:if>

		</div>
	</div>
	<jsp:include page="../layout/footer.jsp"></jsp:include>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/function.js"></script>
	<script type="text/javascript">
		const postsCnt = ${postsCnt};
		console.log("postsCnt", postsCnt);

		let page = 1;
		let flag = false;

		//페이지 들어가자마자 1페이지 추천영상 불러오기
		printPosts(page);

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
					printPosts(page);
					console.log("page", page);
				}
			}
		});
		
		
		$("#mainCriteria").on("change", function(){
			$("#postList").empty();
			page = 1;
			flag = false;
			printPosts(page);
		})
		
	</script>
</body>

</html>
