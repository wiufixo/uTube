<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>엑셀로 다운로드를 해보자</h3>
<form id="excelFrm" action="/test/excelSubmit" method="post">
	<!-- 엑셀파일로 다운로드 받을 날짜를 입력받았다고 가정해보자 ! -->
	<input type="text" id="day1" name="day1" value="2021-05-01" /> 
	<input type="text" id="day2" name="day2" value="2021-05-30" />   		
</form>
<button onclick="poiExcel()">엑셀 다운</button>
<form id="excelFrm2" action="/test/excelSubmit2" method="post">
	<!-- 엑셀파일로 다운로드 받을 날짜를 입력받았다고 가정해보자 ! -->
	<input type="text" id="day1" name="day1" value="1" /> 
	<input type="text" id="day2" name="day2" value="2" />   		
</form>
<button onclick="poiExcel2()">엑셀 다운</button>
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
function poiExcel() {
	var formObj = $("#excelFrm");
	formObj.submit();
}
function poiExcel2() {
	var formObj = $("#excelFrm2");
	formObj.submit();
}
</script>
</body>
</html>