<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>영상관리페이지</title>
<jsp:include page="../layout/head.jsp" />
<style type="text/css">
</style>
</head>
<body>
	<jsp:include page="../layout/menu.jsp" />
	<h3 class="text-center my-5">동영상 목록 (관리자용)</h3>
	<div class="container">
		<form id="postSearchFrm" method="get" class="my-2" action="./list">
			<input type="hidden" name="pageSize" value="${re.pagination.pageSize }">
			<div class="d-flex justify-content-center">
				<div class="row" style="width: 60%">
					<div class="col-md-3">
						<select class="form-select" name="searchType">
							<option value="all" <c:if test="${re.pagination.searchType == 'all'}">selected</c:if>>모두</option>
							<option value="ticon" <c:if test="${re.pagination.searchType == 'ticon'}">selected</c:if>>제목+내용</option>
							<option value="title" <c:if test="${re.pagination.searchType == 'title'}">selected</c:if>>제목</option>
							<option value="content" <c:if test="${re.pagination.searchType == 'content'}">selected</c:if>>내용</option>
							<option value="uploader" <c:if test="${re.pagination.searchType == 'uploader'}">selected</c:if>>업로더</option>

						</select>
					</div>
					<div class="col-md-4">
						<input id="postInput" class="form-control" type="text" name="keyword" value="${re.pagination.keyword }" placeholder="검색어">
					</div>
					<div class="col-md-3">
						<select id="postPerPage" class="form-select" name="perPage">
							<option value="5" <c:if test="${re.pagination.perPage == '5'}">selected</c:if>>5개씩</option>
							<option value="10" <c:if test="${re.pagination.perPage == '10'}">selected</c:if>>10개씩</option>
							<option value="15" <c:if test="${re.pagination.perPage == '15'}">selected</c:if>>15개씩</option>
							<option value="20" <c:if test="${re.pagination.perPage == '20'}">selected</c:if>>20개씩</option>
						</select>
					</div>
					<div class="col-md-2 text-center">
						<button id="postSearchBtn" class="btn mybtn2" type="button">검색</button>
					</div>
				</div>
			</div>
			<div class="d-flex justify-content-between row my-4">
				<div class="col-md-2">
					<select name="criteria" id="postCriteria" class="form-select">
						<option value="post_id" <c:if test="${re.pagination.criteria == 'post_id'}">selected</c:if>>최신순</option>
						<option value="hit" <c:if test="${re.pagination.criteria == 'hit'}">selected</c:if>>조회수순</option>
						<option value="likes" <c:if test="${re.pagination.criteria == 'likes'}">selected</c:if>>좋아요순</option>
						<option value="cmts" <c:if test="${re.pagination.criteria == 'cmts'}">selected</c:if>>댓글순</option>
					</select>
				</div>
				<div style="padding-right: 0" class="col-md-2 d-flex justify-content-end">
					<a href="./insert" class="btn mybtn2">등록</a>
					<button type="button" id="listDeleteBtn" class="btn mybtn2">삭제</button>
				</div>
			</div>
		</form>
		<div class="my-2">${re.pagination.totalCnt}건</div>

		<div class="text-center my-3">
			<table class="table table-striped table-hover text-center">
				<colgroup>
					<col style="width: 5%;" />
					<col style="width: 7%;" />
					<col style="width: 20%;" />
					<col style="width: 10%;" />
					<col style="width: 20%;" />
					<col style="width: 7%;" />
					<col style="width: 7%;" />
					<col style="width: 7%;" />
					<col style="width: 10%;" />
					<col style="width: auto%;" />
				</colgroup>
				<thead>
					<tr>
						<td><input type="checkbox" id="allCheck"></td>
						<th>번호</th>
						<th>제목</th>
						<th>업로더</th>
						<th>내용</th>
						<th>조회수</th>
						<th>like</th>
						<th>댓글</th>
						<th>작성일</th>
						<th>버튼</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty re.list }">
							<tr>
								<td colspan="9" align="center">데이터가 없습니다.</td>
							</tr>
						</c:when>
						<c:when test="${!empty re.list}">
							<c:forEach var="post" items="${re.list}">
								<tr>
									<td><input type="checkbox" class="check" id="${post.postId}"></td>
									<td>${post.postId}</td>
									<td><a href="./detail?postId=${post.postId }"> <c:if test="${fn:length(post.postTitle) > 50}">
										${fn:substring(post.postTitle,0,50)}<span style="color: blue;">...더보기</span>
											</c:if> <c:if test="${fn:length(post.postTitle) <= 50}">
							       		${post.postTitle}
							        </c:if>
									</a></td>
									<td><a href="${pageContext.request.contextPath}/member/detail?memId=${post.memId }">${post.memName}</a></td>
									<td><a href="./detail?postId=${post.postId }"> <c:if test="${fn:length(post.postContent) > 50}">
										${fn:substring(post.postContent,0,50)}<span style="color: blue;">...더보기</span>
											</c:if> <c:if test="${fn:length(post.postContent) <= 50}">
							       		${post.postContent}
							        </c:if>
									</a></td>
									<td>${post.hit}</td>
									<td>${post.likes}</td>
									<td>${post.cmts}</td>
									<td class="registDate">${post.registDateStr}</td>
									<td><a href="./update?postId=${post.postId }" class="btn mybtn2">수정</a></td>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	<c:if test="${not empty re.list }">
		<div class="d-flex justify-content-center">
			<c:if test="${re.pagination.page != 1 }">
				<a class="p-2" href="./list?page=1&searchType=${re.pagination.searchType}&keyword=${re.pagination.keyword}&perPage=${re.pagination.perPage}&pageSize=${re.pagination.pageSize}&criteria=${re.pagination.criteria}">⏪</a>
			</c:if>
			<c:if test="${re.pagination.prev }">
				<a class="p-2"
					href="./list?page=${re.pagination.startPage-1 }&searchType=${re.pagination.searchType}&keyword=${re.pagination.keyword}&perPage=${re.pagination.perPage}&pageSize=${re.pagination.pageSize}&criteria=${re.pagination.criteria}">◀️</a>
			</c:if>
			<c:forEach begin="${re.pagination.startPage }" end="${re.pagination.endPage }" var="i">
				<div class="p-2">
					<a class="page" page="${i }" href="./list?page=${i }&searchType=${re.pagination.searchType}&keyword=${re.pagination.keyword}&perPage=${re.pagination.perPage}&pageSize=${re.pagination.pageSize}&criteria=${re.pagination.criteria}">${i }</a>
				</div>
			</c:forEach>
			<c:if test="${re.pagination.next }">
				<a class="p-2"
					href="./list?page=${re.pagination.endPage+1 }&searchType=${re.pagination.searchType}&keyword=${re.pagination.keyword}&perPage=${re.pagination.perPage}&pageSize=${re.pagination.pageSize}&criteria=${re.pagination.criteria}">▶️</a>
			</c:if>
			<c:if test="${re.pagination.page != re.pagination.totalPages }">
				<a class="p-2"
					href="./list?page=${re.pagination.totalPages }&searchType=${re.pagination.searchType}&keyword=${re.pagination.keyword}&perPage=${re.pagination.perPage}&pageSize=${re.pagination.pageSize}&criteria=${re.pagination.criteria}">⏩</a>
			</c:if>
		</div>
	</c:if>

<jsp:include page="../layout/footer.jsp"></jsp:include>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/function.js"></script>
<script type="text/javascript">
	const login = "${login}";
	
	const currentPage = "${re.pagination.page }";
	const pageLink = $(".page[page='"+currentPage+"']");
	$(pageLink).addClass("active")
	
	checkAllToggle();

</script>

</body>

</html>
