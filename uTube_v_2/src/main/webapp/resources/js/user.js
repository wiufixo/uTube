/* 	$(document).on('click', '#btnSearch', function(e) {
			e.preventDefault();
			var url = "${getBoardListURL}";
			url = url + "?searchType=" + $('#searchType').val();
			url = url + "&keyword=" + $('#keyword').val();
			console.log(url);
			location.href = url;

		});
		
		

		function fn_prev(page, range, rangeSize, searchType, keyword) {

			var page = ((range - 2) * rangeSize) + 1;
			var range = range - 1;

			var url = "${globalCtx}/user/getUserList";
			url = url + "?page=" + page;
			url = url + "&range=" + range;
			url = url + "&searchType=" + searchType;
			url = url + "&keyword=" + keyword;

			location.href = url;
		}

		function fn_pagination(page, range, rangeSize, searchType, keyword) {
			var url = "${getUserListURL}";
			url = url + "?page=" + page;
			url = url + "&range=" + range;
			url = url + "&searchType=" + searchType;
			url = url + "&keyword=" + keyword;
			console.log(url);
			location.href = url;

		}

		function fn_next(page, range, rangeSize, searchType, keyword) {
			var page = parseInt((range * rangeSize)) + 1;
			var range = parseInt(range) + 1;

			var url = "${globalCtx}/user/getUserList";
			url = url + "?page=" + page;
			url = url + "&range=" + range;
			url = url + "&searchType=" + searchType;
			url = url + "&keyword=" + keyword;

			location.href = url;
		} */

let index={
	init:function(){
		$("#btn-save").on("click",()=>{
			this.save();
		});
		$("#btn-update").on("click",()=>{
			this.update();
		});
		/*$("#btn-login").on("click",()=>{
			this.login();
		});*/
	},
	
	save:function(){
		let data = {
			cu_id:$("#cu_id").val(),
			cu_pwd:$("#cu_pwd").val(),
			cu_email:$("#cu_email").val()
		}
		
		console.log(data);
		
		$.ajax({ //비동기호출
			url:"/auth/joinProc",
			type:"POST",
			data:JSON.stringify(data), // http body 데이터
			contentType:"application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
			dataType:"json" // 요청에 의한 응답이 왔을때의 데이터 ==> 기본적으로 받는건 모두 문자열인데 생긴게 json이라고 알려주니 js오브젝트로 변환하여 응답해줌
							// ===> 생략해도 json으로 변환해준다 기능의 발전!
			}).done(function(resp){
				if(resp.status == 500){
					alert("회원가입을 실패하였습니다!");
				}else{
					alert("회원가입을 성공하였습니다!");
					location.href="/";
				}
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); //ajax통신을 이용하여 3개의 데이터를 json의 형식으로 회원가입정보 insert요청
	},
	
	update:function(){
		let data = {
			cu_no:$("#cu_no").val(),
			cu_id:$("#cu_id").val(),
			cu_pwd:$("#cu_pwd").val(),
			cu_email:$("#cu_email").val()
		}
		
		$.ajax({ //비동기호출
			url:"/user",
			type:"POST",
			data:JSON.stringify(data), // http body 데이터
			contentType:"application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
			dataType:"json" // 요청에 의한 응답이 왔을때의 데이터 ==> 기본적으로 받는건 모두 문자열인데 생긴게 json이라고 알려주니 js오브젝트로 변환하여 응답해줌
							// ===> 생략해도 json으로 변환해준다 기능의 발전!
			}).done(function(resp){
				alert("회원수정을 성공하였습니다!");
				console.log(resp)
				location.href="/";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); //ajax통신을 이용하여 3개의 데이터를 json의 형식으로 회원가입정보 insert요청
	}
	
	/*login:function(){
		let data = {
			cu_id:$("#cu_id").val(),
			cu_pwd:$("#cu_pwd").val(),
		}
		
		$.ajax({ //비동기호출
			url:"/api/user/login",
			type:"POST",
			data:JSON.stringify(data), // http body 데이터
			contentType:"application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
			dataType:"json" // 요청에 의한 응답이 왔을때의 데이터 ==> 기본적으로 받는건 모두 문자열인데 생긴게 json이라고 알려주니 js오브젝트로 변환하여 응답해줌
							// ===> 생략해도 json으로 변환해준다 기능의 발전!
			}).done(function(resp){
				alert("로그인을 성공하였습니다!");
				console.log(resp)
				location.href="/";
			}).fail(function(error){
				alert("로그인을 실패하였습니다!");
			}); 
	}*/
}

index.init();