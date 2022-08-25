<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="nav-bg d-flex align-items-center">
	<div class="nav row">
		<div class="col-md-3 y-center">
			<a class="big-link" href="http://125.7.234.18:8000/topiadev/">🌈TOPIA</a>
			<c:if test="${login != null and (login.auth == 'MANAGER' or login.auth == 'MASTER') }">
				&nbsp;
				<div class="dropdown">
					<a class="btn mybtn1 dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false"> 관리 </a>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
						<li>
							<a class="dropdown-item" href="/member/list">유저목록</a>
						</li>
						<li>
							<a class="dropdown-item" href="/post/list">동영상목록</a>
						</li>
					</ul>
				</div>
			</c:if>
		</div>
		<div class="col-md-6 x-center y-center">
			<a href="/">
				<img class="logo" src="${pageContext.request.contextPath}/resources/images/logo.png" />
			</a>
			<select id="mainSearchType" class="form-select" style="width: 25%; height: 35px;">
				<option value="all" <c:if test="${mainSearchType eq 'all'}">selected</c:if>>모두</option>
				<option value="ticon" <c:if test="${mainSearchType eq 'ticon'}">selected</c:if>>제목+내용</option>
				<option value="title" <c:if test="${mainSearchType eq 'title'}">selected</c:if>>제목</option>
				<option value="content" <c:if test="${mainSearchType eq 'content'}">selected</c:if>>내용</option>
				<option value="uploader" <c:if test="${mainSearchType eq 'uploader'}">selected</c:if>>업로더</option>
			</select>
			<input id="mainKeyword" class="form-control" name="keyword" type="text" placeholder="검색어" style="width: 40%; height: 35px;" value="${mainKeyword }">
			<button id="mainSearchBtn" class="btn mybtn1">검색</button>
		</div>
		<div class="col-md-3 y-center right-align">
			<c:choose>
				<c:when test="${login != null }">
					<span>${login.memName }님(${login.auth }), 반갑습니다!</span>&nbsp;
					<div class="dropdown">
						<a class="btn mybtn1 dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false"> menu </a>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
							<li>
								<a class="dropdown-item" href="/">홈</a>
							</li>
							<li>
								<a class="dropdown-item" href="/post/insert">동영상등록</a>
							</li>
							<li>
								<a class="dropdown-item" href="/member/detail?memId=${login.memId }">마이페이지</a>
							</li>
							<li>
								<a class="dropdown-item" href="/member/logout">로그아웃</a>
							</li>
						</ul>
					</div>
				</c:when>
				<c:otherwise>
					<a class="btn mybtn1" href="/member/login">로그인</a>
					<a class="btn mybtn1" href="/member/join">회원가입</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>