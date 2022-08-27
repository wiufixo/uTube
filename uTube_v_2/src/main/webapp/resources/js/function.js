


function getContextPath() {
  return sessionStorage.getItem("contextpath");
}
const ctx = getContextPath();
console.log("ctx", ctx);

if ($("body").height() < $(window).height()) {
  console.log("스크롤 없는 상태");
}

// Add slideDown animation to Bootstrap dropdown when expanding.
$('.dropdown').on('show.bs.dropdown', function() {
  $(this).find('.dropdown-menu').first().stop(true, true).slideDown();
});

// Add slideUp animation to Bootstrap dropdown when collapsing.
$('.dropdown').on('hide.bs.dropdown', function() {
  $(this).find('.dropdown-menu').first().stop(true, true).slideUp();
});

//버튼 관련 이벤트
function btnEvent() {
  $("#postInput").on("keyup", function (key) {
    if (key.keyCode == 13) {
      $("#postSearchFrm").submit();
    }
  });
  $("#memberInput").on("keyup", function (key) {
    if (key.keyCode == 13) {
      $("#memberSearchFrm").submit();
    }
  });
  $("#mainKeyword").on("keyup", function (key) {
    if (key.keyCode == 13) {
      mainSearch();
    }
  });
  $("#cmtContent").on("keyup", function (key) {
    if (key.keyCode == 13) {
      cmtInsert();
    }
  });

  //home 관련
  function mainSearch() {
    const keyword = $("#mainKeyword").val();
    const searchType = $("#mainSearchType").val();

    location.href = `${ctx}/?keyword=${keyword}&searchType=${searchType}`;
  }

  $("#mainSearchBtn").on("click", function () {
    mainSearch();
  });

  //post 관련
  $("#postSearchBtn").on("click", function () {
    $("#postSearchFrm").submit();
  });

  $("#postPerPage").change(function () {
    $("#postSearchFrm").submit();
  });

  $("#postCriteria").change(function () {
    $("#postSearchFrm").submit();
  });

  $("#detailDeleteBtn").on("click", function () {
    const checked = [postId];
    if (confirm("정말 삭제하시겠습니까?")) {
      $.ajax({
        url: "./delete",
        type: "POST",
        traditional: true,
        data: {
          checked: checked,
        },
        success: function (re) {
          if (re == 1) {
            alert("성공적으로 삭제하였습니다!");
            location.href = `${ctx}/`;
          }
        },
      });
    }
  });
  $(document).on("click", "#cmtDelete", function () {
    const cmtId = $(this).next().val();

    const cmtLevel = $(this).prev().val();

    const cmt = $(this).parents(".cmtItem");
    const nextcmtLevel = $(cmt).next().find("#cmtLikeBtn").prev().val();

    /*
    if (nextcmtLevel - cmtLevel == 1) {
        alert("답댓글이 있으면 삭제가 불가능합니다!");
        return false;
    }
    */

    if (confirm("정말 삭제하시겠습니까?")) {
      $.ajax({
        url: `${ctx}/cmt/delete`,
        type: "post",
        data: {
          cmtId: cmtId,
        },
        success: function (re) {
          if (re == 1) {
            alert("성공적으로 삭제하였습니다!");
            $(".cmtList").empty();
            printCmtList(1);
          }
        },
      });
    }
  });

  $(document).on("click", "#cmtUpdate", function () {
    const cmtId = $(this).prev().val();

    const item = $(this).parents(".cmtItem")[0];

    const info = $(this).parents(".cmt-info")[0];

    const writer = $(info).find(".cmtWriter").text();
    const cmtText = $(info).find(".cmtText").text();
    const imgSrc = $(item).find("img").attr("src");
    //console.log(imgSrc);

    const str = `
                <div class="cmt-img">
                    <img src="${imgSrc}" alt="프로필사진">
                </div>
                <div class="cmt-info">
                    <div class="info-top">
                        <span class="fw-bolder" style="font-size: 1.0em;">${writer}</span> 
                    </div>
                    <div class="info-top">
                        <input id="updateCmtInput" class="cmtText my-2 form-control" type="text" value="${cmtText}">
                        <div class="cmtSaveBox">
                            <span class="span" id="cmtSave">저장</span>
                            <input type="hidden" value="${cmtId}">
                            <span class="span" id="cmtUpdateCancel">취소</span>
                        </div>
                    </div> 
                </div>
                `;

    $(item).html(str);

    const box = $(".cmtSaveBox");
    if (box.length > 1) {
      for (i of box) {
        const pinfo = $(i).parents(".cmt-info");
        const cno = $(i).find("input").val();
        //console.log(cno);
        if (cmtId != cno) {
          $.ajax({
            url: `${ctx}/cmt/detail`,
            type: "get",
            data: {
              cmtId: cno,
            },
            dataType: "json",
            success: function (re) {
              console.log(re);
              let input = "";
              input += `<div class="info-top"><span>`;
              if (re.cmtLevel != 0) {
                if (re.cmtLevel != 0) {
                  for (var i = 0; i < re.cmtLevel; i++) {
                    input += " ↳ ";
                  }
                }
              }

              const date = timeBefore(re.registDate);

              input += `
                        <a href="${ctx}/member/detail?memId=${re.memId}"><span class="cmtWriter fw-bolder" style="font-size: 1.0em;">${re.memName}</span></a>
                        </span>
                        <span style="font-size: 0.9em; color: grey">${date}</span>
                    </div>
                    <div class="info-top">
                        <span>
                                `;
              if (re.cmtLevel != 0) {
                for (var i = 0; i < re.cmtLevel; i++) {
                  input += "&nbsp;&nbsp;&nbsp;";
                }
              }
              input += `<span class="cmtText my-2">${re.cmtContent}</span></span>`;

              input += `<div>
                            <input type="hidden" class="cmtLevel" value="${re.cmtLevel}">
                            <span id="cmtDelete">❌</span>
                            <input type="hidden" class="cmtId" value="${re.cmtId}">
                            <span id="cmtUpdate">✏️</span>
                        </div>`;

              input += `</div><div class="info-button-box">`;

              if (re.cmtLevel != 0) {
                for (var i = 0; i < re.cmtLevel; i++) {
                  input += "&nbsp;&nbsp;&nbsp;";
                }
              }

              input += `<input type="hidden" class="cmtLevel" value="${re.cmtLevel}">
                        <span id="cmtLikeBtn" class="mb-1" style="font-size: 0.9em; color: blue">좋아요</span>
                        <input type="hidden" class="cmtId" value="${re.cmtId}">
                        <span id="cmtReplyBtn" class="mb-1" style="font-size: 0.9em; color: grey">답글🗨️</span>
                    </div>`;

              $(pinfo).html(input);
              return false;
            },
          });
        }
      }
    }
    $("#updateCmtInput").focus();
  });

  $(document).on("click", "#cmtSave", function () {
    const cmtId = $(this).next().val();
    const info = $(this).parents(".cmt-info")[0];
    const cmtText = $(info).find(".cmtText").val();

    if (confirm("정말 수정하시겠습니까?")) {
      $.ajax({
        url: `${ctx}/cmt/updateSubmit`,
        type: "post",
        data: {
          cmtId: cmtId,
          cmtContent: cmtText,
        },
        success: function (re) {
          if (re == 1) {
            alert("댓글을 성공적으로 수정하였습니다!");
            $(".cmtList").empty();
            printCmtList(1);
          }
        },
      });
    }
  });

  $("#cmtInsertBtn").on("click", function () {
    cmtInsert();
  });

  $(document).on("click", "#cmtReplyBtn", function () {
    $("#cmtInsertBtn").siblings().remove();
    const info = $(this).parents(".cmt-info")[0];
    const cmtId = $(this).prev().val();
    $("#cmtInsertBtn").after(
      `<input type="hidden" name="cmtId" value="${cmtId}">`
    );
    $("#cmtContent").val("");
    $("#cmtContent").focus();
  });
  
  
  $(document).on("click", "#replyMore", function() {
  			const btn = $(this);
			const cmtRef = $(this).next().val();
			const parentCmt = $(this).parents(".cmtItem");
			
			if($(btn).html() == "답글보기"){
				$.ajax({
				    url: `${ctx}/cmt/listCmtReply`,
				    type: "get",
				    data: {
				      cmtRef
				    },
				    dataType: "json",
				    success: function (re) {
					  let list = "";
				   	  const cmts = re.list;
				      console.log(re.list.length)
				      console.log(re)
				      
				      if(cmts.length){
				    	  for(cmt of cmts){
				    		  const cmtMemId = cmt.memId;
					          const cmtLevel = cmt.cmtLevel;
					          
					          list += `<li class="cmtItem ${cmt.cmtRef}">
					          		<div style="padding:0px 10px;"> ↳ </div>
				                    <div class="cmt-img">
					                    <a href="${ctx}/member/detail?memId=${cmt.memId}">
					                    <img src="${ctx}/resources/upload/member/${cmt.saveImage}" alt="프로필사진">
					                    </a>
				                    </div>
				                    <div class="cmt-info">
				                    <div class="info-top">
				                        <span>`;
							  /*
					          if (cmtLevel != 0) {
					            list += "&nbsp;&nbsp;";
					            list += " ↳ ";
					          }
					          */
					          
					          const time = timeBefore(cmt.registDate);

					          list += `<a href="${ctx}/member/detail?memId=${cmt.memId}"><span class="cmtWriter fw-bolder" style="font-size: 1.0em;">${cmt.memName}</span></a>
					                        </span>
					                        <span style="font-size: 0.9em; color: grey">${time}</span>
					                    </div>
					                    <div class="info-top">
					                        <span>`;
					          /*
					          if (cmtLevel != 0) {
					            list += "&nbsp;&nbsp;&nbsp;";
					          }
					          */

					          if (cmt.refMemName) {
					            if (cmt.cmtLevel > 1) {
					              list += `<span class="annotation fw-bold">@${cmt.refMemName}</span>&nbsp;`;
					            }
					          }
					          list += `<span class="cmtText my-2">`;

					          list += `${cmt.cmtContent}</span></span>`;

					          if (cmtMemId == loginMemId) {
					            list += `<div>
					                        <input type="hidden" class="cmtLevel" value="${cmt.cmtLevel}">
					                        <span id="cmtDelete">❌</span>
					                        <input type="hidden" class="cmtId" value="${cmt.cmtId}">
					                        <span id="cmtUpdate">✏️</span>
					                    </div>`;
					          }
								list += "</div>";
					          if (loginMemId != 0) {
					            list += `<div class="info-button-box">`;

					            /*
					            if (cmtLevel != 0) {
					              list += "&nbsp;&nbsp;&nbsp;";
					            }
					            */

					            list += `<input type="hidden" class="cmtLevel" value="${cmt.cmtLevel}">
					                            <span id="cmtLikeBtn" class="mb-1" style="font-size: 0.9em; color: blue">좋아요</span>
					                            <input type="hidden" class="cmtId" value="${cmt.cmtId}">
					                            <span id="cmtReplyBtn" class="mb-1" style="font-size: 0.9em; color: grey">답글🗨️</span>
					                        </div></div></li>`;
					          }
				    	  }
				    	  
				    	  $(parentCmt).after(list);
				    	  $(btn).html("답글접기");
				    	  
				      }

				    } //success end
				  }); //ajax end
			}else{
				$(`.${cmtRef}`).remove();
				$(btn).html("답글보기");
			}
		})

  //postList 관련
  $("#listDeleteBtn").on("click", function () {
    const checked = [];
    const input = $("input[type='checkbox']:not(:first)");

    for (i of input) {
      if (i.checked == true) {
        const idx = $(i).attr("id");
        checked.push(Number(idx));
      }
    }

    console.log(checked);

    if (checked.length == 0) {
      alert("선택한 항목이 없습니다!");
      return false;
    }

    if (confirm("정말 삭제하시겠습니까?")) {
      $.ajax({
        url: "./delete",
        type: "POST",
        traditional: true,
        data: {
          checked: checked,
        },
        success: function (re) {
          if (re == 1) {
            alert("성공적으로 삭제하였습니다!");
            location.href = "./list";
          }
        },
      });
    }
  });

  //member 관련
  $("#loginBtn").on("click", function () {
    if ($("#loginId").val() == "") {
      alert("아이디를 입력하세요!");
      $("#loginId").focus();
      return false;
    }
    if ($("#loginPwd").val() == "") {
      alert("비밀번호를 입력하세요!");
      $("#loginPwd").focus();
      return false;
    }

    $.ajax({
      url: "./loginSubmit",
      type: "post",
      data: $("#loginFrm").serialize(),
      success: function (re) {
        if (re == -1) {
          alert("아이디 또는 비밀번호가 일치하지 않습니다!");
          $("#loginId").val("");
          $("#loginPwd").val("");
        } else if (re == 1) {
          location.href = `${ctx}/`;
        }
      },
    });
  });

  $("#memberSearchBtn").on("click", function () {
    $("#memberSearchFrm").submit();
  });

  $("#memberPerPage").change(function () {
    $("#memberSearchFrm").submit();
  });

  $("#memberCriteria").change(function () {
    $("#memberSearchFrm").submit();
  });

  $("#joinId").on("keyup", function () {
    const id = $(this).val();
    /* if (id.length < 5 || id.length >14) {
        $("#idOK").css("display", "none");
        if($("#idDouble").css("display")=="none"){
            $("#idNO").css("display", "block");
        }
    }else{
        $("#idNO").css("display", "none");
        if($("#idDouble").css("display")=="none"){
            $("#idOK").css("display", "block");
        }
    } */

    $.ajax({
      url: "./idcheck",
      type: "post",
      data: {
        memName: id,
      },
      success: function (re) {
        $("#idOK").css("display", "none");
        $("#idDouble").css("display", "none");
        $("#idNO").css("display", "none");

        if (id.length < 5 || id.length > 14) {
          //아이디 길이 제한 걸림
          $("#idNO").css("display", "block");
          if (re == 1) {
            //아이디 중복 존재
            $("#idNO").css("display", "none");
            $("#idDouble").css("display", "block");
          } else {
            $("#idDouble").css("display", "none");
          }
        } else {
          //아이디 길이 제한 통과
          if (re == 1) {
            //아이디 중복 존재
            $("#idDouble").css("display", "block");
          } else {
            //적절한 아이디
            $("#idDouble").css("display", "none");
            $("#idOK").css("display", "block");
          }
        }
        /* if(re == 1){
            $("#idOK").css("display", "none");
            $("#idNO").css("display", "none");
            $("#idDouble").css("display", "block");
        }else{
            $("#idDouble").css("display", "none");
            if($("#idNO").css("display")=="none"){
                $("#idOK").css("display", "block");
            }
        } */
      },
    });
  });

  $("#joinPwd2").on("keyup", function () {
    const pwd2 = $(this).val();
    const pwd1 = $("#joinPwd1").val();

    if (pwd2 == pwd1) {
      $("#pwdEqOK").css("display", "block");
      $("#pwdEqNO").css("display", "none");
    } else {
      $("#pwdEqOK").css("display", "none");
      $("#pwdEqNO").css("display", "block");
    }
  });

  $("#joinEmail").on("keyup", function () {
    const email = $(this).val();
    if (!emailCheck(email)) {
      $("#emailOK").css("display", "none");
      $("#emailNO").css("display", "block");
    } else {
      $("#emailOK").css("display", "block");
      $("#emailNO").css("display", "none");
    }
  });

  $("#joinBtn").on("click", function () {
    const id = $("#joinId").val();
    const pwd = $("#joinPwd2").val();
    const channelName = $("#joinChannelName").val();
    const email = $("#joinEmail").val();

    if (id == "") {
      alert("아이디를 입력하세요!");
      $("#id").focus();
      return false;
    }

    if (pwd == "") {
      alert("비밀번호를 입력하세요!");
      $("#pwd1").focus();
      return false;
    }

    if (channelName == "") {
      alert("채널명을 입력하세요!");
      $("#channelName").focus();
      return false;
    }

    if (email == "") {
      alert("이메일을 입력하세요!");
      $("#email").focus();
      return false;
    }

    if (
      $("#idNO").css("display") == "block" ||
      $("#pwdEqNO").css("display") == "block" ||
      $("#emailNO").css("display") == "block"
    ) {
      alert("형식에 맞게 작성해주세요!");
      return false;
    }

    const joinFrm = $("#joinFrm")[0];
    const frmData = new FormData(joinFrm);
    for (let key of frmData.keys()) {
      console.log(key);
    }
    for (let value of frmData.values()) {
      console.log(value);
    }

    $.ajax({
      url: "./joinSubmit",
      type: "post",
      data: frmData,
      contentType: false,
      processData: false,
      success: function (re) {
        if (re == 1) {
          if (login == "") {
            //회원가입
            alert("회원가입을 축하드립니다!");
            location.href = "./login";
          } else {
            //관리자의 회원등록
            alert("성공적으로 회원을 등록하였습니다!");
            location.href = "./list";
          }
        }
      },
    });
  });

  $("#resignBtn").on("click", function () {
    const checked = [memId];
    if (confirm("정말 탈퇴하시겠습니까?")) {
      $.ajax({
        url: "./delete",
        type: "POST",
        traditional: true,
        data: {
          checked: checked,
        },
        success: function (re) {
          if (re == 1) {
            alert("정상적으로 탈퇴하였습니다!");
            location.href = "./logout";
          }
        },
      });
    }
  });

  $("#deleteBtn").on("click", function () {
    const checked = [memId];
    if (confirm("이 회원을 정말 삭제하시겠습니까?")) {
      $.ajax({
        url: "./delete",
        type: "post",
        traditional: true,
        data: {
          checked: checked,
        },
        success: function (re) {
          if (re == 1) {
            alert("회원을 정상적으로 삭제하였습니다!");
            location.href = "./list";
          }
        },
      });
    }
  });

  //follow 관련
  $(document).on("click", "#followBtn", function () {
    follow(loginMemId, memId);
  });

  $(document).on("click", "#followCancelBtn", function () {
    followCancel(loginMemId, memId);
  });
}

btnEvent();

function cmtInsert() {
  let reply = false;

  if ($("#cmtContent").val() == "" || $("#cmtContent").val() == null) {
    alert("댓글내용을 입력해주세요!");
    $("#cmtContent").focus();
    return false;
  }

  if ($(this).next().length) {
    //버튼 뒤에 hidden 으로 답글을 달 댓글의 id 태그가 있는지 ==> 대댓글이다
    console.log($(this).next().val() + "번의 답댓글 등록");
    reply = true;
  }

  $.ajax({
    url: `${ctx}/cmt/insertSubmit`,
    type: "post",
    data: $("#commentFrm").serialize(),
    success: function (re) {
      if (re == 1) {
        alert("댓글을 성공적으로 등록하였습니다!");
        $("#cmtContent").val("");
        $(".cmtList").empty();
        printCmtList(1);
      }
    },
  });
}

function followCancel(follower, followed) {
  $.ajax({
    url: `${ctx}/follow/delete`,
    type: "post",
    data: {
      follower: follower,
      followed: followed,
    },
    success: function (re) {
      /* if(re==1){
        alert("구독취소완료");
        } 
      */
      printFollowBtn(follower, followed);
      followCntUpdate();
    },
  });
}
function follow(follower, followed) {
  $.ajax({
    url: `${ctx}/follow/insert`,
    type: "get",
    data: {
      follower: follower,
      followed: followed,
    },
    success: function (re) {
      /* 
      if(re==1){
      alert("구독완료");
      }
      */
      printFollowBtn(follower, followed);
	  
      followCntUpdate();
    },
  });
}

function followCntUpdate() {
  $.ajax({
    url: `${ctx}/follow/followerCnt`,
    type: "get",
    data: {
      followed: memId,
    },
    success: function (re) {
      $("#followerCnt").html(re);
    },
  });
}

//좋아요 수 입력
function printLikeCnt() {
  $.ajax({
    url: `${ctx}/like/likeCnt`,
    type: "get",
    data: {
      postId: postId,
    },
    success: function (re) {
      $("#likeBtnBox").find("span").html(re);
    },
  });
}

//싫어요 수 입력
function printHateCnt() {
  $.ajax({
    url: `${ctx}/hate/hateCnt`,
    type: "get",
    data: {
      postId: postId,
    },
    success: function (re) {
      $("#hateBtnBox").find("span").html(re);
    },
  });
}

//좋아요 상태에 따른 버튼색 변경
function printLikeBtn() {
  $.ajax({
    url: `${ctx}/like/checkLike`,
    type: "get",
    data: {
      postId: postId,
      memId: loginMemId,
    },
    success: function (re) {
      $("#likeBtnBox").find("i").removeClass("i-active");
      if (re == 1) {
        $("#likeBtnBox").find("i").addClass("i-active");
      }
    },
  });
}

//싫어요 상태에 따른 버튼색 변경
function printHateBtn() {
  $.ajax({
    url: `${ctx}/hate/checkHate`,
    type: "get",
    data: {
      postId: postId,
      memId: loginMemId,
    },
    success: function (re) {
      $("#hateBtnBox").find("i").removeClass("i-active");
      if (re == 1) {
        $("#hateBtnBox").find("i").addClass("i-active");
      }
    },
  });
}

//좋아요버튼
function like(target) {
  if (loginMemId == 0) {
    alert("해당 기능은 로그인 후 사용가능합니다!");
    return false;
  }
  if ($(target).find("i").hasClass("i-active")) {
    $.ajax({
      url: `${ctx}/like/delete`,
      type: "post",
      data: {
        postId: postId,
        memId: loginMemId,
      },
      success: function (re) {
        if (re == 1) {
          printLikeBtn();
          printLikeCnt();
        }
      },
    });
  } else {
    $.ajax({
      url: `${ctx}/like/insert`,
      type: "post",
      data: {
        postId: postId,
        memId: loginMemId,
      },
      success: function (re) {
        if (re == 1) {
          printLikeBtn();
          printLikeCnt();
        }
      },
    });
  }
}

//싫어요버튼
function hate(target) {
  if (loginMemId == 0) {
    alert("해당 기능은 로그인 후 사용가능합니다!");
    return false;
  }
  if ($(target).find("i").hasClass("i-active")) {
    $.ajax({
      url: `${ctx}/hate/delete`,
      type: "post",
      data: {
        postId: postId,
        memId: loginMemId,
      },
      success: function (re) {
        if (re == 1) {
          printHateBtn();
          printHateCnt();
        }
      },
    });
  } else {
    $.ajax({
      url: `${ctx}/hate/insert`,
      type: "post",
      data: {
        postId: postId,
        memId: loginMemId,
      },
      success: function (re) {
        if (re == 1) {
          printHateBtn();
          printHateCnt();
        }
      },
    });
  }
}

//마이페이지 드롭다운 범위밖 커서 눌렀을때 숨김처리
function deleteClassShow() {
  const dropdowns = $(".dropdown-content");
  for (dropdown of dropdowns) {
    if ($(dropdown).hasClass("show")) {
      $(dropdown).removeClass("show");
    }
  }
}

//회원가입 프로필 썸네일 보여주는 함수
function previewImage(input) {
	if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function (e) {
        $('#thumbnailImg').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
  /*
  console.log(target.files[0]);
  const fileName = target.files[0].name;
  str = `<img class="mb-4" src="${ctx}/file/display?fileName=${fileName}" width="100">`;
  $("#thumbnail").html(str);
  */
}

//이메일 형식 체크
function emailCheck(str) {
  var reg_email =
    /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
  if (!reg_email.test(str)) {
    return false;
  } else {
    return true;
  }
}

//상세화면 댓글리스트 불러오기 함수
function printCmtList(num) {
  $.ajax({
    url: `${ctx}/cmt/list`,
    type: "get",
    data: {
      postId: postId,
      page: num,
    },
    dataType: "json",
    success: function (re) {
      
      cmtPage = num;
      const nextPage = re.pagination.endPage + 1;
      const prevPage = re.pagination.startPage - 1;
      const cmtsCnt = re.pagination.totalCnt;
      let list = "";
      let paging = "";

      if (cmtsCnt != 0) {
        const arr = re.list;
        for (cmt of arr) {
          const cmtMemId = cmt.memId;
          const cmtLevel = cmt.cmtLevel;
          //console.log(cmtLevel);
          //console.log(cmtMemId)
          //console.log(loginMemId)

          list += `<li class="cmtItem parentItem">
                    <div class="cmt-img">
                    <a href="${ctx}/member/detail?memId=${cmt.memId}">
                    <img src="${ctx}/resources/upload/member/${cmt.saveImage}" alt="프로필사진">
                    </a>
                    </div>
                    <div class="cmt-info">
                    <div class="info-top">
                        <span>`;

          if (cmtLevel != 0) {
            //for (var i = 0; i <= cmtLevel-1; i++) {
            list += "&nbsp;&nbsp;";
            //}
            list += " ↳ ";
          }

          /*
            if (cmtLevel != 0) {
            if (cmtLevel % 3 == 1) {
                //1,4,7,10,..
                for (var i = 0; i < cmtLevel; i++) {
                list += " ↳ ";
                }
            } else if (cmtLevel % 3 == 2) {
                //2,5,8,11,..
                for (var i = 0; i < cmtLevel; i++) {
                list += " ↳ ";
                }
            } else {
                //3,6,9,12,..
                for (var i = 0; i < cmtLevel; i++) {
                list += " ↳ ";
                }
            }
            }
            */

          const time = timeBefore(cmt.registDate);

          list += `<a href="${ctx}/member/detail?memId=${cmt.memId}"><span class="cmtWriter fw-bolder" style="font-size: 1.0em;">${cmt.memName}</span></a>
                        </span>
                        <span style="font-size: 0.9em; color: grey">${time}</span>
                    </div>
                    <div class="info-top">
                        <span>`;
          if (cmtLevel != 0) {
            //for (var i = 0; i < cmtLevel; i++) {
            list += "&nbsp;&nbsp;&nbsp;";
            //}
            /* for(var i=0; i<cmtLevel; i++){
                    list += "&nbsp;&nbsp;&nbsp;"
                }
                list += "✔️"; */
          }

          if (cmt.refMemName) {
            if (cmt.cmtLevel > 1) {
              list += `<span class="annotation fw-bold">@${cmt.refMemName}</span>&nbsp;`;
            }
          }
          list += `<span class="cmtText my-2">`;

          list += `${cmt.cmtContent}</span></span>`;

          if (cmtMemId == loginMemId) {
            list += `<div>
                        <input type="hidden" class="cmtLevel" value="${cmt.cmtLevel}">
                        <span id="cmtDelete">❌</span>
                        <input type="hidden" class="cmtId" value="${cmt.cmtId}">
                        <span id="cmtUpdate">✏️</span>
                    </div>`;
          }
			list += "</div>";
          if (loginMemId != 0) {
            list += `<div class="info-button-box">`;

            if (cmtLevel != 0) {
              //for (var i = 0; i < cmtLevel; i++) {
              list += "&nbsp;&nbsp;&nbsp;";
              //}
            }

            list += `<input type="hidden" class="cmtLevel" value="${cmt.cmtLevel}">
                            <span id="cmtLikeBtn" class="mb-1" style="font-size: 0.9em; color: blue">좋아요</span>
                            <input type="hidden" class="cmtId" value="${cmt.cmtId}">
                            <span id="cmtReplyBtn" class="mb-1" style="font-size: 0.9em; color: grey">답글🗨️</span>
                        </div>`;
          }
          
          if(cmt.replies){
	          list += `<div>
	          			<div id="replyMore" class="pt-1" style="font-size: 0.9em; color: grey">답글보기</div>
	          			<input type="hidden" class="cmtRef" value="${cmt.cmtRef}">
	          			</div>`
          }
          
          list += `</div></li>`
        }
        /*
            paging += `<div class="d-flex justify-content-center align-items-center">`;
    
            if (re.pagination.page != 1) {
              paging += `<a class="page p-2" onclick="printCmtList(1)">⏪</a>`;
            }
    
            if (re.pagination.prev) {
              paging += `<a class="page p-2" onclick="printCmtList(${prevPage})">◀️</a>`;
            }
    
            for (let i = re.pagination.startPage; i <= re.pagination.endPage; i++) {
              paging += `
                            <div class="p-2">
                                <a class="page" page="${i}" onclick="printCmtList(${i})">${i}</a>
                            </div>
                        `;
            }
    
            if (re.pagination.next) {
              paging += `<a class="page p-2" onclick="printCmtList(${nextPage})">▶️</a>`;
            }
    
            if (re.pagination.page != re.pagination.totalPages) {
              paging += `<a class="page p-2" onclick="printCmtList(${re.pagination.totalPages})">⏩</a>`;
            }
    
            paging += `</div>`;
            */
      }

      $(".cmtList").append(list);
      $("#cmtsCnt").html(cmtsCnt);

      if ($(".parentItem").length >= cmtsCnt) {
        $("#cmtMore").css("display", "none");
      } else {
        $("#cmtMore").css("display", "block");
      }

      //$("#paging").html(paging);

      /*
        const currentPage = re.pagination.page;
        const pageLink = $(".page[page='" + currentPage + "']");
        $(pageLink).addClass("active");
        */
    },
  });
}

//상세화면 무한스크롤 추천영상 불러오기 함수
function printRecommends(num) {
  if ($(".item").length >= recommendsCnt) {
    console.log("추천영상 불러오기 끝");
    return false;
  }

  $.ajax({
    url: "./recommends",
    type: "get",
    //async: false,
    data: {
      postId: postId,
      page: num,
    },
    datatype: "json", //없어도 됨
    success: function (result) {
      let list = "";

      if (result.cnt != 0) {
        for (item of result.list) {
          list += `<li class="item my-1">
                    <div class="item-img">
                    <a href="./detail?postId=${item.postId}">
                        <img src="https://img.youtube.com/vi/${item.urlId}/sddefault.jpg" alt="동영상 썸네일입니다.">
                    </a>
                    </div>
                    <div class="item-info">
                        <a href="./detail?postId=${item.postId}">	
                            <span class="item-title">`;
          if (item.postTitle.length > 30) {
            list += item.postTitle.substr(0, 30) + "...";
          } else {
            list += `${item.postTitle}`;
          }

          const date = timeBefore(item.registDate);

          list += `</span>
                        </a>
                        <span class="item-desc">${item.memName}</span> <span class="item-desc">${item.hit}회</span> <span class="item-desc">${date}</span>
                    </div>
                    <button class="moreBtn">
                        <i class="fas fa-ellipsis-v"></i>
                    </button>
                </li>`;
        }
        //list += ` <div> class="listTitle text-center mt-4">추천영상이 없습니다.</div>`
      }
      $("#recommendList").append(list);
      flag = false;
      page++;
    },
  });
}

//홈화면 무한스크롤 영상 불러오기 함수
function printPosts(num) {
  const criteria = $("#mainCriteria").val();
  const keyword = $("#mainKeyword").val();
  const searchType = $("#mainSearchType").val();

  console.log("length", $(".mycard").length);

  //console.log("criteria",criteria);
  //console.log("keyword",keyword);
  //console.log("searchType",searchType);

  if ($(".mycard").length >= postsCnt) {
    console.log("영상 불러오기 끝");
    return false;
  }

  $.ajax({
    url: `${ctx}/post/postsList`,
    type: "get",
    //async: false,
    data: {
      page: num,
      keyword: keyword,
      searchType: searchType,
      criteria: criteria,
    },
    datatype: "json", //없어도 됨
    success: function (result) {
      const criteria = result.pagination.criteria;
      $("#mainCriteria").val(criteria).prop("selected", true);

      let list = "";
      for (item of result.list) {
        //console.log(item)
        list += `<div class="col-lg-3 p-3">
                    <div class="mycard">
                        <a href="${ctx}/post/detail?postId=${item.postId}"><img class="top" src="https://img.youtube.com/vi/${item.urlId}/sddefault.jpg" alt="thumsnail"></a>
                        <div>
                        <div class="title">`;
        if (item.postTitle.length > 50) {
          list += item.postTitle.substr(0, 50) + "...";
        } else {
          list += `${item.postTitle}`;
        }

        const date = timeBefore(item.registDate);

        list += `</div>
                <div class="text"><span class="fw-bold">${item.memName}</span><br>조회수 ${item.hit}회<br>${date}</div>
                </div>
            </div>
        </div>`;
      }

      $("#postList").append(list);

      flag = false;
      page++;
    },
  });
}

//회원상세페이지 무한스크롤 영상 불러오기 함수
function printmemberPosts(num) {
  if ($(".mycard").length >= uploadCnt) {
    console.log("영상 불러오기 끝");
    return false;
  }

  $.ajax({
    url: `${ctx}/post/memberPosts`,
    type: "get",
    //async: false,
    data: {
      memId: memId,
      page: num,
    },
    datatype: "json", //없어도 됨
    success: function (result) {
      let list = "";

      if (result.cnt != 0) {
        for (item of result.list) {
          list += `<div class="col-lg-3 p-3">
                    <div class="mycard">
                        <a href="${ctx}/post/detail?postId=${item.postId}"><img class="top" src="https://img.youtube.com/vi/${item.urlId}/sddefault.jpg" alt="thumsnail"></a>
                        <div>
                            <div class="title">`;
          if (item.postTitle.length > 50) {
            list += item.postTitle.substr(0, 50) + "...";
          } else {
            list += `${item.postTitle}`;
          }

          const date = timeBefore(item.registDate);

          list += `</div>
                            <div class="text"><span class="fw-bold">${memName}</span> 조회수${item.hit}회 ${date}</div>
                        </div>
                    </div>
                </div>`;
        }
      }
      $("#uploads").append(list);
      flag = false;
      page++;
    },
  });
}

function postValidate() {
  $("#commitBtn").on("click", function () {
    if ($("#postTitle").val() == "" || $("#postTitle").val() == null) {
      alert("제목을 입력해주세요!");
      $("#postTitle").focus();
      return false;
    }
    if ($("#postUrl").val() == "" || $("#postUrl").val() == null) {
      alert("url을 입력해주세요!");
      $("#postUrl").focus();
      return false;
    }
    if ($("#postContent").val() == "" || $("#postContent").val() == null) {
      alert("내용을 입력해주세요!");
      $("#postContent").focus();
      return false;
    }

    $("#commitFrm").submit();
  });
}

function textAreaResize(i) {
  const textEle = $("textarea")[i];
  $(textEle).css("height", "auto");
  let textEleHeight = $(textEle).prop("scrollHeight");
  $(textEle).css("height", textEleHeight + 5 + "px");
}

function printFollowBtn(login, member) {
  $.ajax({
    url: `${ctx}/follow/checkFollow`,
    type: "get",
    data: {
      follower: login,
      followed: member,
    },
    success: function (re) {
      $("#followBtnBox").empty();
      if (loginMemId != 0 && loginMemId != member) {
        if (re == 1) {
          $("#followBtnBox").append(
            '<button id="followCancelBtn" class="btn btn-light subscribe fw-bold fs-6" style="color:red;">구독취소</button>'
          );
        } else if (re == 0) {
          $("#followBtnBox").append(
            '<button id="followBtn" class="btn btn-light subscribe fw-bold fs-6 color" style="color:red;">구독</button>'
          );
        }
      }
    },
  });
}

function checkAllToggle() {
  $("#allCheck").on("click", function () {
    //모두 체크표시 토글기능
    if ($("#allCheck").is(":checked")) {
      $("input[class='check']").prop("checked", true);
    } else {
      $("input[class='check']").prop("checked", false);
    }
  });
}

//등록일자 ~분전 형식을 바꾸는 함수
function printDateBefore() {
  const dateTd = $(".registDate");
  for (var td of dateTd) {
    let date = $(td).html();
    const str = timeBefore(date);
    console.log(str);
    $(td).html(str);
  }
}

//날짜를 ~분전 형식을 바꾸는 함수
function elapsedTime(date) {
  const start = new Date(date);
  const end = new Date(); // 현재 날짜

  const diff = end - start; // 경과 시간

  const times = [
    { time: "분", milliSeconds: 1000 * 60 },
    { time: "시간", milliSeconds: 1000 * 60 * 60 },
    { time: "일", milliSeconds: 1000 * 60 * 60 * 24 },
    { time: "개월", milliSeconds: 1000 * 60 * 60 * 24 * 30 },
    { time: "년", milliSeconds: 1000 * 60 * 60 * 24 * 365 },
  ].reverse();

  // 년 단위부터 알맞는 단위 찾기
  for (const value of times) {
    const betweenTime = Math.floor(diff / value.milliSeconds);

    // 큰 단위는 0보다 작은 소수 단위 나옴
    if (betweenTime > 0) {
      return `${betweenTime}${value.time} 전`;
    }
  }

  // 모든 단위가 맞지 않을 시
  return "방금 전";
}

function timeBefore(date) {
  //현재시간을 가져옴
  var now = new Date();

  //글쓴 시간
  var writeDay = new Date(date);
  //또는 파라미터로 시간을 넘겨받아서 계산할 수도 있음..

  var minus;
  //현재 년도랑 글쓴시간의 년도 비교
  if (now.getFullYear() > writeDay.getFullYear()) {
    minus = now.getFullYear() - writeDay.getFullYear();
    //두개의 차이를 구해서 표시
    return minus + "년 전";
  } else if (now.getMonth() > writeDay.getMonth()) {
    //년도가 같을 경우 달을 비교해서 출력
    minus = now.getMonth() - writeDay.getMonth();
    return minus + "달 전";
  } else if (now.getDate() > writeDay.getDate()) {
    //같은 달일 경우 일을 계산
    minus = now.getDate() - writeDay.getDate();
    return minus + "일 전";
  } else if (now.getDate() == writeDay.getDate()) {
    //당일인 경우에는
    var nowTime = now.getTime();
    var writeTime = writeDay.getTime();
    if (nowTime > writeTime) {
      //시간을 비교
      sec = parseInt(nowTime - writeTime) / 1000;
      day = parseInt(sec / 60 / 60 / 24);
      sec = sec - day * 60 * 60 * 24;
      hour = parseInt(sec / 60 / 60);
      sec = sec - hour * 60 * 60;
      min = parseInt(sec / 60);
      sec = parseInt(sec - min * 60);
      if (hour > 0) {
        //몇시간전인지
        return hour + "시간 전";
      } else if (min > 0) {
        //몇분전인지
        return min + "분 전";
      } else if (sec > 0) {
        //몇초전인지 계산
        return sec + "초 전";
      } else {
        return "방금전";
      }
    }
  }
}
