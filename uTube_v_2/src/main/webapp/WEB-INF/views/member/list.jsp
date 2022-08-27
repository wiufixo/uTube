<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리페이지</title>
<jsp:include page="../layout/head.jsp" />
</head>
<body>
	<jsp:include page="../layout/menu.jsp" />
	<h3 class="text-center my-5">유저 목록 (관리자용)</h3>
	<div class="container">
		<form id="memberSearchFrm" method="get" class="my-2" action="./list">
			<input type="hidden" name="pageSize" value="${re.pagination.pageSize }">
				<div class="d-flex justify-content-center">
					<div class="row" style="width: 60%">
						<div class="col-md-3">
							<select class="form-select" name="searchType">
								<option value="all" <c:if test="${re.pagination.searchType == 'all'}">selected</c:if>>모두</option>
								<option value="idEmail" <c:if test="${re.pagination.searchType == 'idEmail'}">selected</c:if>>아이디+이메일</option>
								<option value="id" <c:if test="${re.pagination.searchType == 'id'}">selected</c:if>>아이디</option>
								<option value="email" <c:if test="${re.pagination.searchType == 'email'}">selected</c:if>>이메일</option>
								<option value="auth" <c:if test="${re.pagination.searchType == 'auth'}">selected</c:if>>권한</option>
							</select> 
						</div>
						<div class="col-md-4">
							<input id="memberInput" class="form-control" type="text" name="keyword" value="${re.pagination.keyword }" placeholder="검색어">
						</div>
						<div class="col-md-3">
							<select class="form-select" name="perPage" id="memberPerPage">
									<option value="5" <c:if test="${re.pagination.perPage == '5'}">selected</c:if>>5개씩</option>
									<option value="10" <c:if test="${re.pagination.perPage == '10'}">selected</c:if>>10개씩</option>
									<option value="15" <c:if test="${re.pagination.perPage == '15'}">selected</c:if>>15개씩</option>
									<option value="20" <c:if test="${re.pagination.perPage == '20'}">selected</c:if>>20개씩</option>
							</select>
						</div>
						<div class="col-md-2 text-center">
							<button type="button" id="memberSearchBtn" class="btn mybtn2">검색</button>
						</div>
					</div>
				</div>
				<div class="d-flex justify-content-between row my-4">
					<div class="col-md-2">
						<select name="criteria" id="memberCriteria" class="form-select">
							<option value="mem_id" <c:if test="${re.pagination.criteria == 'mem_id'}">selected</c:if>>최신순</option>
							<option value="followers" <c:if test="${re.pagination.criteria == 'followers'}">selected</c:if>>구독자순</option>
							<option value="uploads" <c:if test="${re.pagination.criteria == 'uploads'}">selected</c:if>>영상수순</option>
						</select>
					</div>
					<div style="padding-right: 0" class="col-md-4 d-flex justify-content-end">
						<button type="button" id="excelBtn" class="btn mybtn2">엑셀다운</button>
						<button type="button" id="excelAllBtn" class="btn mybtn2">엑셀다운(전체)</button>
						<a href="./join" class="btn mybtn2">등록</a>
						<button type="button" id="listDeleteBtn" class="btn mybtn2">삭제</button>
					</div>
				</div>
		</form>

		<form id="excelFrm" method="post" action="${pageContext.request.contextPath}/excel/download">
			<input type="hidden" name="page" value="${re.pagination.page }">
			<input type="hidden" name="criteria" value="${re.pagination.criteria }">
			<input type="hidden" name="searchType" value="${re.pagination.searchType }">
			<input type="hidden" name="keyword" value="${re.pagination.keyword }">
			<input type="hidden" name="perPage" value="${re.pagination.perPage }">
			<input type="hidden" name="pageSize" value="${re.pagination.pageSize }">
		</form>

		<div class="my-2">${re.pagination.totalCnt}건</div>

		<div class="text-center my-3">
			<table class="table table-striped table-hover text-center">
				<colgroup>
					<col style="width: auto;" />
					<col style="width: 10%;" />
					<col style="width: 15%;" />
					<col style="width: 15%;" />
					<col style="width: 15%;" />
					<col style="width: 10%;" />
					<col style="width: 10%;" />
					<col style="width: 15%;" />
					<col style="width: 10%;" />
				</colgroup>
				<thead>
					<tr>
						<td><input type="checkbox" id="allCheck"></td>
						<th>번호</th>
						<th>아이디</th>
						<th>이메일</th>
						<th>구독자</th>
						<th>업로드</th>
						<th>가입일</th>
						<th>권한</th>
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
							<c:forEach var="mem" items="${re.list}">
								<tr>
									<td><input type="checkbox" class="check" id="${mem.memId}"></td>
									<td>${mem.memId}</td>
									<td><a href="./detail?memId=${mem.memId }"> ${mem.memName} </a></td>
									<td>${mem.email}</td>
									<td>${mem.followers}</td>
									<td>${mem.uploads}</td>
									<td>${mem.registDateStr}</td>
									<td>${mem.auth}</td>
									<td>
									<c:if test="${mem.auth != 'MASTER' }">
									<a href="./update?memId=${mem.memId }" class="btn mybtn2">수정</a>
									</c:if>
									</td>
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
	const searchCnt = ${re.pagination.totalCnt};
	
	const pageLink = $(".page[page='"+currentPage+"']");
	$(pageLink).addClass("active")
	
	checkAllToggle();
	
	$("#excelBtn").on("click", function(){
		if(searchCnt==0){
			alert("검색 결과가 0입니다.");
			return false;
		}
		const formObj = $("#excelFrm");
		formObj.submit();
	})
	
	$("#excelAllBtn").on("click", function(){
		const formObj = $("#excelFrm");
		const page = $(formObj).find("input[name='page']");
		$(page).val(0);
		formObj.submit();
	})
	
	/* 
	$("#excelBtnx").on("click", function(){
		$.ajax({
			url:`${pageContext.request.contextPath}/excel/download`,
			type:"post",
			success:function(){
				alert("다운로드가 완료되었습니다");
			},
			error : function(request,status,error) {
	            alert("다운로드에 실패하였습니다.\ncode:"+request.status+"\n"+"error:"+error);
	        }
		})
	 }) */
	/* 	
		$.ajax({
			url:"./userList",
			type:"get",
			success:function(re){
				$.ajax({
					url:`${pageContext.request.contextPath}/excel/download`,
					type:"post",
					data : JSON.stringify(re),
			        contentType : "application/json; charset=UTF-8",
					success:function(){
						alert("다운로드가 완료되었습니다");
					},
					error : function(request,status,error) {
			            alert("다운로드에 실패하였습니다.\ncode:"+request.status+"\n"+"error:"+error);
			        },
				})
			}
		})
	 */	
	</script>
</body>
</html>